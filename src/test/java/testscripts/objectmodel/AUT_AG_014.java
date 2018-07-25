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
public class AUT_AG_014 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_014()
	{
		testParameters.setCurrentTestDescription("Verify user is able to apply the aspects(Archive, Program Component, Program) in My files/Shared files for uploaded or created assets");
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
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderNames = dataTable.getData("MyFiles", "Version");
		String[] folderNameArray = folderNames.split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNames = dataTable.getData("MyFiles", "FileName");
		String[] fileNameArray = fileNames.split(",");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		String[] aspectNameArray = aspectNames.split(",");
		
		homePage.navigateToMyFilesTab();
		docLibPage.deleteAllFilesAndFolders();
        myFiles.createFolder(folderDetails);
        myFiles.uploadFile(filePath, fileNames);
        
        doAspectFunctionality(folderNameArray[0], aspectNameArray[1], false);
        doAspectFunctionality(folderNameArray[1], aspectNameArray[2], false);
        doAspectFunctionality(fileNameArray[0], aspectNameArray[0], true);
        homePage.navigateToMyFilesTab();
        doAspectFunctionality(fileNameArray[1], aspectNameArray[1], true);
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
		docDetailsPageTestObj.verifyAspectsAvailable(aspectName);
		docDetailsPageObj.addAspectsAndApllyChangesToAFile(aspectName);
		if(isFile){
			docDetailsPageObj.performManageAspectsDocAction();
		}else{
			sitesPage.clickOnMoreSetting(fileOrFolder);
			sitesPage.clickOnMoreOptionLink(fileOrFolder);
		}
		docDetailsPageTestObj.verifyAppliedAspects(aspectName);
		docDetailsPageObj.clickCancelBtnInAspectsPopup();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
