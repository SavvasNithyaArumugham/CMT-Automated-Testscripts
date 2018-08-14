package testscripts.workflows;

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
public class AUT_AG_079_B_P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void run177BPart2()
	{
		testParameters.setCurrentTestDescription("Verify that user recieves an email notification when change the 'Due Date'- Part 2: Verify mail");
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
		String message = dataTable.getData("Workflow", "Message");		
		String comment = dataTable.getData("Workflow", "Comment");
		
		String dueDate = new DateUtil().getCurrentDate();
		String workflowName = message +"_"+ dueDate;
		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);		
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(workflowName);
		gmailobj.verifyCommentsinMail(comment);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}