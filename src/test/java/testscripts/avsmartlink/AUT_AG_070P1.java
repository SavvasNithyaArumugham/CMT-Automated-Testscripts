package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_070P1 extends TestCase {

	@Test
	public void sharebox_011() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3724_Verify MD Pop up smart link object is hidden in Sharebox UI when smart link folder is shared externally.Part 2:Verify external Share UI for smart link folder");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		String extUrlTitle = dataTable.getData("MyFiles", "RelationshipName");

		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(extUrlTitle);
		if (!shareboxPg.verifyFilesInExternalSharebox(extUrlTitle)) {
			report.updateTestLog("Verify files in external sharebox UI",
					"File is not present in external sharebox UI" + "<b>FileName:</b>" + extUrlTitle, Status.PASS);
		} else {
			report.updateTestLog("Verify files in external sharebox UI",
					"File is present in external sharebox UI" + "<b>FileName:</b>" + extUrlTitle, Status.FAIL);

		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}