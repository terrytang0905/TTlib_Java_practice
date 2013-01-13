package com.ttsoftware.utilities.puzzle;

public class ByteArray {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=1<<29;
		System.out.println(i);
		byte[] b;
		while (i<Integer.MAX_VALUE/2) {
			 b= new byte[i];
			System.out.println("byte array size:"+i);
			i=i+10000000;
		}
	}

}
