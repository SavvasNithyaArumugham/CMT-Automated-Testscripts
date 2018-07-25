package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2204_TC003 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_002() {
		testParameters.setCurrentTestDescription(
				"Can see that the new object has an Incoming 'Parent Reference' association (secondary P/C) between the new object and the selected structural object in the left pane of Collections UI.");
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

		// Log in Pearson Schools project

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");
		String browseActionName = dataTable.getData("MyFiles", "BrowseActionName");
		String relationship = dataTable.getData("MyFiles", "RelationshipName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		// Navigate inside the Site.
		homePageObj.navigateToSitesTab();

		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		// Navigate inside the folder.
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPgTest.verifyEditCollectionOption();

		// Click on 'Edit Collection' action.
		collectionPg.clickOnEditCollectionButton();

		ArrayList<String> collectionObjNamesAndTypeList = collectionPgTest
				.getCreateCollectionObjectNamesWithObjectType(createObjectData);

		for (String collectionObjectNameAndType : collectionObjNamesAndTypeList) {

			if (collectionObjectNameAndType.contains("-")) {
				String splittedCollectionObjectNameAndType[] = collectionObjectNameAndType.split("-");

				if (splittedCollectionObjectNameAndType != null && splittedCollectionObjectNameAndType.length > 1) {
					String collectionObjectName = splittedCollectionObjectNameAndType[1];

					// Create a new object.
					// collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);

					collectionPg.createBasicCollectionObjectFromCreateMenu(createObjectData);
					UIHelper.pageRefresh(driver);
					// "Select the ""View Details"" action on newly created
					// object.
					// (To make ""View Details"" action visible hover over the
					// object and from the right hand menu select ""More"")"
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting(collectionObjectName);
					collectionPg.commonMethodForClickOnMoreSettingsOption(collectionObjectName, browseActionName);
					collectionPgTest.verifyCollectionRelationFilePath(relationship, filePath);

				}
			}
		}

	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
