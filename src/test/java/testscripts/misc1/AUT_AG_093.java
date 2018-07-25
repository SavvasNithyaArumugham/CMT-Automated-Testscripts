package testscripts.misc1;

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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_093 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_083()
	{
		testParameters.setCurrentTestDescription("Verify that user should not able to see site in Copy to pop up in which user is not a member");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTestObj = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		ArrayList<String> createdSitesNameList = sitesPage.getSitesFromMySites("");
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.navigateToSitesTab();
		ArrayList<String> recentSiteNamesList = sitesPage.getRecentSites();
		
		for(String recentSiteName:recentSiteNamesList)
		{
			createdSitesNameList.add(recentSiteName);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			sitesPage.clickOnMoreSetting(fileName);
			
			String docActionVal = dataTable.getData("MyFiles", "MoreSettingsOption");
			
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
			docLibPgTestObj.verifyRecentSitesInCopyToOrMoveToPopup(createdSitesNameList, docActionVal);
			
			docLibPgTestObj.verifyFavoriteSitesInCopyToOrMoveToPopup(createdSitesNameList, docActionVal);
			
			docLibPgTestObj.verifyAllSitesInCopyToOrMoveToPopup(createdSitesNameList, docActionVal);
			
			//docLibPgTestObj.verifyEnabledRepositorySitesInCopyToOrMoveToPopup(createdSitesNameList, docActionVal);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
