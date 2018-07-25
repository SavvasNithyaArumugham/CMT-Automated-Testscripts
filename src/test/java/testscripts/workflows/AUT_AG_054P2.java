package testscripts.workflows;

import java.text.ParseException;

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

public class AUT_AG_054P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_40()
	{
		testParameters.setCurrentTestDescription("To verify assignee name in the task assignment notification email - 'Simple Task' Workflow - Part2");
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
		//pearsonMailObj.searchmailwithWFmessage();
		
		String message = dataTable.getData("Workflow", "Message");
		String dueDate = dataTable.getData("Workflow", "DueDate");
		String assignNme = dataTable.getData("Gmail", "AssigneeName");
		System.out.println(""+assignNme);
		
		String actAssignNme = pearsonMailObj.openMailAndCheckAssigneeNme(assignNme);
		System.out.println(""+actAssignNme);
		if(actAssignNme.contains(assignNme))
		{
			report.updateTestLog("Verify assignee name in the task assignment notification email",
					"Assignee Name displayed successfully in the Mail received"+actAssignNme, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify assignee name in the task assignment notification email",
					"Assignee Name wrongly displayed in the Mail received. Assignee Name displayed --> "+actAssignNme, Status.FAIL);
		}
		
		String inititatorName = dataTable.getData("Gmail", "InititatorName");
		System.out.println(""+inititatorName);
		
		String intAssignNme = pearsonMailObj.openMailAndCheckIntiatorNme(inititatorName);
		System.out.println(""+intAssignNme);
		if(intAssignNme.contains(inititatorName))
		{
			report.updateTestLog("Verify Initiator name in the task assignment notification email",
					"Initiator Name displayed successfully in the Mail received"+intAssignNme, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify Initiator name in the task assignment notification email",
					"Initiator Name wrongly displayed in the Mail received. Assignee Name displayed --> "+intAssignNme, Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
