package com.ttsoftware.utilities.performance;

public class Padding {
	static class Pair {
		 long c1;
		 long c2;
	}

	static Pair p = new Pair();

	static class Worker implements Runnable {

		private static final int INT = Integer.MAX_VALUE / 8;
		private final boolean b;

		Worker(boolean b) {
			this.b = b;
		}

		@Override
		public void run() {
			long start = System.currentTimeMillis();
			if (b) {
				for (int i = 0; i < INT; i++) {
					p.c1++;
				}
			} else {
				for (int i = 0; i < INT; i++) {
					p.c2++;
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("took: " + (end - start) + " " + p.c1 + p.c2);
		}

	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Worker(true));
		Thread t2 = new Thread(new Worker(false));

		t1.start();
		t2.start();

	}

}
