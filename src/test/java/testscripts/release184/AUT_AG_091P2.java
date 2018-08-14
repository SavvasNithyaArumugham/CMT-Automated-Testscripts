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
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Naveen Duvvuru
 */
public class AUT_AG_091P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_AUT_091P2() {
		testParameters
				.setCurrentTestDescription("Verify the navigation on clicking breadcrumb form view details page for the linked folders with different site created by different user");
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
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		String siteName = dataTable.getData("Sites", "SiteName");
		String targetSiteName = dataTable.getData("Sites", "TargetSiteName");
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteName);
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
		docLibPg.commonMethodForClickOnMoreSettingsOption(parentFolderName, moreSettingsOption);
		docLibPg.performLinkToOperation(targetSiteName, folderName);
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(targetSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		myFiles.openCreatedFolder(folderName);
		
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
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}