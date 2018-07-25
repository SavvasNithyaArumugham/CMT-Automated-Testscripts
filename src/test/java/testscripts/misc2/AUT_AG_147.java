package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_147 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_043() {
		testParameters
				.setCurrentTestDescription("Verify that Document Actions List is not going off of the Page when it is opened in new Incognito window");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoDocumentDetailsPage docLibPg = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteNameValue);		
		sitesPage.documentlib();
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String viewOption= dataTable.getData("MyFiles", "BrowseActionName");
		String options= dataTable.getData("MyFiles", "MoreSettingsOption");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		myFiles.deleteUploadedFile(fileName);		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);		
		docLibPg.selectDocumentLibView(viewOption);	
		
		String splittedFileNames[] = fileName.split(",");
		for (String fileNameVal : splittedFileNames) {
		sitesPage.clickOnMoreSetting(fileNameVal);
		
		if (!sitesPage.checkAllMoreOptionsAvil(fileNameVal,options)) {
			report.updateTestLog("Check more options available for "+fileNameVal,
					"More options not displayed", Status.FAIL);
		} else {
			report.updateTestLog("Check more options available for "+fileNameVal,
					"More options displayed in document library", Status.PASS);
		}
		}

	}

	@Override
	public void tearDown() {

	}
}
