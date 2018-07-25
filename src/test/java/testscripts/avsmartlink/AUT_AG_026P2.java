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

public class AUT_AG_026P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_025() {
		testParameters.setCurrentTestDescription(
							
				
			"ALFDEPLOY-4135_Verify the validation message is displayed on uploading unsupported image "
			+ "file through Edit Smart Link screen under Image Preview in Metrodigi Link."
			+"ALFDEPLOY-4135_Verify the validation message is displayed on selecting a unsupported file through Edit "
			+ "Smart Link screen under Image Preview in Metrodigi Link"
			+"ALFDEPLOY-4135_Verify the version of Smart Link changes for Metrodigi link after Image preview "
			+ "upload file field is updated with new image and submitted successfully."
			+"ALFDEPLOY-4135_Verify the version of Smart Link changes for Metrodigi link when already uploaded "
			+ "image in Image preview upload file field is cleared and submitted successfully."
			+"ALFDEPLOY-4135_Verify the version of Smart Link changes for Metrodigi link after Image preview select "
			+ "file field is updated with new image and submitted successfully."
			+"ALFDEPLOY-4135_Verify the version of Smart Link changes for Metrodigi link when already selected "
			+ "image in Image preview select file field is cleared and submitted successfully."
			+"ALFDEPLOY-4135_Verify the version is not incremented on clicking clear for image preview for "
			+ "already added image via Edit smart link and on performing cancel action for Metrodigi link"

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
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String siteName = dataTable.getData("Sites", "SiteName");
		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");		
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String[] smartlinktype = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String typeofsmart = "";
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		//UIHelper.waitForLong(driver);
		// Navigate into sites
		sitesPage.navigateToSitesTab();
		sitesPage.siteFinder(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();	
		docLibPg.deleteAllFilesAndFolders();
		
		// Upload the file in the targeted site:
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[2]);
		UIHelper.waitForPageToLoad(driver);
		
		//navigated back to source site
		sitesPage.navigateToSitesTab();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();	
		docLibPg.deleteAllFilesAndFolders();
		
		
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
			}else if (link.equalsIgnoreCase("pdfLink")){
				typeofsmart = "PDF Link";
			}else if (link.equalsIgnoreCase("mdPopUp")){
				typeofsmart = "MD Pop Up ";
			}
		
		// UPLOAD: Verify the version of the smartlink when we upload the image and submit			
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType(typeofsmart, link);		
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "cred"
				+ "it","", System.getProperty("user.dir")+filePath+fileName[0]);		
		avsmart.EnteringImageref(link, title);	
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		//UIHelper.waitForPageToLoad(driver);
		avsmart.VersionCheckdocument("1.0", title);
		
		
		//UPLOAD: Clear the image and submit it and check the version.
		report.updateTestLog("Version Check:", "<b>After Clearing the Uploaded image and Submit", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		avsmart.ClearbuttonforAllSmartlink(link, title);
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		//UIHelper.waitForPageToLoad(driver);
		avsmart.VersionCheckdocument("1.1", title);
		
		//SELECT: Verify the version of the smartlink when we select the image and submit	
		report.updateTestLog("Version Check:", "<b> select the image ", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		//UIHelper.waitForLong(driver);
		avsmart.selectimagefromSelect(link,targetSiteName,fileName[0]);
		//UIHelper.waitForLong(driver);
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		avsmart.VersionCheckdocument("1.2", title);
		
		//SELECT: Clear the select image:
		report.updateTestLog("Version Check:", "<b> Clear the selected image ", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		//UIHelper.waitForLong(driver);
		avsmart.ClearbuttonforAllSmartlink(link, title);
		//UIHelper.waitForLong(driver);
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		avsmart.VersionCheckdocument("1.3", title);
		
		//Version not incremented:
		report.updateTestLog("Version Check:", "<b> Version should not update ", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		//UIHelper.waitForLong(driver);
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "cred"
				+ "it","", System.getProperty("user.dir")+filePath+fileName[0]);		
		avsmart.EnteringImageref(link, title);	
		avsmart.submitbutton(link,title);
		//UIHelper.waitForLong(driver);
		//UIHelper.waitForPageToLoad(driver);
		avsmart.VersionCheckdocument("1.4", title);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		//UIHelper.waitForLong(driver);	
		avsmart.ClearbuttonforAllSmartlink(link, title);
		avsmart.cancelButtonOnEditSmartlink(link, title);
		//UIHelper.waitForLong(driver);	
		avsmart.VersionCheckdocument("1.4", title);
		
		// validation message of unsupported file for select and upload
		report.updateTestLog("Unsupported File Message", "<b>Checking Upload and Select Unsupported File Message", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		//UIHelper.waitForLong(driver);
		avsmart.ClearbuttonforAllSmartlink(link, title);
		avsmart.editsmarttypedatainduvidual(link, "imgpreview", System.getProperty("user.dir")+filePath+fileName[2]);
		avsmart.errormsgSelectAndUpload(fileName[2], "Upload", link);
		avsmart.selectimagefromSelect(link,targetSiteName,fileName[2]);
		avsmart.errormsgSelectAndUpload(fileName[2], "Select", link);
			
		}	
		}catch(Exception e){
			report.updateTestLog("AUT_AG_026P1 status:", "<br>AUT_AG_026P1 Testcase is Failed", Status.FAIL);							
			
		}
		
	}

	@Override
	public void tearDown() {

	}
}
