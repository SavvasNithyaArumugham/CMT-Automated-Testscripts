package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_028 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_021()
	{
		testParameters.setCurrentTestDescription("1) ALFDEPLOY-3784_Verify that Alfresco is storing user information While trying to republish the folder which caontains zip file in it for EPS folder type.<br>");	
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
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
			
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] fileNames = fileName.split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderName = dataTable.getData("MyFiles", "Version");
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String Option = dataTable.getData("MyFiles", "MoreSettingsOption");		
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
			
		if (sitesPage.Checkdocument(folderName)) {
			myFiles.openCreatedFolder(folderName);
		} else {
			myFiles.createFolder(folderDetails);
			myFiles.openCreatedFolder(folderName);
		}

		if (sitesPage.Checkdocument(fileName)) {
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, Option);
			docLibPg.clickDeletePopup();
		}
		testcase1(fileDetails, folderName, fileNames[0]);
		sitesPage.clickOnCancelBtnInEditPropWindow();
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		testcsae2(filePath, fileNames[1], folderName);
	}
	
	private void testcase1(String fileDetails, String folderName, String fileName){
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoEPSPageTest epsTest = new AlfrescoEPSPageTest(scriptHelper);
		String moreSettingsOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");
		myFiles.createFile(fileDetails);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(folderName);
		sitesPage.clickOnMoreSetting(fileName);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);		
	//	docLibPg.clickPublishPopup();
		UIHelper.waitForPageToLoad(driver);	
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);	
		UIHelper.waitFor(driver);
		epsTest.verifyFilePublish(fileName,"published");
		sitesPage.clickOnEditProperties(fileName);
		sitesPage.checkPublishedURLfield();
		
	}
	
	private void testcsae2(String filePath, String fileName, String folderName){
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		String moreSettingsOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");
		AlfrescoEPSPageTest epsTest = new AlfrescoEPSPageTest(scriptHelper);
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(folderName);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptName);		
		//docLibPg.clickPublishPopup();
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);	
		UIHelper.waitFor(driver);
		epsTest.verifyFilePublish(fileName,"published");
		sitesPage.commonMethodForPerformBrowseOption(folderName, "Edit Properties");	
		sitesPage.checkFolderPublishURLfield();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}