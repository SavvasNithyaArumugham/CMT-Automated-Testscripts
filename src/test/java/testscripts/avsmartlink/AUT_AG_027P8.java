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

public class AUT_AG_027P8 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_025() {
		testParameters.setCurrentTestDescription(
							
				
		"ALFDEPLOY-4118_Verify the Minor version increment on editing any fields available for external website"
		+ "links via Edit smart link screen"
		+"ALFDEPLOY-4118_Verify the version should not get incremented for external websitelinks "
		+ "when saving the details via Edit smart link screen without editing any field values."
		+"ALFDEPLOY-4223_Verify title and url of external website  smart link object is editable and there "
		+ "should be minor increment in version once changes are done."
		+"ALFDEPLOY-4119_Verify there should be increment version when there is a change in URL for "
		+ "smart link -external website"
		+"ALFDEPLOY-4119_Verify there should be increment version when there is a change in Title for smart"
		+ " link - external website"
		+"ALFDEPLOY-4119_Verify there should be increment version when there is a change in URL and title for"
		+ " smart link - external website"
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
		UIHelper.waitForPageToLoad(driver);
		
		//navigated back to source site
		sitesPage.navigateToSitesTab();
		sitesPage.siteFinder(siteName);
		sitesPage.enterIntoDocumentLibrary();	
		docLibPg.deleteAllFilesAndFolders();
		
		// Upload:
		
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
					
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType(typeofsmart, link);		
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "cred"
				+ "it","", "");		
		//avsmart.EnteringImageref(link, title);	
		avsmart.submitbutton(link,title);
	//	UIHelper.waitForLong(driver);
	//	UIHelper.waitForPageToLoad(driver);
		avsmart.VersionCheckdocument("1.0", title);
		
		
		//External URL and title link updation
		report.updateTestLog("Edit Smartlink Updation:", "<b>External URL and Title Update", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		avsmart.editsmarttypedatainduvidual(link, "extURLLink", properties.getProperty("ApplicationUrl"));
		avsmart.editsmarttypedatainduvidual(link, "title", "Changed");
		avsmart.submitbutton(link,"Changed");
	//	UIHelper.waitForLong(driver);
	//	UIHelper.waitForPageToLoad(driver);
		avsmart.VersionCheckdocument("1.1", "Changed");
		
		//No version updation, when submitting with out editing any fields
		report.updateTestLog("Edit Smartlink Updation:", "<b>No updating, Click on submit without "
				+ "editing any files", Status.DONE);
		collectionPg.clickOnMoreSetting("Changed");
		collectionPg.commonMethodForClickOnMoreSettingsOption("Changed","Edit SmartLink");
	//	UIHelper.waitForLong(driver);
		avsmart.submitbutton(link,"Changed");
	//	UIHelper.waitForLong(driver);
		avsmart.VersionCheckdocument("1.1", "Changed");
			
		}	
		}catch(Exception e){
			report.updateTestLog("AUT_AG_027P3 status:", "<br>AUT_AG_027P3 Testcase is Failed", Status.FAIL);							
			
		}
		
	}

	@Override
	public void tearDown() {

	}
}
