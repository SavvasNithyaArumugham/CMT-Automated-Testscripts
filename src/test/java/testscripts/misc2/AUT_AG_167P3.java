package testscripts.misc2;

import java.util.regex.Pattern;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_167P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_063()
	{
		testParameters.setCurrentTestDescription("Verify that all site members receives an Email notification who are following the same asset,when asset get Edit Offline by the member(depending upon the role in site)<br> - Part3: Navigate to the configuerd user Email ID and check Mail");
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
		
		String finalFileName;
		if (fileName.contains(".")) {
			String splitVal[] = fileName.split(Pattern.quote("."));
			String part1 = splitVal[0];
			finalFileName = part1 +" (Working Copy)"+ "." + splitVal[1];
		} else {
			finalFileName = fileName;
		}
		
		String tempFollowedFileEditOfflineMsg = "USER_NAME has edited-offline FILE_NAME";
		
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		pearsonMailObj.searchFollowedFileMessageByDocAction(finalFileName, userName, moreSettOptVal, tempFollowedFileEditOfflineMsg);
	}

	@Override
	public void tearDown() {
		
	}

}
