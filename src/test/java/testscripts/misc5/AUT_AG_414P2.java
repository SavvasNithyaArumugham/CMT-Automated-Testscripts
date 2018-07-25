package testscripts.misc5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_414P2 extends TestCase {

	@Test
	public void linkedShareFolderUploadFileMailCheckInShareboxP2() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3527_Verify the ShareBox New file available in shared folder activity email is"
						+ "triggered when the shared folder is linked with any folders inside.-Part2");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

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
		
		
	    String folderName = dataTable.getData("MyFiles", "FileName").split(",")[1];
	    String fileName= dataTable.getData("MyFiles", "FileName").split(",")[0];

	    GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
  	     Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);	
		String requiredDueDate="";
		try {
			requiredDueDate = new DateUtil().convertDateToCustomizedFormat("EEEEE, MMMMMM d", strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(folderName);
		if (gmailobj.verifyTimeStampMatchInMail(requiredDueDate)) {
			report.updateTestLog("Verify activity mail triggered", "Activity email triggered for"
					+ "<b> FolderName:</b>" + folderName, Status.PASS);
		}else {
			report.updateTestLog("Verify activity mail triggered", "Activity email triggered not triggered for"
					+ "<b> FolderName:</b>" + folderName, Status.FAIL);
		}
		UIHelper.waitFor(driver);
		gmailobj.searchWFmessage(fileName);
        if (gmailobj.verifyTimeStampMatchInMail(requiredDueDate)) {
        	report.updateTestLog("Verify activity mail triggered", "Activity email triggered for"
					+ "<b> FileName:</b>" + fileName, Status.PASS);
		}else {
			report.updateTestLog("Verify activity mail triggered", "Activity email triggered not triggered for"
					+ "<b> FileName:</b>" + fileName, Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}