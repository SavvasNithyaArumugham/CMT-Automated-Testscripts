package testscripts.workflows;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_036P3 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void workflows_26()
	{
		testParameters.setCurrentTestDescription("Verify the template for General info in Worfkow details page of Simple Workflow"
				+"2. Verify that the text in Message field is same as the text given in description field by initiator while initiating Simple Worklfow."
				+"3. Verfiy that no field is displayed more than once in General info in Simple Task Workflow Details Page."
				+ "4. Verify that Due date in Workflow summary and General info is same in Workflow Detail page for Simple Task Workflow."
				+"5. Verify that Priority in Workflow summary and General info is same in Workflow Detail page for Simple Task Workflow."
				+"6. Verify that Status in Workflow summary and General info is same in Workflow Details page for Simple Task Workflow."
				+"7. Verify that Locale field in not there on Workflow Details page for Simple Task Workflow.- Check Mail Format");
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
		pearsonMailObj.searchMailWithSubject("You have been assigned a task");
		pearsonMailObj.verifyTaskAssignedMailTemplate();
	}

	@Override
	public void tearDown() {
		
	}

}
