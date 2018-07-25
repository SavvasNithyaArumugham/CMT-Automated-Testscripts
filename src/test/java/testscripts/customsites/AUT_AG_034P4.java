package testscripts.customsites;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_034P4 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_018()
	{
		testParameters.setCurrentTestDescription("1. Verify the Request to join button should be visible for the moderate site"
				+ "<br>2. To verify that the site manager receives an email notification, when user requests to to Join a site"
				+ "<br>3. To verify that Site Manager is able to approve the request to Join the site"
				+ "<br>4. To verify that the user should get approval mail notification email after the site manager approves the request to join the site"
				+ "<br>5. The format of the email received to the site manager should be in the following manner:"
				+ "<br> Hi Manager name,"
				+ "<br> User, User name, has requested to join the sitename site."
				+ "<br> Click this link to view the request:"
				+ "<br> Request URL. Sample: http://gwipqa.pearson.com/share/pa"
				+ "<br> Part 4: User should get mail after manger approves the request");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String message = dataTable.getData("Workflow", "Message");
		String siteName=sitesPage.getCreatedSiteName();
		String tempsubject= message+" "+siteName;
		String subject =tempsubject+" "+"site is approved";
		pearsonMailObj.searchMailWithSubjectTitle(subject);
	}

	@Override
	public void tearDown() {
		
	}

}
