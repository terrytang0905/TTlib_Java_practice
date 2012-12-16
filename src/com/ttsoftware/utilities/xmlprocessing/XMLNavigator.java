package com.ttsoftware.utilities.xmlprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;
import org.dom4j.util.UserDataAttribute;

import com.ttsoftware.utilities.xmlprocessing.UserElement.XMLOperation;
import com.ttsoftware.utilities.xmlprocessing.exception.XMLProcessingException;

/**
 * A collection of XML navigation utilities for XML processing by DOM4J. 
 * This utilities use D0M4J API to navigate xml documents included insert,update,delete actions.
 * 
 * @author tangz
 * @since 2012
 * @version 0.1
 * 
 */


public class XMLNavigator implements XMLProcessing {

	private void addXMLNode(File xmlfile, UserElement xmlDataStream)
			throws DocumentException, IOException {

		SAXReader reader = new SAXReader();
		Document document = reader.read(xmlfile);

		String selectedElementName = xmlDataStream.getElementName();

		List<?> list = document.selectNodes(xmlDataStream.getxPath());
		if (list.size() == 0) {
			throw new XMLProcessingException("Please set the valid xPath.");
		}
		for (int i = 0; i < list.size(); i++) {
			Object xmlNode = list.get(i);
			if (xmlNode instanceof DefaultAttribute) {
				Attribute attribute = (Attribute) xmlNode;
				throw new XMLProcessingException("Couldn't add the attribute "
						+ attribute.getName() + " that is already exist.");
			} else if (xmlNode instanceof DefaultElement) {
				Element parentElement = (Element) xmlNode;
				if (!parentElement.getName().equals(selectedElementName)) {
					Element childElement = parentElement
							.addElement(selectedElementName);
					String newText = xmlDataStream.getText();
					HashMap<javax.xml.namespace.QName, String> userAttributesMap = xmlDataStream
							.getUserAttributesMap();
					if (userAttributesMap.size() > 0) {
						Set<Entry<javax.xml.namespace.QName, String>> entrySet = userAttributesMap
								.entrySet();
						Iterator<Entry<javax.xml.namespace.QName, String>> iter = entrySet
								.iterator();
						while (iter.hasNext()) {
							Entry<javax.xml.namespace.QName, String> entry = iter
									.next();
							UserDataAttribute userDataAttribute = convert2DOM4JUserDataAttribute(entry);
							childElement.addAttribute(
									userDataAttribute.getQName(),
									userDataAttribute.getValue());
						}
						childElement.setText(newText);
					}
				} else {
					throw new XMLProcessingException(
							"The input xPath Expression doesn't match the input element !");
				}
			}
		}

		XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfile));
		writer.write(document);
		writer.flush();
		writer.close();

	}

	private void deleteXMLNode(File xmlfile, UserElement xmlDataStream)
			throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(xmlfile);

		String selectedElementName = xmlDataStream.getElementName();
		List<?> list = document.selectNodes(xmlDataStream.getxPath());
		if (list.size() == 0) {
			throw new XMLProcessingException("Please set the valid xPath.");
		}
		for (int i = 0; i < list.size(); i++) {
			Object xmlNode = list.get(i);
			if (xmlNode instanceof DefaultAttribute) {
				Attribute attribute = (Attribute) xmlNode;
				Element parentElement = attribute.getParent();
				HashMap<javax.xml.namespace.QName, String> userAttributesMap = xmlDataStream
						.getUserAttributesMap();
				if (userAttributesMap.size() > 0) {
					Set<Entry<javax.xml.namespace.QName, String>> entrySet = userAttributesMap
							.entrySet();
					Iterator<Entry<javax.xml.namespace.QName, String>> iter = entrySet
							.iterator();
					while (iter.hasNext()) {
						Entry<javax.xml.namespace.QName, String> entry = iter
								.next();
						UserDataAttribute userDataAttribute = convert2DOM4JUserDataAttribute(entry);
						if (attribute.getQName().equals(
								userDataAttribute.getQName())) {
							parentElement.remove(attribute);
						}
					}
				}
			} else if (xmlNode instanceof DefaultElement) {
				Element parentElement = (Element) xmlNode;
				if (parentElement.getName().equals(selectedElementName)) {
					HashMap<javax.xml.namespace.QName, String> userAttributesMap = xmlDataStream
							.getUserAttributesMap();
					if (userAttributesMap.size() > 0) {
						Set<Entry<javax.xml.namespace.QName, String>> entrySet = userAttributesMap
								.entrySet();
						Iterator<Entry<javax.xml.namespace.QName, String>> iter = entrySet
								.iterator();
						while (iter.hasNext()) {
							Entry<javax.xml.namespace.QName, String> entry = iter
									.next();
							UserDataAttribute userDataAttribute = convert2DOM4JUserDataAttribute(entry);
							Attribute attribute = parentElement
									.attribute(userDataAttribute.getQName());
							if (attribute != null) {
								parentElement.remove(attribute);
							}
						}
					}
				} else {
					throw new XMLProcessingException(
							"The input xPath Expression doesn't match the input element !");
				}
			}
		}

		XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfile));
		writer.write(document);
		writer.flush();
		writer.close();

	}

	private void updateXMLNode(File xmlfile, UserElement xmlDataStream)
			throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(xmlfile);

		String selectedElementName = xmlDataStream.getElementName();
		List<?> list = document.selectNodes(xmlDataStream.getxPath());
		if (list.size() == 0) {
			throw new XMLProcessingException("Please set the valid xPath.");
		}
		for (int i = 0; i < list.size(); i++) {
			Object xmlNode = list.get(i);
			if (xmlNode instanceof DefaultAttribute) {
				Attribute attribute = (Attribute) xmlNode;
				HashMap<javax.xml.namespace.QName, String> userAttributesMap = xmlDataStream
						.getUserAttributesMap();
				if (userAttributesMap.size() > 0) {
					Set<Entry<javax.xml.namespace.QName, String>> entrySet = userAttributesMap
							.entrySet();
					Iterator<Entry<javax.xml.namespace.QName, String>> iter = entrySet
							.iterator();
					while (iter.hasNext()) {
						Entry<javax.xml.namespace.QName, String> entry = iter
								.next();
						UserDataAttribute userDataAttribute = convert2DOM4JUserDataAttribute(entry);
						if (attribute.getQName().equals(
								userDataAttribute.getQName())&&attribute.getValue().equals(
										userDataAttribute.getValue())) {
							attribute.setValue(userDataAttribute.getValue());
						}
					}
				}
			} else if (xmlNode instanceof DefaultElement) {
				Element parentElement = (Element) xmlNode;
				if (parentElement.getName().equals(selectedElementName)) {
					String updateText = xmlDataStream.getText();
					parentElement.setText(updateText);
				} else {
					throw new XMLProcessingException(
							"The input xPath Expression doesn't match the input element !");
				}
			}
		}

		XMLWriter writer = new XMLWriter(new FileOutputStream(xmlfile));
		writer.write(document);
		writer.flush();
		writer.close();

	}

	private UserDataAttribute convert2DOM4JUserDataAttribute(
			Entry<javax.xml.namespace.QName, String> entry) {
		UserDataAttribute userDateAttribute = new UserDataAttribute(new QName(
				entry.getKey().toString()), entry.getValue());
		return userDateAttribute;
	}

	public UserElement loadXMLNode(XMLOperation operationType,
			String elementName, String text, String[] attributeNames,
			String[] attributeValues, String xPath) {

		UserElement xmlDataStream = new UserElement();
		xmlDataStream.setOperationType(operationType);
		xmlDataStream.setElementName(elementName);
		xmlDataStream.setText(text);
		xmlDataStream.setxPath(xPath);
		if (attributeNames != null && attributeValues != null
				&& attributeNames.length == attributeValues.length) {
			HashMap<javax.xml.namespace.QName, String> userAttributesMap = new HashMap<javax.xml.namespace.QName, String>();
			for (int i = 0; i < attributeNames.length; i++) {
				javax.xml.namespace.QName qname = new javax.xml.namespace.QName(
						attributeNames[i]);
				String value = attributeValues[i];
				userAttributesMap.put(qname, value);
			}
			xmlDataStream.setUserAttributesMap(userAttributesMap);
		}
		return xmlDataStream;
	}

	public List<UserElement> loadPropertiesXMLNode(Properties prop) {
		List<UserElement> xmlDataStreamList = new ArrayList<UserElement>();
		String operationType = prop
				.getProperty(XMLProcessingConstants.operationType);
		String elementName = prop
				.getProperty(XMLProcessingConstants.elementName);
		String text = prop.getProperty(XMLProcessingConstants.text);
		String attributeNames = prop
				.getProperty(XMLProcessingConstants.attributeNames);
		String attributeValues = prop
				.getProperty(XMLProcessingConstants.attributeValues);
		String xPath = prop.getProperty(XMLProcessingConstants.xPath);
		if (operationType == null || operationType.equals("")
				|| elementName == null || elementName.equals("")
				|| xPath == null || xPath.equals("")) {
			throw new NullPointerException(
					"operationType or elementName or xPath couldn't be set to null or \"\".");
		}

		XMLOperation operation = null;
		switch (Integer.parseInt(operationType)) {
		case 0:
			operation = XMLOperation.INSERT;
			break;
		case 1:
			operation = XMLOperation.UPDATE;
			break;
		case 2:
			operation = XMLOperation.DELETE;
			break;
		}
		String[] attributeNamesArray;
		String[] attributeValueArray;
		if (attributeNames != null && !attributeNames.equals("")
				&& attributeValues != null && !attributeValues.equals("")) {
			attributeNamesArray = attributeNames.split(",");
			attributeValueArray = attributeValues.split(",");
		} else {
			attributeNamesArray = null;
			attributeValueArray = null;
		}
		UserElement xmlDataStream1 = loadXMLNode(operation, elementName, text,
				attributeNamesArray, attributeValueArray, xPath);
		xmlDataStreamList.add(xmlDataStream1);
		return xmlDataStreamList;
	}

	@Override
	public void processXMLNode(Reader inputReader, UserElement xmlDataStream) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processXMLNode(File xmlFile, UserElement xmlDataStream) {
		// TODO Auto-generated method stub
		try {
			switch (xmlDataStream.getOperationType()) {
			case INSERT:
				addXMLNode(xmlFile, xmlDataStream);
				break;
			case UPDATE:
				updateXMLNode(xmlFile, xmlDataStream);
				break;
			case DELETE:
				deleteXMLNode(xmlFile, xmlDataStream);
				break;
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
