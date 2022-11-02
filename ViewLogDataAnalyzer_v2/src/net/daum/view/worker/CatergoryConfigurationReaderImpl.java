package net.daum.view.worker;

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

import net.daum.view.model.ViewCategory;
import net.daum.view.model.ViewCategoryTag;

public class CatergoryConfigurationReaderImpl implements CatergoryConfigurationReader{

	private DocumentBuilderFactory documentBuilderFactory;
	private DocumentBuilder documentBuilder;
	private Document document;
	private NodeList listOfCategories;
	private Node categoryNode;
	private Element categoryElement;
	private NodeList firstCategoryName;
	private Element firstCategoryNameElement;
	private NodeList textCategoryName;
	private NodeList tabList;
	private Node tabNode;
	private Element tabElement;
	private NodeList parameterNode;
	private Node paramNode;
	private Element paramElement;
	private ViewCategoryTag viewCategoryTag;
	private String categoryName = null;

	private void declareVariables(String fileName)
			throws ParserConfigurationException, SAXException, IOException {
		documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilder = documentBuilderFactory.newDocumentBuilder();
		document = documentBuilder.parse (new File(System.getenv("HADOOP_HOME") + "/conf/" + fileName));
		document.getDocumentElement ().normalize ();
		viewCategoryTag = new ViewCategoryTag();
	}
	
	public boolean readCategoryConfiguration(ViewCategory viewCategory, String fileName, String category){  
		return read(viewCategory, fileName, category);
	}

	private boolean read(ViewCategory viewCategory, String fileName, String category) {
		try {
            declareVariables(fileName);
            return readConfigurationValue(viewCategory, category);
            }catch (Exception e) {
            	e.printStackTrace();
            }
		return false;
	}

	private boolean readConfigurationValue(ViewCategory viewCategory, String category) {
		listOfCategories = document.getElementsByTagName(viewCategoryTag.getTagCategory());
		for(int s=0; s<listOfCategories.getLength() ; s++){
		    getCategory(viewCategory, s);
			if(isEquals(category)){
				tabList = categoryElement.getElementsByTagName(viewCategoryTag.getTagTab());
	            for(int count = 0; count < tabList.getLength(); count++){
	                    getTabNameAndDirectory(viewCategory, count);
						getParameter(viewCategory, count);
            	}
	            return true;
	    	}
		}
		return false;
	}

	private void getCategory(ViewCategory viewCategory, int s) {
		categoryNode = listOfCategories.item(s);
		categoryElement = (Element)categoryNode;
		categoryName = getChildNode(categoryElement, viewCategoryTag.getTagCategoryName());
		viewCategory.getCategoryNames().add(categoryName);
	}

	private void getTabNameAndDirectory(ViewCategory viewCategory, int count) {
		tabNode = tabList.item(count);
		tabElement = (Element)tabNode;
		viewCategory.getTabNames().add(getChildNode(tabElement, viewCategoryTag.getTagTabName()));
		viewCategory.getDirectories().add(getChildNode(tabElement, viewCategoryTag.getTagDirectory()));
	}

	private void getParameter(ViewCategory viewCategory, int count) {
		parameterNode = tabElement.getElementsByTagName(viewCategoryTag.getTagParameter());
		for(int c = 0; c < parameterNode.getLength(); c++){
			getSetParameterValue(viewCategory, c);
		}
		if(isLengthZero()){
	        setParameterValue(viewCategory);
		}
	}

	private void setParameterValue(ViewCategory viewCategory) {
		viewCategory.getParameterNames().add("");
		viewCategory.getParameterValues().add("");
	}

	private void getSetParameterValue(ViewCategory viewCategory, int c) {
		paramNode = parameterNode.item(c);
		paramElement = (Element)paramNode;
		viewCategory.getParameterNames().add(getChildNode(paramElement, viewCategoryTag.getTagParamName()));
		viewCategory.getParameterValues().add(getChildNode(paramElement, viewCategoryTag.getTagParamValue()));
	}

	private String getChildNode(Element element, String tag) {
		firstCategoryName = element.getElementsByTagName(tag);
        firstCategoryNameElement = (Element)firstCategoryName.item(0);
        if(isNotNull()){
	        textCategoryName = firstCategoryNameElement.getChildNodes();
	        return ((Node)textCategoryName.item(0)).getNodeValue().trim();
        }
		return null;
	}

	private boolean isEquals(String category) {
		return categoryName.equals(category);
	}
	
	private boolean isLengthZero() {
		return parameterNode.getLength() == 0;
	}
	
	private boolean isNotNull() {
		return firstCategoryNameElement != null;
	}
}		
