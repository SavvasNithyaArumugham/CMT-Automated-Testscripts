package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_046 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_037()
	{
		testParameters.setCurrentTestDescription("Verify that there should be no Bulk option present on the Document Library page");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPagetest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String linkName = dataTable.getData("General_Data", "BulkJobType");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		
		docLibPagetest.verifyoptioninHeaderbar(linkName);
		
		if(!sitesPage.Checkdocument(fileName)){
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPagetest.verifyBrowseActionopt(browseActionName, fileName);
		
		homePageObj.navigateToMyFilesTab();
		
		docLibPagetest.verifyoptioninHeaderbar(linkName);
		
		if(!sitesPage.Checkdocument(fileName)){
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPagetest.verifyBrowseActionopt(browseActionName, fileName);
		
		homePageObj.navigateToSharedFilesTab();
		docLibPagetest.verifyoptioninHeaderbar(linkName);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			if(myFilesTestObj.validateCreatedFolders(folderName))
			{
				myFiles.openCreatedFolder(folderName);
			}
			else
			{
				myFiles.createFolder(folderDetails);
				myFiles.openCreatedFolder(folderName);
			}
			
			if(!sitesPage.Checkdocument(fileName)){
				myFiles.uploadFileInMyFilesPage(filePath, fileName);
			}
			
			sitesPage.clickOnMoreSetting(fileName);
			docLibPagetest.verifyBrowseActionopt(browseActionName, fileName);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}