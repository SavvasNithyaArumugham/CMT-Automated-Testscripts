package testscripts.sharebox;

import java.util.ArrayList;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_033P3 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_032() {
		testParameters
				.setCurrentTestDescription("Verify the Message 'oops,looks like you have visited an invalid link' when the External User uploads file via External Sharebox after the Sharing has been Stopped. -Part3 : check warning message displayed");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

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
						+ properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String message = dataTable.getData("Sharebox", "ErrorMessageValue");

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOpt = dataTable.getData("MyFiles",
				"MoreSettingsOption");
		

		try {
	
			String shareBoxURL = new FileUtil()
					.readDataFromFile("src/test/resources/AppTestData/TestOutput/shareBox.txt");
			UIHelper.waitFor(driver);
			driver.findElement(By.cssSelector("body")).sendKeys(
					Keys.CONTROL + "t");
			
			ArrayList<String> tabs = new ArrayList<String>(
					driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
			driver.get(shareBoxURL);
			driver.switchTo().window(tabs.get(0));
			homePage.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();

			ArrayList<String> folderNamesList = myFiles
					.getFolderNames(folderDetails);
			for (String folderName : folderNamesList) {
				sitesPage.clickOnMoreSetting(folderName);
				docLibPg.commonMethodForClickOnMoreSettingsOption(folderName,
						moreSettingsOpt);
				String notification = dataTable.getData("Sharebox", "Notifications");
				String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
				String properties = dataTable.getData("Sharebox", "EditShareboxFieldsData");
				String editMessage = dataTable.getData("Sharebox", "EditMessage");
				shareboxPg.commonMethodToEnterSharingProperties(properties,
						editMessage, notification, notifyDetails);
				shareboxPg.clickOnSaveBtnInSharingPopup();

			}
			driver.switchTo().window(tabs.get(1));
			shareboxPg.checkMessageDisplayed(message);
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	@Override
	public void tearDown() {
		// Nothing to do
	}
}