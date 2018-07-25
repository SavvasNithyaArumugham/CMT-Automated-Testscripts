package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_024P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(				
				
				"<br>ALFDEPLOY-4134_Verify the UI path for an already uploaded image under Image Preview - Upload file "
				+ "button for Video External link through Edit smartlink."
				+"<br>ALFDEPLOY-4134_Verify the UI path on uploading a new file under Image Preview - Upload file button "
				+ "<br>for Video External link through Edit smartlink."
				+"<br>ALFDEPLOY-4134_Verify the UI path for an already uploaded image under Image Preview - Select file "
				+ "button for Video External link through Edit smartlink."
				+"<br>ALFDEPLOY-4134_Verify the UI path on uploading a new file under Image Preview - Select file button "
				+ "for Video External link through Edit smartlink"
				+"ALFDEPLOY-4130_Verify Select File Button is displayed under Video External link to choose existing "
				+ "files from alfresco in Edit smartlink Screen."
				+"ALFDEPLOY-4129_Verify Select File Button is displayed under Video External link to choose from existing "
				+ "files in Create smartlink Screen."
				+"ALFDEPLOY-4224_Verify on click of Edit smartlink for Video External object, the Edit smartlink form "
				+ "should be expanded."
				+"ALFDEPLOY-4131_Verify user is able to clear the uploaded file and its path under Video External "
				+ "Link through Edit Smart Link option."
				+"ALFDEPLOY-4131_Verify user is able to clear the selected file and its path under Video External Link "
				+ "through Edit Smart Link option."
				+"ALFDEPLOY-4132_Verify user can create new association for other user's smart link object"
				
				);
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
		try{
			
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");		
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String[] smartlinktype = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String typeofsmart = "";
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
	
		sitesPage.enterIntoDocumentLibrary();	
		
		for (String link:smartlinktype){
			
			if (link.equalsIgnoreCase("imageExternalLink")){
				typeofsmart = "Image External Link";
			}else if (link.equalsIgnoreCase("videoExternalLink")){
				typeofsmart = "Video External Link";
			}else if (link.equalsIgnoreCase("audioExternalLink")){
				typeofsmart = "Audio External Link";
			}else if (link.equalsIgnoreCase("externalWebsiteLink")){
				typeofsmart = "External Website Link";
			}else if (link.equalsIgnoreCase("thirdPartyInteractiveLink")){
				typeofsmart = "3rd Party Interactive Link";
			}else if (link.equalsIgnoreCase("metrodigiLink")){
				typeofsmart = "Metrodigi Link";
			}else if (link.equalsIgnoreCase("tableLink")){
				typeofsmart = "Table Link";
			}
		
			String image = "ImageSmartlink";
			//user can create association for other user's smartlink object	
			sitesPage.documentdetails(image);		
			if(sitesPage.documentAvailable("AlfrescoTestTitleFile.jpg")){
				report.updateTestLog("Verify user can create new association for other user's smart link object ",
						"user can create new association for other user's smart link object ", Status.PASS);
			}else{
				report.updateTestLog("Verify user can create new association for other user's smart link object ",
						"user can create new association for other user's smart link object ", Status.FAIL);
			}
			
			
			
			collectionPg.clickOnMoreSetting(image);
			collectionPg.commonMethodForClickOnMoreSettingsOption(image,"Edit SmartLink");
			avsmart.EditSmartlinkFilePathValidation("imageExternalLink",image,siteName,fileName);
			avsmart.ClearbuttonforAllSmartlink("imageExternalLink", image);
			avsmart.selectimagefromSelectreportory("imageExternalLink",targetSiteName,fileName);
			
			avsmart.submitbutton("imageExternalLink",image);
			collectionPg.clickOnMoreSetting(image);
			collectionPg.commonMethodForClickOnMoreSettingsOption(image,"Edit SmartLink");
			avsmart.EditSmartlinkFilePathValidation("imageExternalLink",image,targetSiteName,fileName);
			
			avsmart.cancelButtonOnEditSmartlink("imageExternalLink", image);
			
			
			sitesPage.navigateToSitesTab();
			sitesPage.siteFinder(targetSiteName);
			sitesPage.enterIntoDocumentLibrary();	
			docLibPg.deleteAllFilesAndFolders();
			
			// Upload the file in the targeted site:
			collectionPg.uploadFileInCollectionSite(filePath, fileName);
			String siteNames = dataTable.getData("Sites", "SiteName");
			sitesPage.navigateToSitesTab();
			sitesPage.siteFinder(siteNames);
			UIHelper.waitFor(driver);
		sitesPage.enterIntoDocumentLibrary();		
		docLibPg.deleteAllFilesAndFolders();	
	//	UIHelper.waitForPageToLoad(driver);	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType(typeofsmart, link);		
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "credit","", System.getProperty("user.dir")+filePath+fileName);
		avsmart.EnteringImageref(link, title);
		avsmart.SmartlinkFilePathvalidation(link, fileName,"upload","","");
		avsmart.ClearbuttonforAllSmartlink(link, title);
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "credit","", System.getProperty("user.dir")+filePath+fileName);
		
		//avsmart.entersmarttypedata(link, "", "", "", "", "","", System.getProperty("user.dir")+filePath+fileName);
		//UIHelper.waitForLong(driver);
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		avsmart.EditSmartlinkFilePathValidation(link,title,siteName,fileName);
		avsmart.cancelButtonOnEditSmartlink(link, title);
		sitesPage.enterIntoDocumentLibrary();
		docLibPg.deleteAllFilesAndFolders();
		
		// Select:
		
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType(typeofsmart, link);
		//UIHelper.waitForPageToLoad(driver);
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "credit","", System.getProperty("user.dir")+filePath+fileName);		
		avsmart.EnteringImageref(link, title);
		avsmart.selectimagefromSelect(link,targetSiteName,fileName);
		//UIHelper.waitForPageToLoad(driver);
		avsmart.SmartlinkFilePathvalidation(link, fileName,"select",targetSiteName,title);
		avsmart.ClearbuttonforAllSmartlink(link, title);
		avsmart.selectimagefromSelect(link,targetSiteName,fileName);
		//.waitForPageToLoad(driver);
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		//UIHelper.waitForPageToLoad(driver);		
		avsmart.EditSmartlinkFilePathValidation(link,title,targetSiteName,fileName);
		avsmart.selectimagefromSelect(link,targetSiteName,fileName);
		//UIHelper.waitForPageToLoad(driver);
		avsmart.EditSmartlinkFilePathValidation(link,title,targetSiteName,fileName);
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		//sitesPage.enterIntoDocumentLibrary();
		//docLibPg.deleteAllFilesAndFolders();
		//UIHelper.waitForPageToLoad(driver);
		
		}
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("AUT_AG_024P2 status:", "<br>AUT_AG_024P2 Testcase is Failed", Status.FAIL);							
			
		}
		
	}

	@Override
	public void tearDown() {

	}
}
