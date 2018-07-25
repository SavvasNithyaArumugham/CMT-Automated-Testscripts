package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_181 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_076()
	{
		testParameters.setCurrentTestDescription("Verify that when User add a folder to my favorite from Shared files then it will show under My Favorite option ");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);		
		AlfrescoDocumentLibPage docLibPgObj = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPagetest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		
		
		String link = dataTable.getData("MyFiles", "MoreSettingsOption");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "FileName");
		
		
		homePageObj.navigateToSharedFilesTab();	
		UIHelper.waitForPageToLoad(driver);
		if(sitesPage.Checkdocument(folderName)){
			appSearchPg.deletedocument(folderName);
			}		
		myFiles.createFolder(folderDetails);		
		UIHelper.waitForPageToLoad(driver);
		docLibPgObj.clickOnDocOptions(folderName, link);	
		UIHelper.waitFor(driver);
		docLibPagetest.verifyFavoptionselected();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		taskPage.filterWFtasks();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		docLibPgObj.checkFileIsAvailable(folderName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}