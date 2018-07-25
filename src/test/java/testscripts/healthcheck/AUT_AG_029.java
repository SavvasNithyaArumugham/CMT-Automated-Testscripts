package testscripts.healthcheck;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_029 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_052()
	{
		testParameters.setCurrentTestDescription("Verify that user can Edit Offline (Check out) a mutiple files");
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
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String file1 = dataTable.getData("MyFiles", "FilePath");
		String file2 = dataTable.getData("MyFiles", "FileName");
		
		//myFiles.releaseLockOnFile("Documents");
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		myFiles.createFile(fileDetails);
		
		//docDetailsPageObj.backToFolderOrDocumentPage("");
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.selectAllItems("Multiple Files");
		
		sitesPage.clickOnSelectedItems();
	
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.verifyLockedBanner(file1);
		sitesPage.verifyLockedBanner(file2);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}