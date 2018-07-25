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
public class AUT_AG_11 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_11() {
		testParameters
		.setCurrentTestDescription("<br> 1. Verify PCS Metadata is enabled for aspect attached folder/file"
				+ "<br>2. Verify user is directed to new edit properties page on clicking PCS Metadata from more option in document library"
				+ "<br>3. Verify user is directed to new edit properties page on clicking PCS Metadata option "
				+ "under Document Action page in preview page of files/View Details page of folders");
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);

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
		//myFiles.createFolder(rootFolderDetails);
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		String label = dataTable.getData("MyFiles", "Version");
		myFiles.createFile(fileDetails);

		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
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
		AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(
				scriptHelper);
		sitesPageTestObj
				.verifyMoreSettingsOptionForFileOrFolderItem(
						fileNme, "PCS Metadata");

		myFiles.openUploadedOrCreatedFile(fileNme, "");
		AlfrescoDocumentDetailsPageTest docDetailsPgTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);

		docDetailsPgTest
				.verifyDocumentActionName("PCS Metadata");
		
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(fileNme);
		String option = dataTable.getData("MyFiles", "MoreSettingsOption");
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		 docDetailsPage.clickDocAction(option);
		
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcseditPage= pcsObj.pcseditPage
					.replace("CRAFT", option);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcseditPage);
			if(UIHelper.checkForAnElementbyXpath(driver, finalpcseditPage)){
				UIHelper.highlightElement(driver, finalpcseditPage);
				report.updateTestLog("Navigate to PCS Edit",
						"Navigated to PCS Edit page passed", Status.PASS);
			}else{
				report.updateTestLog("Navigate to PCS Edit",
						"Navigation to PCS Edit page failed", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Navigate to PCS Edit",
					"Navigation to PCS Edit page failed", Status.FAIL);
		}
	
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileNme);
		//sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileNme, option);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, option);
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcseditPage= pcsObj.pcseditPage
					.replace("CRAFT", option);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcseditPage);
			if(UIHelper.checkForAnElementbyXpath(driver, finalpcseditPage)){
				UIHelper.highlightElement(driver, finalpcseditPage);
				report.updateTestLog("Navigate to PCS Edit",
						"Navigated to PCS Edit page passed", Status.PASS);
			}else{
				report.updateTestLog("Navigate to PCS Edit",
						"Navigation to PCS Edit page failed", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("Navigate to PCS Edit",
					"Navigation to PCS Edit page failed", Status.FAIL);
		}
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileNme);
		//sitePgTestObj.verifyMoreSettingsOptionForFileOrFolderItem(fileNme, option);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, option);
		
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcsEditLabel= pcsObj.pcsEditValue
					.replace("CRAFT", label);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcsEditLabel);
			if(UIHelper.checkForAnElementbyXpath(driver, finalpcsEditLabel)){
				UIHelper.highlightElement(driver, finalpcsEditLabel);
				report.updateTestLog("PCS Matadata Edit page disabled",
						"PCS Metadata Edit page is disabled as expected", Status.PASS);
			}else{
				report.updateTestLog("PCS Matadata Edit page disabled",
						"PCS Metadata Edit page is not disabled", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Matadata Edit page disabled",
					"PCS Metadata Edit page is not disabled", Status.FAIL);
		}
		
		UIHelper.click(driver, pcsObj.pcsCancelButton);
		
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		sitesPage.documentdetails(fileNme);
		
		 docDetailsPage.clickDocAction(option);
		
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcsEditLabel= pcsObj.pcsEditValue
					.replace("CRAFT", label);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcsEditLabel);
			if(UIHelper.checkForAnElementbyXpath(driver, finalpcsEditLabel)){
				UIHelper.highlightElement(driver, finalpcsEditLabel);
				report.updateTestLog("PCS Matadata Edit page disabled",
						"PCS Metadata Edit page is disabled as expected", Status.PASS);
			}else{
				report.updateTestLog("PCS Matadata Edit page disabled",
						"PCS Metadata Edit page is not disabled", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Matadata Edit page disabled",
					"PCS Metadata Edit page is not disabled", Status.FAIL);
		}
		
				
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);

			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.pcsCancelButton);
			if(UIHelper.checkForAnElementbyXpath(driver, pcsObj.pcsCancelButton)){
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, pcsObj.pcsCancelButton));
				UIHelper.highlightElement(driver, pcsObj.pcsCancelButton);
				report.updateTestLog("PCS Matadata Cancel button",
						"PCS Metadata Cancel button is disabled as expected", Status.PASS);
			}else{
				report.updateTestLog("PCS Matadata Cancel button",
						"PCS Metadata Cancel button is not disabled", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Matadata Cancel button",
					"PCS Metadata Cancel button is not disabled", Status.FAIL);
		}
		
/*		String inputname = dataTable
				.getData("MyFiles", "FilePath");
		ArrayList<String> inputList = myFiles.getFolderNames(inputname);
		ArrayList<String> labelList = new ArrayList<String>();
		System.out.println(labelList);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, option);
		
		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String finalpcsedit1Page= pcsObj.pcseditPage
					.replace("CRAFT", "PCS Metadata");
			String finalpcsedit2Page= pcsObj.pcseditPage
					.replace("CRAFT", "PCS Program Metadata");
			if(UIHelper.checkForAnElementbyXpath(driver, finalpcsedit1Page) && 
					UIHelper.checkForAnElementbyXpath(driver, finalpcsedit2Page)){
				
				UIHelper.highlightElement(driver, finalpcsedit1Page);
				

				for (String input : inputList) {
					String finalEditLabel = pcsObj.pcsEditLabel.replace(
							"CRAFT", input);
					labelList.add(UIHelper.findAnElementbyXpath(driver,
							finalEditLabel).getText());
					
					System.out.println(labelList);
					System.out.println("kj "+inputList);
					if (inputList.equals(labelList)) {
						report.updateTestLog("PCS Metadata UI",
								"PCS Metadata UI is as expected", Status.PASS);
					}else{
						report.updateTestLog("PCS Metadata UI",
								"PCS Metadata UI is not as expected", Status.FAIL);
			} 
				}
			}else{
				report.updateTestLog("PCS Metadata UI",
						"PCS Metadata UI is not as expected", Status.FAIL);
			}
				}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog("PCS Metadata UI",
					"PCS Metadata UI is not as expected", Status.FAIL);
		}*/
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}