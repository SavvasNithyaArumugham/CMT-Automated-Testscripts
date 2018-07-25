package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMySitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_336P3 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_035(){
		testParameters.setCurrentTestDescription("Verify user is not getting activity feeds for any action performed for the site after disabling the feeds - Disable Active Feed and do some Action<br>"+
	"ALFDEPLOY-5277 _Verify shared activity feed is not displayed in site activity dashlet when Activity feed is disabled via My sites screen.-Part1");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMySitesPage mySitesPage = new AlfrescoMySitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		
		homePage.navigateToSitesTab();
		homePage.navigateToMySitesPage();
					
		mySitesPage.disableActivityFeeds(siteName);
						
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		String moreSettingsOptionName1 = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message+"_"+date;
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");

		myFiles.createFolder(folderDetails);

		String folderName = dataTable.getData("MyFiles", "FileName");
		sitesPage.clickOnMoreSetting(folderName);

		docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moreSettingsOptionName1);

		docLibPg.clickOnOkBtnInPopup();
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		sitesPage.openFolder(folderName);
		String fileName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		
		myFiles.uploadFile(filePath, fileName);
		
		  sitesPage.enterIntoSiteDashboard();
		
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		homePageTest.verifyActivityFeedAfterDisabled(siteName);
		
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
