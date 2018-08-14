package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_198P4 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTC198Part4()
	{
		testParameters.setCurrentTestDescription(" Asset will have the associated detail of the change <br> - Part4: Navigate to the first user registerd Email Id who followed the document earlier");
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
		pearsonMailObj.searchFollowedFileMessageByDocAction(fileName, userName, moreSettOptVal, tempFollowedFileUploadNewVersMsg);
	}

	@Override
	public void tearDown() {
		
	}

}