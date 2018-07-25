package testscripts.release184;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/* 
 * @author Naveen Duvvuru
 */
public class AUT_AG_088 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_AUT_088() {
		testParameters
				.setCurrentTestDescription("Verify the navigation on clicking breadcrumb form view details page for the,"
						+"<br>1. Linked folders within shared files"
						+"<br>2. Linked folders from shared files to other site");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String moreSetOption1 = "", moreSetOption2 = "";
		if(moreSettingsOption.contains(","))
		{
			String splittedMoreSetOptions[] = moreSettingsOption.split(",");
			
			if(splittedMoreSetOptions!=null && splittedMoreSetOptions.length>1)
			{
				moreSetOption1 = splittedMoreSetOptions[0];
				moreSetOption2 = splittedMoreSetOptions[1];
			}
			else
			{
				moreSetOption1 = "Link To";
				moreSetOption2 = "UnLink";
			}
		}
		else
		{
			moreSetOption1 = "Link To";
			moreSetOption2 = "UnLink";
		}
		
		String siteDetails = dataTable.getData("Sites", "CreateMultipleSites");
		
		sitesPage.createMultipleSitesWithoutTimeStamp(siteDetails);
		
		String createdSiteNames[] = sitesDashboardPageTest.getTheCreatedSiteNames();
		
		String publicSiteNameVal = "", moderateSiteNameVal = "";
		if(createdSiteNames!=null && createdSiteNames.length>0)
		{
			publicSiteNameVal = createdSiteNames[0];
			moderateSiteNameVal = createdSiteNames[1];
		}
		
		homePage.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(moderateSiteNameVal);
		sitesPage.enterIntoDocumentLibrary();
		
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String modSiteFolderName = "", modSiteFolderTitle = "", modSiteFolderDescription = "";
		if (folderDetails.contains(",")) {
			String splittedFolderValues[] = folderDetails.split(",");
			
			modSiteFolderName = splittedFolderValues[0].replace("FolderName:", "");
			modSiteFolderTitle = splittedFolderValues[1].replace("Title:", "");
			modSiteFolderDescription = splittedFolderValues[2].replace("Description:",
					"");
			
			myFiles.commonMethodForCreateFolder(modSiteFolderName,
					modSiteFolderTitle, modSiteFolderDescription);
			
			myFilesTestObj.verifyCreatedFolder(folderDetails);
		}
		
		homePage.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(publicSiteNameVal);
		sitesPage.enterIntoDocumentLibrary();
		
		try {
			docLibPg.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String parentFolderName = "", folderName = "", folderTitle = "", folderDescription = "";

		if (folderDetails.contains(",")) {
			String splittedFolderValues[] = folderDetails.split(",");

			folderName = splittedFolderValues[0].replace("FolderName:", "");
			folderTitle = splittedFolderValues[1].replace("Title:", "");
			folderDescription = splittedFolderValues[2].replace("Description:",
					"");

			for (int index = 1; index <= 5; index++) {
				
				if(index==1)
				{
					parentFolderName = folderName + index;
				}
				myFiles.commonMethodForCreateFolder(folderName + index,
						folderTitle + index, folderDescription + index);
				
				myFilesTestObj.verifyCreatedFolder(folderName + index);

				myFiles.openCreatedFolder(folderName + index);
			}

		}
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(parentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(parentFolderName, moreSetOption1);
		docLibPg.performLinkToOperation(moderateSiteNameVal, modSiteFolderName);
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(moderateSiteNameVal);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(modSiteFolderName);
		
		String breadCumbFolderLinkDetails = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		
		for(int index=1; index<=5; index++)
		{
			if(index==5)
			{
				myFiles.openCreatedFolder(folderName + index);
				
				docLibPg.clickOnBreadCrumbLinkDocLibPg(folderName + index);
				
				docDetailsPage.checkBreadCumbLinkXpath(folderName + index);
				
				if (breadCumbFolderLinkDetails.contains(";")) {
					String splittedBreadCumbDetails[] = breadCumbFolderLinkDetails.split(";");
					for (String breadCumbLinkVal : splittedBreadCumbDetails)
					{
						myFiles.backToFolderOrDocumentPage(breadCumbLinkVal);
						docLibPg.clickOnBreadCrumbLinkDocLibPg(breadCumbLinkVal);
						docDetailsPage.checkBreadCumbLinkXpath(breadCumbLinkVal);
					}
				}
			}
			else
			{
				if(index==1)
				{
					myFilesTestObj.verifyLinkedFolder(folderName + index);
				}
				
				docLibPg.naviageToFolder(folderName + index, folderName + (index+1));
			}
			
		}
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(publicSiteNameVal);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(parentFolderName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(parentFolderName, moreSetOption2);
		docLibPg.performUnLinkOperation(moderateSiteNameVal, modSiteFolderName);
		
		docLibPg.selectDocumentLibItems("Folders");
		sitesPage.clickOnSelectedItems();
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		sitePgTestObj.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
	
		docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		docLibPg.performLinkToOperation(moderateSiteNameVal, modSiteFolderName);
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(moderateSiteNameVal);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(modSiteFolderName);
		
		for(int index=1; index<=5; index++)
		{
			if(index==5)
			{
				myFiles.openCreatedFolder(folderName + index);
				
				docLibPg.clickOnBreadCrumbLinkDocLibPg(folderName + index);
				
				docDetailsPage.checkBreadCumbLinkXpath(folderName + index);
				
				if (breadCumbFolderLinkDetails.contains(";")) {
					String splittedBreadCumbDetails[] = breadCumbFolderLinkDetails.split(";");
					for (String breadCumbLinkVal : splittedBreadCumbDetails)
					{
						myFiles.backToFolderOrDocumentPage(breadCumbLinkVal);
						docLibPg.clickOnBreadCrumbLinkDocLibPg(breadCumbLinkVal);
						docDetailsPage.checkBreadCumbLinkXpath(breadCumbLinkVal);
					}
				}
			}
			else
			{
				if(index==1)
				{
					myFilesTestObj.verifyLinkedFolder(folderName + index);
				}
				
				docLibPg.naviageToFolder(folderName + index, folderName + (index+1));
			}
		}
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}