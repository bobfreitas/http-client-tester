package com.freitas.test;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class XMLParser {

	
	public Document parse(String xml) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(xml));
			doc = dBuilder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	
	public String getModelName(Document doc) {
		Element rootEle = doc.getDocumentElement();
		String modelName = rootEle.getElementsByTagName("name").item(0).getTextContent();
		return modelName;
	}
	
	
	
}
