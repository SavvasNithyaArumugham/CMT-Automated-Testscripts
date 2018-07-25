package testscripts.transformations;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRulePage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_016 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void transformations_16() {
		testParameters.setCurrentTestDescription(
				"Verify that user is able to transform 'ms-excel.addin.macroenabled.12 - xlam' file to below format files"
						+ "<br>-bmp" + "<br>-cgm" + "<br>-gif" + "<br>-ief" + "<br>-jpeg" + "<br>-png" + "<br>-tiff");

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
		ArrayList<String> targetFolderNamesList = new ArrayList<String>();

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToSitesTab();

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();

		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String targetFolderDetails = dataTable.getData("Sites", "TargetFolder");
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		targetFolderNamesList = myFiles.getFolderNames(targetFolderDetails);
		for (String targetFolderName : targetFolderNamesList) {

			if (!myFilesTestObj.validateCreatedFolders(targetFolderName)) {
				myFiles.createFolder(targetFolderDetails);
				myFilesTestObj.verifyCreatedFolder(targetFolderDetails);
			}

			if (myFilesTestObj.validateCreatedFolders(targetFolderName)) {
				myFiles.openCreatedFolder(targetFolderName);

				try {
					docLibPg.deleteAllFilesAndFolders();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		sitesPage.enterIntoDocumentLibraryWithoutReport();

		boolean isRanRuleSuccessfully = false;
		ArrayList<String> testDatafolderNamesList = myFiles.getFolderNames(folderDetails);
		for (String folderName : testDatafolderNamesList) {

			if (!myFilesTestObj.validateCreatedFolders(folderName)) {
				myFiles.createFolder(folderDetails);
				myFilesTestObj.verifyCreatedFolder(folderDetails);
			}

			if (myFilesTestObj.validateCreatedFolders(folderName)) {
				myFiles.openCreatedFolder(folderName);

				String filePath = dataTable.getData("MyFiles", "FilePath");
				String fileName = dataTable.getData("MyFiles", "FileName");

				ArrayList<String> uploadedFilesNameList = myFiles.getUploadedFileOrFolderTitle();

				if (fileName.contains(",")) {
					String splittedFileNames[] = fileName.split(",");
					for (String fileNameVal : splittedFileNames) {
						if (!myFilesTestObj.commonMethodForValidateUploadedFile(fileNameVal, uploadedFilesNameList)) {
							myFiles.uploadFileInMyFilesPage(filePath, fileNameVal);
							myFilesTestObj.verifyUploadedMultipleFiles(fileNameVal);
						} else {
							myFilesTestObj.verifyUploadedMultipleFiles(fileNameVal);
						}
					}
				} else {
					if (!myFilesTestObj.commonMethodForValidateUploadedFile(fileName, uploadedFilesNameList)) {
						myFiles.uploadFileInMyFilesPage(filePath, fileName);
						myFilesTestObj.verifyUploadedMultipleFiles(fileName);
					} else {
						myFilesTestObj.verifyUploadedMultipleFiles(fileName);
					}
				}

				myFiles.navigateToMyFilesContainer();
				sitesPage.clickOnMoreSetting(folderName);
				AlfrescoRulePage rulePage = new AlfrescoRulePage(scriptHelper);
				String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
				sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSettingsOption);

				String ruleNames = dataTable.getData("Rule", "RuleName");
				String sitesRuleDescription = dataTable.getData("Rule", "RuleDescription");
				String sitesRuleCriteriaIs = dataTable.getData("Rule", "RuleCriteriaIs");
				String sitesRuleMimeType = dataTable.getData("Rule", "RuleMimeType");
				String destinationFolder = dataTable.getData("Rule", "DestinationFolder");

				boolean isDiaplayeCreatedRule = false;
				if (ruleNames.contains(";") && sitesRuleDescription.contains(";")
						&& sitesRuleCriteriaIs.contains(";")) {

					String splittedRuleNames[] = ruleNames.split(";");
					String splittedRuleDescription[] = sitesRuleDescription.split(";");
					String splittedRuleCriterials[] = sitesRuleCriteriaIs.split(";");

					String splittedRuleMimeType[] = new String[splittedRuleNames.length];
					String splittedDestFolder[] = new String[splittedRuleNames.length];
					if (sitesRuleMimeType.contains(";") && destinationFolder.contains(";")) {
						splittedRuleMimeType = sitesRuleMimeType.split(";");
						splittedDestFolder = destinationFolder.split(";");
					} else if (sitesRuleMimeType.contains(";") && !destinationFolder.contains(";")) {
						splittedRuleMimeType = sitesRuleMimeType.split(";");
						for (int index = 0; index < splittedRuleNames.length; index++) {
							splittedDestFolder[index] = destinationFolder;
						}
					} else if (!sitesRuleMimeType.contains(";") && destinationFolder.contains(";")) {
						splittedDestFolder = destinationFolder.split(";");
						for (int index = 0; index < splittedRuleNames.length; index++) {
							splittedRuleMimeType[index] = sitesRuleMimeType;
						}
					} else if (!sitesRuleMimeType.contains(";") && !destinationFolder.contains(";")) {
						for (int index = 0; index < splittedRuleNames.length; index++) {
							splittedRuleMimeType[index] = sitesRuleMimeType;
							splittedDestFolder[index] = destinationFolder;
						}
					}

					if ((splittedRuleNames != null) && (splittedRuleCriterials != null)
							&& (splittedRuleMimeType != null)
							&& (splittedRuleNames.length == splittedRuleCriterials.length)
							&& (splittedRuleNames.length == splittedRuleMimeType.length)
							&& (splittedRuleNames.length == splittedDestFolder.length)) {
						int counter = 0;
						for (String ruleName : splittedRuleNames) {
							if (!rulePage.checkRuleNameInRulesPage(ruleName)) {
								rulePage.commonMethodForCreateRule(siteName, ruleName, splittedRuleDescription[counter],
										splittedRuleCriterials[counter], splittedRuleMimeType[counter],
										splittedDestFolder[counter]);
							}

							if (rulePage.checkRuleNameInRulesPage(ruleName)) {
								isDiaplayeCreatedRule = true;
							} else {
								isDiaplayeCreatedRule = false;
							}

							counter++;
						}
					}
				} else if (!ruleNames.contains(";") && !sitesRuleDescription.contains(";")
						&& !sitesRuleCriteriaIs.contains(";") && !sitesRuleMimeType.contains(";")
						&& !destinationFolder.contains(";")) {
					if (!rulePage.checkRuleNameInRulesPage(ruleNames)) {
						rulePage.commonMethodForCreateRule(siteName, ruleNames, sitesRuleDescription,
								sitesRuleCriteriaIs, sitesRuleMimeType, destinationFolder);
					}

					if (rulePage.checkRuleNameInRulesPage(ruleNames)) {
						isDiaplayeCreatedRule = true;
					} else {
						isDiaplayeCreatedRule = false;
					}
				}

				if (isDiaplayeCreatedRule) {
					report.updateTestLog("Verify created rule(s)",
							"User able to see 'created rule(s):" + ruleNames + "'", Status.PASS);
					isRanRuleSuccessfully = rulePage.clickRunRuleButton();
				}
			}
		}

		if (isRanRuleSuccessfully) {
			sitesPage.enterIntoDocumentLibrary();

			String transformedFileNames = dataTable.getData("Rule", "TransformedFileName");
			ArrayList<String> transFileNamesList = new ArrayList<String>();

			if (transformedFileNames.contains(";")) {
				String splittedTransFileNames[] = transformedFileNames.split(";");
				for (String transFileName : splittedTransFileNames) {
					transFileNamesList.add(transFileName);
				}
			} else {
				transFileNamesList.add(transformedFileNames);
			}

			int index = 0;
			if (targetFolderNamesList.size() == transFileNamesList.size()) {
				for (String tagetFolderName : targetFolderNamesList) {
					myFiles.openCreatedFolder(tagetFolderName);

					myFilesTestObj.verifyTransformedFiles(transFileNamesList.get(index));

					sitesPage.enterIntoDocumentLibrary();
				}
			}
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
