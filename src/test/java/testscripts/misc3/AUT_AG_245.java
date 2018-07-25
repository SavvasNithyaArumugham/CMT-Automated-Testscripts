package testscripts.misc3;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_245 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_033()
	{
		testParameters.setCurrentTestDescription("TC043_7-10 see all comments indicated with user and date stamp");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
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
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
		
		sitesPage.documentlib();
		
		if(sitesPage.Checkdocument(fileName)){
			sitesPage.documentdetails(fileName);
			
		}
		else {
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			myFiles.createFile(fileDetails);
		}
		
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		docDetailsPageObj.Addcomment(fileName);
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
	
		docDetailsPageTest.Verifycomment();
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}