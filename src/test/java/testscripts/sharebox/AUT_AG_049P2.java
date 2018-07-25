package testscripts.sharebox;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_049P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;
	@Test
	public void sharebox_049P2() {
		testParameters.setCurrentTestDescription("Part 2:Verify external user not able to see the excluded files and folders in external sharebox UI"
				+"<br>Part 2. Verify external user able to view and access all the folder files via external sharebox UI after the exclusion is removed for folders files");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application","Invoke the application under test @ "+ properties.getProperty("GmailUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String message = dataTable.getData("Sharebox", "Message");
		String folderdetails = dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		
		String moreSettingsOptionNames[] = null;
		if (moreSettingsOptionName.contains(";")) 
		{
		moreSettingsOptionNames = moreSettingsOptionName.split(";");
		}
		
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(folderdetails);
		shareboxPg.verifyfolderOrfiledisplayedinExShareboxUINegative(filePath);
		shareboxPg.verifyfolderOrfiledisplayedinExShareboxUINegative(fileName);
		shareboxPg.uploadFileInSharedFolder(filePath, fileName);
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application","Invoke the application under test @ "+ properties.getProperty("ApplicationUrl"), Status.DONE);
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.openFolder(folderdetails);
		sitesPage.clickOnMoreSetting(filePath);
		sitesPage.commonMethodForClickOnMoreOptionLink(filePath, moreSettingsOptionNames[2]);
		docLibPg.clickOnOkBtnInPopup();
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName, moreSettingsOptionNames[2]);
		docLibPg.clickOnOkBtnInPopup();
		driver.close();
		driver.switchTo().window(tabs.get(0));
		shareboxPg.navigateToShareboxLinkFromMail(folderdetails);
		shareboxPg.verifyfolderOrfiledisplayedinExShareboxUI(filePath);
		shareboxPg.verifyfolderOrfiledisplayedinExShareboxUI(fileName);
		}
	@Override
	public void tearDown() {
		// Nothing to do
	}
}