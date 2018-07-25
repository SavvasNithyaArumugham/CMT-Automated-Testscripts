package testscripts.marklogic;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMarklogicPageTest;
import com.pearson.automation.utils.CC_API_Helper;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_154 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_AUT_154() {
		testParameters
				.setCurrentTestDescription("Validate JSON response if user moves folder level upto 20 levels under watched content folder from one watched content folder");
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

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentLibPageTest docLibTestPage = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoAdminToolsPage adminPg = new AlfrescoAdminToolsPage(
				scriptHelper);
		AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(
				scriptHelper);

		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String editPropLableNames = dataTable.getData("MyFiles", "TagName");
		String valueInEditPropInputBox = dataTable.getData("MyFiles",
				"EditTagName");

		String categoryItem = dataTable.getData("MyFiles",
				"ContentCategoryItem");

		String urnLabelName = "", workURNLabelName = "";
		if (editPropLableNames.contains(",")) {
			String splittedEditPropLabels[] = editPropLableNames.split(",");
			urnLabelName = splittedEditPropLabels[0];
			workURNLabelName = splittedEditPropLabels[1];
		}

		String expectedURNValue = "", expectedWorkURNValue = "";
		if (valueInEditPropInputBox.contains(",")) {
			String splittedEditPropLabels[] = valueInEditPropInputBox
					.split(",");
			expectedURNValue = splittedEditPropLabels[0];
			expectedWorkURNValue = splittedEditPropLabels[1];
		}

		String markLogicURLStoreFilePath = "src/test/resources/AppTestData/TestOutput/MarkLogicURLs.txt";
		String urnURLStoreFilePath = "src/test/resources/AppTestData/TestOutput/URNURLs.txt";
		String workURNURLStoreFilePath = "src/test/resources/AppTestData/TestOutput/WorkURNURLs.txt";
		String folderNamesStoreFilePath = "src/test/resources/AppTestData/TestOutput/FolderNames.txt";

		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();

		String parentFolderName = "", folderName = "", folderTitle = "", folderDescription = "";
		String firstChildFolderName = "", secondParentFolder = "", dataFolderName = "", parentdataFolderChildFodler = "";

		if (folderDetails.contains(",")) {
			String splittedFolderValues[] = folderDetails.split(",");

			folderName = splittedFolderValues[0].replace("FolderName:", "");
			folderTitle = splittedFolderValues[1].replace("Title:", "");
			folderDescription = splittedFolderValues[2].replace("Description:",
					"");

			for (int index = 1; index <= 2; index++) {
				parentFolderName = folderName + "WC" + index;

				myFiles.commonMethodFordeleteFolder(parentFolderName);

				myFiles.commonMethodForCreateFolder(parentFolderName,
						folderTitle + index, folderDescription + index);

				sitesPage.clickOnMoreSetting(parentFolderName);
				if (sitesPage.checkMoreSettingsOption(parentFolderName,
						moreSetOptions[0])) {
					report.updateTestLog("Verify '" + moreSetOptions[0]
							+ "' aspect for empty folder under 'More' option",
							"Verified Successfully", Status.PASS);
					sitesPage.commonMethodForClickOnMoreOptionLink(
							parentFolderName, moreSetOptions[0]);
					UIHelper.waitFor(driver);

				} else {
					report.updateTestLog("Verify '" + moreSetOptions[0]
							+ "' aspect for empty folder under 'More' option",
							"Not able to Verify", Status.FAIL);
				}

				secondParentFolder = folderName + "WC" + (index + 1);

				myFiles.commonMethodFordeleteFolder(secondParentFolder);

				myFiles.commonMethodForCreateFolder(secondParentFolder,
						folderTitle + "WC" + (index + 1), folderDescription
								+ "WC" + (index + 1));

				sitesPage.clickOnMoreSetting(secondParentFolder);
				if (sitesPage.checkMoreSettingsOption(secondParentFolder,
						moreSetOptions[0])) {
					report.updateTestLog("Verify '" + moreSetOptions[0]
							+ "' aspect for empty folder under 'More' option",
							"Verified Successfully", Status.PASS);
					sitesPage.commonMethodForClickOnMoreOptionLink(
							secondParentFolder, moreSetOptions[0]);
					UIHelper.waitFor(driver);

				} else {
					report.updateTestLog("Verify '" + moreSetOptions[0]
							+ "' aspect for empty folder under 'More' option",
							"Not able to Verify", Status.FAIL);
				}
			}

			dataFolderName = folderName + "Folder";

			boolean isDisplayedDataFolder = false;
			ArrayList<String> docFolderNamesList = myFiles
					.getUploadedFileOrFolderTitle();

			for (String docFolder : docFolderNamesList) {
				if (docFolder.equalsIgnoreCase(dataFolderName)) {
					isDisplayedDataFolder = true;
					break;
				} else {
					isDisplayedDataFolder = false;
				}
			}

			if (!isDisplayedDataFolder) {

				for (int index = 1; index <= 21; index++) {

					if (index == 1) {
						myFiles.commonMethodForCreateFolder(dataFolderName,
								folderTitle + index, folderDescription + index);

						myFiles.openCreatedFolder(dataFolderName);
					} else {
						if (index == 2) {
							parentdataFolderChildFodler = folderName
									+ (index - 1);
						}

						myFiles.commonMethodForCreateFolder(folderName
								+ (index - 1), folderTitle + (index - 1),
								folderDescription + (index - 1));

						myFiles.openCreatedFolder(folderName + (index - 1));
					}

				}
			}
		}

		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(dataFolderName);

		sitesPage.clickOnViewDetails(parentdataFolderChildFodler);

		docDetailsPage.clickCopyToDocAction();
		docDetailsPage.selectFolderToCopyInCopyPopUp(
				parentdataFolderChildFodler, siteNameValue, parentFolderName);

		sitesPage.enterIntoDocumentLibrary();

		new FileUtil().clearFile(folderNamesStoreFilePath);
		new FileUtil().clearFile(urnURLStoreFilePath);
		new FileUtil().clearFile(workURNURLStoreFilePath);
		new FileUtil().clearFile(markLogicURLStoreFilePath);

		String actualURNUrl = "", actualWorkURL = "", token = "";
		Response auth = null;
		Response answerForURN = null;
		Response answerForWorkURN = null;

		// Perform Move to and check validations for watched content source
		// parent and child folders

		for (int index = 1; index <= 21; index++) {
			String folderNameVal = "";
			if (index == 1) {
				folderNameVal = folderName + "WC" + index;
			} else {
				folderNameVal = folderName + (index - 1);
			}

			new FileUtil().appendTextToFile(folderNameVal,
					folderNamesStoreFilePath);

			docLibTestPage.verifyContentCategoryItemInDocLibPag(folderNameVal,
					categoryItem);

			sitesPage.clickOnMoreSetting(folderNameVal);
			sitesPage.clickOnMoreLinkOptions(folderNameVal, moreSetOptions[1]);
			docDetailsPage.clickAllProperties();

			actualURNUrl = docDetailsPage
					.getValueInEditPropertiesInputBox(urnLabelName);
			actualWorkURL = docDetailsPage
					.getValueInEditPropertiesInputBox(workURNLabelName);

			if (actualURNUrl.contains(expectedURNValue)) {

				report.updateTestLog(
						"Verify " + urnLabelName + " value",
						"URN value:" + actualURNUrl + " displayed successfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualURNUrl + " displayed", Status.FAIL);
			}

			if (actualWorkURL.contains(expectedWorkURNValue)) {

				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualWorkURL
								+ " displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualWorkURL + " displayed",
						Status.FAIL);
			}

			String finalURNVal = myFiles.getMarkLogicManifestUrl(actualURNUrl);
			new FileUtil().appendTextToFile(finalURNVal, urnURLStoreFilePath);

			auth = marklogic.verifyLoginAuth();

			token = CC_API_Helper.retriveValuefromJson("$..tokenId", auth
					.getBody().asString());

			answerForURN = CC_API_Helper.getManifestationReturnresponse(token,
					finalURNVal);
			marklogic.verifygetManifestationResponse(answerForURN);

			String finalWorkURNVal = myFiles
					.getMarkLogicManifestUrl(actualWorkURL);
			new FileUtil().appendTextToFile(finalWorkURNVal,
					workURNURLStoreFilePath);

			answerForWorkURN = CC_API_Helper.getManifestationReturnresponse(
					token, finalURNVal);
			marklogic.verifygetManifestationResponse(answerForWorkURN);

			docDetailsPage.clickCancelInEditProperties();

			sitesPage.clickOnViewDetails(folderNameVal);
			String markLogicUrl = myFiles.getMarkLogicUrl();

			new FileUtil().appendTextToFile(markLogicUrl,
					markLogicURLStoreFilePath);

			if (index == 1) {
				docDetailsPage.backToFolderOrDocumentPage("");
			} else {
				docDetailsPage.backToFolderOrDocumentPage(folderNameVal);
			}

			if (index != 21) {
				myFiles.openCreatedFolder(folderNameVal);
			}
		}

		ArrayList<String> folderNamesList = new ArrayList<String>();
		ArrayList<String> urnURLsList = new ArrayList<String>();
		ArrayList<String> workURNURLsList = new ArrayList<String>();
		ArrayList<String> markLogicURLsList = new ArrayList<String>();
		try {
			folderNamesList = new FileUtil()
					.readListOFDataFromFile(folderNamesStoreFilePath);
			urnURLsList = new FileUtil()
					.readListOFDataFromFile(urnURLStoreFilePath);
			workURNURLsList = new FileUtil()
					.readListOFDataFromFile(workURNURLStoreFilePath);
			markLogicURLsList = new FileUtil()
					.readListOFDataFromFile(markLogicURLStoreFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (folderNamesList != null && urnURLsList != null
				&& markLogicURLsList != null && workURNURLsList != null
				&& (urnURLsList.size() == markLogicURLsList.size())
				&& (workURNURLsList.size() == markLogicURLsList.size())
				&& (markLogicURLsList.size() == folderNamesList.size())) {
			for (int index = 0; index < markLogicURLsList.size(); index++) {
				homePage.navigateToAdminTab();
				adminPg.navigateToNodeBrowserTab();
				adminPg.searchNodeBrowserResults(markLogicURLsList.get(index));
				adminPg.navigateToNodeBrowserDetailsPg(folderNamesList
						.get(index));

				answerForURN = CC_API_Helper.getManifestationReturnresponse(
						token, urnURLsList.get(index));
				answerForWorkURN = CC_API_Helper
						.getManifestationReturnresponse(token,
								workURNURLsList.get(index));

				marklogic.verifyallresponseValue(answerForURN);

				marklogic.verifyallresponseValue(answerForWorkURN);

			}
		}

		// Perform Move to and check validations for watched content dest parent
		// and child folders
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder(parentFolderName);
		sitesPage.clickOnViewDetails(firstChildFolderName);

		docDetailsPage.clickMoveToDocAction();
		docDetailsPage.selectFileInMovePopUp(firstChildFolderName,
				siteNameValue, secondParentFolder);

		sitesPage.enterIntoDocumentLibrary();

		new FileUtil().clearFile(folderNamesStoreFilePath);
		new FileUtil().clearFile(urnURLStoreFilePath);
		new FileUtil().clearFile(workURNURLStoreFilePath);
		new FileUtil().clearFile(markLogicURLStoreFilePath);

		String actualURNUrl2 = "", actualWorkURL2 = "";
		Response answerForURN2 = null;
		Response answerForWorkURN2 = null;

		for (int index = 1; index <= 21; index++) {
			String folderNameVal = "";
			if (index == 1) {
				folderNameVal = folderName + "WC" + (index + 1);
			} else {
				folderNameVal = folderName + (index - 1);
			}

			new FileUtil().appendTextToFile(folderNameVal,
					folderNamesStoreFilePath);

			docLibTestPage.verifyContentCategoryItemInDocLibPag(folderNameVal,
					categoryItem);

			sitesPage.clickOnMoreSetting(folderNameVal);
			sitesPage.clickOnMoreLinkOptions(folderNameVal, moreSetOptions[1]);
			docDetailsPage.clickAllProperties();

			actualURNUrl2 = docDetailsPage
					.getValueInEditPropertiesInputBox(urnLabelName);
			actualWorkURL2 = docDetailsPage
					.getValueInEditPropertiesInputBox(workURNLabelName);

			if (actualURNUrl2.contains(expectedURNValue)) {

				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualURNUrl2
								+ " displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualURNUrl2 + " displayed",
						Status.FAIL);
			}

			actualWorkURL2 = docDetailsPage
					.getValueInEditPropertiesInputBox(workURNLabelName);

			if (actualWorkURL2.contains(expectedWorkURNValue)) {

				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualWorkURL2
								+ " displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify " + urnLabelName + " value",
						"URN value:" + actualWorkURL2 + " displayed",
						Status.FAIL);
			}

			String finalURNVal = myFiles.getMarkLogicManifestUrl(actualURNUrl2);
			new FileUtil().appendTextToFile(finalURNVal, urnURLStoreFilePath);

			answerForURN2 = CC_API_Helper.getManifestationReturnresponse(token,
					finalURNVal);
			marklogic.verifygetManifestationResponse(answerForURN2);

			String finalWorkURNVal = myFiles
					.getMarkLogicManifestUrl(actualWorkURL2);
			new FileUtil().appendTextToFile(finalWorkURNVal,
					workURNURLStoreFilePath);

			answerForWorkURN2 = CC_API_Helper.getManifestationReturnresponse(
					token, finalURNVal);
			marklogic.verifygetManifestationResponse(answerForWorkURN2);

			docDetailsPage.clickCancelInEditProperties();

			sitesPage.clickOnViewDetails(folderNameVal);
			String markLogicUrl = myFiles.getMarkLogicUrl();

			new FileUtil().appendTextToFile(markLogicUrl,
					markLogicURLStoreFilePath);

			if (index == 1) {
				docDetailsPage.backToFolderOrDocumentPage("");
			} else {
				docDetailsPage.backToFolderOrDocumentPage(folderNameVal);
			}

			if (index != 21) {
				myFiles.openCreatedFolder(folderNameVal);
			}
		}

		ArrayList<String> folderNamesList2 = new ArrayList<String>();
		ArrayList<String> urnURLsList2 = new ArrayList<String>();
		ArrayList<String> workURNURLsList2 = new ArrayList<String>();
		ArrayList<String> markLogicURLsList2 = new ArrayList<String>();
		try {
			folderNamesList2 = new FileUtil()
					.readListOFDataFromFile(folderNamesStoreFilePath);
			urnURLsList2 = new FileUtil()
					.readListOFDataFromFile(urnURLStoreFilePath);
			workURNURLsList2 = new FileUtil()
					.readListOFDataFromFile(workURNURLStoreFilePath);
			markLogicURLsList2 = new FileUtil()
					.readListOFDataFromFile(markLogicURLStoreFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (folderNamesList2 != null && urnURLsList2 != null
				&& markLogicURLsList2 != null && workURNURLsList2 != null
				&& (urnURLsList2.size() == markLogicURLsList2.size())
				&& (workURNURLsList2.size() == markLogicURLsList2.size())
				&& (markLogicURLsList2.size() == folderNamesList2.size())) {
			for (int index = 0; index < markLogicURLsList2.size(); index++) {
				homePage.navigateToAdminTab();
				adminPg.navigateToNodeBrowserTab();
				adminPg.searchNodeBrowserResults(markLogicURLsList2.get(index));
				adminPg.navigateToNodeBrowserDetailsPg(folderNamesList2
						.get(index));

				answerForURN2 = CC_API_Helper.getManifestationReturnresponse(
						token, urnURLsList2.get(index));
				answerForWorkURN2 = CC_API_Helper
						.getManifestationReturnresponse(token,
								workURNURLsList2.get(index));

				marklogic.verifyallresponseValue(answerForURN2);

				marklogic.verifyallresponseValue(answerForWorkURN2);

			}
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}