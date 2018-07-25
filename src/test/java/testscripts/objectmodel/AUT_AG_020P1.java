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
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
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
public class AUT_AG_020P1 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_004()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-4207_Verify user able to link a video file with clippable aspect to anyother location<br>"+
	"ALFDEPLOY-4207_Verify user able to copy a video file with clippable aspect to anyother location<br>");
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSiteMembersPageTest siteMemPgTest = new AlfrescoSiteMembersPageTest(
				scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
	
	
		String siteName = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String aspectprop = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String destinationFolder = dataTable.getData("Sites", "TargetFolder");
		
		String siteuserName = dataTable.getData("Sites", "InviteUserName");
		String roleName = dataTable.getData("Sites", "Role");
		functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToSitesTab();
		sitesPage.createSite(siteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
	///	sitesPage.siteFinder(site);
		
      	sitesPage.performInviteUserToSite(site);
        siteMemPgTest.verifySiteMemebrs(site, siteuserName, roleName);
		
		sitesPage.enterIntoDocumentLibrary();

		
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
		
				
	
		sitesPage.enterIntoDocumentLibrary();	
		 sitesPage.documentdetails(fileName);
		
		docDetailsPageObj.clickCopyToDocAction();
		docDetailsPageObj.selectFolderToCopyInCopyPopUp(fileName,site,destinationFolder);
		 
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
		 
		 docLibPage.deleteAllFilesAndFolders();
		 
		 sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.clickOnMoreSetting(fileName);
		sitesPage.clickOnMoreLinkOptions(fileName, "Link To..");
		
		docDetailsPageObj.selectFolderInLinkPopUp(site);
		docDetailsPageObj.clickLinkBtnInLinkPopUp();
		
		
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		
		sitesPage.enterIntoDocumentLibrary();
		
		//myFilesTestObj.verifyLinkImage(fileName);
		
		 sitesPage.documentdetails(destinationFolder);

		 if(sitesPage.documentAvailable(fileName)) {
			 report.updateTestLog("Verify Clippable aspest applied file Link to functionality",
						"Clippable aspest applied file Link to functionality successful" + "<br/><b>File Name:</b>"
								+ fileName,
						Status.PASS);
			 
		 }else {
			 report.updateTestLog("Verify Clippable aspest applied file Link to functionality",
						"Clippable aspest applied file Link to functionality FAILEd" + "<br/><b>File Name:</b>"
								+ fileName,
						Status.FAIL);
		 }
		
		
		
		/*********************************************************/
		
		/*homePage.navigateToMyFilesTab();
		
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
