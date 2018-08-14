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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_043P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_136()
	{
		testParameters.setCurrentTestDescription("Verify that assignee when approves the task and email notification is sent that Task is completed - Part2: Login has reviewer and check files are visible");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
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
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
	//	homePageObj.openGmailTab();
		gmailobj.enterCredentials();
		String Message = dataTable.getData("Workflow", "Message");
		String subject = dataTable.getData("Gmail", "Subject");
	
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		String Msg = Message + "_" + strDate;
	//	gmailobj.searchWFStatusmessageNew(Msg);
		
		gmailobj.commonMethodforMailchecking(subject,Msg);
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}