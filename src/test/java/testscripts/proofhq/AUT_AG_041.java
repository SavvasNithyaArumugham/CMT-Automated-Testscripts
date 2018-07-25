package testscripts.proofhq;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_041 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_041() {
		testParameters
				.setCurrentTestDescription("Verifying the alfresco 'PCS metadata' feature for the proof created file.");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);

		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String moreSettingsOptions = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSettingsOption1="",moreSettingsOption2="";
		if(moreSettingsOptions.contains(","))
		{
			String splittedMoreSettingOptions[] = moreSettingsOptions.split(",");
			if(splittedMoreSettingOptions!=null && splittedMoreSettingOptions.length>1)
			{
				moreSettingsOption1=splittedMoreSettingOptions[0];
				moreSettingsOption2=splittedMoreSettingOptions[1];
			}
			else
			{
				moreSettingsOption1="Create Proof in ProofHQ";
				moreSettingsOption2="PCS Metadata";
			}
		}
		else
		{
			moreSettingsOption1="Create Proof in ProofHQ";
			moreSettingsOption2="PCS Metadata";
		}
	
		sitesPage.siteFinder(siteassertValue);
		
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		myFiles.uploadFile(filePath, fileNme);
		myFilesTestObj.verifyUploadedFile(fileNme, "");
		
		sitesPage.clickOnMoreSetting(fileNme);
	/*	sitesPageTests.verifyMoreSettingsOptionForFileOrFolderItem(fileNme, moreSettingsOption1);*/
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileNme, moreSettingsOption1);
		
		String recepients=  dataTable.getData("MyFiles", "Recepients");
		String policy=  dataTable.getData("MyFiles", "ProofHQPolicy");
		sitesPage.addProofHQ(recepients, policy, fileNme);

		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		siteDashboard.navigateToSiteDashboard();
		pcsObj.checkiteminPCSDashlet(fileNme);
		pcsObj.searchisbnPCS("0133415430");
		pcsObj.clickbutton("Go");
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
		UIHelper.click(driver, pcsObj.chooseRecord);
		pcsTest.verifypromptMsg("Ok");
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileNme);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileNme, moreSettingsOption2);

		try {
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			
			String finalpcsEditLabel= pcsObj.pcsEditValue
					.replace("CRAFT", "ISBN 10");
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalpcsEditLabel);
			String ISBNValue = UIHelper.findAnElementbyXpath(driver, finalpcsEditLabel).getAttribute("value");
			
			if(ISBNValue.equals("0133415430")){
				report.updateTestLog("PCS Metadata Edit page Updated",
						"PCS Metadata Edit page updated for different Region. <br><b> Expected ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 0133415430 </b>" , Status.PASS);
			}else{
				report.updateTestLog("PCS Metadata Edit page Updated",
						"PCS Metadata Edit page is not updated for different Region. <br><b> ISBN : </b>" 
				+ISBNValue + " <br><b> Actual ISBN : 0133415430 </b>", Status.FAIL);
			
			}
			
			UIHelper.click(driver, pcsObj.pcsCancelButton);
		
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("PCS Metadata Edit page Updated",
					"PCS Metadata Edit page is not updated for different Region.", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}