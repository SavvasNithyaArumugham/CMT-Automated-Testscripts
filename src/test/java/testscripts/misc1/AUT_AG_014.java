package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_014 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_014()
	{
		testParameters.setCurrentTestDescription("To check if the user sees a folder link in the breadcrumb when a relationship is added to an asset and not to a folder");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String addRelationSite = dataTable.getData("Sites", "SiteToSelect");
		sitesPage.openSiteFromRecentSites(addRelationSite);
		
		homePageObj.navigateToSitesTab();
		String siteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyUploadedFile(fileName,"");
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettingsOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOpt);
		
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.addRelationshipBtwAssetAndSite(relationshipName, fileName, addRelationSite);
		
		myFiles.openUploadedOrCreatedFile(fileName,"");
		
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
		docDetailsPageTest.verifyAddedRelationshipData(addRelationSite.toLowerCase());
		
		if(docDetailsPage.openAndVerifyRelationshipSite()){
			report.updateTestLog("Navigate to Target site Dashdoard",
					"Navigated successfully"
							+ "</br><b>Target Site Name:</b> " + addRelationSite,
					Status.PASS);
		}else{
			report.updateTestLog("Navigate to Target site Dashdoard",
					"Navigation Failed"
							+ "</br><b>Target Site Name:</b> " + addRelationSite,
					Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}