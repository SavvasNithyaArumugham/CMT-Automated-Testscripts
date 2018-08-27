package testscripts.sanity.usppewip;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_206P1 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_039()
	{
		testParameters.setCurrentTestDescription("To verify ,for user who is not in site owner group,Sharebox user group, Repository Manager group is not getting below options under More options"+
													"<br>Share Folder Externally"+
													"<br>Copy to Repository"
													);
								
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
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
	//	AlfrescoSiteMembersPage siteMemPg = new AlfrescoSiteMembersPage(scriptHelper);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		String folder = dataTable.getData("MyFiles", "CreateFolder");


		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.performInviteUserToSite(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
		docLibPg.deleteAllFilesAndFolders();
		myFiles.createFolder(folder);

		}
	
	@Override
	public void tearDown() {
		
	}

}
