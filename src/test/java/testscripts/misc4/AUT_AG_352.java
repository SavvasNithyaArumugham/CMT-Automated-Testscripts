package testscripts.misc4;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_352 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_047()
	{
		testParameters.setCurrentTestDescription("To verify user is able to upload multiple file");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		
        sitesPage.openSiteFromRecentSites(siteName);

        sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.deleteCreatedFolder(folderDetails);
		
        myFiles.createFolder(folderDetails);
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);
			
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
			myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}