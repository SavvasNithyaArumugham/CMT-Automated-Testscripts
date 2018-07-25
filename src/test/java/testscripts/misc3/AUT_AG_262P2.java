package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_262P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_047()
	{
		testParameters.setCurrentTestDescription("Test user B can still preview the file that is locked by user A - Part 2");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage.loginAsValidDifferentUser();
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		homePage.navigateToSitesTab();
		AlfrescoSitesPage sitePage = homePage.navigateToSiteFinder();
		
		AlfrescoDocumentLibPage docLibPage = sitePage.findAndSelectSite();
		sitePage.enterIntoDocumentLibrary();
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		docLibPage.openAFile(fileName);
		
		if(docLibPage.isFileLocked()){
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			docLibPageTest.verifyDocumentPreviewed(fileName);
		}
	}

	@Override
	public void tearDown() {
		
	}
}
