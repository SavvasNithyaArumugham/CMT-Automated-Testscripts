package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_070P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_49()
	{
		testParameters.setCurrentTestDescription("Verify Task rejection by the assigned user");
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
		signOnPage.loginAsValidUser();
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage tasksPage = homePage.navigateToTasksTab();
		
		String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
	 //   tasksPage.rejectTask();	  
	    try{
	    	String temp = new FileUtil().readDataFromFile(testOutputFilePath);
			String taskName = "Invitation to join " + temp + " site";
			tasksPage.rejectInvitation(taskName);
	    }
	    catch (Exception e) {
	    	report.updateTestLog(
					"Find the Task and Reject",
					"Failed to Reject Task.Please verify Active is created and avaialble to Reject.",
					Status.FAIL);
	    	
	    }
	    
	    
	  }
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}