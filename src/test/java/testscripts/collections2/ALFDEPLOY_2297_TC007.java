package testscripts.collections2;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2297_TC007 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_023() {
		testParameters.setCurrentTestDescription(
				"1. Can hover over a Course in the right pane of the Collection UI and select the 'Generate Realize CSVs' action."
						+ "<br> 2. can click on the 'Generate Realize CSVs' action to see a notification that the publishing process has began: 'Realize CSVs generation in progress'"
						+ "<br> 3. can navigate to the data Export folder and find a course structure document under 'Data Exports' > Course Name > Date (email notifications are handled in a separate story) "
						+ "<br> 4. can see that the course structure document has the same name as the course object");
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

		// Login into Application
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);

		// Navigate to Site tab
		homePageObj.navigateToSitesTab();

		String siteNameValue = dataTable.getData("Sites", "SiteName");

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String objectName = dataTable.getData("MyFiles", "FileName");
		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

		// Create new collections site
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();

		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		// Navigate to Programs>Program object and click "Edit Collection"
		// option
		sitesPage.documentdetails(folderNames[0]);
		sitesPage.documentdetails(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();

		// Click on Generate Realize Csv for course object
		//Modified as part of NALS Starts
		//collectionPg.clickOnBrowseActionsInCollectionUI(objectName, browseActionName);
				collectionPg.clickOnMoreSetting(objectName);
				collectionPg.commonMethodForClickOnMoreSettingsOption(objectName, browseActionName);
				collectionPg.clickonrealizebox();
		//Modified as part of NALS Ends
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		for (String path : filePath) {

			sitesPage.documentdetails(path);

		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		String fileName = objectName + "-" + currentDate;

		if (mediaTransPage.isTransferredFileIsAvailable(fileName)) {
			report.updateTestLog("Verify CSV file " + fileName + "is available", fileName + " is available",
					Status.PASS);

		} else {
			report.updateTestLog("Verify CSV file " + fileName + "is available", fileName + " is not available",
					Status.FAIL);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}
