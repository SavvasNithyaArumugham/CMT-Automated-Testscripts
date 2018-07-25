package testscripts.lifecycle;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_017 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_017()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to revert the version of the lifecycle attached files");
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
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption);
	
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);
		
		String fileNameUploadNewVersion = dataTable.getData("Document_Details", "FileName");
		String filePathUploadNewVersion = dataTable.getData("Document_Details", "FilePath");
		String versionDetails = dataTable.getData("Document_Details",
				"Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		
		docLibPg.uploadNewVersionFileInDocLibPage(fileNameUploadNewVersion,
				filePathUploadNewVersion, versionDetails, comments);
		
		myFiles.openUploadedOrCreatedFile(fileNameUploadNewVersion, "");
		
		String docActionVal = dataTable.getData("Document_Details", "DocumentActionName");
		docDetailsPageTest.verifyDocAction(docActionVal);
	
		docDetailsPg.commonMethodForPerformDocAction(docActionVal);
	
		String attachLifeCycleDropdownVal = dataTable.getData("Document_Details", "LifeCycleDropdownValue");
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
	
		String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
		docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
		
		docDetailsPageTest.verifyVersionHistoryHeader();
		
		String expectedOldVersionNoForFile = dataTable.getData("MyFiles", "Version");
		docDetailsPageTest.verifyOlderVersionForFile(expectedOldVersionNoForFile.replace("'", ""));
		
		docDetailsPg.performReverVersionForFile();
	
		String expectedRevertedVersionNoForFile = dataTable.getData("Document_Details", "RevertedFileVersionNo");
		docDetailsPageTest.verifyRevertedVersionNoForFile(expectedRevertedVersionNoForFile);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}