package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_059P5 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_41()
	{
		testParameters.setCurrentTestDescription("To verify comment detail in the task re-assignment notification email - 'Simple Review and Approve' Workflow (Single Assignee) <br> Part3:Verify Comment details in Email");
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
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String comment = dataTable.getData("Workflow", "Comment");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;	
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(Msg);
		gmailobj.verifyCommentsinMail(comment);
		
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub
		
	}

}
