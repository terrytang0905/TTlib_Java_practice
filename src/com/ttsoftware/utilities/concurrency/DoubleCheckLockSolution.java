package com.ttsoftware.utilities.concurrency;

import java.util.HashMap;
import java.util.regex.Pattern;

abstract class OncePer {
    public final Object getObject(String arg) throws Exception {
        System.out.println(Thread.currentThread() + " entering getObject(arg: " + arg + ")");
        Object obj = doGet(arg);
        if (obj == null) {
            obj = synchronizedGetObject(arg);
        }
        return obj;
    }
    
    protected final synchronized Object synchronizedGetObject(String arg) throws Exception {
        System.out.println(Thread.currentThread() + " entering synchronizedGetObject(arg: " + arg + ")");
        Object obj = doGet(arg);
        if (obj == null) {
            obj = doCreate(arg);
        }
        return obj;
    }
    
    protected abstract Object doGet(String arg) throws Exception;
    
    protected abstract Object doCreate(String arg) throws Exception;
}

abstract class Once1Get {
    private volatile boolean todo = true;
    private Object obj = null;
    
    public final Object getObject() throws Exception {
        System.out.println(Thread.currentThread() + " entering getObject");
        if (todo) {
            synchronized (this) {
                if (todo) {
                    obj = doGet();
                    todo = false;
                }
            }
        }
        return obj;
    }
    
    protected abstract Object doGet() throws Exception;
}

abstract class Once2Get extends Once2 {
    private Object obj = null;
    
    public final Object getObject() throws Exception {
        System.out.println(Thread.currentThread() + " entering getObject");
        boolean first = acquire();
        if (first) {
            try {
                obj = doGet();
                release();
            } catch (Exception e) {
                e.printStackTrace();
                release(false);
                throw e;
            }
        }
        return obj;
    }
    
    protected abstract Object doGet() throws Exception;
}

abstract class Once2Block extends Once2 {
    public final void runBlock() throws Exception {
        System.out.println(Thread.currentThread() + " entering runBlock");
        boolean first = acquire();
        if (first) {
            try {
                doRun();
                release();
            } catch (Exception e) {
                e.printStackTrace();
                release(false);
                throw e;
            }
        }
    }
    
    protected abstract void doRun() throws Exception;
}

class Once2 {
    private volatile boolean todo = true;
    private volatile boolean first = true;
    
    public final boolean acquire() throws InterruptedException {
        System.out.println(Thread.currentThread() + " entering acquire");
        if (todo) {
            synchronized (this) {
                if (todo) {
                    for (;;) {
                        if (first) {
                            first = false;
                            return true;
                        } else {
                            wait();
                            if (!todo) {
                                break;
                            }
                        }
                    }
                }
            };
        }
        return false;
    }
    
    public final void release() {
        System.out.println(Thread.currentThread() + " entering release");
        release(true);
    }
    
    public final void release(boolean success) {
        System.out.println(Thread.currentThread() + " entering release(success:" + success + ")");
        if (success) {
            synchronized (this) {
                todo = false;
                notifyAll();
            }
        } else {
            synchronized (this) {
                first = true;
                notify();
            }
        }
    }
}

// Example test. Others removed due to length restrictions.

public class DoubleCheckLockSolution {
    private Object oncePerObj = null;
    
    public static void main(String[] args) {
    	DoubleCheckLockSolution main = new DoubleCheckLockSolution();
        main.testOncePer();
    }
    
    public void testOncePer() {
        System.out.println(Thread.currentThread() + " entering testOncePer");

        final OncePer once = new OncePer() {
            private HashMap table = new HashMap(); // This could be a WeakHashMap
            
            protected Object doGet(String arg) throws Exception {
                System.out.println(Thread.currentThread() + " entering doGet(arg: " + arg + ")");
                return table.get(arg);
            }
            
            protected Object doCreate(String arg) throws Exception {
                System.out.println(Thread.currentThread() + " entering doCreate(arg: " + arg + ")");
                Pattern pattern = Pattern.compile(arg);
                table.put(arg, pattern);
                return pattern;
            }
        };
        
        Thread th1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread() + " entering run");
                try {
                    oncePerObj = (Pattern) once.getObject("[a-zA-Z0-9]+");
                    //test for matches
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "th1");
        
        Thread th2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread() + " entering run");
                try {
                    oncePerObj = (Pattern) once.getObject("[a-zA-Z0-9]+");
                    //test for matches
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "th2");
        
        try {
            th1.start();
            th2.start();
            th1.join();
            th2.join();
            System.out.println(Thread.currentThread() + " oncePerObj: " + oncePerObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
