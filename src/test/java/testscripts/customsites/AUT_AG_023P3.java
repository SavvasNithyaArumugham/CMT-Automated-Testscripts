package testscripts.customsites;

import java.text.ParseException;

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

public class AUT_AG_023P3 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void CUSTOMSITES_008()
	{
		testParameters.setCurrentTestDescription("To verify that Site Manager is able to approve the request to Join the moderate site"
				+ " to Join the moderate site<br> Part 1: Manger Approves the request");
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
		//String nowPageName=driver.getTitle();
		AlfrescoTasksPage taskPage=new AlfrescoTasksPage(scriptHelper);
		
	//	taskPage.claimTask();
		
		taskPage.approveTask();
		
	}

	@Override
	public void tearDown() {
		
	}

}
