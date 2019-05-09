package com.newroad.util.practice.performance.lock;

// A Read/Write Lock without using Java Util Concurrent
public interface RWLock {
	void lockRead();
	void unlockRead();
	void lockWrite();
	void unlockWrite();
}
