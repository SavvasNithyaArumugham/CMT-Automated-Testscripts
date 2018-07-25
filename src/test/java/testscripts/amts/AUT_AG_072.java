package testscripts.amts;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_072 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_034() {
		testParameters.setCurrentTestDescription(
				"To Verify whether the user is able to select a Transformation profile from the list of available and apply to a one or moreimage file.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);

		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");
		String type = dataTable.getData("Media_Transform", "filePath");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String file1 = dataTable.getData("MyFiles", "FileName");
		String file2 = dataTable.getData("MyFiles", "RelationshipName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String targetfolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String folder = dataTable.getData("MyFiles", "Version");
		String target = dataTable.getData("MyFiles", "MoreSettingsOption");

		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		/*
		 * if (mediaTransPage.checkMediaProfAvail(profName)){
		 * mediaTransPage.deleteProfileFrmMediaTransPg(profName); }
		 */

		// mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.enterImageProfDetailsWithUniqueNameAndSubAsst(profName, subAsstCode);
		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {

			report.updateTestLog("Verify Image profile created",
					"Video/Image created successfully." + "<br><b>Image Profile Name : </br>" + profName, Status.PASS);

			mediaTransPage.selectImgTransType(type);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();
			UIHelper.waitFor(driver);
		} else {
			report.updateTestLog("Verify Image profile created", "Failed to create Image profile", Status.FAIL);
		}

		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();

		docLibPge.deleteAllFilesAndFolders();
		// myFiles.createFolder(targetfolder);
		myFiles.createFolder(folderDetails);
		sitesPage.documentdetails(folder);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		myFiles.uploadFile(filePath, file1);
		myFiles.uploadFile(filePath, file2);

		sitesPage.enterIntoDocumentLibrary();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(folder);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folder, docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, preTxt);

		sitesPage.documentlib();
		sitesPage.documentdetails(folder);
		if (mediaTransPage.isTransferredFileIsAvailable(preTxt)) {
			report.updateTestLog("Verify Image profile applied for multi files", "Image profile applied successfully.",
					Status.PASS);
		}
		else {
			report.updateTestLog("Verify video profile applied Successfully to the File " + file1,
					"Video profile NOT applied successfully." + "<br><b>File  Name : </br>", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}