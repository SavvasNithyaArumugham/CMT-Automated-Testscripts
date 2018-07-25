package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_441 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void userOptionAndActivePreviewFrenchCheck()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-5567_Verify admin user able to see the french translation for below custom links under user profile" + 
				"<br> Contact Support" + 
				"<br> Alfresco Forum" + 
				"<br> Training" + 
				"<br>2.ALFDEPLOY-5550_Verify user able to see the french translation for Active Preview option via hover"+
				"<br>3.ALFDEPLOY-5550_Verify user able to see the french translation for Active Preview option via document preview page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesPageTest sitesPgTest=new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPgTest=new AlfrescoDocumentDetailsPageTest(scriptHelper);
		String[] userOption = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingsOptionName= dataTable.getData("MyFiles", "MoreSettingsOption");
		String actual;
		homePage.clickonUserMenu();
		for(String option:userOption) {
			actual=homePage.getUserOptionValue(option);
			if(!actual.isEmpty() && actual.equalsIgnoreCase(option)) {
				report.updateTestLog("Verify "+option+ " profile link","profile link same as expected <br><b>Expected: </b>"+option+"<br><b>Actual: </b>"+actual, Status.PASS);
			}else {
				report.updateTestLog("Verify "+option+ " profile link","profile link not same as expected <br><b>Expected: </b>"+option+"<br><b>Actual: </b>"+actual, Status.FAIL);
			}
		}
		
		homePage.navigateToSitesTab();
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.clickOnMoreSetting(fileName);
		sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(fileName,moreSettingsOptionName);
		
		UIHelper.waitFor(driver);
		myFiles.openAFile(fileName);
		docDetailsPgTest.verifyDocAction(moreSettingsOptionName);
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}