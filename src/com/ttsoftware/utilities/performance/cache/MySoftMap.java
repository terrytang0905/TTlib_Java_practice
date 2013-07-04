package com.ttsoftware.utilities.performance.cache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
// Does not support and you should never use null keys
public class MySoftMap<K, V> implements Cache<K, V> {

	private class MySoftReference extends SoftReference<V> {
		private final K key;

		public MySoftReference(V referent, K key) {
			super(referent, refQueue);
			this.key = key;
		}

	}

	private final ReferenceQueue<V> refQueue = new ReferenceQueue<V>();
	private final Map<K, MySoftReference> internalMap = new HashMap<K, MySoftReference>();

	@Override
	public V get(K key) {
		SoftReference<V> result = internalMap.get(key);
		return (result == null) ? null : result.get();
	}

	@Override
	public V put(K key, V value) {
		cleanUnusedEntries();
		MySoftReference oldValue = internalMap.put(key, new MySoftReference(
				value, key));
		return (oldValue == null) ? null : oldValue.get();
	}

	@SuppressWarnings("unchecked")
	private void cleanUnusedEntries() {
		MySoftReference ref = null;
		while ((ref = (MySoftReference) refQueue.poll()) != null) {
			if (internalMap.get(ref.key) == ref) {
				internalMap.remove(ref.key);
			}
		}
	}

	
	
	@Override
	public int size() {
		cleanUnusedEntries();
		return internalMap.size();
	}

	public static void main(String[] args) {
		Cache<Integer,Integer> c = new MySoftMap<Integer, Integer>();
		
		c.put(100, Integer.valueOf(100));
		c.put(1000, Integer.valueOf(1000));
		
		System.out.println(c.get(100));
		System.out.println(c.get(1000));
		
		
		try {
			long[] b = new long[238489792];
		}catch (OutOfMemoryError e) {
			System.out.println(e.getMessage());
		}
		System.out.println(c.get(100));
		System.out.println(c.get(1000));
		
	}
}
