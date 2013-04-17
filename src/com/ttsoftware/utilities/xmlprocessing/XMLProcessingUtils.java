package com.ttsoftware.utilities.xmlprocessing;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ttsoftware.utilities.xmlprocessing.exception.XMLProcessingException;

/**
 * A collection of utilities for XML processing. This utilities use XML DOM
 * parser and xPath location to navigate XML documents included get,insert,update,delete actions.
 * 
 * @author tangz
 * @since 2012
 * @version 0.1
 * 
 */

public class XMLProcessingUtils {

	private Document document;
	private XPath xpath;

	public XMLProcessingUtils() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder m_builder = factory.newDocumentBuilder();
			document = m_builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public XMLProcessingUtils(File xmlFile) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder m_builder = factory.newDocumentBuilder();
			document = m_builder.parse(xmlFile);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public XMLProcessingUtils(InputStream is) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder m_builder = factory.newDocumentBuilder();
			document = m_builder.parse(is);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Document getDocumentInstance() {
		return document;
	}

	public void getXPathInstance() {
		if (xpath == null) {
			XPathFactory xpfactory = XPathFactory.newInstance();
			xpath = xpfactory.newXPath();
		}
	}

	public Element getRootElement() {
		return document.getDocumentElement();
	}

	public NodeList getElementsByTagName(String elementName) {
		return document.getElementsByTagName(elementName);
	}

	public Node getNodeByXPath(String xPathExpr)
			throws XPathExpressionException {
		validateXPathExprFormat(xPathExpr);
		getXPathInstance();
		Object evalResult = xpath.evaluate(xPathExpr, document,
				XPathConstants.NODE);
		return (Node) evalResult;
	}

	public NodeList getNodeListByXPath(String xPathExpr)
			throws XPathExpressionException {
		validateXPathExprFormat(xPathExpr);
		getXPathInstance();
		Object evalResult = xpath.evaluate(xPathExpr, document,
				XPathConstants.NODESET);
		return (NodeList) evalResult;
	}

