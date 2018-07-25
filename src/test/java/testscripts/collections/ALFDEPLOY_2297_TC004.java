package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class ALFDEPLOY_2297_TC004 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_022() {
		testParameters.setCurrentTestDescription("Do not see the action to 'Generate Realize CSVs' within the Share UI (e.g., hover over on Courses in Share Folder List View, Search Results List, Document Details Page, etc.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		// // login
		functionalLibrary.loginAsValidUser(signOnPage);
		
		// // goto site > document lib
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder("Courses");
		
		
		try {
			sitesPage.clickOnMoreSetting("Course");
			docLibPg.negativeCommonMethodForClickOnMoreSettingsOption("Course", "Generate Realize CSVs");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog(
			"Verify More Settings Option for '"+"Course"+"' folder in Shared UI",
			"Option available successfully"
			+"<br><b> Verified Option : </b>" + "Generate Realize CSVs", Status.FAIL);
		} catch (Exception e) {
			report.updateTestLog(
			"Verify More Settings Option for '"+"Course"+"' folder in Shared UI",
			"Option not available"
			+"<br><b> Verified Option : </b>" + "Generate Realize CSVs", Status.PASS);
			
			
		}
		
		
//		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
//		functionalLibrary.loginAsValidUser(signOnPage);
//		
//		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
//		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
//		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
//		
//		homePageObj.navigateToSitesTab();
//		
//		String sourceSiteName = dataTable.getData("Sites", "SiteName");
//		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
//		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
//		
//		sitesPage.openSiteFromRecentSites(sourceSiteName);
//		sitesPage.enterIntoDocumentLibrary();
//		
//		for (String folderName : folderNames) {
//			
//			sitesPage.clickOnMoreSetting(folderName);
//			if(sitesPage.checkMoreSettingsOption(folderName, moreSettingsOptionName)){
//				report.updateTestLog(
//						"Verify More Settings Option for '"+folderName+"' folder in Shared UI",
//						"Option available successfully"
//						+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.FAIL);
//			}else{
//				report.updateTestLog(
//						"Verify More Settings Option for '"+folderName+"' folder in Shared UI",
//						"Option not available"
//						+"<br><b> Verified Option : </b>" + moreSettingsOptionName, Status.PASS);
//			}
//			UIHelper.waitFor(driver);
//		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
