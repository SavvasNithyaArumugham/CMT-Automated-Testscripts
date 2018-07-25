package testscripts.misc3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_313P3 extends TestCase {

	@Test
	public void sharebox_003() {
		testParameters
				.setCurrentTestDescription("Verify the user receives the notification email after 1 hour, if the Notifications is set as 'Every Hour' while Shared folder is updated with new file inside the subfolder via  external share UI"
						+ " and verify folder Path is not displayed");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {

		
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoPearsonMailPageTest pearsonMailTestObj=new AlfrescoPearsonMailPageTest(scriptHelper);
		AlfrescoPearsonMailPage pearsonMailObj=new AlfrescoPearsonMailPage(scriptHelper);
		gmailobj.enterCredentials();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		
		String strDate = sdf.format(date);	
		String folderName=dataTable.getData("MyFiles", "FileName");
		String subject = dataTable.getData("Gmail", "Subject");
		
		gmailobj.commonMethodforMailchecking(subject,subject+" "+folderName+"_"+strDate);
		
		String requiredDueDate="";
		try {
			requiredDueDate = new DateUtil().convertDateToCustomizedFormat("EEEEE, MMMMMM d", strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		gmailobj.verifyTimestampinMail(requiredDueDate);
		
		//gmailobj.searchWFmessage(message);
		String expectedMessage = dataTable.getData("Gmail", "MailBodyText");
		pearsonMailTestObj.verifyMailBodyText("FolderPath",expectedMessage);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}