package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.tests.AlfrescoPearsonMailPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_175P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_070()
	{
		testParameters.setCurrentTestDescription("Verify that the text 'To review content please go to' and link has been removed from email notification when user performs 'all aspect template download' operation from search results<br> - Part2: Navigate to the configuerd user Email ID and check for the All Aspect Template download email");
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
		
		String messages[]=null;
		try {
			messages = new FileUtil().readSetOFDataFromFile("src/test/resources/AppTestData/TestOutput/ConfMessages.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String staticMessageVal1 = "DOWNLOAD_METADATA COMPLETED Id";
		
		for(String message:messages)
		{
			if(message!=null)
			{
				if(message.contains(":"))
				{
					String splittedMessage[] = message.split(":");
					
					if(splittedMessage!=null)
					{
						String finalMessage = staticMessageVal1+splittedMessage[1];
						AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
						pearsonMailObj.commonMethodForSearchMail("All Aspect Template Download", finalMessage);
						
						String expectedBulkDownloadMailMessage = dataTable.getData("Gmail", "MailBodyText");
						
						AlfrescoPearsonMailPageTest pearsonMailTestObj = new AlfrescoPearsonMailPageTest(scriptHelper);
						pearsonMailTestObj.verifyMailBodyText("All Aspect Template Download",expectedBulkDownloadMailMessage);
						
						pearsonMailObj.clickOnInboxItem();
					}
				}
			}
		}
	}

	@Override
	public void tearDown() {
		
	}

}
