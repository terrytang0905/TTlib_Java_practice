package com.ttsoftware.utilities.performance.cache;
//implement a Soft value Cache

public interface Cache<K, V> {
	V get(K key);

	// returns the old value if exists
	V put(K key, V value);

	int size();
}
