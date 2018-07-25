package testscripts.objectmodel;

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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_018P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_018()
	{
		testParameters.setCurrentTestDescription("1.Verify Manage Aspect is hidden for a file created by others -Consumer<br>"
				+"2.Verify Manage Aspect is hidden for a folder created by others -Reviewer<br>"
				+"Part 1:: Create site and add user with  Role");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
	
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
			
		String siteName=sitesPage.getCreatedSiteName();
		
		sitesPage.performInviteUserToSite(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		myFiles.createFolder(folderDetails);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
