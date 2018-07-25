package testscripts.proofhq;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_003P3 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void proofhq_03() {
		testParameters
				.setCurrentTestDescription("[1]ALFDEPLOY-3008_Verify Collaborator is able to create a proof without getting welcome mail to access the proof.");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		try {
			String message = "New proof: ";
			String fileName = dataTable.getData("MyFiles", "FileName");
			message = message + fileName;

			AlfrescoProofHQPage proofHQPgObj = new AlfrescoProofHQPage(scriptHelper);
			
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			signOnPage = functionalLibrary.loginAsValidUserforPearsonMail(signOnPage);	
			
			AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
			pearsonMailObj.searchMailWithSubjectTitle(message);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			//UIHelper.waitForVisibilityOfEleByXpath(driver, proofHQPgObj.mailsharelink);
			if(!UIHelper.checkForAnElementbyXpath(driver, proofHQPgObj.mailsharelink)){
				
				report.updateTestLog("Share proof with someone else link",
						"Share proof with someone else link is not visible as expected"
								, Status.PASS);
				
			} else{
				report.updateTestLog("Share proof with someone else link",
						"Share proof with someone else link is visible which is not expected."
								, Status.FAIL);
			}

			
			proofHQPgObj.navigateToProofHQPage(fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}