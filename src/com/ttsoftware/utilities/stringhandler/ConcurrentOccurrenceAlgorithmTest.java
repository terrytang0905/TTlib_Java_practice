package com.ttsoftware.utilities.stringhandler;

import org.junit.Test;

public class ConcurrentOccurrenceAlgorithmTest {

	private String[] inputSentence = new String[] { "he is from China" };
	private String[] inputSentence2 = new String[] {
			"He is Nevin and she is Cherry ", "he is from Japan" };
	private String[] inputSentence3 = new String[] { "She is Klarvier and he is Terry " };

	@Test
	public void testConcurrentIndexSentences() throws Throwable {
		OccurrenceAlgorithm oa = new OccurrenceAlgorithm();

		IndexThread[] threads = new IndexThread[3];
		threads[0] = new IndexThread(0, inputSentence, oa);
		threads[1] = new IndexThread(1, inputSentence2, oa);
		threads[2] = new IndexThread(2, inputSentence3, oa);

		for (int i = 0; i < 3; i++) {
			threads[i].start();
		}
		for (int i = 0; i < 3; i++) {
			threads[i].join();
		}

		Thread.sleep(1000);
		oa.showOccurence();
	}

	private class IndexThread extends Thread {

		private final int threadID;
		private String[] inputSentences = null;
		private Throwable throwable;
		private OccurrenceAlgorithm oa;

		public IndexThread(int threadID, String[] inputSentences,
				OccurrenceAlgorithm oa) {
			setName("IndexThread:" + threadID);
			this.threadID = threadID;
			this.inputSentences = inputSentences;
			this.oa = oa;
		}

		@Override
		public void run() {
			try {
				oa.indexSentences(inputSentences, " ", false);
			} catch (Throwable e) {
				throwable = e;
			}
		}

	}
}
