package testscripts.misc4;

import java.text.ParseException;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_377P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_034P2()
	{
		testParameters.setCurrentTestDescription("Validate Review and approve Archive submission task mail for Line of business English for approved task <br> Part2: Accept task and Check mail");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("GmailUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUserforPearsonMail(signOnPage);
		
		AlfrescoLoginPage alfrescoSignOnPage = new AlfrescoLoginPage(scriptHelper);

		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		AlfrescoPearsonMailPageTest pearsonMailObjTest = new AlfrescoPearsonMailPageTest(scriptHelper);
		AlfrescoTasksPage taskPgObj = new AlfrescoTasksPage(scriptHelper);
		
		String message = dataTable.getData("Workflow", "Message");
		
		pearsonMailObj.searchMailWithSubjectTitle("Archive Pooled Task"+" "+message);
		
		String parentWindow = driver.getWindowHandle();
		
		pearsonMailObj.clickOnEditTaskLinkInArchivePooledTask();
		try {
		for(String windowID:driver.getWindowHandles())
		{
			if(!windowID.equalsIgnoreCase(parentWindow))
			{
				driver.switchTo().window(windowID);
				UIHelper.waitForPageToLoad(driver);
				functionalLibrary.loginAsValidUser(alfrescoSignOnPage);
				
				taskPgObj.approveTask();
				break;
			}
		}
		
		driver.switchTo().window(parentWindow);
		UIHelper.waitForPageToLoad(driver);
		}catch(Exception e) {
			
		}
		pearsonMailObj.searchMailWithSubjectTitle("Archive Pooled Task"+" "+message+" has been Approved");
		
		pearsonMailObjTest.verifyMailSubAndBodyForRevAppSubTask(message+" has been Approved");
	}

	@Override
	public void tearDown() {
		
	}

}
