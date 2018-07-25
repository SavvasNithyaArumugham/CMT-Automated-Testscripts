package testscripts.misc3;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_259 extends TestCase {
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_043()
	{
		testParameters.setCurrentTestDescription("To Check whether the Aspect 'INDIA Higher ED' is present in Alfresco under 'Manage Aspects'"
				+"<br>2.To check if the user is able to add india Higher ed aspect to content from manage aspects"
				+"<br>3.To check if the user is able to see the following fields for the india higher ed aspect"
				+"<br>4.To check if the user is able to enter values for the fields under india higher ed aspect and save them"
				+"<br>5.To Check if the user is able to save the aspect without populating values in the fields(non-mandatory fields)");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileName);
		myFiles.openAFile(fileName);
		
		docDetailsPage.performManageAspectsDocAction();
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTestObj.verifyAspectsAvailable();
		
		docDetailsPage.addAspectsAndApllyChangesToAFile();
		
        docDetailsPage.performManageAspectsDocAction();

		docDetailsPageTestObj.verifyAppliedAspects();
		sitesPage.clickOnCancelBtnInManageAspectsPopup();
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		
		docDetailsPageTestObj.verifyMandatoryAttributeInEditProperties();
		docDetailsPage.clickSaveInEditProperties();
		
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		sitesPage.documentdetails(fileName);
		
	//	AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTestObj.verifyDocEditProperties();
	/*	myFiles.openAFile(fileName);*/
		
	  /*  String docPropertiesList = dataTable.getData("Document_Details", "DocProperties");
		String[] SplittedDocPropertiesList = docPropertiesList.split(";");
		String docProperties=SplittedDocPropertiesList[0];
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		String docPropertiesValue = "Test1,Test2,Test3";
		String[] docPropDetails = docProperties.split(",");
		String[] docPropValues = docPropertiesValue.split(",");
		ArrayList<String> docPropertyPassList = new ArrayList<String>();
		ArrayList<String> docPropertyFailList = new ArrayList<String>();
		int index = 0;
		for (String property : docPropDetails) {
			if(docDetailsPage.isFilePropertyAvailable(property, docPropValues[index])){
				docPropertyPassList.add(property);
				index++;
			}else{
				docPropertyFailList.add(property);
			}
		}
		
		if (docPropertyFailList.size() > 0) {
			report.updateTestLog("Verify the Property and value for "
					+ aspectName + "' aspect",
					"<br>" + docPropertyFailList.toString()
							+ " <br>properties are not available",
					Status.FAIL);
		} else {
			report.updateTestLog(
					"Verify the Property and value for " + aspectName
							+ "' aspect",
					"<br><b>Expected Result:</b>All properties should be matched/available"
							+ "<br><b>Actual Result</b>:All properties matched/available<br>"
							+ "Properties Verified are:<br>"
							+ docPropertyPassList.toString(), Status.PASS);
		}
		
		UIHelper.waitFor(driver);
		
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		myFiles.openAFile(fileName);*/
		
	/*	String docProperties2 = SplittedDocPropertiesList[1];
		String docPropertiesValue2 = " , , ";
		String[] docPropDetails2 = docProperties2.split(",");
		String[] docPropValues2 = docPropertiesValue2.split(",");
		ArrayList<String> docPropertyPassList2 = new ArrayList<String>();
		ArrayList<String> docPropertyFailList2 = new ArrayList<String>();
		int index2 = 0;
		for (String property : docPropDetails2) {
			if(docDetailsPage.isFilePropertyAvailable(property, docPropValues2[index2].trim())){
				docPropertyPassList2.add(property);
				index2++;
			}else{
				docPropertyFailList2.add(property);
			}
		}
		
		if (docPropertyFailList2.size() > 0) {
			report.updateTestLog("Verify the Properties having empty values",
					"Property not contains Empty values"+
					"<br><b> Failed Properties : </b>" + docPropertyFailList2.toString(),
					Status.FAIL);
		} else {
			report.updateTestLog("Verify the Properties having empty values",
					"Properties verified successfully"+
					"<br><b> Properties verified : </b>" + docPropertyPassList2.toString(),
					Status.PASS);
		}
		
		UIHelper.waitFor(driver);*/
	}
	
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}

		
		
	}
	
