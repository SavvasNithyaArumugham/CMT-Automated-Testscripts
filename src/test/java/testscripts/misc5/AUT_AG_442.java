package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_442 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void userOptionNonAdminFrenchCheck()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-5567_Verify non admin user able to see the french translation for below custom links under user profile" + 
				"<br> Contact Support" + 
				"<br> Alfresco Forum" + 
				"<br> Training");
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
		String[] userOption = dataTable.getData("MyFiles", "BrowseActionName").split(",");
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
		
	
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}