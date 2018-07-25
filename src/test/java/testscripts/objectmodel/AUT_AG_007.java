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
public class AUT_AG_007 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_007()
	{
		testParameters.setCurrentTestDescription("Verify user is able to do the functionalities of 'Archive Aspect' aspect for selected folder/file");
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
		String folderName = dataTable.getData("MyFiles", "Version");
		
		homePage.navigateToMyFilesTab();
		docLibPage.deleteAllFilesAndFolders();
        myFiles.createFolder(folderDetails);
        
        doAspectFunctionality(folderName, false, false, false);
	}
	
	private void doAspectFunctionality(String fileOrFolder, boolean isMandatoryCheckRequired, 
			boolean isDeleteAspectRequired, boolean isFile){
		
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
		docDetailsPageTestObj.verifyAspectsAvailable();
		docDetailsPageObj.addAspectsAndApllyChangesToAFile();
		if(isFile){
			sitesPage.enterIntoDocumentLibrary();
		}
		
		if(isMandatoryCheckRequired){
			if(isFile){
				myFiles.openAFile(fileOrFolder);
			}else{
				sitesPage.clickOnViewDetails(fileOrFolder);
			}
			docDetailsPageTestObj.verifyAttributeInDocProperties();
			
			sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnEditProperties(fileOrFolder);
			docDetailsPageObj.clickAllProperties();
			docDetailsPageTestObj.verifyAllEditProperties();
			
			String docPropertiesForPopup = dataTable.getData("Document_Details", "MandatoryDocPropertiesField");
			docDetailsPageObj.commonMethodForEnterEditPropertiesData(docPropertiesForPopup);
			docDetailsPageObj.mouseOverclickSaveInEditProperties();
			docDetailsPageTestObj.verifyMandatoryFieldEmptyPopUp();
		}else{
			sitesPage.clickOnEditProperties(fileOrFolder);
			docDetailsPageObj.clickAllProperties();
			docDetailsPageTestObj.verifyAllEditProperties();
		}
		
		docDetailsPageObj.enterDataAndSaveIntoEditProperties();
		if(isFile){
			myFiles.openAFile(fileOrFolder);
		}else{
			sitesPage.clickOnViewDetails(fileOrFolder);
		}
		docDetailsPageTestObj.verifyAttributeAndValuesInDocProperties();
		
		if(isDeleteAspectRequired){
			sitesPage.enterIntoDocumentLibrary();	
			sitesPage.clickOnMoreSetting(fileOrFolder);			
			sitesPage.clickOnMoreOptionLink(fileOrFolder);
			String aspectNames = dataTable.getData("Document_Details", "SubAspects");
			docDetailsPageObj.commonMethodForDeleteAspects(aspectNames);
			sitesPage.clickOnEditProperties(fileOrFolder);
			docDetailsPageObj.clickAllProperties();
			docDetailsPageTestObj.verifyRemovedAspectsEditProperties();
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
