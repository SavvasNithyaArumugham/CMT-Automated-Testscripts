package testscripts.misc3;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_287P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3()
	{
		testParameters.setCurrentTestDescription("Verify that user with Coordinator role have permissions to Deletecontent created by another user<br> - Part2:Accept invitation to join in Site and perform Delete operation");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		/*boolean isDisplayedSite = homePageObj.commonMethodForCheckTaskInMyTasksDashlet(sourceSiteName);
		
		if(isDisplayedSite)
		{
			homePageObj.clickOnTaskInMyDashlet(sourceSiteName);
			taskPage.performAcceptOnTask(sourceSiteName);
		}*/
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFilesTestObj.verifyUploadedMultipleFiles(fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileName, moreSettingsOption);
	
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);
		
		docLibPg.clickDeleteBtnInDeleteDocumentPopup();
		
		myFilesTestObj.verifyDocumentDeletion(fileName);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}