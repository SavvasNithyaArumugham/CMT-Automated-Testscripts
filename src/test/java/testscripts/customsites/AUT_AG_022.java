package testscripts.customsites;

import java.util.ArrayList;

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
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 *s
 * 
 * @author 516188
 */
public class AUT_AG_022 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void CUSTOMSITES_007() {
		testParameters
				.setCurrentTestDescription("Verify that the user is able to add the files from the present folder not from the parent folder");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		// Login
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		// Goto My files
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToMyFilesTab();
		// upload files
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileNames = dataTable.getData("MyFiles", "FileName");
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFolderNames(folderDetails);
		myFiles.openCreatedFolder(folderNamesList.get(0));
		
		myFiles.deleteUploadedFile(fileNames);
		myFiles.uploadFileInMyFilesPage(filePath,fileNames);
		
		ArrayList<String> filesList=new ArrayList<String>();
		filesList=myFiles.getFileNames(fileNames);
		
		myFiles.commonMethodForSelectingFile(filesList.get(0));
		
		sitesPage.clickOnSelectedItems();		
		
		String selectedItemMenuOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		docLibPage.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		AlfrescoDocumentDetailsPage myDocPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		myDocPage.selectStartWorkflow();

		myDocPage.selectWorkflowOption();

		myDocPage.enterSimpleReviewApproveForm();
		
		myDocPage.clickOnAddItemsButton();
		
		myDocPage.addMoreItem(filesList.get(1));
		
		myDocPage.clickOnAddItemsOkButton();
		
		myDocPage.sumbitWorkflow();

		myDocPage.verifyCreatedWorkflow();
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}