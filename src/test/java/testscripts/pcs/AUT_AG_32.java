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
public class AUT_AG_32 extends TestCase {
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);

		String rootFolderDetails = dataTable.getData("MyFiles",
				"CreateFolder");
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
	//	String pcssite = dataTable.getData("Parametrized_Checkpoints", "Help URL");
		sitesPage.siteFinder(siteassertValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		//myFiles.uploadFile(filePath, fileNme);
		myFiles.createFolder(rootFolderDetails);

		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
	siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("9780133375565");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		
		/*AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.openURLWithNewTab(pcssite);
	
		signOnPage.loginAsValidDifferentUser();
		
		pcsObj.pcsSitetabNavigate("Production", "Format/File Management", "9780132931281");
		UIHelper.waitForPageToLoad(driver);
	UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.pcsAlfrescoButton);
		UIHelper.click(driver, pcsObj.pcsAlfrescoButton);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.switchtab(2);
		
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyUploadedFileIsOpened(fileNme,"");*/
			
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}