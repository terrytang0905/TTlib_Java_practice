package com.ttsoftware.utilities.puzzle;

public interface Cache<K,V> {
	V get(K key);
	
	V put(K key,V value);
	
	int size();
}
