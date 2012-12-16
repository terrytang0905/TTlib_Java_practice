package com.ttsoftware.utilities.xmlprocessing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stax.StAXResult;
import javax.xml.transform.stax.StAXSource;

import com.ttsoftware.utilities.xmlprocessing.UserElement.XMLOperation;

/**
 * A stax parser utilities for XML processing. 
 * This utilities  XML Stax parser.
 * 
 * @author tangz
 * @since 2012
 * @version 0.1
 * 
 */

public class XMLProcessingStaxParser implements XMLProcessing {

	/*
	 * 
	 */
	private UserElement processXMLStream(File xmlFile, UserElement userElement)
			throws XMLStreamException {
		Stack<UserElement> stack = new Stack<UserElement>();
		XMLInputFactory inputfactory = XMLInputFactory.newInstance();
		XMLOutputFactory outputfactory = XMLOutputFactory.newInstance();
		XMLStreamReader streamReader = null;
		XMLStreamWriter streamWriter = null;
		try {
			FileInputStream fis = new FileInputStream(xmlFile);
			streamReader = inputfactory
					.createXMLStreamReader(new InputStreamReader(fis));
			streamWriter = outputfactory.createXMLStreamWriter(new FileWriter(
					xmlFile));
			int event = streamReader.getEventType();

			while (true) {
				switch (event) {
				case XMLStreamConstants.START_DOCUMENT:
					System.out.println("Start Document.");
					break;
				case XMLStreamConstants.START_ELEMENT:
					System.out.println("Start ELEMENT.");
					String name = streamReader.getLocalName();
					if (name.equals(userElement.getElementName())) {
						stack.push(userElement);
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					if (streamReader.isWhiteSpace())
						break;
					System.out.println("Text: " + streamReader.getText());
					break;
				case XMLStreamConstants.END_ELEMENT:
					System.out.println("End Element:" + streamReader.getName());
					break;
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println("End Document.");
					break;
				}
				if (!streamReader.hasNext())
					break;
				event = streamReader.next();

			}
			if (!stack.empty()) {
				return stack.pop();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			streamReader.close();
			streamWriter.close();
		}
		return null;
	}

	@SuppressWarnings("unused")
	private void processXMLEvent(File xmlFile, UserElement userElement)
			throws XMLStreamException {
		// get the default factory instance
		XMLInputFactory inputfactory = XMLInputFactory.newInstance();
		// configure it to create readers that coalesce adjacent character
		// sections
		inputfactory.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);
		XMLOutputFactory outputfactory = XMLOutputFactory.newInstance();

		XMLEventReader eventReader = null;
		XMLEventWriter eventWriter = null;

		try {
			FileInputStream fis = new FileInputStream(xmlFile);
			eventReader = inputfactory
					.createXMLEventReader(new InputStreamReader(fis));
			eventWriter = outputfactory.createXMLEventWriter(new FileWriter(
					xmlFile));
			int count = 0;
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					System.out.println("Start Document.");
					eventWriter.add(event);
					break;
				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					QName elementName = startElement.getName();
					System.out.println("Start Element: " + elementName);
					if (elementName.toString().equals(
							userElement.getElementName())) {
						updateXMLEvent(event, eventWriter, userElement);
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					if (characters.isWhiteSpace())
						break;
					System.out.println("Text: " + characters.getData());
					eventWriter.add(event);
					break;
				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();
					System.out.println("End Element:" + endElement.getName());
					eventWriter.add(event);
					break;
				case XMLStreamConstants.END_DOCUMENT:
					System.out.println("End Document.");
					eventWriter.add(event);
					break;
				}
				System.out.println("Count:" + count++);
			}
			eventWriter.flush();

		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			eventReader.close();
			eventWriter.close();
		}
	}

	private int updateXMLEvent(XMLEvent event, XMLEventWriter eventWriter,
			UserElement xmlDataStream) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		if (event.isStartElement()) {
			StartElement startElement = event.asStartElement();
			eventWriter.add(eventFactory.createStartElement(
					startElement.getName(), startElement.getAttributes(),
					startElement.getNamespaces()));
			@SuppressWarnings("rawtypes")
			Iterator iterator = startElement.getAttributes();
			while (iterator.hasNext()) {
				Attribute attribute = (Attribute) iterator.next();
				System.out.println("Attribute: " + attribute.getName() + "="
						+ attribute.getValue());
				updateXMLEvent(attribute, eventWriter, xmlDataStream);
			}
		} else if (event.isAttribute()) {
			Attribute attribute = (Attribute) event;
			HashMap<QName,String> userAttributesMap = new HashMap<QName,String>();
			if (userAttributesMap.size() == 1) {
				Set<Entry<QName,String>> entrySet=userAttributesMap.entrySet();
				Iterator<Entry<QName,String>> iter=entrySet.iterator();
				Entry<QName,String> entry=iter.next();
				eventWriter.add(eventFactory.createAttribute(
						entry.getKey(), entry.getValue()));
				System.out.println("Update Attribute: " + attribute.getName()
						+ "=" + attribute.getValue());
				return 1;
			} else {
				eventWriter.add(event);
			}
		}
		return 0;
	}

	public boolean transformXMLStaxEvent(XMLEventReader eventReader,
			XMLEventWriter eventWriter) {
		boolean flag = true;
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer = tFactory.newTransformer();

			StAXSource source = new StAXSource(eventReader);
			StAXResult result = new StAXResult(eventWriter);
			transformer.transform(source, result);
		} catch (Exception ex) {
			flag = false;
			ex.printStackTrace();
		}
		return flag;
	}

	@Override
	public UserElement loadXMLNode(XMLOperation operationType,
			String elementName, String text, String[] attributeNames,
			String[] attributeValues, String xPath) {
		if (operationType == null || elementName == null || elementName == ""
				|| xPath == null || xPath == "") {
			throw new NullPointerException(
					"operationType or elementName or xPath couldn't be set to null or \"\".");
		}
		UserElement xmlDataStream = new UserElement();
		xmlDataStream.setOperationType(operationType);
		xmlDataStream.setElementName(elementName);
		xmlDataStream.setText(text);
		xmlDataStream.setxPath(xPath);
		if (attributeNames != null && attributeValues != null
				&& attributeNames.length == attributeValues.length) {
			HashMap<QName,String> userAttributesMap = new HashMap<QName,String>();
			for (int i = 0; i < attributeNames.length; i++) {
				QName qname = new QName(attributeNames[i]);
				String value = attributeValues[i];
				userAttributesMap.put(qname, value);
			}
			xmlDataStream.setUserAttributesMap(userAttributesMap);
		}
		return xmlDataStream;
	}

	@Override
	public void processXMLNode(Reader inputReader, UserElement xmlDataStream) {
		// TODO Auto-generated method stub

	}

	@Override
	public void processXMLNode(File xmlFile, UserElement xmlDataStream) {
		// TODO Auto-generated method stub
		try {
			processXMLStream(xmlFile, xmlDataStream);
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<UserElement> loadPropertiesXMLNode(Properties prop) {
		// TODO Auto-generated method stub
		return null;
	}

}
