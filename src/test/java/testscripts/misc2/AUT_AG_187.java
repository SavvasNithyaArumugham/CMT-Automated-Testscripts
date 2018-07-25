package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_187 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_082()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to convert single file from 'word to xml'");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderDetails1 = dataTable.getData("MyFiles", "CreateChildFolder");
		String folderName1 = dataTable.getData("MyFiles", "BrowseActionName");
		String fileName1 = dataTable.getData("MyFiles", "RelationshipName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String moreSettingsOptName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String XmlfileName = dataTable.getData("MyFiles", "Version");
		
		/*homePageObj.navigateToRepositoryPage();
		if(sitesPage.Checkdocument(folderName)){
			myFiles.openFolder(folderName);
		}
		else{
			myFiles.createFolder(folderDetails);
			myFiles.openFolder(folderName);
		}
		if(sitesPage.Checkdocument(fileName)){
			
		}else{
		myFiles.uploadFile(filePath, fileName);
		}*/
		
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		myFiles.createFolder(folderDetails1);
		sitesPage.clickViewDetails();		
		docDetailsPage.performManageAspectsDocAction();
		docDetailsPage.addAspectsAndApllyChangesToAFile();		
		docDetailsPage.clickOnEditProperties();
		docDetailsPage.clickOnAssociateDocRuleAvail();
		docDetailsPage.attachTopNotchFileInAssociatedDocRule(siteName, folderName, fileName);
		docDetailsPage.clickSaveInEditProperties();
		UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName1);
		myFiles.uploadFile(filePath, fileName1);
		sitesPage.clickOnMoreSetting(fileName1);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName1,
				moreSettingsOptName);
		UIHelper.waitFor(driver);
		if(docLibPg.checkFileIsAvailable(XmlfileName)){
			report.updateTestLog(
					"Check xml File generated",
					"XML file generated successfully",
					Status.PASS);
		}else{
			report.updateTestLog(
					"Check xml File generated",
					"XML file is not generated",
					Status.FAIL);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}