package com.ttsoftware.utilities.stringhandler;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OccurrenceAlgorithm {

  private ConcurrentMap<String, AtomicInteger> wordsMap = new ConcurrentHashMap<String, AtomicInteger>();

  public ConcurrentMap<String, AtomicInteger> getWordMap() {
    return wordsMap;
  }

  public void indexSentences(String[] inputSentence, String regex, boolean caseSensitive) {
    if (inputSentence != null) {
      for (String sentence : inputSentence) {
        if (!caseSensitive) sentence = sentence.toLowerCase();
        indexString(sentence, regex);
      }
    } else {
      System.out.println("No input sentence.");
    }
  }

  private void indexString(String sentence, String regex) {
    String[] stringarray = sentence.split(regex);
    for (String str : stringarray) {
      if (wordsMap.containsKey(str)) {
        AtomicInteger value = wordsMap.get(str);
        value.incrementAndGet();
        wordsMap.put(str, value);
      } else {
        wordsMap.putIfAbsent(str, new AtomicInteger(1));
      }
    }

  }

  public void showOccurence() {
    if (wordsMap.size() > 0) {
      Iterator<Entry<String, AtomicInteger>> iterword = wordsMap.entrySet().iterator();
      while (iterword.hasNext()) {
        Entry<String, AtomicInteger> wordEntry = iterword.next();
        System.out.println("'" + wordEntry.getKey() + "' is " + wordEntry.getValue());
      }
    } else {
      System.out.println("No sentence.");
    }
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    OccurrenceAlgorithm oa = new OccurrenceAlgorithm();
    for (String arg : args) {
      oa.indexSentences(new String[] { arg }, " ", false);
    }
    oa.showOccurence();
  }

}
