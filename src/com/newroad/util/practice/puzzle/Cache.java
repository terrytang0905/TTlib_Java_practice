package com.newroad.util.practice.puzzle;

public interface Cache<K,V> {
	V get(K key);
	
	V put(K key,V value);
	
	int size();
}
