package testscripts.workflows;

import org.testng.annotations.Test;










import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;




/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_077 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_56()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to view open tasks");
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
		
		AlfrescoTasksPage taskPage=homePage.navigateToTasksTab();
		
		AlfrescoTasksPageTest taskTestPage=new AlfrescoTasksPageTest(scriptHelper);
		taskTestPage.verifyActiveTasks();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}