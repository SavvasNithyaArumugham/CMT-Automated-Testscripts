package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
 * @author Moshin
 */
public class AUT_AG_361 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void misc4_055() {
		testParameters
				.setCurrentTestDescription("Verify the thumbnail of the created Web Resource page base on the domain ofURL.");
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
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);
			
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg= new AlfrescoDocumentLibPage(scriptHelper);
			homePageObj.navigateToMyFilesTab();
			docLibPg.deleteAllFilesAndFolders();
			
			String webName = dataTable.getData("Parametrized_Checkpoints", "FileName");
			String webURL = dataTable.getData("Parametrized_Checkpoints", "Help URL");
			String type = dataTable.getData("Parametrized_Checkpoints", "FilePath");
			
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			if(sitesPage.Checkdocument(webName)){
			
			}else {
				myFiles.createcontenttype(type);
				myFiles.createWebResource(webName, webURL);
			}
			
			String finalwebthumbnail = myFiles.webthumbnail.replace("CRAFT", webName);
			String finalwebthumbnailcheck = myFiles.webthumbnailcheck.replace("CRAFT", webName);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalwebthumbnailcheck);
			if(UIHelper.checkForAnElementbyXpath(driver, finalwebthumbnailcheck))
			{
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalwebthumbnail);
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalwebthumbnail));
				UIHelper.highlightElement(driver, finalwebthumbnail);
				
				report.updateTestLog("Verify Web resource thumbnail image",
						"Web resource thumbnail image based on domain of URL", Status.PASS);
			}else {
			
				report.updateTestLog("Verify Web resource thumbnail image",
						"Web resource thumbnail image not based on domain of URL", Status.FAIL);
			}
		} catch (Exception e) {
	
			report.updateTestLog("Verify Web resource thumbnail image",
					"Web resource thumbnail image not based on domain of URL", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}