package testscripts.pcs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
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
public class AUT_AG_17 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_17() {
		testParameters
				.setCurrentTestDescription("Verify whether 'Program Component Title' field in edit properties is populated with 'Title' from PCS.");
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

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String siteassertValue = dataTable.getData("Sites", "SiteName");

	
		sitesPage.siteFinder(siteassertValue);
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFolder");
		myFiles.createFolder(fileDetails);


		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
	
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("9780133375565");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		
		String inputname = dataTable
				.getData("MyFiles", "CreateFileDetails");
		
		String finallabelValueXpath = pcsObj.newlabelvalueXpath.replace(
				"CRAFT", inputname);
		UIHelper.waitForVisibilityOfEleByXpath(driver,
				finallabelValueXpath);
		String expectedValue = UIHelper.findAnElementbyXpath(driver,
				finallabelValueXpath).getAttribute("value");
		
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		UIHelper.waitFor(driver);
		
		
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.commonMethodForPerformBrowseOption(fileNme, "Edit Properties");
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);	
		docDetailsPageObj.clickAllProperties();
		
		String finalalllabelValueXpath = pcsObj.editvalue.replace("CRAFT",
				"prop_cplg_programComponentTitle");
	
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalalllabelValueXpath);
			String actualValue = UIHelper.findAnElementbyXpath(driver, finalalllabelValueXpath).getText();
			
			if(actualValue.equals(expectedValue)){
				report.updateTestLog(" Verify PCS Metadata value",
						"PCS Metadata Edit page updated for different Region. <br><b> Program Component Title : </b>" 
				+actualValue + " <br><b> Title : </b>" +expectedValue , Status.PASS);
			}else{
				report.updateTestLog("Verify PCS Metadata value",
						"PCS Metadata 'Program Component Title' field in edit properties is populated with 'Title'. <br><b> Program Component Title : </b>" 
				+actualValue + " <br><b> Title : </b>" +expectedValue , Status.FAIL);
			
			}
			
			UIHelper.click(driver, pcsObj.pcsCancelButton);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Verify PCS Metadata value",
					"PCS Metadata Edit page is not updated", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}