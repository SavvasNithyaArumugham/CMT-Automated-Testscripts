package testscripts.collections2;
import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2421_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_024() {
		testParameters
		.setCurrentTestDescription("Can hover over an object in collections and see the actions in the following order");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");	
		String browseActionNames = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String filePath = dataTable.getData("MyFiles",
				"FilePath");
		String fileName = dataTable.getData("MyFiles",
				"FileName");

		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();

		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);		
		collectionPg.checkOnBrowseActionsInCollectionUI(
				fileName, browseActionNames);	
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
