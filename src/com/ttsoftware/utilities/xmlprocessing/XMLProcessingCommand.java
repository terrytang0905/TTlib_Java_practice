package com.ttsoftware.utilities.xmlprocessing;

import java.io.File;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
		Element element=xmlUtil.getUniqueElementByXPath("//index-server-configuration/category-definitions/category/do-text-extraction/for-element-with-name", "path", "/dmftdoc/dmftcustom//dmftcontentref");
		Element childElement=(Element) element.getElementsByTagName("save-tokens-for-summary-processing").item(0);
		HashMap<String,String> attributesMap=new HashMap<String,String>();
		attributesMap.put("token-size", "-1");
		Document doc=xmlUtil.updateAttributes(childElement, attributesMap);
		xmlUtil.saveDocument(doc, xmlfile);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		XMLProcessingCommand command=new XMLProcessingCommand();
		command.xmlUtilsUpdateElementSample();
	}

}
