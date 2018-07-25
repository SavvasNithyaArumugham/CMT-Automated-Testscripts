package testscripts.proofhq;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_028 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_15() {
		testParameters
				.setCurrentTestDescription("Verifying the alfresco 'Delete' feature for proof related files/folders is working properly");

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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(
				scriptHelper);

		homePage.navigateToSitesTab();

		String siteName = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles",
				"MoreSettingsOption");

		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {

		}
		
		myFiles.createFolder(folderDetails);
		myFilesTestObj.verifyCreatedFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {
			myFiles.openCreatedFolder(folderName);

			myFiles.uploadFile(filePath, fileName);
			myFilesTestObj.verifyUploadedFile(fileName, "");
	
			String moreSetOption1 = "", moreSetOption2 = "";
			if (moreSettingsOption.contains(",")) {
				String splittedMoreSetOptions[] = moreSettingsOption.split(",");
	
				if (splittedMoreSetOptions != null
						&& splittedMoreSetOptions.length > 1) {
					moreSetOption1 = splittedMoreSetOptions[0];
					moreSetOption2 = splittedMoreSetOptions[1];
				} else {
					moreSetOption1 = "Create Proof in ProofHQ";
					moreSetOption2 = "Delete Document";
				}
			} else {
				moreSetOption1 = "Create Proof in ProofHQ";
				moreSetOption2 = "Delete Document";
			}
	
			sitesPage.clickOnMoreSetting(fileName);
			sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(
					fileName, moreSetOption1);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
					moreSetOption1);
	
			String recepients = dataTable.getData("MyFiles", "Recepients");
			String policy = dataTable.getData("MyFiles", "ProofHQPolicy");
			sitesPage.addProofHQ(recepients, policy, fileName);
			
		/*	sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSetOption2);*/
			
			myFiles.deleteUploadedFile(fileName);
			
		//	myFilesTestObj.verifyDeletedFile(fileName);
		}
	}

	@Override
	public void tearDown() {

	}

}
