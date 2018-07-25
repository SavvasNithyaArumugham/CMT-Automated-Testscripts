package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_044P5 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_036() {
		testParameters
				.setCurrentTestDescription("");
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
		

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
	
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
	
	

		String properties2 = dataTable.getData("Sharebox", "EditShareboxFieldsData");
		String message2 = dataTable.getData("Sharebox", "EditMessage");
		String date = new DateUtil().getCurrentDate();
		message2 = message2+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folder3 = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "Version");
	
		functionalLibrary.loginAsValidUser(signOnPage);	
		String site=sitesPage.getCreatedSiteName();
       	sitesPage.siteFinder(site);
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder(folderDetails);
		
		sitesPage.clickOnMoreSetting(folderName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(folderName, moreSettingsOptionName);

		sitesPage.clickOnMoreSetting(folder3);
		docLibPg.commonMethodForClickOnMoreSettingsOption(folder3, moreSettingsOptionName);
		shareboxPg.commonMethodToEnterSharingProperties(properties2,
				message2, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		if (shareboxPg.checkShareIconAvailable(folder3) ){
			report.updateTestLog("Check Sharing icon available",
					"Sharing Icon displayed", Status.PASS);
		} else {
			report.updateTestLog("Check Sharing icon available",
					"Sharing Icon is not displayed", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}