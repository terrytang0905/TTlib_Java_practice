package com.ttsoftware.utilities.performance;

import java.util.Arrays;


public class SortedArraySpeed {
	public static void main(String[] args) {
		byte[] arr = new byte[10000000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (byte) (Math.random() * 500);
		}
		long counter = 0;
		Arrays.sort(arr);
		long start  = System.nanoTime();
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > 100) {
					counter++;
				}
			}			
//		}
		long end = System.nanoTime();
		System.out.println("It took: " + (end - start) + " " + counter);
		
	}
}
