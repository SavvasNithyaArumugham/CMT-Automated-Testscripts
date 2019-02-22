package testscripts.collections9;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_1798_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Confirm New bucket folder called “Dynamic Content Object” is auto created");
		
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				
		String levelXpath =".//*[contains(@id,'prop_cpnals_dynamicContentType') and @name='prop_cpnals_dynamicContentType']";
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);

		// goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.enterIntoDocumentLibrary();
		
		// Verify Pre defined folders
				collectionPg.getFoldersFromLeftTreeInShareUi();
			
				myFilesTestObj.verifyFolder("Dynamic Content Objects");
				
		
		sitesPage.enterIntoDocumentLibrary();
				
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		UIHelper.waitFor(driver);
			
		collectionPg.clickOnCreateButton();
		collectionPg.clickOnCreateMenuItem("Dynamic Content Object");
		
		UIHelper.waitFor(driver);
		
		UIHelper.selectbyVisibleText(driver,levelXpath,"TOC" );
			
		boolean dynamicXpath = collectionPg.isObjectFieldAvailable("Dynamic Content Type:");
		
		if(dynamicXpath = true) 
		{
			report.updateTestLog("Verify Dynamic Content Type fields and drop down options",	"Dynamic Content Type and drop down options available", Status.PASS);
		}else
		{
			report.updateTestLog("Verify Dynamic Content Type fields and drop down options", "Dynamic Content Type field and drop down options not available", Status.FAIL);
		}
		
		// TOC
		UIHelper.selectbyVisibleText(driver,levelXpath,"TOC" );
		
		UIHelper.waitFor(driver);
		
		boolean folioPrefix = collectionPg.isObjectFieldAvailable("Folio Prefix:");
		boolean folioStyle = collectionPg.isObjectFieldAvailable("Folio Style:");
		boolean folioStart = collectionPg.isObjectFieldAvailable("Folio Start:");
		boolean tocIncludeFrom = collectionPg.isObjectFieldAvailable("TOC Include From:");
		boolean tocIncludeTo = collectionPg.isObjectFieldAvailable("TOC Include To:");
		
	
		if((folioPrefix = true) && (folioStyle= true) &&(folioStart= true) &&(tocIncludeFrom= true) &&(tocIncludeTo= true)) 
		{
			report.updateTestLog("Verify dynamic fields are displayed on selecting TOC for Dynamic Content Type","Fields Folio Prefix \r\n" + 
					"Folio Style: \r\n" + 
					"Folio Start: \r\n" + 
					"TOC Scope: \r\n" + 
					"TOC Include From: \r\n" + 
					"TOC Include To: available", Status.PASS);
		}else
		{
			report.updateTestLog("Verify dropdown values available for the “Dynamic Content Type” property", "TOC - Dynamic Content Type field not available", Status.FAIL);
		}
		
					
		String folioPrefixXpath = ".//*[contains(@id,'prop_cpnals_folioPrefix') and @name='prop_cpnals_folioPrefix']";
		String folioStartXpath = ".//*[contains(@id,'prop_cpnals_folioStart') and @name='prop_cpnals_folioStart']";
		String folioStyleXpath = ".//*[contains(@id,'prop_cpnals_folioStyle') and @name='prop_cpnals_folioStyle']";
		String tocIncFromXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeFrom') and @name='prop_cpnals_tocIncludeFrom']";
		String tocIncToXpath = ".//*[contains(@id,'prop_cpnals_tocIncludeTo') and @name='prop_cpnals_tocIncludeTo']";
		String stdCorrelationTableTypeXpath = ".//*[contains(@id,'cpnals_standardCorrelationTableType') and @name='prop_cpnals_standardCorrelationTableType']";
		String contentObjectAggregationXpath = ".//*[contains(@id,'prop_cpnals_contentObjectAggregationType') and @name='prop_cpnals_contentObjectAggregationType']";
		
		UIHelper.sendKeysToAnElementByXpath(driver,folioPrefixXpath , "testvalue12@  ");
		UIHelper.selectbyVisibleText(driver,folioStyleXpath,"1 - Arabic" );
		UIHelper.sendKeysToAnElementByXpath(driver,folioStartXpath , "1");
		UIHelper.selectbyVisibleText(driver,tocIncFromXpath,"Level 1" );
		UIHelper.selectbyVisibleText(driver,tocIncToXpath,"Level 1" );
		UIHelper.waitFor(driver);
		
		// Standard Correlation Table
		
		UIHelper.selectbyVisibleText(driver,levelXpath,"Standard Correlation Table" );
		
		boolean folioPrefix1 = collectionPg.isObjectFieldAvailable("Folio Prefix:");
		boolean folioStyle1 = collectionPg.isObjectFieldAvailable("Folio Style:");
		boolean folioStart1 = collectionPg.isObjectFieldAvailable("Folio Start:");
		boolean stdCorrelationTableType = collectionPg.isObjectFieldAvailable("Standard Correlation Table Type:");
		
		if((folioPrefix1 = true) && (folioStyle1= true) &&(folioStart1= true) &&(stdCorrelationTableType= true)) 
		{
			report.updateTestLog("Verify dynamic fields are displayed on selecting Standard Correlation Table for Dynamic Content Type","FIELDS- Folio Prefix \r\n" + 
					"Folio Style: \r\n" + 
					"Folio Start: \r\n" + 
					"Standard Correlation Table Type: \r\n", Status.PASS);
		}else
		{
			report.updateTestLog("Verify dropdown values available for the “Dynamic Content Type” property", "Standard Correlation Table - Dynamic Content Type field not available", Status.FAIL);
		}
			
		UIHelper.sendKeysToAnElementByXpath(driver,folioPrefixXpath, "testvalue12@  ");
		UIHelper.selectbyVisibleText(driver,folioStyleXpath,"1 - Arabic" );
		UIHelper.sendKeysToAnElementByXpath(driver,folioStartXpath, "1");
		UIHelper.selectbyVisibleText(driver,stdCorrelationTableTypeXpath,"Forward" );
		UIHelper.waitFor(driver);
		
		// Content Object Aggregation
		
		UIHelper.selectbyVisibleText(driver,levelXpath,"Content Object Aggregation" );
		
		boolean folioPrefix2 = collectionPg.isObjectFieldAvailable("Folio Prefix:");
		boolean folioStyle2 = collectionPg.isObjectFieldAvailable("Folio Style:");
		boolean folioStart2 = collectionPg.isObjectFieldAvailable("Folio Start:");
		boolean contentObjAggregationType = collectionPg.isObjectFieldAvailable("Content Object Aggregation Type:");
		
		if((folioPrefix2 = true) && (folioStyle2= true) &&(folioStart2= true) &&(contentObjAggregationType= true)) 
		{
			report.updateTestLog("Verify dynamic fields are displayed on selecting Content Object Aggregation for Dynamic Content Type","FIELDS- Folio Prefix \r\n" + 
					"Folio Style: \r\n" + 
					"Folio Start: \r\n" + 
					"Content Object Aggregation Type: \r\n", Status.PASS);
		}else
		{
			report.updateTestLog("Verify dropdown values available for the “Dynamic Content Type” property", "Content Object Aggregation  - Dynamic Content Type field not available", Status.FAIL);
		}
		
		
		UIHelper.sendKeysToAnElementByXpath(driver,folioPrefixXpath , "testvalue12@  ");
		UIHelper.selectbyVisibleText(driver,folioStyleXpath,"1 - Arabic" );
		UIHelper.sendKeysToAnElementByXpath(driver,folioStartXpath , "1");
		UIHelper.selectbyVisibleText(driver,contentObjectAggregationXpath,"Additional Answers" );
		UIHelper.waitFor(driver);
		
		//Index
		
		UIHelper.selectbyVisibleText(driver,levelXpath,"Index" );
		
		boolean folioPrefix3 = collectionPg.isObjectFieldAvailable("Folio Prefix:");
		boolean folioStyle3 = collectionPg.isObjectFieldAvailable("Folio Style:");
		boolean folioStart3 = collectionPg.isObjectFieldAvailable("Folio Start:");
				
		if((folioPrefix3 = true) && (folioStyle3= true) &&(folioStart3= true)) 
		{
			report.updateTestLog("Verify dynamic fields are displayed on selecting Index for Dynamic Content Type","FIELDS- Folio Prefix \r\n" + 
					"Folio Style: \r\n" + 
					"Folio Start: \r\n", Status.PASS);
		}else
		{
			report.updateTestLog("Verify dropdown values available for the “Dynamic Content Type” property", "Index - Dynamic Content Type field not available", Status.FAIL);
		}
		
	
		UIHelper.sendKeysToAnElementByXpath(driver,folioPrefixXpath, "testvalue12@  ");
		UIHelper.selectbyVisibleText(driver,folioStyleXpath,"1 - Arabic" );
		UIHelper.sendKeysToAnElementByXpath(driver,folioStartXpath , "1");
		UIHelper.waitFor(driver);
			
		// Check if error message is thrown if alphabet and special char is given for folio start field 
		
		
		UIHelper.sendKeysToAnElementByXpath(driver,folioStartXpath , "aba&^@");
		WebElement folioStartElement = driver.findElement(By.xpath(folioStartXpath));
		UIHelper.highlightElement(driver, folioStartXpath);
		if (folioStartElement.getAttribute("title").equals("Value is not a number.")) {
		report.updateTestLog("Error message for folio start if given alphabet as input","Error message is displayed as expected",Status.PASS);
		}else {
		report.updateTestLog("Error message for folio start if given alphabet as input","error message is correct not correct",Status.FAIL);
		}
		UIHelper.waitFor(driver);
		
	}
		
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
