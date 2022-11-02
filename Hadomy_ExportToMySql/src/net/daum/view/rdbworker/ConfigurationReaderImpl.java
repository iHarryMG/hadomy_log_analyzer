package net.daum.view.rdbworker;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ConfigurationReaderImpl implements ConfigurationReader {

	private String configurationValue = null;
	private File file;
	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private NodeList nodeList;
	private Node nameNode;
	private Element nameElement;
	private NodeList propertyNameList;
	private Element propertyNameElement;
	private NodeList propertyNameChild;
	private NodeList propertyValueList;
	private Element propertyValueElement;
	private NodeList propertyValueChild;
	
	@Override
	public String readConfiguration(String fileName, String varName) {
		return read(fileName, varName);
	}

	private void declareVariables(String fileName) throws ParserConfigurationException,
	SAXException, IOException {
		file = new File(System.getenv("HADOMY_HOME") + "/conf/" + fileName);
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse(file);
		document.getDocumentElement().normalize();
		nodeList = document.getElementsByTagName("property");
	}

	private String read(String fileName, String varName) {
		try{
			declareVariables(fileName);
			getConfigurationValue(varName);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return configurationValue;
	}
	
	private void getConfigurationValue(String varName) {
		for(int s = 0; s < nodeList.getLength(); s++){
			nameNode = nodeList.item(s);
			if(isElementNode()){
				getPropertyName();
				if(isEquals(varName)){
					getPropertyValue();
				}
			}
		}
	}

	private void getPropertyName() {
		nameElement = (Element)nameNode;
		propertyNameList = nameElement.getElementsByTagName("name");
		propertyNameElement = (Element)propertyNameList.item(0);
		propertyNameChild = propertyNameElement.getChildNodes();
	}
	
	private void getPropertyValue() {
		propertyValueList = nameElement.getElementsByTagName("value");
		propertyValueElement = (Element)propertyValueList.item(0);
		propertyValueChild = propertyValueElement.getChildNodes();
		configurationValue = ((Node)propertyValueChild.item(0)).getNodeValue();
	}
	
	private boolean isEquals(String varName) {
		return varName.equals(((Node)propertyNameChild.item(0)).getNodeValue());
	}
	
	private boolean isElementNode() {
		return nameNode.getNodeType() == Node.ELEMENT_NODE;
	}
}
