package com.ttsoftware.utilities.stringhandler;

public class StringNumberConvertor {

	public String reverseString(String str) {
		StringBuilder reverseStr = new StringBuilder();
		if (str != null) {
			int strlength = str.length();
			for (int i = strlength - 1; i > 0; i--) {
				char c = str.charAt(i);
				reverseStr.append(c);
			}
		}
		return reverseStr.toString();

	}

	public String analyzeNumbers(int num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < num + 1; i++) {
			if (i % 3 == 0 && i % 5 != 0) {
				sb.append("Fizz");
			} else if (i % 5 == 0 && i % 3 != 0) {
				sb.append("Buzz");
			} else if (i % 3 == 0 && i % 5 == 0) {
				sb.append("FizzBuzz");
			} else {
				sb.append(i);
			}
		}
		return sb.toString();
	}

	public String analyzeNumbers2(int number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < number + 1; i++) {
			String numberStr = String.valueOf(i);
			if (i % 3 == 0 && i % 5 == 0) {
				sb.append("FizzBuzz");
			} else if ((i % 3 == 0 && i % 5 != 0)
					|| numberStr.indexOf("3") >= 0) {
				sb.append("Fizz");
			} else if ((i % 5 == 0 && i % 3 != 0)
					|| numberStr.indexOf("5") >= 0) {
				sb.append("Buzz");
			} else {
				sb.append(i);
			}
		}
		return sb.toString();
	}

}
