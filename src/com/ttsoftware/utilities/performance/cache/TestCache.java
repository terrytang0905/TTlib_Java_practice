package com.ttsoftware.utilities.performance.cache;


public class TestCache {
	public static void main(String[] args) throws Exception{
		Cache<String,Integer> softMap=new MySoftMap<String,Integer>();
		softMap.put("key1", 100);
		softMap.put("key2", 300);
		
		System.out.println(softMap.get("key1"));
		try{
			int[] arr=new int[20000000];
		}catch(OutOfMemoryError e){
			e.printStackTrace();
		}
		
		System.out.println(softMap.get("key2"));
		
	}
}
