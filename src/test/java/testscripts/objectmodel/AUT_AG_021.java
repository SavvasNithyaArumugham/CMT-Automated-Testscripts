package testscripts.objectmodel;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
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
public class AUT_AG_021 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_004()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-4207_Verify user able to see clippable aspect in aspects selection window while click on manage aspect for a video file <br>" +
	"ALFDEPLOY-4207_Verify user able to apply clippable aspect for a video file using manage aspect option<br>"+
				"\"ALFDEPLOY-4207_Verify user able to see below properties in view details page for a video file after applying clippable aspect\r\n" + 
				"clip-id - the unique name or number\r\n" + 
				"clip-desc - description\r\n" + 
				"clip-start - timestamp for start\r\n" + 
				"clip-end - timestamp for end\r\n" + 
				"clip-duration- once the start and end is added, duration is auto-created\" <br>"+
				"ALFDEPLOY-4207_Verify user able to edit clippable aspect properties for a video file<br>"+
				"ALFDEPLOY-4207_Verify user able to apply clippable aspect for a video file with formats mp4, mov, avi, wmv using manage aspect option<br>"+
				"ALFDEPLOY-4207_Verify user able to edit offline for any video file with clippable aspect<br>"+
				"ALFDEPLOY-4207_Verify user able to upload new version for any video file with clippable aspect<br>"+
				"ALFDEPLOY-4207_Verify user able to see the clippable aspect properties while version of the file get revert by using back option in version history");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
		String rename = dataTable.getData("MyFiles", "RelationshipName");
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docActionVal = dataTable.getData("Sites",
				"SelectedItemsMenuOption");
		String docActionVal1 = dataTable.getData("Document_Details", "DocPropertyValues");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String preTxt = dataTable.getData("Media_Transform", "Preference");
		String aspectprop = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String attachLifeCycleDropdownVal = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
		
		
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateVideoProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc,
				macCode, subAsstCode);
				
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify Video profile details",
					"Video Profile details displayed successfully", Status.PASS);	
			
			mediaTransPage.clickSaveBtn();

		} else {
			report.updateTestLog("Verify Video Profile creation",
					"Failed to create Video profile", Status.FAIL);
		}

		homePageObj.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileNme);
		
		 sitesPage.documentdetails(fileNme);
	        
	        docDetailsPageObj.performManageAspectsDocAction();
	        
	        docDetailsPageTestObj.verifyAspectsAvailable();
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
			
			String ActualAspectType = appSearchPg.getMetadata(fileNme,
					"Clips:");

			
			
			if(ActualAspectType.equals(aspectprop)) {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
								+ ActualAspectType,
						Status.PASS);
			}else {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
								+ aspectprop,
						Status.FAIL);
			}
			
			docDetailsPageObj.commonMethodForPerformDocAction(docActionVal1);
			
			
			docDetailsPageObj.changeLifeCycleSate(attachLifeCycleDropdownVal);
			
		sitesPage.enterIntoDocumentLibrary();
		myFiles.rename(fileNme, rename);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(rename);
		docLibPg.commonMethodForClickOnMoreSettingsOption(rename,
				docActionVal);
		mediaTransPage.selectProfileFrmListAndApplyTransformation(profName,
				preTxt);
		sitesPage.enterIntoDocumentLibrary();
		//myFiles.openCreatedFolder(folderName);   
		String finalFileName = rename.replace(".3gp","_"+subAsstCode+"-"+preTxt+"."+imageType);
		if(mediaTransPage.isNavigatedToDocumentLibrary()){
			if(docLibPg.isFileIsAvailable(finalFileName)){
				report.updateTestLog("Verify the Transformed file with sub assert code",
						"File verified successfully"
								+ "<br><b> Transformed File Name : </b>"
								+ finalFileName, Status.PASS);
			}else{
				report.updateTestLog("Verify the Transformed file with sub assert code",
						"File not verified"
								+ "<br><b> Transformed File Name : </b>"
								+ finalFileName, Status.FAIL);
			}
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
