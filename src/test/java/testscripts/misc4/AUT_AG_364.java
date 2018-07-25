package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_364 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_057() {
		testParameters
				.setCurrentTestDescription("Verify that user is able to create Web Resource in My Files and Shared files");
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
		
		//Verify that user is able to create Web Resource in My Files
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.navigateToMyFilesTab();
			
			String webName = dataTable.getData("Parametrized_Checkpoints", "FileName");
			String webURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
			
			AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
			docLibPge.deleteAllFilesAndFolders();
			
			myFiles.createcontenttype(type);
			myFiles.createWebResource(webName, webURL);
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			if(sitesPage.Checkdocument(webName)){
				
				report.updateTestLog("Verify Web resource Creation",
						"Web resource Creation successfully in same location", Status.PASS);
				
			}else {
				report.updateTestLog("Verify Web resource Creation",
						"Web resource Creation failed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Web resource Creation",
					"Web resource Creation failed", Status.FAIL);
		}
		
		
		//Verify that user is able to create Web Resource in Shared Files
		
		try {
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.navigateToSharedFilesTab();
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			String folder = dataTable.getData("MyFiles", "FileName");
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			
			String webName = dataTable.getData("Parametrized_Checkpoints", "FileName");
			String webURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
			
			
			if (sitesPage.Checkdocument(folder)) {
				sitesPage.documentdetails(folder);
			} else {
				myFiles.createFolder(folderDetails);
				sitesPage.documentdetails(folder);
			}
			
			
			UIHelper.waitForPageToLoad(driver);
			if (sitesPage.Checkdocument(webName)) {
				AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
				docLibPge.deleteAllFilesAndFolders();
			}
			
			myFiles.createcontenttype(type);
			myFiles.createWebResource(webName, webURL);
			

			if(sitesPage.Checkdocument(webName)){
				
				report.updateTestLog("Verify Web resource Creation",
						"Web resource Creation successfully in same location", Status.PASS);
				
			}else {
				report.updateTestLog("Verify Web resource Creation",
						"Web resource Creation failed", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Web resource Creation",
					"Web resource Creation failed", Status.FAIL);
		}
		
		
		
		
	}
	

	@Override
	public void tearDown() {
		// Nothing to do
	}
}