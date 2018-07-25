package testscripts.customsites;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_004 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_004()
	{
		testParameters.setCurrentTestDescription("1. Verfiy the user is able to provide numerical value for Edition Number Field in Program Component Dashlet"
				+"<br>2. Verfiy the user is able to provide numerical value for Copyright Year Field in Program Component Dashlet"
				+"<br>3. Verfiy the user is not able to provide non- numerical value for Copyright Year Field in Program Component Dashlet"
				+"<br>4. Verfiy the user is not able to provide non- numerical value for Edition Number Field in Program Component Dashlet");
		
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);
		
		String fieldNames = dataTable.getData("Sites", "SiteDashletFieldNames");
		sitesDashboardPageTest.verifyProgramComponentSiteFields(fieldNames);
		
		String programComponentSiteFieldValue = dataTable.getData("Sites", "SiteDashletFieldValues");
		String splittedPCSFieldValues[] = null;
		if(programComponentSiteFieldValue.contains(";"))
		{
			splittedPCSFieldValues = programComponentSiteFieldValue.split(";");
		}
		
		String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
		String splittedDispMsgValues[] = null;
		if(expectedMessageVal.contains(";"))
		{
			splittedDispMsgValues = expectedMessageVal.split(";");
		}
		
		sitesDashboardPage.updateProgramComponentSiteFieldData(splittedPCSFieldValues[0]);
		docLibPgTest.verifyConfirmationDailogMessage(splittedDispMsgValues[0]);

		sitesDashboardPage.updateProgramComponentSiteFieldData(splittedPCSFieldValues[1]);
		docLibPgTest.verifyConfirmationDailogMessage(splittedDispMsgValues[0]);
		
		sitesDashboardPage.updateProgramComponentSiteFieldData(splittedPCSFieldValues[2]);
		docLibPgTest.verifyConfirmationDailogMessage(splittedDispMsgValues[1]);
		
		sitesDashboardPage.updateProgramComponentSiteFieldData(splittedPCSFieldValues[3]);
		docLibPgTest.verifyConfirmationDailogMessage(splittedDispMsgValues[2]);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}