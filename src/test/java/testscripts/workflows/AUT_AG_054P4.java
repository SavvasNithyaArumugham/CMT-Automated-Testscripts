package testscripts.workflows;

import java.text.ParseException;
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
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_054P4 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_40()
	{
		testParameters.setCurrentTestDescription("To verify initiator first name in the task completion notification email - 'Simple Review and Approve' Workflow - Part3");
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
	
		String requiredDueDate="";
		try {
			requiredDueDate = new DateUtil().convertDateToCustomizedFormat("EEEEE, MMMMMM d", strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			
		gmailobj.verifyTimestampinMail(requiredDueDate);
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		String inititatorName = dataTable.getData("Gmail", "InititatorName");
		String AssigneeName = dataTable.getData("Gmail", "AssigneeName");
		String assigneNmeXPathFinal = pearsonMailObj.assigneNmeXPath.replace("CRAFT", AssigneeName);
		String initiatorXPathFinal = pearsonMailObj.intNmeXPath.replace("CRAFT", AssigneeName);
		
		String assignerName = UIHelper.getTextFromWebElement(driver, initiatorXPathFinal);
		String assigneeName = UIHelper.getTextFromWebElement(driver, assigneNmeXPathFinal);
		System.out.println(assigneeName);
		System.out.println(AssigneeName);	
		
		if(assignerName.contains(inititatorName))
		{
			report.updateTestLog("Verify Initiator name in the task Completion notification email",
					"Initiator Name displayed successfully in the Mail received "+assignerName, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify Initiator name in the task Completion notification email",
					"Initiator Name wrongly displayed in the Mail received. Initiator Name displayed --> "+assignerName, Status.FAIL);
		}
		
		if(assigneeName.contains(AssigneeName))
		{
			report.updateTestLog("Verify Assignee name in the task Completion notification email",
					"Assignee Name displayed successfully in the Mail received "+assigneeName, Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify Assignee name in the task Completion notification email",
					"Assignee Name wrongly displayed in the Mail received. Initiator Name displayed --> "+assigneeName, Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		
	}

}
