package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_035P3 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_26()
	{
		testParameters.setCurrentTestDescription("1. Verify the template for General Info in Worfkow details page of Simple Review and Approve Workflow."
				 +"<br>2. Verify that the text in message field is same as the text given in description field by initiator while initiating Simple Review and Approve Workflow."
				+"<br>3. Verify that no field is displayed more than once in General info in Simple Review and Approve Workflow Details Page."
				 + "<br>4. Verify that Due date in Workflow summary and General info is same in Workflow Detail page for Simple Review and Approve Workflow."
				+"<br>5. Verify that Priority in Workflow summary and General info is same in Workflow Detail page for Simple Review and Approve Workflow."
				 +"<br>6. Verify that Status in Workflow summary and General info is same in Workflow Details page for Simple Review and Approve Workflow ."
				+"<br>7. Verify that Locale field is not displayed in Workflow Details page for Simple Review and Approve Workflow"
				 +"<br>8.Verify the template for the mail received by assignee for Simple Review and approve Workflow ."
				+"<br>9. Verify that assignee get email notification once due date is changed for any task."
		+"<br>10. Verify that detail Field in workflow detail page for Simple review and approve workflow."
				+"<br>11.Verify that text of value Field in workflow detail page for Simple review and approve workflow.");
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
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		String task = dataTable.getData("Workflow", "Message");
		String dueDate = new DateUtil().getCurrentDate();
		String Message = task+"_"+dueDate;
		String subject = dataTable.getData("Gmail", "Subject");
		gmailobj.commonMethodforMailchecking(subject,Message);
		pearsonMailObj.verifyTaskAssignedMailTemplate();
		
		
		String comment = dataTable.getData("Workflow", "Comment");
	}

	@Override
	public void tearDown() {
		
	}

}
