package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_069P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTCAUT_AG_251P3()
	{
		testParameters.setCurrentTestDescription("Verify that all site members receives an Email notification who are following the same asset,when asset get Edit Offline by the member - Part3");
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
		String tempFollowedFileUploadNewVersMsg = "USER_NAME has updated FILE_NAME";
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		pearsonMailObj.searchFollowFileMessageByDocAction(fileName, userName, moreSettOptVal, tempFollowedFileUploadNewVersMsg);
	}

	@Override
	public void tearDown() {
		
	}

}
