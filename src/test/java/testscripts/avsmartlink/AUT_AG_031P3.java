package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_031P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("1.Smart link option not available under create in document library for Reviewer");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);

		String fileName = dataTable.getData("MyFiles", "FileName");

	

		String filePath = dataTable.getData("MyFiles", "FilePath");

		String editSL = dataTable.getData("MyFiles", "MoreSettingsOption");

		

		String extURLLink = dataTable.getData("Document_Details", "FileName");
		String type = dataTable.getData("Home", "DashletName");
		
	
		String data = dataTable.getData("MyFiles", "CreateFileDetails");

		functionalLibrary.loginAsValidUser(signOnPage);

		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		UIHelper.waitForVisibilityOfEleByXpath(driver, myFiles.disablecreate);
		
		if(UIHelper.findAnElementbyXpath(driver,  myFiles.disablecreate).getAttribute("disabled").equals("true")){
			
			report.updateTestLog("Verify" + type + "Element available",
					type + " Element is not displayed for reviewer role", Status.PASS);
			
		}else{
			report.updateTestLog("Verify" + type + "Element available",
					type + " Element is displayed for reviewer role", Status.FAIL);
		}

		if (data.contains(";")) {
			String splittedFileDetails[] = data.split(";");
			for (String nxtval : splittedFileDetails) {
				String splittedsmartDetails[] = nxtval.split(",");

				String smartfile1 = splittedsmartDetails[0];
				String smartfolder = splittedsmartDetails[1];
				String smartfile2 = splittedsmartDetails[2];
				String editsmart = splittedsmartDetails[3];
				String smarttype = splittedsmartDetails[4];
				
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				sitesPage.documentdetails(smartfile1);
				UIHelper.waitForLong(driver);
				sitesPage.clickOnMoreSetting(smartfile1);
				UIHelper.waitFor(driver);
				docLibPg.commonMethodForClickOnMoreSettingsOption(smartfile1, editSL);

				avsmart.entersmarttypedata(smarttype, editsmart, extURLLink,
						"Images", "", "", "", System.getProperty("user.dir")
								+ filePath + fileName);
				avsmart.negsubmit(smarttype);
				UIHelper.waitForLong(driver);

				if (sitesPage.documentAvailable(smartfile1)) {
					report.updateTestLog(
							"Verify user unable to edit smart link",
							"User unable to edit smart link as expected",
							Status.PASS);
				} else {
					report.updateTestLog(
							"User unable to edit smart link",
							"User able to edit smart link which is not expected. ",
							Status.FAIL);
				}

				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitForLong(driver);
				sitesPage.documentdetails(smartfolder);
				UIHelper.waitForLong(driver);
				sitesPage.clickOnMoreSetting(smartfile2);
				UIHelper.waitFor(driver);
				docLibPg.commonMethodForClickOnMoreSettingsOption(smartfile2,
						editSL);
				avsmart.entersmarttypedata(smarttype, editsmart, extURLLink,
						"Images", "", "", "", System.getProperty("user.dir")
								+ filePath + fileName);
				//avsmart.submitbutton(smarttype, editsmart);
				avsmart.negsubmit(smarttype);
				UIHelper.waitFor(driver);
				sitesPage.documentAvailable(editsmart);
				//myFilesTestObj.verifyUploadedFile(editsmart, "");

			}
		}

		

	}
		
 
	@Override
	public void tearDown() {

	}
}