	public Object getEvalResultByXPath(String xPathExpr, String typeName) {
		validateXPathExprFormat(xPathExpr);
		getXPathInstance();
		try {
			QName returnType = (QName) XPathConstants.class.getField(typeName)
					.get(null);
			Object evalResult = xpath.evaluate(xPathExpr, document, returnType);
			return evalResult;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Object getEvalResultByXPath(String xPathExpr) {
		validateXPathExprFormat(xPathExpr);
		getXPathInstance();
		try {
			Object evalResult = xpath.evaluate(xPathExpr, document);
			return evalResult;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getElementContentByXPath(String xPathExpr)
			throws XPathExpressionException {
		Element elementObj = (Element) getNodeByXPath(xPathExpr);
		return elementObj.getTextContent();
	}

	/**
	 * The method is used to get unique element according the single checked attribute
	 * @param xPathExpr
	 * @param checkedAttributeName
	 * @param checkedAttributeValue
	 * @return
	 */
	public Element getUniqueElementByXPath(String xPathExpr,
			String checkedAttributeName, String checkedAttributeValue) {
		NodeList nodeList;
		try {
			nodeList = getNodeListByXPath(xPathExpr);
			int nodeNum = nodeList.getLength();
			if (nodeNum == 1) {
				return (Element) nodeList.item(0);
			} else if (nodeNum > 1) {
				if (checkedAttributeName == null
						|| checkedAttributeValue == null) {
					throw new XMLProcessingException(
							"Couldn't identify the unique element in multiple element list without attribute !");
				}
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					if (element.hasAttribute(checkedAttributeName)
							&& checkedAttributeValue.equals(element
									.getAttribute(checkedAttributeName))) {
						return element;
					}
				}
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * The method is used to get unique element according the multiple checked attributes map
	 * @param xPathExpr
	 * @param attributesMap
	 * @return
	 */
	public Element getUniqueElementByXPath(String xPathExpr,
			HashMap<String,String> attributesMap) {
		NodeList nodeList;
		try {
			nodeList = getNodeListByXPath(xPathExpr);
			int nodeNum = nodeList.getLength();
			if (nodeNum == 1) {
				return (Element) nodeList.item(0);
			} else if (nodeNum > 1) {
				if (attributesMap==null||attributesMap.size()<=0) {
					throw new XMLProcessingException(
							"Couldn't identify the unique element in multiple element list without attribute !");
				}
				int attributeNum=attributesMap.size();
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					Set<Entry<String,String>> entrySet=attributesMap.entrySet();
					Iterator<Entry<String,String>> iter=entrySet.iterator();
					int count=0;
					while(iter.hasNext()){
						Entry<String,String> entry=iter.next();
						if (element.hasAttribute(entry.getKey())
								&& entry.getValue().equals(element
										.getAttribute(entry.getKey()))) {
							count++;
						}
					}
					if(count==attributeNum){
						return element;
					}
				}
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public Attr getAttributeByXPath(String xPathExpr, String attributeName)
			throws XPathExpressionException {
		Element element = (Element) getUniqueElementByXPath(xPathExpr, null, null);
		if (element != null && element.hasAttribute(attributeName)) {
			return element.getAttributeNode(attributeName);
		}
		return null;
	}

	/**
	 * The method is unde
	 * @param xPathExpr
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 */
	public Attr getElementListAttributeByXPath(String xPathExpr,
			String attributeName, String attributeValue) {
		NodeList nodeList;
		try {
			nodeList = getNodeListByXPath(xPathExpr);
			if (nodeList.getLength() > 0) {
				for (int i = 0; i < nodeList.getLength(); i++) {
					Element element = (Element) nodeList.item(i);
					if (element.hasAttribute(attributeName)
							&& attributeValue.equals(element
									.getAttribute(attributeName))) {
						return element.getAttributeNode(attributeName);
					}
				}
			}
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void saveDocument(Document doc, File xmlFile) {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer t;
		try {
			t = tf.newTransformer();
			DOMSource ds = new DOMSource(doc);
			StreamResult sr = new StreamResult(xmlFile);
			t.transform(ds, sr);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Document addElement(String xPathExpr, String newElementName,
			String newElementText) {
		if (newElementName == null || "".equals(newElementName)) {
			throw new XMLProcessingException(
					"The new Element couldn't be set null or '' !");
		}
		Element parentElement = getUniqueElementByXPath(xPathExpr, null, null);
		Element childElement = document.createElement(newElementName);
		childElement.setTextContent(newElementText);
		parentElement.appendChild(childElement);
		return document;
	}

	public Document addElementAttributes(String xPathExpr,
			String newElementName, String newElementText,
			HashMap<String,String> attributesMap) {
		if (newElementName == null || "".equals(newElementName)) {
			throw new XMLProcessingException(
					"The new Element couldn't be set null or '' !");
		}
		Element parentElement = getUniqueElementByXPath(xPathExpr, null, null);
		Element childElement = document.createElement(newElementName);
		childElement.setTextContent(newElementText);
		if(attributesMap!=null&&attributesMap.size()>0){
			Set<Entry<String,String>> entrySet=attributesMap.entrySet();
			Iterator<Entry<String,String>> iter=entrySet.iterator();
			while(iter.hasNext()){
				Entry<String,String> entry=iter.next();
				childElement.setAttribute(entry.getKey(), entry.getValue());
			}
		}
		parentElement.appendChild(childElement);
		return document;
	}

	public Document updateElementContent(String xPathExpr, String value) {
		Element element = getUniqueElementByXPath(xPathExpr, null, null);
		if (element != null) {
			element.setTextContent(value);
		}
		return document;
	}

	
	public Document updateAttributes(Element element,
			HashMap<String,String> attributesMap) {
		if(attributesMap!=null&&attributesMap.size()>0){
			Set<Entry<String,String>> entrySet=attributesMap.entrySet();
			Iterator<Entry<String,String>> iter=entrySet.iterator();
			while(iter.hasNext()){
				Entry<String,String> entry=iter.next();
				element.setAttribute(entry.getKey(), entry.getValue());
			}
		}
		return document;
	}

	
	public Document updateAttributes(String xPathExpr,
			HashMap<String,String> attributesMap) {
		Element element = getUniqueElementByXPath(xPathExpr, null, null);
		if(attributesMap!=null&&attributesMap.size()>0){
			Set<Entry<String,String>> entrySet=attributesMap.entrySet();
			Iterator<Entry<String,String>> iter=entrySet.iterator();
			while(iter.hasNext()){
				Entry<String,String> entry=iter.next();
				element.setAttribute(entry.getKey(), entry.getValue());
			}
		}
		return document;
	}


	/**
	 * The method is used to set the attribute value according to the checked
	 * attribute name/value.
	 * For instance, change the element <property name='query-default-locale' value='en'/> to <property name='query-default-locale' value='zh'/>
	 * @param xPathExpr
	 * @param checkAttributeName
	 * @param checkAttributeValue
	 * @param attributesMap
	 * @return
	 */
	public Document updateElementListAttributes(String xPathExpr,
			String checkAttributeName, String checkAttributeValue,
			HashMap<String,String> attributesMap) {
		Element element = getUniqueElementByXPath(xPathExpr,
				checkAttributeName, checkAttributeValue);
		if(attributesMap!=null&&attributesMap.size()>0){
			Set<Entry<String,String>> entrySet=attributesMap.entrySet();
			Iterator<Entry<String,String>> iter=entrySet.iterator();
			while(iter.hasNext()){
				Entry<String,String> entry=iter.next();
				element.setAttribute(entry.getKey(), entry.getValue());
			}
		}
		return document;
	}

	public Document deleteElement(String xPathExpr, String checkedAttributeName, String checkedAttributeValue) {
		Element element = getUniqueElementByXPath(xPathExpr,
				checkedAttributeName, checkedAttributeValue);
		Element parentElement=(Element) element.getParentNode();
		parentElement.removeChild(element);
		return document;
	}
	
	private void validateXPathExprFormat(String xPathExpr) {
		if (xPathExpr.indexOf("@") >= 0 || xPathExpr.indexOf("=") >= 0) {
			throw new XMLProcessingException(
					"The xPath format is unsupport in XMLProcessing API :( ");
		} else if (xPathExpr.length()-1==xPathExpr.lastIndexOf("/") ) {
			throw new XMLProcessingException(
					"The xPath format is unsupport in XMLProcessing API :( ");
		}
	}

	public static Properties getProperties(String xmlProperty)
			throws IOException {
		// String configFile = xmlProperty;
		if (xmlProperty != null) {
			InputStream is = new BufferedInputStream(new FileInputStream(
					new File(xmlProperty)));
			Properties ps = new Properties();
			ps.load(is);
			return ps;
		}
		return null;
	}

}
