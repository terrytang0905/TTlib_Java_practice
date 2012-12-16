package com.ttsoftware.utilities.xmlprocessing;

import java.util.HashMap;

import javax.xml.namespace.QName;

/**
 * A xml Element node object for XML processing. 
 * 
 * @author tangz
 * @since 2012
 * @version 0.1
 * 
 */

public class UserElement {

	public enum XMLOperation {
		INSERT, UPDATE, DELETE
	}

	private XMLOperation operationType;
	private String xPath;

	private String elementName;
	private String text;
	private HashMap<QName,String> userAttributesMap = new HashMap<QName,String>();

	public XMLOperation getOperationType() {
		return operationType;
	}

	public void setOperationType(XMLOperation operationType) {
		this.operationType = operationType;
	}

	public String getxPath() {
		return xPath;
	}

	public void setxPath(String xPath) {
		this.xPath = xPath;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	public HashMap<QName, String> getUserAttributesMap() {
		return userAttributesMap;
	}

	public void setUserAttributesMap(HashMap<QName, String> userAttributesMap) {
		this.userAttributesMap = userAttributesMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userAttributesMap == null) ? 0 : userAttributesMap.hashCode());
		result = prime * result
				+ ((elementName == null) ? 0 : elementName.hashCode());
		result = prime * result
				+ ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((xPath == null) ? 0 : xPath.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserElement other = (UserElement) obj;
		if (userAttributesMap == null) {
			if (other.userAttributesMap != null)
				return false;
		} else if (!userAttributesMap.equals(other.userAttributesMap))
			return false;
		if (elementName == null) {
			if (other.elementName != null)
				return false;
		} else if (!elementName.equals(other.elementName))
			return false;
		if (operationType != other.operationType)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (xPath == null) {
			if (other.xPath != null)
				return false;
		} else if (!xPath.equals(other.xPath))
			return false;
		return true;
	}

}
