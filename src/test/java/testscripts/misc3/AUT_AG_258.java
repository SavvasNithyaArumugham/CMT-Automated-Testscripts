package testscripts.misc3;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_258 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_042() {
		testParameters
				.setCurrentTestDescription("Verify the 'Program Name' should not be the mandatory feild in Manage Aspects edit properties");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		homePageObj.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);

		sitesPage.enterIntoDocumentLibrary();

		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");

		myFiles.createFolder(folderDetails);

		myFilesTestObj.verifyCreatedFolder(folderDetails);

		ArrayList<String> folderNamesList = myFiles
				.getFolderNames(folderDetails);

		for (String folderName : folderNamesList) {

			sitesPage.clickOnMoreSetting(folderName);

			String moreSettiongsOptionName = dataTable.getData("MyFiles",
					"MoreSettingsOption");

			sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(folderName,
					moreSettiongsOptionName);

			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
					moreSettiongsOptionName);

			docDetailsPage.performManageAspectsDocAction();
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
            
			//String aspectsName = dataTable.getData("Document_Details", "AspectName");
			//docDetailsPageObj.addOrDeleteAspect(aspectsName);
			

			sitesPage.clickOnEditProperties(folderName);
			docDetailsPageObj.clickAllProperties();

			String fieldLabelNameList = dataTable.getData("Document_Details",
					"DocProperties");
	          String[] splittedFieldName=fieldLabelNameList.split(",");
			
			
			docDetailsPageTestObj.verifyMandatoryFieldInEditPropPageForNegativeCase(splittedFieldName[0]);
		    docDetailsPageTestObj.verifyMandatoryFieldInEditPropPageForNegativeCase(splittedFieldName[1]);
			docDetailsPageTestObj.verifyMandatoryFieldInEditPropPageForNegativeCase(splittedFieldName[2]);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}