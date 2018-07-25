package testscripts.workflows;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * 
 * @author Lokesh
 */
public class AUT_AG_039P2 extends TestCase{

private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_29()
	{
		testParameters.setCurrentTestDescription("Verify that reviewer gets mail if initiator again assign the task when task is rejected by approver - Part 2: Verify mail");
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
		//String subject = dataTable.getData("Gmail", "ContentOne");	
		String comment = dataTable.getData("Workflow", "Comment");
		String dueDate = new DateUtil().getCurrentDate();
		Message = Message+"_"+dueDate;		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);	
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		gmailobj.enterCredentials();	
		pearsonMailObj.searchMailWithSubjectforSingleReview("Review Only");
	//	Message=subject+" "+Message;
	//	gmailobj.searchWFmessage(Message);	
		
		gmailobj.verifyCommentsinMail(comment);
		/*
		ArrayList<String> content=new ArrayList<String>();
		String assigneeName=dataTable.getData("Gmail","AssigneeName");
		String initatorName=dataTable.getData("Gmail", "InititatorName");
		String taskName=dataTable.getData("Gmail", "TaskName");		
		content.add(assigneeName);
		content.add(initatorName);
		content.add(taskName);		
		pearsonMailObj.validateMailContents(content);
		gmailobj.verifyCommentsinMail("test");*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
