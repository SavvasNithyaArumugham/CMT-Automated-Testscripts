package testscripts.unzip;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_008 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void unzip_08() {
		testParameters
				.setCurrentTestDescription("Verify user can Unzip a folder with destination folder from the My files by selecting Unzip to option under document actions");
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
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		
		homePageObj.navigateToMyFilesTab();

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String splitVal[] = fileName.split(Pattern.quote("."));
		String finalFileName = splitVal[0];
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		myFiles.deleteUploadedFile(fileName);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			myFiles.deleteUploadedFile(folderName);
		}

        myFiles.createFolder(folderDetails);
        
        myFilesTestObj.verifyCreatedFolder(folderDetails);
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		myFilesTestObj.verifyUploadedFile(fileName, "");
		
		myFiles.openUploadedOrCreatedFile(fileName,"");
		
		docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		
		docDetailsPageObj.performUnzipDocAction(extractTo);
		
		homePageObj.navigateToMyFilesTab();
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);
			
			myFilesTestObj.verifyUnzippedFolder(finalFileName);
		
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}