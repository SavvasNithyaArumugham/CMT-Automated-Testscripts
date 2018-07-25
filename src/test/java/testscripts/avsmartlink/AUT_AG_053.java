package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_053 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_025() {
		testParameters.setCurrentTestDescription(
					
			"ALFDEPLOY-4452_Verify the created Smart Link folders for Table link have Smart Link object appropriately."
			+"ALFDEPLOY-3995_Verify Table smart link object and new content folder is created in site activities and verify navigation of smart link object"
			+"ALFDEPLOY-3995_Validate the smart link is updated correctly when Title,external URL fields are edited via edit properties for Table link."		
			
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
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);	
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoHomePage homepg =  new AlfrescoHomePage(scriptHelper);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String siteName = dataTable.getData("Sites", "SiteName");
		String type = dataTable.getData("Home", "ColumnNoofremvDashlet");
		String title = dataTable.getData("Document_Details", "FilePath");
		String editextURLlInk = dataTable.getData("Document_Details", "Comments");
		String extURLLink = dataTable.getData("Document_Details", "FileName");		
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		String[] smartlinktype = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String typeofsmart = "";
		
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
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
		
						
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType(typeofsmart, link);		
		avsmart.entersmarttypedata(link, title, extURLLink, "Image", "Caption", "cred"
				+ "it","", System.getProperty("user.dir")+filePath+fileName[0]);		
		avsmart.EnteringImageref(link, title);	
		avsmart.submitbutton(link,title);
		
		sitesPage.enterIntoSiteDashboard();
		// verfying in site content:
		
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		if(!sitesDashboardPg.isSiteContentDashletAdded()){
			sitesDashboardPg.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		
		sitesDashboardPg.selectSiteContentInSiteActivityDashlet();
		Boolean flag= homepg.isUserActivityDisplayedSiteContentDashlet(title);
		if(flag){
			report.updateTestLog(
					"Verify Table smart link object and new content folder is created in site activities",
					"Table smart link"+title+" object and new content folder is created in site activities",
					Status.PASS);
		}else{
			report.updateTestLog(
					"Verify Table smart link object and new content folder is not created in site activities",
					"Table smart link"+title+" object and new content folder is not created in site activities",
					Status.FAIL);
		}
		
		homepg.ClickActivityDisplayedinSiteContentDashlet(title);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.openFolder(title);
		report.updateTestLog("Edit Smartlink", "<b>Edit the Title , External URL", Status.DONE);
		collectionPg.clickOnMoreSetting(title);
		collectionPg.commonMethodForClickOnMoreSettingsOption(title,"Edit SmartLink");
		avsmart.editsmarttypedatainduvidual(link, "title", "ChangedTitle");
		avsmart.editsmarttypedatainduvidual(link, "extURLLink", editextURLlInk);
		avsmart.submitbutton(link,"ChangedTitle");
		
		}	
		}catch(Exception e){
			report.updateTestLog("AUT_AG_053 status:", "<br>AUT_AG_053 Testcase is Failed", Status.FAIL);							
			
		}
		
	}

	@Override
	public void tearDown() {

	}
}
