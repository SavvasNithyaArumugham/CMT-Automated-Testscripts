package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_080 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_040()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to navigate to the document library after applying the created profile");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		/*if (mediaTransPage.checkMediaProfAvail(profName)){
			mediaTransPage.deleteProfileFrmMediaTransPg(profName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}*/
		
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateVideoProfBtn();

		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc, macCode, subAsstCode);
				
		if (mediaTransPage.clickOnAddTransformationRulesBtn()) {
			/*mediaTransPage.clickAddBtn();*/
			mediaTransPage.clickSaveBtn();
		}
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		//docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileName);
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName, preTxt);
		
		if(mediaTransPage.isNavigatedToDocumentLibrary()){
			report.updateTestLog("Verify page navigated to Document Library after applied the Transformation Profile",
					"Navigated successfully", Status.PASS);
		}else{
			report.updateTestLog("Verify page navigated to Document Library after applied the Transformation Profile",
					"Page not Navigated", Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		
	}

}
