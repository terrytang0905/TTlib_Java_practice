package com.ttsoftware.utilities.xmlprocessing;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

public class RandomXMLGenerator {

	private static int elementRecord = 360000;
	private static int xmlDocNumber = 1;
	private static String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
	private static String path = "C:\\Resource\\Benchmark\\IndexQuery";
	private Random r = new Random();
	private StringBuilder sb = new StringBuilder();

	public void addElement(Element parentElement) {
		Element childElement = parentElement.addElement("element");
		String idText = generateCharacterStr(64);
		childElement.addAttribute("id", idText);
		String nameStr;
		String elementText;
		for (int j = 0; j < 10; j++) {
			char name = (char) (j + 'a');
			nameStr = Character.toString(name);
			elementText = generateCharacterStr(32);
			childElement.addAttribute(nameStr, elementText);
		}
	}

	private String generateCharacterStr(int charSize) {
		sb.delete(0, sb.length());
		for (int i = 0; i < charSize; i++) {
			char symbel = alphabet.charAt(r.nextInt(36));
			sb.append(symbel);
		}
		return sb.toString();
	}

	public void writeDocument(Document document, File xmlFile) {
		try {
			XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(xmlFile));
			xmlWriter.write(document);
			xmlWriter.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void main(String[] args) {
		RandomXMLGenerator rxg = new RandomXMLGenerator();
		String fileName;
		File xmlFile;
		Document xmlDoc;
		Element rootElement;
		System.out.println("Start:" + System.currentTimeMillis());
		for (int k = 0; k < xmlDocNumber; k++) {
			fileName = "xmlDocument_" + k + ".xml";
			xmlFile = new File(path + File.separator + fileName);
			xmlDoc = org.dom4j.DocumentHelper.createDocument();
			rootElement = xmlDoc.addElement("root");
			for (int p = 0; p < elementRecord; p++) {
				rxg.addElement(rootElement);
				if (p % 1000 == 0) {
					rxg.writeDocument(xmlDoc, xmlFile);
					System.out.println("record p:" + p);
				}
			}
			System.out.println("generate XML document " + fileName);
		}
		System.out.println("End:" + System.currentTimeMillis());

	}

}
