package testscripts.pcs;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_32P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_1187() {
		testParameters
				.setCurrentTestDescription("Verify on clicking 'View Alfresco' button in PCS Site, user is redirected to the file/folder in Alfresco to which metadata is attached");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		String pcssite = dataTable.getData("Parametrized_Checkpoints", "Help URL");

		driver.get(pcssite);
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ pcssite, Status.DONE);
	}

	@Override
	public void executeTest() {
	
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);

		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);

		String fileNme = dataTable.getData("MyFiles", "FileName");

		signOnPage.loginAsValidDifferentUser();
		
		pcsObj.pcsSitetab("Production", "Format/File Management",
				"9780133375565");
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.pcsAlfrescoButton);
		UIHelper.click(driver, pcsObj.pcsAlfrescoButton);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.switchtab(1);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
	//	functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		docDetailsPageTest.verifyUploadedFileIsOpened(fileNme, "");
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}