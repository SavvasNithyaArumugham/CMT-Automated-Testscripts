package testscripts.avsmartlink;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.Util;

public class AUT_AG_001dfgfd extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_01() {
		testParameters.setCurrentTestDescription("Verify whether user is able to create smart link object in My files or shared files");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get("C:/Automation/Alfresco/DownloadFiles/Meta_API_Integration_Test_Report/Meta_API_Integration_Test_Report/surefire-report.html");
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		System.out.println("1dzsd");
		 Map<String,String> teststatus = new LinkedHashMap<String, String>();
		 Map<String, ArrayList<String>> testtime = new LinkedHashMap<String, ArrayList<String>>();
		 
		 UIHelper.waitForPageToLoad(driver);
		 UIHelper.waitForPageToLoad(driver);
		 UIHelper.waitFor(driver);
		//UIHelper.waitForVisibilityOfEleByXpath(driver, ".//a[contains(@name,'TC_META-TEST')]");
		
	List<WebElement> ele = driver.findElements(By.xpath(".//a[contains(@name,'TC_META-TEST')]"));
	System.out.println("1");
 for(WebElement value : ele){
	 System.out.println("2");
	 String name = value.getAttribute("name");
	 
	 String status = UIHelper.findAnElementbyXpath(driver, ".//td/a[contains(@name,'TC_META-TEST')]//ancestor::tr//img").getAttribute("src");
	
	 System.out.println(name);
	 System.out.println(status);
	 
	 
	 if(status.contains("success")){
	 
	 teststatus.put(name, "PASS");

	 }else if(status.contains("error")){
		 teststatus.put(name, "FAIL");
	 }else{
		 teststatus.put(name, "FAIL");
	 }
	 
 }
 
 String name = "dhc.xml";
	String xmlFilePath = "C://Automation//Alfresco//DownloadFiles//Meta_API_Integration_Test_Report//Meta_API_Integration_Test_Report//"
			+ name;
 
 System.out.println(teststatus);
 
 DocumentBuilderFactory documentFactory = DocumentBuilderFactory
			.newInstance();

	DocumentBuilder documentBuilder;
	try {
		documentBuilder = documentFactory.newDocumentBuilder();
	

	Document document = documentBuilder.newDocument();

	Element root = document.createElement("testng-results");

	document.appendChild(root);
	
	Element root1a = document.createElement("suite");

	root.appendChild(root1a);
	
	Attr attr = document.createAttribute("duration-ms");

	attr.setValue("");

	root.setAttributeNode(attr);

	Attr attr1 = document.createAttribute("name");

	attr1.setValue("DHCTEST");

	root.setAttributeNode(attr1);

	/*Attr attr2 = document.createAttribute("thread-count");

	attr2.setValue("1");

	root.setAttributeNode(attr2);*/

	// employee element

	Element employee = document.createElement("test");

	root1a.appendChild(employee);

	// set an attribute to staff element

	/*Attr attr3 = document.createAttribute("name");

	attr3.setValue("Test");

	employee.setAttributeNode(attr3);
*/
	// you can also use staff.setAttribute("id", "1") for this

	// firstname element
	Element firstName = document.createElement("class");

	// firstName.appendChild(document.createTextNode("James"));

	employee.appendChild(firstName);
	
	Attr attr3 = document.createAttribute("name");

	attr3.setValue("adTest");

	employee.setAttributeNode(attr3);
	
	for (Map.Entry<String,String> entry : teststatus.entrySet()){
		Element lastname = document.createElement("test-method");
		firstName.appendChild(lastname);
		
		Attr attr4 = document.createAttribute("name");
		attr4.setValue(entry.getKey());
		lastname.setAttributeNode(attr4);
		
		Attr attr5 = document.createAttribute("status");
		attr5.setValue(entry.getValue());
		lastname.setAttributeNode(attr5);
	}
		
		/*//functionalLibrary.loginAsValidUser(signOnPage);
		
		//homePageObj.navigateToMyFilesTab();
		
		String type = dataTable.getData("Home", "DashletName");
		
		myFiles.createcontenttype(type);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, avsmart.messageXpath);
			String getMessage = UIHelper.getTextFromWebElement(driver,
					avsmart.messageXpath);
			
			if(getMessage.equalsIgnoreCase("This action is not possible in My Files")){
				
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link cannot be created in my files. <br><b>Message : <b> " +getMessage, Status.PASS);
			}else{
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link can be created in my files which is not execpted", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link",
					"Smart Link can be created in my files which is not execpted", Status.FAIL);
		}
		
		
		homePageObj.navigateToSharedFilesTab();
		
		myFiles.createcontenttype(type);
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, avsmart.messageXpath);
			String getMessage = UIHelper.getTextFromWebElement(driver,
					avsmart.messageXpath);
			
			if(getMessage.equalsIgnoreCase("This action is not possible in Shared Files area")){
				
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link cannot be created in my files. <br><b>Message : <b> " +getMessage, Status.PASS);
			}else{
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link can be created in my files which is not execpted", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link",
					"Smart Link can be created in my files which is not execpted", Status.FAIL);
		}*/
	
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
		

	@Override
	public void tearDown() {

	}
}
