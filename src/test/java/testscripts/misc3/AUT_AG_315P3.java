package testscripts.misc3;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_315P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription(
				"Internal User linked sub folder and files to other site folders and files, for verifying folder path  displaying in 1hr Aggregated mail");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();

		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String createdFolder1=dataTable.getData("MyFiles", "FileName");
		String createdFile = dataTable.getData("MyFiles", "CreateFileDetails");
		String title = dataTable.getData("MyFiles", "Version");
		String targetSite = dataTable.getData("Sites", "TargetSiteName");
	
		myFiles.openFolder(folderDetails);
		myFiles.openFolder(createdFolder1);
		

		
		sitesPage.clickOnMoreSetting(title);
		docLibPg.commonMethodForClickOnMoreSettingsOption(title, moreSettingsOptionName);
		docDetailsPageObj.selectFolderInLinkPopUp(targetSite);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		sitesPage.clickOnMoreSetting(createdFile);
		docLibPg.commonMethodForClickOnMoreSettingsOption(createdFile, moreSettingsOptionName);
		docDetailsPageObj.selectFolderInLinkPopUp(targetSite);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}