package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_098P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_088()
	{
		testParameters.setCurrentTestDescription("To verify that related activities displayed in the 'My Activity' dashlet, when a user leaves the site - Part 3");
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
		
		AlfrescoHomePageTest homePageTest = new AlfrescoHomePageTest(scriptHelper);
		
		String activityDid = dataTable.getData("MyActivities", "ActivityDid");
		String activityName = dataTable.getData("MyActivities", "TitleName");
		String activityType = dataTable.getData("MyActivities", "MyActivityDropDownValue");
		
		String menuToNavigate = dataTable.getData("Sites", "CustomizeSiteMenuToNavigate");
		
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String siteName = "";
		try {
			siteName = new FileUtil().readDataFromFile(testOutputFilePath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		//homePageTest.verifyUserActivityDisplayedInMyActivityDashlet();		
		homePageTest.verifyUserActivityDisplayedInMyActivityDashlet(activityName, activityDid, siteName,
				menuToNavigate, activityType);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
