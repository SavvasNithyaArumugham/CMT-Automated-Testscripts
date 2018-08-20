package testscripts.misc2;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_163 extends TestCase
{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_059()
	{
		testParameters.setCurrentTestDescription("Verify that link in the email notification for the recent activities should be in the correct format and navigated to the user notification page when clicked.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		
	
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		gmailobj.enterCredentials();
		gmailobj.searchRecentActivityMail();
		//Added part of NALS 
		gmailobj.verifyRecActURL();
		//Added part of NALS 
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.switchtab(1);
		
		String pageName = driver.getTitle();
		System.out.println(pageName);
		if(pageName.equalsIgnoreCase("Sign In"))
		{
			functionalLibrary.loginAsValidUser(signOnPage);
		}
		String nowPageName=driver.getTitle();
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}