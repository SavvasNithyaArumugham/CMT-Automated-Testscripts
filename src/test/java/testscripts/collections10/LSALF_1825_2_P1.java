package testscripts.collections10;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1825_2_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_1770() {
		testParameters.setCurrentTestDescription(
			"Update JSON Export for all new Print Automation requirements"
				);
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		String createObjectDataDC = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");
		String createObjectDataC = dataTable.getData("MyFiles", "FieldDataForAllProperties");
		String createObjectDataCO = dataTable.getData("MyFiles", "FieldDataForQuickEdit");		
				// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		// Select Sites > Create Site from top black menu.
		homePageObj.navigateToSitesTab();

		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);

		// Navigate to document library and click on a program>Program Object
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);

		// Click on "Edit collection"
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
	collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
		collectionPg.openCollectionObject("AutoCourse");
		UIHelper.waitFor(driver);
	collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectDataDC);
		collectionPg.openCollectionObject("AutoDCObj");
		UIHelper.waitFor(driver);
	collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectDataC);
		UIHelper.waitFor(driver);
		collectionPg.openCollectionObject("AutoContainer");
		UIHelper.waitFor(driver);
		collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectDataCO);
		UIHelper.waitFor(driver);
		driver.findElement(By.linkText("Program")).click();
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting("AutoCourse");
		UIHelper.waitFor(driver);
		collectionPg.commonMethodForClickOnMoreSettingsOption("AutoCourse","Generate JSON");
		}


	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
