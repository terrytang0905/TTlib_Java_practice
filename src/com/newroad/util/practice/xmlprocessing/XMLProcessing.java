package com.newroad.util.practice.xmlprocessing;

import java.io.File;
import java.io.Reader;
import java.util.List;
import java.util.Properties;

public interface XMLProcessing {
	
	  public UserElement loadXMLNode(UserElement.XMLOperation operationType, String elementName, String text,
                                     String[] attributeNames, String[] attributeValues, String xPath);
	  
	  public List<UserElement> loadPropertiesXMLNode(Properties prop);
	  
	  public void processXMLNode(Reader inputReader, UserElement xmlDataStream);
	  
	  public void processXMLNode(File xmlFile, UserElement xmlDataStream);
	  
}
