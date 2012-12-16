package com.ttsoftware.utilities.xmlprocessing;

import java.io.File;
import java.util.HashMap;

import org.w3c.dom.Document;

public class XMLProcessingCommand {

	
	public void xmlUtilsAddElementSample(){
		File xmlfile=new File(XMLProcessingConstants.xmlSourceFile);
		XMLProcessingUtils xmlUtil=new XMLProcessingUtils(xmlfile);
		HashMap<String,String> attributesMap=new HashMap<String,String>();
		attributesMap.put("name", "locale");
		attributesMap.put("value", "Shanghai");
		Document doc=xmlUtil.addElementAttributes("//index-server-configuration/search-config/properties", "property",null, attributesMap);
		xmlUtil.saveDocument(doc, xmlfile);
	}
	
	public void xmlUtilsDeleteElementSample(){
		File xmlfile=new File(XMLProcessingConstants.xmlSourceFile);
		XMLProcessingUtils xmlUtil=new XMLProcessingUtils(xmlfile);
		Document doc=xmlUtil.deleteElement("//index-server-configuration/search-config/properties/property", "name", "locale");
		xmlUtil.saveDocument(doc, xmlfile);
	}
	
	
	public void xmlUtilsUpdateElementSample(){
		File xmlfile=new File(XMLProcessingConstants.xmlSourceFile);
		XMLProcessingUtils xmlUtil=new XMLProcessingUtils(xmlfile);
		HashMap<String,String> attributesMap=new HashMap<String,String>();
		attributesMap.put("value", "678");
		Document doc=xmlUtil.updateElementListAttributes("//index-server-configuration/search-config/properties/property","name", "query-default-result-batch-size",attributesMap);
		xmlUtil.saveDocument(doc, xmlfile);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLProcessingCommand command=new XMLProcessingCommand();
		command.xmlUtilsDeleteElementSample();
	}

}
