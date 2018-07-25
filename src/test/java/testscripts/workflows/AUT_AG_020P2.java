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
public class AUT_AG_020P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_109P2()
	{
		testParameters
		.setCurrentTestDescription("1. To check if a member of the media hub alfresco group is able to see the Media HUB request workflow when user starts a workflow in Alfresco brazil"+
				"<br>2. To check if the Initiator receives email notification for MH Workflow once initiator starts workflow. "
				+"<br>3. To check if the Media HUB request can be assigned to a group of users(known as coordinators). Note : Only Media hub group members can be assigned workflow tasks for this type of workflow. Also kindly note that the text box against the reviewer label is us"
				+"<br>4. To check if the initiator is able to see the created workflow task Under : Tasks > workflows I have started."
				+"<br><b> Part 2: Verify mail </b>");
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
		String Message = dataTable.getData("Workflow", "Message");		
		String subject = dataTable.getData("Gmail", "Subject");	
		String dueDate = new DateUtil().getCurrentDate();
		Message = subject+" "+Message+"_"+dueDate;		
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