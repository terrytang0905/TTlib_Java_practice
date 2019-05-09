package com.newroad.util.practice.stringhandler;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringNumberConvertorTest {

	
	private String testStr="Successful";
	
	@Test
	public void testReverseString(){
		StringNumberConvertor su=new StringNumberConvertor();
		String result=su.reverseString(testStr);
		System.out.print(result);
		assertEquals("reverse String:","lufsseccu",result);
	}
	
	@Test
	public void testAnalyzeNumbers(){
		StringNumberConvertor su=new StringNumberConvertor();
		String result=su.analyzeNumbers(10);
		assertEquals("Count number:","12Fizz4BuzzFizz78FizzBuzz",result);
		
	}
	
	@Test
	public void testAnalyzeNumbers2(){
		StringNumberConvertor su=new StringNumberConvertor();
		String result=su.analyzeNumbers2(20);
		assertEquals("Count number:","12Fizz4BuzzFizz78FizzBuzz11FizzFizz14FizzBuzz1617Fizz19Buzz",result);
		
	}
}
