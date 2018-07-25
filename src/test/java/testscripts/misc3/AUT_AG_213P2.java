package testscripts.misc3;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_213P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc3_011() {
		testParameters
				.setCurrentTestDescription("1.Verify that the Help Option is availabe under User Dropdown for Non-Admin users"
						+ "help page after clicking on Help"
						+"<br>2. Verify that the page is directing to Alfresco Training Site  Link  on clicking Help for Admin User");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		
		//Veryfying Help option is available for Non-Admin User
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.clickonUserMenu();

		AlfrescoHomePageTest homePageTestObj = new AlfrescoHomePageTest(
				scriptHelper);
		homePageTestObj.verifyHelpOption();

		//Verify that the page is directing to Alfresco Training Site  Link  on clicking Help Non-Admin User
	
		homePageTestObj.verifyHelpURL();

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}