package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_044P5 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void TC948Part4()
	{
		testParameters.setCurrentTestDescription("Verify that initiator is able to continue with review and approve task for rejected task by providing comments - 'Simple Review and Approve' Workflow(Multiple Assignees) <br> Part3:Verify Comment details in Email");
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
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		String message = dataTable.getData("Workflow", "Message");
		String currentDate = new DateUtil().getCurrentDate();
		String finalTaskName = message+"_"+currentDate;
		String comment = dataTable.getData("Workflow", "Comment");
		
		pearsonMailObj.searchMailWithSubjectTitle(finalTaskName);
		
		AlfrescoPearsonMailPageTest pearsonMailTest = new AlfrescoPearsonMailPageTest(scriptHelper);
		pearsonMailTest.verifyCommentDetails(comment);
		
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
