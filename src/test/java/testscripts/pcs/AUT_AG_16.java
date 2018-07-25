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
public class AUT_AG_16 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_16() {
		testParameters
				.setCurrentTestDescription("Verify whetehr PCS metadata is present for different regions - US HE, PEarson Canada and PCS PTG");
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
		String option = dataTable.getData("MyFiles", "MoreSettingsOption");
	
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
		//myFiles.createFolder(rootFolderDetails);
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		myFiles.createFile(fileDetails);

		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		String label = dataTable.getData("MyFiles", "Version");
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("9780133375565");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, option);

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcsEditLabel= pcsObj.pcsEditValue
					.replace("CRAFT", "ISBN 13");
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcsEditLabel);
			String ISBNValue = UIHelper.findAnElementbyXpath(driver, finalpcsEditLabel).getAttribute("value");
			
			if(ISBNValue.equals("9780133375565")){
				report.updateTestLog("PCS Matadata Edit page Updated",
						"PCS Metadata Edit page updated for different Region. <br><b> Expected ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 9780133375565 </b>" , Status.PASS);
			}else{
				report.updateTestLog("PCS Matadata Edit page Updated",
						"PCS Metadata Edit page is not updated for different Region. <br><b> ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 9780133375565 </b>", Status.FAIL);
			
			}
			
			UIHelper.click(driver, pcsObj.pcsCancelButton);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Matadata Edit page Updated",
					"PCS Metadata Edit page is not updated for different Region.", Status.FAIL);
		}
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("0132931281");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, option);
		
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcsEditLabel= pcsObj.pcsEditValue
					.replace("CRAFT", "ISBN 10");
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcsEditLabel);
			String ISBNValue = UIHelper.findAnElementbyXpath(driver, finalpcsEditLabel).getAttribute("value");
			
			if(ISBNValue.equals("0132931281")){
				report.updateTestLog("PCS Matadata Edit page Updated",
						"PCS Metadata Edit page updated for different Region. <br><b> Expected ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 0132931281 </b>" , Status.PASS);
			}else{
				report.updateTestLog("PCS Matadata Edit page Updated",
						"PCS Metadata Edit page is not updated for different Region. <br><b> ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 0132931281 </b>", Status.FAIL);
			
			}
			
			UIHelper.click(driver, pcsObj.pcsCancelButton);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Matadata Edit page Updated",
					"PCS Metadata Edit page is not updated for different Region.", Status.FAIL);
		}
		
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("9780132931281");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, option);
		
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcsEditLabel= pcsObj.pcsEditValue
					.replace("CRAFT", "ISBN 13");
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcsEditLabel);
			String ISBNValue = UIHelper.findAnElementbyXpath(driver, finalpcsEditLabel).getAttribute("value");
			
			if(ISBNValue.equals("9780132931281")){
				report.updateTestLog("PCS Matadata Edit page Updated",
						"PCS Metadata Edit page updated for different Region. <br><b> Expected ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 9780132931281 </b>" , Status.PASS);
			}else{
				report.updateTestLog("PCS Matadata Edit page Updated",
						"PCS Metadata Edit page is not updated for different Region. <br><b> ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 9780132931281 </b>", Status.FAIL);
			
			}
			
			UIHelper.click(driver, pcsObj.pcsCancelButton);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Matadata Edit page Updated",
					"PCS Metadata Edit page is not updated for different Region.", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}