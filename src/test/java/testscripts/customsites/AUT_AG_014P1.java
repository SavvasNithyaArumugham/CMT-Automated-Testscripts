package testscripts.customsites;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_014P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_014P1()
	{
		testParameters.setCurrentTestDescription("Verify if user of the Program Site is not able to update root folder named with Component Site in Program's document library - Part 1");
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
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		String testOutputMultipleFilePath = "src/test/resources/AppTestData/TestOutput/MultipleSiteDetails.txt";
		new FileUtil().clearFile(testOutputMultipleFilePath);
		sitesPage.createMultipleSitesWithTimeStamp(siteDetails);		
		
		ArrayList<String> sitedata = null;
		try {
			sitedata = new FileUtil().readListOFDataFromFile(testOutputMultipleFilePath);
			System.out.println("DATA : " + sitedata.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(sitedata.get(0));
		sitesPage.performInviteUserToSite(sitedata.get(0));
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
