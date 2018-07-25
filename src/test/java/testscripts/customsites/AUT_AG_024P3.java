package testscripts.customsites;

import java.text.ParseException;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_024P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_009()
	{
		testParameters.setCurrentTestDescription("To verify that Site Manager is able to reject the request to Join the site<br> Part 3: Leave site from mail");
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
		AlfrescoWorkflowPage workflowPg = new AlfrescoWorkflowPage(scriptHelper);
		AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String message = dataTable.getData("Workflow", "Message");
		String siteName=sitesPage.getCreatedSiteName();
		String subject= message+" "+siteName; 
		pearsonMailObj.searchMailWithSubjectTitle(subject);
		
		pearsonMailObj.clickOnLinkInReceivedMail();
		
		String pageName = driver.getTitle();
		if(pageName.equalsIgnoreCase("Sign In"))
		{
			functionalLibrary.loginAsValidUser(signOnPage);
		}

		workflowPg.enterComments("Reject task");

	}

	@Override
	public void tearDown() {
		
	}

}
