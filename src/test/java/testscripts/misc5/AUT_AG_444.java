package testscripts.misc5;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;


public class AUT_AG_444 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void copyToRepoSharedSubFolderCheck()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5929_Verify shared sub folder becomes unshared in target location upon copying to different repository via copy to repo"
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
		String siteToSelect=dataTable.getData("Sites", "SiteToSelect");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails=dataTable.getData("MyFiles", "CreateFolder");
		String folderName=dataTable.getData("MyFiles", "Version");
		String[] bulkJobDetails=dataTable.getData("MyFiles", "Sort Options").split(",");
		String[] moreSettingsOption=dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String toVerifyFolderDetails=dataTable.getData("MyFiles", "CreateFileDetails");
		String toVerifyFolderName=dataTable.getData("MyFiles", "TagName");
		int i=0,size;
		
		sitesPage.siteFinder(siteToSelect);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		
		
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		
		String properties = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String message = dataTable.getData("Sharebox", "Message");
		String date = new DateUtil().getCurrentDate();
		String notification = dataTable.getData("Sharebox", "Notifications");
		String notifyDetails = dataTable.getData("Sharebox", "Notify_Sharer");
		String repositryName="";
		String[] siteOrFolderName=dataTable.getData("MyFiles", "RelationshipName").split(",");

		String environment = Settings.getInstance().getProperty("ApplicationUrl");
		if(environment.contains("qa")) {
			repositryName="Repository[QA WIP (AWS)]";
		}else if(environment.contains("usppewip")) {
			repositryName="Repository[AWS US PPE WIP]";
		}
		
		sitesPage.clickOnMoreSetting(folderName);
		sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSettingsOption[0]);
		shareboxPg.commonMethodToEnterSharingProperties(properties,
				message, notification, notifyDetails);
		shareboxPg.clickOnSaveBtnInSharingPopup();
		
	   if(shareboxPg.checkShareIconAvailable(folderName)){
			myFiles.openCreatedFolder(folderName);
			myFiles.createFolder(toVerifyFolderDetails);
			sitesPage.clickOnMoreSetting(toVerifyFolderName);
			sitesPage.commonMethodForClickOnMoreOptionLink(toVerifyFolderName, moreSettingsOption[0]);
			shareboxPg.commonMethodToEnterSharingProperties(properties,
					message, notification, notifyDetails); 
			shareboxPg.clickOnSaveBtnInSharingPopup();
		
		
			if(shareboxPg.checkShareIconAvailable(toVerifyFolderName) && !repositryName.isEmpty()) {
				sitesPage.clickOnMoreSetting(toVerifyFolderName);
				sitesPage.commonMethodForClickOnMoreOptionLink(toVerifyFolderName, moreSettingsOption[1]);
				docLibPage.clickOnRepositoryButtonInCopyToRepoPopUp();
				docLibPage.selectRepositryUnderPathInCopyToRepoPopUp(repositryName);
				for(String value:siteOrFolderName) {
					if(value.equals("SITENAME")) {
						value=siteToSelect.toLowerCase();
					}
					docLibPage.selectSiteOrFolderUnderSelectedRepositryInCopyToRepoPopUp(value);
				}
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
			
				sitesPage.siteFinder(siteToSelect);
				sitesPage.enterIntoDocumentLibrary();
				if (myFiles.isFileOrFolderDisplayed(toVerifyFolderName) && !shareboxPg.checkShareIconAvailable(toVerifyFolderName)) {
					report.updateTestLog("Verify shared folder become unshared after copy to repo",
						"Shared sub folder become unshared after copy to repo successfull", Status.PASS);
				} else {
					report.updateTestLog("Verify shared folder become unshared after copy to repo",
						"Shared sub folder become unshared after copy to repo failed", Status.FAIL);
				}
		  
			
			} else {
				report.updateTestLog("Verify shared folder become unshared after copy to repo",
					"Pre condition itself failed", Status.FAIL);
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
