package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;


public class AUT_AG_448 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void copyToRepoMultipleSharedAndLinkedFolderCheck()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5929_Verify linked shared folder containing multiple shared subfolders becomes unshared and unlinked in target location(shared folder) upon copying to different repository via copy to repo"
				   );
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoHomePage homePageObj=new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails=dataTable.getData("MyFiles", "CreateFolder");
		String[] folderName=dataTable.getData("MyFiles", "Version").split(",");
		String[] bulkJobDetails=dataTable.getData("MyFiles", "Sort Options").split(",");
		String[] moreSettingsOption=dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String multipleFolderDetails=dataTable.getData("MyFiles", "CreateFileDetails");
		String[] multipleFolderName=dataTable.getData("MyFiles", "TagName").split(",");
		int i=0,size;
		
		homePageObj.navigateToSharedFilesTab();
		if(myFiles.isFileOrFolderDisplayed(folderName[0])) {
			myFiles.deleteCreatedFolder(folderName[0]);
		}
		
		
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String repositryName="";
		String siteOrFolderName=dataTable.getData("MyFiles", "RelationshipName");

		String environment = Settings.getInstance().getProperty("ApplicationUrl");
		if(environment.contains("qa")) {
			repositryName="Repository[QA WIP (AWS)]";
		}else if(environment.contains("usppewip")) {
			repositryName="Repository[AWS US PPE WIP]";
		}
		
		sitesPage.clickOnMoreSetting(folderName[0]);
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName[0], moreSettingsOption[0]);
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
		
		
	   if(shareboxPg.checkShareIconAvailable(folderName[0])){
			myFiles.openCreatedFolder(folderName[0]);
			myFiles.createFolder(multipleFolderDetails);
			
			for(String folder:multipleFolderName) {
			sitesPage.clickOnMoreSetting(folder);
			sitesPage.commonMethodForClickOnMoreOptionLink(folder, moreSettingsOption[0]);
			shareboxPg.commonMethodToEnterSharingProperties(properties,
					message, notification, notifyDetails); 
			shareboxPg.clickOnSaveBtnInSharingPopup();
			}
		
			if(shareboxPg.checkShareIconAvailable(multipleFolderName[0]) &&  shareboxPg.checkShareIconAvailable(multipleFolderName[1]) && !repositryName.isEmpty()) {
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.clickOnMoreSetting(folderName[0]);
				docLibPage.commonMethodForClickOnMoreSettingsOption(folderName[0], moreSettingsOption[1]);
				docDetailsPageObj.selectAFolderInLinkPopUp(siteNameValue,folderName[1]);
				docDetailsPageObj.clickLinkBtnInLinkPopUp();
				
				if(docLibPage.isLinkIconAvailable(folderName[0])) {
					sitesPage.clickOnMoreSetting(folderName[0]);
					sitesPage.commonMethodForClickOnMoreOptionLink(folderName[0], moreSettingsOption[2]);
					docLibPage.clickOnRepositoryButtonInCopyToRepoPopUp();
					docLibPage.selectRepositryUnderPathInCopyToRepoPopUp(repositryName);
					docLibPage.selectSiteOrFolderUnderSelectedRepositryInCopyToRepoPopUp(siteOrFolderName);
					docLibPage.clickOnCopyButtonInCopyToRepoPopUp();
			
					String id=docLibPage.getLoadingText();
					System.out.println(id);
			
					homePageObj.navigateToHomePage();
					if (!UIHelper.checkForAnElementbyXpath(driver, homePageObj.isBulkDashletAvailable)) {
						homePageObj.customizeSiteDashboard();
						sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
					}
			
			
					if(id!=null) {
						String[] tempId=id.split("_");
						size=tempId.length;
						id=tempId[size-1].trim().replaceAll("\\D+", "");
						System.out.println(id);
						do {
							UIHelper.waitFor(driver);
							UIHelper.waitFor(driver);
							if(homePageObj.isBulkJobpresentAsExpected(bulkJobDetails[0], bulkJobDetails[1],bulkJobDetails[2],id )) {
								break;
							}
							i++;
							if(i==10) {
								report.updateTestLog("BulK job check",
										"Bulk job check failed", Status.FAIL);
							}else {
								report.updateTestLog("BulK job check",
										i+" try for bulk job done", Status.DONE);
							}
						}while(i<10); 
					}else {
						UIHelper.waitForLong(driver);
					}
			
					homePageObj.navigateToSharedFilesTab();
					if (myFiles.isFileOrFolderDisplayed(folderName[0]) && !shareboxPg.checkShareIconAvailable(folderName[0]) && docLibPage.isLinkIconAvailable(folderName[0])) {
						myFiles.openCreatedFolder(folderName[0]);
						if(myFiles.isFileOrFolderDisplayed(multipleFolderName[0]) && myFiles.isFileOrFolderDisplayed(multipleFolderName[1]) && !shareboxPg.checkShareIconAvailable(multipleFolderName[0]) &&  !shareboxPg.checkShareIconAvailable(multipleFolderName[1])) {
							report.updateTestLog("Verify shared folder become unshared and unlinked after copy to repo",
									"Shared parent folder and mulitple sub folder become unshared after copy to repo successfull", Status.PASS);
						}
						else {
							report.updateTestLog("Verify shared folder become unshared and unlinked after copy to repo",
									"Shared parent folder and mulitple sub folder become unshared and unlinked after copy to repo failed", Status.FAIL);
						}
						
						
					} else {
						report.updateTestLog("Verify shared folder become unshared after copy to repo",
								"Shared parent folder itself unshared or unlinked after copy to repo failed", Status.FAIL);
					}
		  
			
				}else {
					report.updateTestLog("Verify shared folder become unshared after copy to repo",
							"Pre condition of link itself failed", Status.FAIL);
				}
			
	       }else {
				report.updateTestLog("Verify shared folder become unshared after copy to repo",
					"Pre condition of sub folder share itself failed", Status.FAIL);
			}
	}else {
		report.updateTestLog("Verify shared folder become unshared after copy to repo",
				"Pre condition of parent folder share itself failed", Status.FAIL);
	}
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
