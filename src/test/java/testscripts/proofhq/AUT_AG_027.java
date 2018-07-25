package testscripts.proofhq;

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

public class AUT_AG_027 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void proofhq_14() {
		testParameters
				.setCurrentTestDescription("Verifying the alfresco 'Move to' feature for proof related files/folders is working properly");

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
		
		String folderName1 = "", folderName2 = "", folderName = "", folderTitle = "", folderDescription = "";
		if (folderDetails.contains(",")) {
			String splittedFolderValues[] = folderDetails.split(",");

			folderName = splittedFolderValues[0].replace("FolderName:", "");
			folderTitle = splittedFolderValues[1].replace("Title:", "");
			folderDescription = splittedFolderValues[2].replace("Description:",
					"");

			for (int index = 1; index <= 2; index++) {
				
				if(index==1)
				{
					folderName1 = folderName + index;
				}
				else
				{
					folderName2 = folderName + index;
				}

				myFiles.commonMethodForCreateFolder(folderName + index,
						folderTitle + index, folderDescription + index);
				myFilesTestObj.verifyCreatedFolder(folderName + index);
			}
		}

		myFiles.openCreatedFolder(folderName1);
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
				moreSetOption2 = "Move to";
			}
		} else {
			moreSetOption1 = "Create Proof in ProofHQ";
			moreSetOption2 = "Move to";
		}

		sitesPage.clickOnMoreSetting(fileName);
	/*	sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(
				fileName, moreSetOption1);*/
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
				moreSetOption1);

		String recepients = dataTable.getData("MyFiles", "Recepients");
		String policy = dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSetOption2);
		docLibPg.performMoveToOperation(siteName, folderName2);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName2);
		
		myFilesTestObj.verifyMovedFileInTargetSite(fileName,"");
	}

	@Override
	public void tearDown() {

	}

}
