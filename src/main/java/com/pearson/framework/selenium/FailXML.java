package com.pearson.framework.selenium;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;

import com.gargoylesoftware.htmlunit.html.xpath.LowerCaseFunction;

import com.pearson.framework.FrameworkParameters;
import com.pearson.framework.Util;

public class FailXML {
	public FrameworkParameters frameworkParameters = FrameworkParameters
			.getInstance();

	public void createXML(ArrayList<String> item, String suitename) {
		try {
			String name = System.getProperty("suite.fileName");
			String xmlFilePath = this.frameworkParameters.getRelativePath()
					+ Util.getFileSeparator()
					+ "src\\test\\resources\\fail\\fail_"
					+ name;

			DocumentBuilderFactory documentFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder documentBuilder = documentFactory
					.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			Element root = document.createElement("suite");

			document.appendChild(root);

			Attr attr = document.createAttribute("name");

			attr.setValue("Alfresco_Automation_Failure_Suite");

			root.setAttributeNode(attr);

			Attr attr1 = document.createAttribute("parallel");

			attr1.setValue("classes");

			root.setAttributeNode(attr1);

			Attr attr2 = document.createAttribute("thread-count");

			attr2.setValue("1");

			root.setAttributeNode(attr2);

			// employee element

			Element employee = document.createElement("test");

			root.appendChild(employee);

			// set an attribute to staff element

			Attr attr3 = document.createAttribute("name");

			attr3.setValue("Test");

			employee.setAttributeNode(attr3);

			// you can also use staff.setAttribute("id", "1") for this

			// firstname element
			Element firstName = document.createElement("classes");

			// firstName.appendChild(document.createTextNode("James"));

			employee.appendChild(firstName);

			// lastname element

			// Element lastname = rootNode.removeChild(sdf);

			for (String input : item) {
				Element lastname = document.createElement("class");
				firstName.appendChild(lastname);
				Attr attr4 = document.createAttribute("name");

				String value = "testscripts." + suitename.toLowerCase() + "."
						+ input;
				attr4.setValue(value);

				lastname.setAttributeNode(attr4);
			}

			// create the xml file

			// transform the DOM Object to an XML File

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();

			Transformer transformer = transformerFactory.newTransformer();

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource domSource = new DOMSource(document);

			// If you use

			StreamResult streamResult = new StreamResult(new File(xmlFilePath));

			// the output will be pushed to the standard output ...

			// You can use that for debugging

			transformer.transform(domSource, streamResult);

		} catch (ParserConfigurationException pce) {

			pce.printStackTrace();

		} catch (TransformerException tfe) {

			tfe.printStackTrace();

		}
	}
	
	public void modifyFailXML(ArrayList<String> item, String suitename) {
		try {

			String filepath =  this.frameworkParameters.getRelativePath()
					+ Util.getFileSeparator()
					+ "src\\test\\resources\\suite_files\\testng_fail.xml";
			
			File file = new File(filepath);
			if(file.exists()){

				
			}else{
				DocumentBuilderFactory documentFactory = DocumentBuilderFactory
						.newInstance();

				DocumentBuilder documentBuilder = documentFactory
						.newDocumentBuilder();

				Document document = documentBuilder.newDocument();

				Element root = document.createElement("suite");

				document.appendChild(root);

				Attr attr = document.createAttribute("name");

				attr.setValue("Alfresco_Automation_Failure_Suite");

				root.setAttributeNode(attr);

				Attr attr1 = document.createAttribute("parallel");

				attr1.setValue("classes");

				root.setAttributeNode(attr1);

				Attr attr2 = document.createAttribute("thread-count");

				attr2.setValue("1");

				root.setAttributeNode(attr2);

				// employee element

				Element employee = document.createElement("test");

				root.appendChild(employee);

				// set an attribute to staff element

				Attr attr3 = document.createAttribute("name");

				attr3.setValue("Test");

				employee.setAttributeNode(attr3);
				
				Element firstName = document.createElement("classes");

				employee.appendChild(firstName);
				
				TransformerFactory transformerFactory = TransformerFactory
						.newInstance();

				Transformer transformer = transformerFactory.newTransformer();

				DOMSource domSource = new DOMSource(document);

				// If you use

				StreamResult streamResult = new StreamResult(new File(filepath));
				transformer.transform(domSource, streamResult);
			}
			
			
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);

			Node classes = doc.getElementsByTagName("classes").item(0);

			

			for (String input : item) {
				
				String value = "testscripts." + suitename.toLowerCase() + "."
						+ input;
				Element add = doc.createElement("class");
				//	add.appendChild(doc.createAttribute("name"));
					
					classes.appendChild(add);
					Attr attr4 = doc.createAttribute("name");

					attr4.setValue(value);

					add.setAttributeNode(attr4);
			}
			
			
			// append a new node to staff
			

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source, result);

		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (TransformerException tfe) {
			tfe.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		}
}
