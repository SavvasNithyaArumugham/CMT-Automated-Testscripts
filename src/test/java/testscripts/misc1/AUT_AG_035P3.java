package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
public class AUT_AG_035P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_032()
	{
		testParameters.setCurrentTestDescription("Verify if coordinator is able to become owner of the folder created by collaborator by clicking 'Become Owner' in View details to have full ownership rights of the folder<br> - Part3:Check the 'Become Owner' functionality");
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
	AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
			scriptHelper);
	AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	
	String siteName = sitesPage.getCreatedSiteName();

	sitesPage.siteFinder(siteName);

	sitesPage.enterIntoDocumentLibrary();
	
	String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
	myFiles.createFolder(folderDetails);
	
	myFilesTestObj.verifyCreatedFolder(folderDetails);
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}