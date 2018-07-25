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
public class AUT_AG_038P2 extends TestCase{

private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_28()
	{
		testParameters.setCurrentTestDescription("Verify that reviewer gets mails if no document is attach to the workflow - Part 2: Verify mail");
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
	

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);	
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		gmailobj.enterCredentials();	
		String Message = dataTable.getData("Workflow", "Message");
		
	
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		String Msg = Message + "_" + strDate;
	//	gmailobj.searchWFStatusmessageNew(Msg);
		String subject = dataTable.getData("Gmail", "Subject").replace("CRAFT", Msg);
		String header = dataTable.getData("Gmail", "ContentOne");
		
		System.out.println(subject);
	//	gmailobj.commonMethodforMailchecking(subject,subject);
		
		pearsonMailObj.searchMailWithSubjectforSingleReview("Review Only");
		
		ArrayList<String> content=new ArrayList<String>();
		String assigneeName=dataTable.getData("Gmail","AssigneeName");
		String initatorName=dataTable.getData("Gmail", "InititatorName");
		
		content.add(assigneeName);
		content.add(initatorName);
		content.add(Msg);		
		pearsonMailObj.validateMailContents(content);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}

}
