package com.ttsoftware.utilities.performance.lock;

public class Usage {

	private static class Resource {
		public void read() {
			try {
				// emulates read
				Thread.sleep((long) (Math.random() * 3000));

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void write() {
			try {
				// emulates write
				Thread.sleep((long) (Math.random() * 4000));
				System.out.println("Writing in Thread - "
						+ Thread.currentThread().getName());

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// example of using RWLock
	public static void main(String[] args) {
		final Resource r = new Resource();
		final RWLock lock = new RWLockImpl();

		Runnable readerRunnable = new Runnable() {

			@Override
			public void run() {
				lock.lockRead();
				System.out.println("Starting to Read in Thread - "
						+ Thread.currentThread().getName());
				r.read();
				System.out.println("Finishing to Read in Thread - "
						+ Thread.currentThread().getName());
				lock.unlockRead();
			}
		};
		
		Runnable writerRunnable = new Runnable() {

			@Override
			public void run() {
				lock.lockWrite();
				System.out.println("Starting to Write in Thread - "
						+ Thread.currentThread().getName());
				r.write();
				System.out.println("Finishing to Write in Thread - "
						+ Thread.currentThread().getName());
				lock.unlockWrite();
			}
		};

		// will start 3 readers and 3 writers
		for (int i = 0; i < 3; i++) {
			Thread t = new Thread(readerRunnable);
			t.setName("Reader - " + i);
			t.start();
			t = new Thread(writerRunnable);
			t.setName("Writer - " + i);
			t.start();

		}

	}

}
