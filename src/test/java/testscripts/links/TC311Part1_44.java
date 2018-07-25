package testscripts.links;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class TC311Part1_44 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_040()
	{
		testParameters.setCurrentTestDescription("Verify that Contributer is able to Link  to self created Files or Folders and "
				+ "also created by other roles <br/>Part1 :Manages creates sites and invites Contributer,manger creates folders");
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
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		homePage.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		
		AlfrescoSitesPageTest siteTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		//homePage.navigateToSitesTab();'
		siteTestObj.verifyCreatedSite();
		
		String siteName=sitesPage.getCreatedSiteName();	
		
		sitesPage.performInviteUserToSite(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		myFiles.createFolder(folderDetails);
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}