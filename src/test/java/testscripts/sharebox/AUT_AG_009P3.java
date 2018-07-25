package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_009P3 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void sharebox_009()
	{
		testParameters.setCurrentTestDescription("Verify the internal user and other external users(to whom folders are shared) receives the activity email, if an external user uploads a file into the Shared Folder"
				+ "<br>Part3: Navigate to the internal user mailbox and verify that the user received an activity email");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUserforPearsonMail(signOnPage);
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		if(pearsonMailObj.isMailWithSubject("New File uploaded by")){
			report.updateTestLog("Verify the Internal user mailbox",
					"Mail received successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the Internal user mailbox",
					"Mail not received", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
