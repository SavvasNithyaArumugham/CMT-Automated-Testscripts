package testscripts.proofhq;


import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_007 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void proofhq_07()
	{
		testParameters.setCurrentTestDescription("Non-Seated User accesses Proof in Alfresco");
		
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);	
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	
		

		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.siteFinder(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileName=  dataTable.getData("MyFiles", "FileName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);
		/*AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOption);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQNegative(recepients, policy, fileName);
		
		//sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItemNegativeCase(fileName, moreSettingsOption);
		AlfrescoSearchPage searchPageObj = new AlfrescoSearchPage(scriptHelper);
		String Message = searchPageObj.getTheMessageText("There was an error creating a Proof in ProofHQ.");
		
		if (Message.contains("error")) {

			report.updateTestLog("Verfiy warning message displayed for Contributor role ",
					"Contributor role should not be able to create a proof via alfresco", Status.PASS);

		} else {
			report.updateTestLog("Verfiy warning message displayed for Contributor role",
					"Contributor role should not be able to create a proof via alfresco", Status.FAIL);
		}*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}