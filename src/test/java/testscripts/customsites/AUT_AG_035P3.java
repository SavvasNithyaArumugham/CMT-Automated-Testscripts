package testscripts.customsites;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_035P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_035P3()
	{
		testParameters.setCurrentTestDescription("1. To verify that Site Manager is able to Reject the request to Join the moderate site"
				+ "<br> 2. To verify that the user should get rejection mail notification email after the site manager rejects the request to join the site"
				+ "<br> Part 3: Manger Rejects the request by clicking on link received in email");
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
		String subject= message+" "+siteName; 
		pearsonMailObj.searchMailWithSubjectTitle(subject);
		
		pearsonMailObj.clickOnLinkInReceivedMail();
		
		String pageName = driver.getTitle();
		if(pageName.equalsIgnoreCase("Sign In"))
		{
			functionalLibrary.loginAsValidUser(signOnPage);
		}
		String nowPageName=driver.getTitle();
		System.out.println(nowPageName);
		AlfrescoTasksPage taskPage=new AlfrescoTasksPage(scriptHelper);
		
		taskPage.claimTask();
		
		taskPage.rejectWorkflowApproveTask();
		
		
		
		
	}

	@Override
	public void tearDown() {
		
	}

}
