package testscripts.amts;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
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
public class AUT_AG_121 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runTCAUT_AG_112()
	{
		testParameters.setCurrentTestDescription("Verify whether the user is able to copy the link below Share from the document actions menu");
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
		String imageType = dataTable.getData("Media_Transform", "OutPutFormat");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String prefTxt = dataTable.getData("Media_Transform", "Preference");
		String file = dataTable.getData("MyFiles", "Version");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		/*if (mediaTransPage.checkMediaProfAvail(profName)){
			mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		}*/
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateImageProfBtn();
		mediaTransPage.enterImageProfDetailsWithUniqueNameAndSubAsst(profName,
				subAsstCode);
		
		/*UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();

		mediaTransPage.commonMethodForEnterImageProfDetails(profName,
				profDesc, macCode, subAsstCode,
				null, null);*/
				
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			mediaTransPage.selectImgTransType(imageType);
			mediaTransPage.clickAddBtnImgProf();
			mediaTransPage.clickSaveBtnImgProf();
		}
		
		homePageObj.navigateToRepositoryPage();
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Profiles");
		UIHelper.waitFor(driver);
		myFiles.openCreatedFolder("Image");
		
		String inLineEditProfName = profName+"+"+macCode+"+"+subAsstCode;
		
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		sitesPage.documentdetails(inLineEditProfName);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		 docDetailsPage.clickDocAction("Edit in Alfresco");
		//sitesPage.clickOnMoreSetting(inLineEditProfName);
		//docLibPg.commonMethodForClickOnMoreSettingsOption(inLineEditProfName, "Inline Edit");
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		mediaTransPage.changeRenditionValue();
		mediaTransPage.saveInLineEdit();
		
		UIHelper.waitFor(driver);
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileName);
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, docActionVal);
		
		mediaTransPage.applyRenditionProfile(profName);
		
		String transFileName= fileName+"_"+subAsstCode+"-"+prefTxt+"."+imageType;
		docLibPg.navigateToDocumentLibrary();
		docLibPg.openAFile(transFileName);
		mediaTransPage.copyShareURL();
		
	}

	@Override
	public void tearDown() {
		
	}

}
