package testscripts.links;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC16 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC16()
	{
		testParameters.setCurrentTestDescription("1). Verify user is able to find 'Link to' option from selected items dropdown."
				+ "2). Verify user is able to find 'Link to' option in Document Action.");
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);


		String siteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		functionalLibrary.loginAsValidUser(signOnPage);		
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);		
		sitesPage.enterIntoDocumentLibrary();		
		
		docLibPg.deleteAllFilesAndFolders();

        myFiles.createFolder(folderDetails);		
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		sitesPage.enterIntoDocumentLibrary();		
		myFilesTestObj.verifySelectedItemHaveLinkToOpt();
		
		
//		Verify user is able to find Link to option in Document Action.		
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
	
        myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		sitesPage.enterIntoDocumentLibrary();
			
		sitesPage.clickViewDetails();
		
		myFilesTestObj.verifyDocActionMenuHaveLinkToOpt();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}