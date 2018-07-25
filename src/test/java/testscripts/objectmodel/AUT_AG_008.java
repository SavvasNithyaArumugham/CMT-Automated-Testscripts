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
public class AUT_AG_008 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_008()
	{
		testParameters.setCurrentTestDescription("Verfiy user can apply more than one aspect to selected folder and file");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
        myFiles.createFolder(folderDetails);
        myFiles.uploadFile(filePath, fileName);
        
        doAspectFunctionality(folderName, aspectNames, false);
        doAspectFunctionality(fileName, aspectNames, true);
	}
	
	private void doAspectFunctionality(String fileOrFolder, String aspectName, boolean isFile){
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		if(isFile){
			myFiles.openAFile(fileOrFolder);
			docDetailsPageObj.performManageAspectsDocAction();
		}else{
			sitesPage.clickOnMoreSetting(fileOrFolder);
			sitesPage.clickOnMoreOptionLink(fileOrFolder);
		}
		docDetailsPageObj.addAspectsAndApllyChangesToAFile(aspectName);
		if(isFile){
			docDetailsPageObj.performManageAspectsDocAction();
		}else{
			sitesPage.clickOnMoreSetting(fileOrFolder);
			sitesPage.clickOnMoreOptionLink(fileOrFolder);
		}
		
		String[] aspectNameArray = aspectName.split(",");
		for (String aspect : aspectNameArray) {
			docDetailsPageTestObj.verifyAppliedAspects(aspect);
		}
		
		docDetailsPageObj.clickCancelBtnInAspectsPopup();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
