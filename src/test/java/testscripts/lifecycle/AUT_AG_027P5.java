package testscripts.lifecycle;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_027P5 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_008()
	{
		testParameters.setCurrentTestDescription("Verify that email notification is sent to an user1 who follows file when user 2 attaches lifecycle"+
	"Verify that email notification is not sent to an user1 who unfollowed file when user 2 change lifecycle state"
				);
		
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
		String fileName = dataTable.getData("MyFiles", "FileName");
		String userName = dataTable.getData("MyFiles", "UserFullName");
		String moreSettOptVal = dataTable.getData("MyFiles", "MoreSettingsOption");
		String tempFollowedFileUploadNewVersMsg1 = "USER_NAME has attached lifecycle to the FILE_NAME";
		String tempFollowedFileUploadNewVersMsg2 = "USER_NAME has changed lifecycle's state FILE_NAME";
		//String tempFollowedFileUploadNewVersMsg3 = "USER_NAME has dettached lifecycle to the FILE_NAME";
		
		pearsonMailObj.searchFollowedFileMessageByDocAction(fileName, userName, moreSettOptVal, tempFollowedFileUploadNewVersMsg1);
		
		//pearsonMailObj.searchFollowedFileMessageByDocAction(fileName, userName, moreSettOptVal, tempFollowedFileUploadNewVersMsg3);
	    
		try{
			pearsonMailObj.searchFollowedFileMessageByDocAction(fileName, userName, moreSettOptVal, tempFollowedFileUploadNewVersMsg2);
			report.updateTestLog("AUT_AG_027P5 status:", "<br>AUT_AG_027P5 Testcase is Failed", Status.FAIL);
		}
		catch (Exception e){
			report.updateTestLog("AUT_AG_027P5 status:", "<br>AUT_AG_027P5 Testcase is Passed", Status.PASS);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
