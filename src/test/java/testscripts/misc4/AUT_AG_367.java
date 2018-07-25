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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
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
public class AUT_AG_367 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_059() {
		testParameters
				.setCurrentTestDescription("Check that the Icon for Web Resource should change depending on URL domain");
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
			
			
			String webName = dataTable.getData("Parametrized_Checkpoints", "FileName");//same
			String webURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			String[] splittedwebURL=webURL.split(",");
			String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");//same
			
			String fileIcon = dataTable.getData("Parametrized_Checkpoints", "fileIcon");
			String[] splittedFileIcon=fileIcon.split(",");
			
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			homePageObj.navigateToMyFilesTab();
			
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			if(sitesPage.Checkdocument(webName)){
				myFiles.deleteUploadedFile(webName);
			}
			myFiles.createcontenttype(type);
			myFiles.createWebResource(webName, splittedwebURL[0]);
					
			docLibPageTest.verifyFileIcon(webName, splittedFileIcon[0]);
			
			
			
			
			homePageObj.navigateToMyFilesTab();

	
			if(sitesPage.Checkdocument(webName)){
				myFiles.deleteUploadedFile(webName);
			}
				myFiles.createcontenttype(type);
				myFiles.createWebResource(webName, splittedwebURL[1]);
						
			docLibPageTest.verifyFileIcon(webName, splittedFileIcon[1]);
			
			
			
			homePageObj.navigateToMyFilesTab();
			if(sitesPage.Checkdocument(webName)){
				myFiles.deleteUploadedFile(webName);
			}
			myFiles.createcontenttype(type);
			myFiles.createWebResource(webName, splittedwebURL[2]);
						
			docLibPageTest.verifyFileIcon(webName, splittedFileIcon[1]);
			
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
	
}