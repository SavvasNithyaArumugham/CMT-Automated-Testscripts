package testscripts.sharebox;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_013P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void sharebox_013()
	{
		testParameters.setCurrentTestDescription("Verify the contributor is not able to view 'Share Folder Externally' option on the folders created by other users - Accept Site as Contributor and check for 'Share Folder Externally' option");
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
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(folderName);
		if(sitesPage.checkMoreSettingsOption(folderName, moreSettingsOpt)){
			report.updateTestLog("Verify the 'Share Folder Externally' option available for Contributor",
					"Option Available for Contributor role"
					+ "<br /><b> Folder Name : </b>" + folderName, Status.FAIL);
		}else{
			report.updateTestLog("Verify the 'Share Folder Externally' option available for Contributor",
					"Option not Available for Contributor role"
					+ "<br /><b> Folder Name : </b>" + folderName, Status.PASS);
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}