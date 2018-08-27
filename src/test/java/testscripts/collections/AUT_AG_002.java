package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_041() {
		testParameters
				.setCurrentTestDescription(
		 "TC_ALFDEPLOY-3309_To verify user not able to add thumbnail if invalid Site URL name is given in thumbnail field(SiteURL:File name)and there should be error message 'Site for [x-asset] not found' when trying to autolink"
		+"<br>TC_ALFDEPLOY-3309_To verify user not able to add thumbnail if unavailable file name is given in thumbnail field(SiteURL:File name)and there should not be a thumbnail image after trying to auto-link."
		+"<br>TC_ALFDEPLOY-3309_To verify user not able to add thumbnail if Site display name is given in thumbnail field(Site name:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user not able to add thumbnail if file name is not available in thumbnail folder"
		+"<br>TC_ALFDEPLOY-3309_To verify user is not able to add thumbnail if incorrect format given in thumbnail field(:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field where user is not a member of the public site(Site name:File name)"
		+"<br>TC_ALFDEPLOY-3309_To verify user able to add thumbnail if Site URL name is given in thumbnail field with different case(SiteURL:File name)"
		+"<br> Similar testcases of Grid Thumbnail");
		
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(
				scriptHelper);
		AlfrescoSearchPage alfrescoSearchPage = new AlfrescoSearchPage(
				scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		String moreSettingsOptionName = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		String errorfile = dataTable.getData("MyFiles", "Subfolders1");

		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");

		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
				errorfile);

		// Log in Pearson Schools project
		functionalLibrary.loginAsValidUser(signOnPage);

		// Create site
		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName =  sitesPage.getCreatedSiteName().toLowerCase();
		String site =  sitesPage.getCreatedSiteName();

		// Go to collection UI
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// enter into default course object
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);

		// create collections objects
		String createObjectData = dataTable.getData("MyFiles",
				"CollectionObjectBasicData");
		collectionPg
				.createBasicCollectionObjectFromCreateMenu(createObjectData);

		// verify Grid Thumbnail to link: ,Thumbnail to link: for content,
		// composite, learning bundel, sequence
		ArrayList<String> listOfObjects = new ArrayList<String>();
		listOfObjects = collectionPg.getFoldersFromRightPanInCollectionUi();

		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						listOfObjectsString, "Edit Properties");
				UIHelper.waitFor(driver);
				collectionPg.enterCollectionObjectA2LData(
						"Grid Thumbnail to link:", "collection"
								+ ":AlfGrid.jpg");
				collectionPg.enterCollectionObjectA2LData("Thumbnail to link:",
						"collection" + ":AlfTum.jpg");
				UIHelper.waitFor(driver);
				collectionPg.clickOnSaveBtn();
			}
		}

		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);

		// Click auto link assets on parent course
		driver.navigate().back();
		UIHelper.waitForPageToLoad(driver);
		collectionPg.clickOnEditCollectionButton();
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, moreSettingsOptionName);
		UIHelper.pageRefresh(driver);
		UIHelper.pageRefresh(driver);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		collectionPg.collectionObjStatus(collectionObjectName);

		collectionPg.clickOnEditCollectionButton();
		collectionPg.clickOnMoreSetting(collectionObjectName);
		collectionPg.commonMethodForClickOnMoreSettingsOption(
				collectionObjectName, "View Error Report");
		new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,
				errorfile);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);

		collectionPg
				.errormessage("Grid Thumbnail",
						"Site collection is not found for Grid Thumbnail AlfGrid.jpg.");
		collectionPg.errormessage("AlfTum.jpg",
				"Site collection is not found for Thumbnail AlfTum.jpg.");
		
		homePageObj.switchtab(0);
		
		UIHelper.waitFor(driver);
		collectionPg.openCollectionObject(collectionObjectName);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
				errorfile);
		
		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						listOfObjectsString, "Edit Properties");
				UIHelper.waitFor(driver);
				collectionPg.enterCollectionObjectA2LData(
						"Grid Thumbnail to link:", siteName
								+ ":AlfGrid.jpg");
				collectionPg.enterCollectionObjectA2LData("Thumbnail to link:",
						siteName + ":AlfTum.jpg");
				UIHelper.waitFor(driver);
				collectionPg.clickOnSaveBtn();
				
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				// Click auto link assets on parent course
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						collectionObjectName, moreSettingsOptionName);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				collectionPg.collectionObjStatus(collectionObjectName);

				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						collectionObjectName, "View Error Report");
				new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,
						errorfile);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);

				collectionPg
						.errormessage("Grid Thumbnail",
								"Grid Thumbnail for AlfGrid.jpg is not found on site "+siteName+".");
				collectionPg.errormessage("AlfTum.jpg",
						"Thumbnail for AlfTum.jpg is not found on site "+siteName+".");
			}
		}
		
homePageObj.switchtab(0);
		
		UIHelper.waitFor(driver);
		collectionPg.openCollectionObject(collectionObjectName);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
				errorfile);
		
		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						listOfObjectsString, "Edit Properties");
				UIHelper.waitFor(driver);
				collectionPg.enterCollectionObjectA2LData(
						"Grid Thumbnail to link:", site
								+ ":AlfGrid.jpg");
				collectionPg.enterCollectionObjectA2LData("Thumbnail to link:",
						site + ":AlfTum.jpg");
				UIHelper.waitFor(driver);
				collectionPg.clickOnSaveBtn();
				
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				// Click auto link assets on parent course
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						collectionObjectName, moreSettingsOptionName);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				collectionPg.collectionObjStatus(collectionObjectName);

				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						collectionObjectName, "View Error Report");
				new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,
						errorfile);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);

				collectionPg
						.errormessage("Grid Thumbnail",
										"Grid Thumbnail for AlfGrid.jpg is not found.");
				collectionPg.errormessage("AlfTum.jpg","Thumbnail for AlfTum.jpg is not found.");
			}
		}
		
		
		
homePageObj.switchtab(0);
		
		UIHelper.waitFor(driver);
		collectionPg.openCollectionObject(collectionObjectName);
		
		new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,
				errorfile);
		
		for (String listOfObjectsString : listOfObjects) {
			if (listOfObjectsString.contains("AutoContentObj")
					|| listOfObjectsString.contains("AutoCompositeObj")
					|| listOfObjectsString.contains("AutoSeqObj")
					|| listOfObjectsString.contains("AutoLearningBundle")) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						listOfObjectsString, "Edit Properties");
				UIHelper.waitFor(driver);
				collectionPg.enterCollectionObjectA2LData(
						"Grid Thumbnail to link:",
								":AlfGrid.jpg");
				collectionPg.enterCollectionObjectA2LData("Thumbnail to link:",
						":AlfTum.jpg");
				UIHelper.waitFor(driver);
				collectionPg.clickOnSaveBtn();
				
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);

				// Click auto link assets on parent course
				driver.navigate().back();
				UIHelper.waitForPageToLoad(driver);
				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						collectionObjectName, moreSettingsOptionName);
				UIHelper.pageRefresh(driver);
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitFor(driver);
				collectionPg.collectionObjStatus(collectionObjectName);

				collectionPg.clickOnEditCollectionButton();
				collectionPg.clickOnMoreSetting(collectionObjectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(
						collectionObjectName, "View Error Report");
				new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,
						errorfile);

				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);

				collectionPg
						.errormessage("Grid Thumbnail",
								"Grid Thumbnail for :AlfGrid.jpg is not found.");
				collectionPg.errormessage("AlfTum.jpg",
						"Thumbnail for :AlfTum.jpg is not found.");
			}
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
