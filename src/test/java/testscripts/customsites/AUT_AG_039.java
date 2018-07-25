package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_039 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_022()
	{
		testParameters.setCurrentTestDescription("1. Verify that Non- Admin user(who is not a part of Group SITE_ADMIN and ALFRESCO_ADMINISTRATORS) is not able to see Create Site option under Sites Pull-down menu."
				+ "<br>2. Verify that non admin user (who is not a part of Group SITE_ADMIN and ALFRESCO_ADMINISTRATORS) is not able to see create site option in Sites tab from simple search result page"
				+ "<br>3. Verify that non admin user (who is not a part of Group SITE_ADMIN and ALFRESCO_ADMINISTRATORS) is not able to see create site option in Sites tab from Advance search result page"
				+ "<br>4. Verify that non admin user (who is not a part of Group SITE_ADMIN and ALFRESCO_ADMINISTRATORS) is not able to see create site option in Sites tab from Search Manager page");
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
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoHomePageTest homePageTstObj = new AlfrescoHomePageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		
		homePage.navigateToSitesTab();
		homePageTstObj.verifyCreatSiteLinkNotDisplyedInSite();
		
		String fileName = dataTable.getData("MyFiles", "FileName");		
		String filePath = dataTable.getData("MyFiles", "FilePath");		
		
		homePage.navigateToMyFilesTab();		
		
		if (!sitesPage.Checkdocument(fileName)) {
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}
		
		appSearchPg.performSearch();
		homePage.navigateToSitesTab();
		homePageTstObj.verifyCreatSiteLinkNotDisplyedInSite();
		
		homePage.navigateToAdvSearch();
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		homePage.navigateToSitesTab();
		homePageTstObj.verifyCreatSiteLinkNotDisplyedInSite();
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}