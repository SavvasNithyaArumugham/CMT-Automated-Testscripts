package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_369 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_061() {
		testParameters
				.setCurrentTestDescription("Verify the Description feild should be displayed in Web Resource edit properites");
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
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePageObj.navigateToSitesTab();
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String webName = dataTable.getData("Parametrized_Checkpoints", "FileName");
		String webURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
		String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createcontenttype(type);
		myFiles.createWebResource(webName, webURL);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnEditProperties(webName);
		
		if(docLibPage.isDiscriptionFieldAvailable()){
			report.updateTestLog("Verify the Discription filed in Edit Properties Page",
					"Discription field able to display successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify the Discription filed in Edit Properties Page",
					"Discription field not able to display", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}