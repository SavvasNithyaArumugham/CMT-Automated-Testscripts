package testscripts.session;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMarklogicPageTest;
import com.pearson.automation.utils.CC_API_Helper;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Naresh Kumar Salla
 */
public class AUT_AG_ClickOnLeftSideDocFilterInSiteDocLibUnderXMins extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_CNF() {
		testParameters
				.setCurrentTestDescription("Create folder level upto 500 levels in alfresco");
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
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String labelName = dataTable.getData("Labels", "LabelName");
		String minutes = dataTable.getData("Labels", "Minutes");
		
		homePage.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		report.updateTestLog("Verify the current time",
				"Current Time: "+new DateUtil().getCurrentDateAndTime(),
				Status.DONE);
		
		int minuteInNumeric = Integer.parseInt(minutes);
		
		try{
			Thread.sleep(1000*minuteInNumeric*60);
		}
		catch(Exception e)
		{
		}
		
		report.updateTestLog("Wait upto "+minutes+" minutes without perform any action in application",
				"User waited "+minutes+" minutes",
				Status.DONE);
		
		docLibPg.clickOnDocOrLibraryFilterItem("Documents", labelName, minutes,"Negative");
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}