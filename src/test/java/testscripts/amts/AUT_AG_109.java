package testscripts.amts;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_109 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_064()
	{
		testParameters.setCurrentTestDescription("Verify that grey scale and compression has a value only if the check box is enabled");
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
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String type = dataTable.getData("Media_Transform", "filePath");
		String testData = dataTable.getData("Media_Transform",
				"verifyVdoSettings");
		String testData1 = dataTable.getData("Media_Transform",
				"height");
		
		
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);/*
		if (mediaTransPage.checkMediaProfAvail(profName)) {
			mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		}*/
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.enterImageProfDetailsWithUniqueNameAndSubAsst(profName,
				subAsstCode);
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Image profile created",
					"Video/Image created successfully."
							+ "<br><b>Image Profile Name : </br>" + profName,
					Status.PASS);
			
			mediaTransPage.selectImgTransType(type);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.convertToGrayScale();
			mediaTransPage.convertTocompress();
			UIHelper.waitFor(driver);
			mediaTransPage.clickSaveBtnImgProf();
			mediaTransPage.navigateToMediaTransPage();
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			if ( mediaTransPage.verifyVideoSettingsDetails(testData)){
				//UIHelper.waitFor(driver);
				report.updateTestLog("Verify Image profile details",
						"Details displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Image profile details",
						"Details mismatches", Status.FAIL);
			}
			
			mediaTransPage.removeToGrayScale();
			mediaTransPage.clickSaveBtnImgProf();
			mediaTransPage.navigateToMediaTransPage();
			mediaTransPage.editProfileFrmMediaTransPg(profName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			if ( !mediaTransPage.verifyVideoSettingsDetails(testData1)){
				//UIHelper.waitFor(driver);
				report.updateTestLog("Verify Image profile details",
						"Details not displayed successfully", Status.PASS);
			} else {
				report.updateTestLog("Verify Image profile details",
						"Details mismatches", Status.FAIL);
			}
			
		} else {
			report.updateTestLog("Verify Image profile created",
					"Failed to create Image profile", Status.FAIL);
		}
		
		
		
	}
	

	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}