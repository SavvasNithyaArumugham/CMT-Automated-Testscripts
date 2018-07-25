package testscripts.links;

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
public class AUT_AG_065P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_017()
	{
		testParameters.setCurrentTestDescription("Verify that 'Link to' option is visible under 'Selected item' drop down with collaborator role on selecting a file - Open site with collaborator role and create file in Document Library and invite other user to site");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
			
		String siteName=sitesPage.getCreatedSiteName();
		
		sitesPage.performInviteUserToSite(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		myFiles.createFile(fileDetails);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(folderDetails);
		
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}