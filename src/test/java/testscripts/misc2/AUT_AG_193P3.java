package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_193P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_087()
	{
		testParameters.setCurrentTestDescription("Able to follow templates, when a template is edited, then the follower will receive an indicaton in their dashboard<br> - Part3: Navigate to the configuerd user Email ID and check Mail");
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
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String userName = dataTable.getData("MyFiles", "UserFullName");
		String moreSettOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		String tempFollowedFileUploadNewVersMsg = "USER_NAME has deleted FILE_NAME";
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		pearsonMailObj.searchFollowedFileMessageByDocAction(fileName, userName, moreSettOptVal, tempFollowedFileUploadNewVersMsg);
	}

	@Override
	public void tearDown() {
		
	}

}