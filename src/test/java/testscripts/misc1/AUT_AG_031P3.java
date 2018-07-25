package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_031P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_031()
	{
		testParameters.setCurrentTestDescription("Verify if sitemanager is able to become owner of the folder created by collaborator by clicking 'Become owner' in View details to have full ownership rights of the folder<br> - Part3:Check the 'Become Owner' functionality");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);

		String siteName = sitesPage.getCreatedSiteName();
		
		homePageObj.navigateToSitesTab();

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String docActionName = dataTable.getData("Document_Details", "DocumentActionName");
		String ownerName = dataTable.getData("Document_Details", "OwnerName");
		String propertyValues = dataTable.getData("Document_Details", "DocProperties");
		String propertyValues1 = dataTable.getData("Document_Details", "AspectName");
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(
				scriptHelper);
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnViewDetails(folderName);
			
			docDetailsPageTest.verifyAttributesInPropertySecForNegativeCase(ownerName, "Owner");
			
			docDetailsPageTest.verifyDocAction(docActionName);
			
			docDetailsPage.commonMethodForPerformDocAction(docActionName);
			
			docDetailsPage.clickOnOkBtnInBecomeOwnerPopup();
			
			appSearchPg.getMetavalue("Owner",
					ownerName);
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
			
			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();
			
			docDetailsPage.enterDataAndSaveIntoEditProperties();
			
			sitesPage.clickOnViewDetails(folderName);
		
			docDetailsPageTestObj.verifyPropertiesInDocDetailPage(propertyValues);
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
		}
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.openUploadedOrCreatedFile(fileName,"");
		
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
			
		docDetailsPageTest.verifyAttributesInPropertySecForNegativeCase(ownerName, "Owner");
		
		docDetailsPageTest.verifyDocAction(docActionName);
		
		docDetailsPage.commonMethodForPerformDocAction(docActionName);
		
		docDetailsPage.clickOnOkBtnInBecomeOwnerPopup();
		
		appSearchPg.getMetavalue("Owner",
				ownerName);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPage.clickAllProperties();
		
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		//sitesPage.clickOnViewDetails(fileName);
		myFiles.openUploadedOrCreatedFile(fileName,"");
	
		docDetailsPageTestObj.verifyPropertiesInDocDetailPage(propertyValues);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}