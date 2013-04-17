package com.ttsoftware.utilities.xmlprocessing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class StAXRandomXMLGenerator {

	private static int elementRecord = 360000;
	private static int xmlDocNumber = 1;
	private static String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
	private static String path = "C:\\Resource\\Benchmark\\IndexQuery";
	private Random r = new Random();
	private StringBuilder sb = new StringBuilder();

	public void addElement(XMLStreamWriter xmlsw) throws XMLStreamException {
		xmlsw.writeStartElement("element");
		String idText = generateCharacterStr(64);
		xmlsw.writeAttribute("id", idText);
		String nameStr;
		String elementText;
		for (int j = 0; j < 10; j++) {
			char name = (char) (j + 'a');
			nameStr = Character.toString(name);
			elementText = generateCharacterStr(32);
			xmlsw.writeAttribute(nameStr, elementText);
		}
		xmlsw.writeEndElement();
	}

	private String generateCharacterStr(int charSize) {
		sb.delete(0, sb.length());
		for (int i = 0; i < charSize; i++) {
			char symbel = alphabet.charAt(r.nextInt(36));
			sb.append(symbel);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		StAXRandomXMLGenerator rxg = new StAXRandomXMLGenerator();
		String fileName;
		File xmlFile;
		FileWriter fileWriter;
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		System.out.println("Start:" + System.currentTimeMillis());
		try {
			for (int k = 0; k < xmlDocNumber; k++) {
				fileName = "xmlDocument_" + k + ".xml";
				xmlFile = new File(path + File.separator + fileName);
				fileWriter = new FileWriter(xmlFile);

				XMLStreamWriter xmlsw = xof.createXMLStreamWriter(fileWriter);
				xmlsw.writeStartDocument("utf-8", "1.0");
				xmlsw.writeStartElement("root");
				for (int p = 0; p < elementRecord; p++) {
					rxg.addElement(xmlsw);
					if (p % 1000 == 0) {
						xmlsw.flush();
						System.out.println("record p:" + p);
					}
				}
				xmlsw.writeEndElement();
				xmlsw.flush();
				xmlsw.close();
				System.out.println("generate XML document " + fileName);
			}
			System.out.println("End:" + System.currentTimeMillis());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
