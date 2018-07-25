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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_037P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_034()
	{
		testParameters.setCurrentTestDescription("Verify if coordinator is able to become owner of the folder created by sitemanager by clicking 'Become Owner' in View details to have full ownership rights of the folder<br> - Part2: Login with user and create folder");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(
				scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		String folderDet = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(folderDet);
		
		myFilesTestObj.verifyCreatedFolder(folderDet);
		
		
		String docActionName = dataTable.getData("Document_Details", "DocumentActionName");
		String ownerName = dataTable.getData("Document_Details", "OwnerName");
		String propertyValues = dataTable.getData("Document_Details", "DocProperties");
		
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
			UIHelper.waitFor(driver);
			AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
					scriptHelper);
			docDetailsPageTestObj.verifyPropertiesInDocDetailPage(propertyValues);
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}