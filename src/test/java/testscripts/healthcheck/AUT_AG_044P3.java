package testscripts.healthcheck;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_044P3 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_118P3()
	{
		testParameters
		.setCurrentTestDescription("1.To check if the coordinator is able to reject the workflow task that is under review."
				+"<br>2.To check if the initiator receives an email once the task is rejected by coordinator."
				+"<br>3.To check if the task reverts to open state(claimable/unlocked) once the task is rejected by coordinator."
				+"<br>4.To check if the initiator is able to resubmit the rejected workflow task to coordinator by clicking on Continue Review and Approve button."
				+"<br><b> Part 3. Create mail after rejection");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("GmailUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		String task = dataTable.getData("Workflow", "Message");		
		String subject = dataTable.getData("Gmail", "Subject");	
		String status = dataTable.getData("Workflow", "Description");
		String userName = dataTable.getData("Sites",
				"InviteUserName");
		String dueDate = new DateUtil().getCurrentDate();
		String Message = subject+" "+task+"_"+dueDate;		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(Message);	
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}