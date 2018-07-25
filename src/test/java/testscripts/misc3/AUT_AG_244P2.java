package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


/**
 * Test for check out a document
 * @author 516188
 */
public class AUT_AG_244P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_032()
	{
		testParameters.setCurrentTestDescription("check out a document<br>Part2: Verify File locked");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		UIHelper.waitFor(driver);
		String file = dataTable.getData("Sites", "FileName");
		sitesPage.isFileLocked(file);
		//AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		//myFiles.openUploadedOrCreatedFile(file,"");
		sitesPage.documentdetails(file);
		
		AlfrescoDocumentDetailsPageTest docDetailsTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		String documentActions=dataTable.getData("MyFiles","MoreSettingsOption");
		docDetailsTest.verifyDocumentActionsOption(documentActions);
	}

	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}