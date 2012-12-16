package com.ttsoftware.utilities.stringhandler;

import org.junit.Assert;
import org.junit.Test;



public class OccurrenceAlgorithmTest extends Assert{
  
  private String inputSentence="he is from China";
  private String inputSentence2="He is Nevin and she is Cherry ";
  
  @Test
  public void testWordOccurrence1(){
    System.out.println("Test1:");
    OccurrenceAlgorithm oa = new OccurrenceAlgorithm();
    oa.indexSentences(new String[]{inputSentence,inputSentence2}, " ", true);
    assertEquals("Word 'he' occurrence:",1,oa.getWordMap().get("he").intValue());
    assertEquals("Word 'is' occurrence:",3,oa.getWordMap().get("is").intValue());
    assertEquals("Word 'China' occurrence:",1,oa.getWordMap().get("China").intValue());
    assertEquals("Word 'He' occurrence:",1,oa.getWordMap().get("He").intValue());
    oa.showOccurence();
  }
  
  @Test
  public void testWordOccurrence2(){
    System.out.println("Test2:");
    OccurrenceAlgorithm oa = new OccurrenceAlgorithm();
    oa.indexSentences(new String[]{inputSentence,inputSentence2}, " ", false);
    assertEquals("Word 'he' occurrence:",2,oa.getWordMap().get("he").intValue());
    assertEquals("Word 'is' occurrence:",3,oa.getWordMap().get("is").intValue());
    assertEquals("Word 'china' occurrence:",1,oa.getWordMap().get("china").intValue());
    oa.showOccurence();
  }
  
  @Test
  public void testWordOccurrence3(){
    System.out.println("Test3:");
    OccurrenceAlgorithm oa = new OccurrenceAlgorithm();
    oa.indexSentences(null, " ", true);
    assertEquals("WordMap size:",0,oa.getWordMap().size());
    oa.showOccurence();
  }

}
