package com.ttsoftware.utilities.xmlprocessing;

/**
 * A collection of XML navigation utilities for XML processing by DOM4J. 
 * This utilities use D0M4J API to navigate xml documents included insert,update,delete actions.
 * 
 * @author tangz
 * @since 2012
 * @version 0.1
 * 
 */

public interface XMLProcessingConstants {
	
  public static String xmlSourceFile = "src/com/ttsoftware/utilities/xmlprocessing/indexserverconfig.xml"; 	

  public static String xmlProperty = "src/xml.properties";
  
  public static String xmlPath="xmlPath";
  
  public static String operationType="operationType";
  
  public static String elementName="elementName";
  
  public static String text="text";
  
  public static String attributeNames="attributeNames";
  
  public static String attributeValues="attributeValues";
  
  public static String xPath="xPath";
  
}
