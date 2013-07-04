package com.ttsoftware.utilities.performance;

public class MultiDimArray {
	private static final int MEGA = 5024;

	public static void main(String[] args)  {
		int[][] arr = new int[MEGA][MEGA];

		for (int i = 0; i < MEGA; i++)
			for (int j = 0; j < MEGA; j++) {
				arr[i][j] = i + j;
			}

		long start = System.nanoTime();
		long sum = 0;
		for (int i = 0; i < MEGA; i++)
			for (int j = 0; j < MEGA; j++) {
				sum += arr[j][i];
			}

		long end = System.nanoTime();
		System.out.println(end - start);
		System.out.println("sum: " + sum);

		start = System.nanoTime();
		sum = 0;
		for (int i = 0; i < MEGA; i++)
			for (int j = 0; j < MEGA; j++) {
				sum += arr[i][j];
			}

		end = System.nanoTime();
		System.out.println(end - start);
		System.out.println("sum: " + sum);
	}
}
