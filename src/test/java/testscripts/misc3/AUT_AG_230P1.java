package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_230P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_020()
	{
		testParameters.setCurrentTestDescription("Verify that user with restricted permission can only download & View the file in browser and canot perform below operations:"
				+"<br>- Edit Offline"
				+"<br>- Edit Properties"
				+"<br>- Move"
				+"<br>- Delete"
				+"<br> Part1: Open site with consumer role and create file in Document Library and invite other user to site");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSiteMembersPage siteMemPg = new AlfrescoSiteMembersPage(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		String site = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(site, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		
		
		sitesPage.performInviteUserToSite(siteName);
		
		
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		myFiles.createFile(fileDetails);
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}