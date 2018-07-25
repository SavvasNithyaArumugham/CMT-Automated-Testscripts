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
public class AUT_AG_019 extends TestCase
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);	
		AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(
				scriptHelper);
		AlfrescoSitesDashboardPage siteDashboard=new AlfrescoSitesDashboardPage(scriptHelper);
	
	
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String docPropertiesForPopup = dataTable.getData("Document_Details", "MandatoryDocPropertiesField");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String docProperties = dataTable.getData("Document_Details", "DocPropertyValues");
		String propertyValues = dataTable.getData("Document_Details", "DocProperties");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		String action = dataTable.getData("Document_Details", "Title");
		String aspectprop = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String destinationFolder = dataTable.getData("Sites", "TargetFolder");
		String dashletNme = dataTable.getData("Home", "DashletName");
		String dashlet = dataTable.getData("Home", "DashBoardName");
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteNameValue);
		
		

		
		
		
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashlet))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
				myFiles.uploadFileInMyFilesPage(filePath, fileName);
        
        sitesPage.documentdetails(fileName);
        
        docDetailsPageObj.performManageAspectsDocAction();
        
        docDetailsPageTestObj.verifyAspectsAvailable();
		docDetailsPageObj.addAspectsAndApllyChangesToAFile();
		
		String ActualAspectType = appSearchPg.getMetadata(fileName,
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
		
		//docDetailsPageTestObj.verifyAttributeInDocPropertiesnew(aspectprop);
		
		docDetailsPageObj.commonMethodForPerformDocAction("Edit Properties");
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnEditProperties(fileName);
		docDetailsPageObj.clickAllProperties();

		docDetailsPageObj.commonMethodForEnterEditPropertiesDatanew(docProperties);
		UIHelper.waitFor(driver);
		docDetailsPageObj.mouseOverclickSaveInEditProperties();
		
		sitesPage.documentdetails(fileName);
		
		String ActualAspectType1 = appSearchPg.getMetadata(fileName,
				"Clips:");
		
		
		if(ActualAspectType1.equals(propertyValues)) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
							+ ActualAspectType1,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
							+ propertyValues,
					Status.FAIL);
		}

			/*siteDashboard.navigateToSiteDashboard();
				pcsObj.checkiteminPCSDashlet(fileName);
				pcsObj.searchisbnPCS("9780321896667");
				pcsObj.clickbutton("Go");
				UIHelper.waitFor(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.chooseRecord);
				UIHelper.click(driver, pcsObj.chooseRecord);
				pcsTest.verifypromptMsg("Ok");
				
				siteDashboard.navigateToSiteDashboard();
				pcsTest.verifyupdatedPCSValues(fileName,"9780321896667");*/

		
				sitesPage.enterIntoDocumentLibrary();
				
				sitesPage.documentdetails(fileName);
		
				docDetailsPageObj.commonMethodForPerformDocAction("Upload New Version");
	
				docLibPage.uploadNewVersionFileInDocLibPage(fileName,
				filePath, versionDetails, comments);
		
	
		mediaTransPage.checkVersionOfTransformedFile("1.1");
		
		String expectedOldVersionNoForFile = dataTable.getData("MyFiles", "Version");
	//	docDetailsPageTestObj.verifyOlderVersionForFile(expectedOldVersionNoForFile.replace("'", ""));
		
		docDetailsPageObj.performReverVersionForFile();
	
		//String expectedRevertedVersionNoForFile = dataTable.getData("Document_Details", "RevertedFileVersionNo");
		docDetailsPageTestObj.verifyRevertedVersionNoForFile("1.2");
		
	
		
		sitesPage.enterIntoDocumentLibrary();
		
		// sitesPage.documentdetails(destinationFolder);
		 
		// docLibPage.deleteAllFilesAndFolders();
		
		 sitesPage.documentdetails(fileName);
		
		 docDetailsPageObj.clickMoveToDocAction();
		 docDetailsPageObj.selectFileInMovePopUp();
		 
		 sitesPage.enterIntoDocumentLibrary();
		
		 sitesPage.documentdetails(destinationFolder);
		 
		 if(sitesPage.documentAvailable(fileName)) {
			 report.updateTestLog("Verify Clippable aspest applied file Move to functionality",
						"Clippable aspest applied file Move to functionality successful" + "<br/><b>File Name:</b>"
								+ fileName,
						Status.PASS);
			 
		 }else {
			 report.updateTestLog("Verify Clippable aspest applied file Move to functionality",
						"Clippable aspest applied file Move to functionality FAILEd" + "<br/><b>File Name:</b>"
								+ fileName,
						Status.FAIL);
		 }
		 
		/* docLibPage.deleteAllFilesAndFolders();
		 
		homePage.navigateToMyFilesTab();
		
		docLibPage.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
	     sitesPage.documentdetails(fileName);
	        
	    docDetailsPageObj.performManageAspectsDocAction();
	        
	    docDetailsPageTestObj.verifyAspectsAvailable();
	    docDetailsPageObj.addAspectsAndApllyChangesToAFile();
	    
	    String ActualAspectType2 = appSearchPg.getMetadata(fileName,
				"Clips:");

		
		
		if(ActualAspectType2.equals(aspectprop)) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
							+ ActualAspectType2,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
							+ aspectprop,
					Status.FAIL);
		}
		
		docDetailsPageObj.commonMethodForPerformDocAction("Link To..");
			sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, "Link To..");
		
		docDetailsPageObj.selectFolderInLinkPopUp(siteNameValue);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		
		myFilesTestObj.verifyLinkImage(fileName);
	
		
		
		
		
		
		
		
		
		
		
		
	/*	
		docDetailsPageObj.performManageAspectsDocAction();	
		docDetailsPageObj.commonMethodForDeleteAspects(aspectNames);*/
	     
		
/*		homePage.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		
		if(sitesPage.Checkdocument(folderName)){
			sitesPage.documentdetails(folderName);
			docLibPage.deleteAllFilesAndFolders();
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}else{
			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(folderName);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
		}
		
		//myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
	       sitesPage.documentdetails(fileName);
	        
	        docDetailsPageObj.performManageAspectsDocAction();
	        
	        docDetailsPageTestObj.verifyAspectsAvailable();
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
			docDetailsPageTestObj.verifyAttributeInDocPropertiesnew(aspectprop);*/
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
