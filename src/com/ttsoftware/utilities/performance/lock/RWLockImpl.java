package com.ttsoftware.utilities.performance.lock;

import java.util.LinkedList;
import java.util.Queue;

// You need to implement this class without using any java.util.concurrent types
// The implementation MUST be thread-safe
// Writers should obtain lock in a FIFO order
// If you encounter a deadlock, use JVisualVM (or a regular thread-dump) to analyze
public class RWLockImpl implements RWLock {

	// / Example of a beginning of implementation. You may change this.
	private int activeReaders;
	private int waitingReaders;
	private int waitingWriters;
	private int activeWriters; // can't be more than one

	private Queue<Object> writerLocks = new LinkedList<Object>();

	@Override
	// will block if there is a currently active writer
	// will block if there are waiting writers
	public synchronized void lockRead() {
		if (activeWriters > 0 || waitingWriters > 0) {
			try {
				waitingReaders++;
				wait();
				waitingReaders--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		activeReaders++;

	}

	@Override
	// releases the read lock (activeReaders--)
	public synchronized void unlockRead() {
		activeReaders--;
		if (activeReaders == 0 && waitingWriters > 0) {
			Object wLockObj = writerLocks.remove();
			synchronized (wLockObj) {
				wLockObj.notify();
			}
		}
	}

	@Override
	// will block if there are currently activeReaders
	// (waitingWriters++)
	public void lockWrite() {
		Object lockObject = new Object();
		synchronized (lockObject) {
			synchronized (this) {
				if (activeReaders > 0 || activeWriters > 0) {
					// create our own lock object
					writerLocks.add(lockObject);
					waitingWriters++;
				} else {
					activeWriters++;
					return;
				}
			}
			try {
				lockObject.wait();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (this) {
				waitingWriters--;
			}
		}

	}

	@Override
	// releases the writelock (activeWriters--)
	public synchronized void unlockWrite() {
		activeWriters--;
		if (waitingReaders > 0) {
			// notify readers
			this.notifyAll();
		} else if (waitingWriters > 0) {
			Object wLockObj = writerLocks.remove();
			synchronized (wLockObj) {
				wLockObj.notify();
			}
		}

	}

}
