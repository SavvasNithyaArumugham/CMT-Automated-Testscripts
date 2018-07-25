package testscripts.misc3;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_321P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void sharebox_003() {
		testParameters.setCurrentTestDescription("1.Verify a French translation for lifecycle options for selected items menu."
				+"<br>2.Verify user can perform basic functionalities such as create a folder, upload files , copy , move etc with french language.");
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
		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			functionalLibrary.loginAsValidUser(signOnPage);

			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoSitesPageTest sitesPgTest = new AlfrescoSitesPageTest(scriptHelper);
		
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		

			String sourceSiteName = dataTable.getData("Sites", "SiteName");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String folderDetails= dataTable.getData("MyFiles", "CreateFolder");
			
			String folderName = dataTable.getData("MyFiles", "Version");
			String copyOperation=dataTable.getData("MyFiles", "RelationshipName");
			String moveOperation=dataTable.getData("MyFiles", "BrowseActionName");
			String destmoveFolder=dataTable.getData("MyFiles","CreateFileDetails");

			String attachLifecycleoptions = dataTable.getData("MyFiles", "MoreSettingsOption");
			String detachLifeCycleoptions= dataTable.getData("Sites","SelectedItemsMenuOption");

		
			homePageObj.navigateToSitesTab();
			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			myFiles.uploadFile(filePath, fileName);
			sitesPage.clickOnMoreSetting(fileName);
			sitesPgTest.verifyMoreSettingsOptionForFileOrFolderItem(fileName, attachLifecycleoptions);
            sitesPage.commonMethodForPerformBrowseOption(fileName, attachLifecycleoptions);

			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			docDetailsPage.changeLifeCycleSate(attachLifeCycleDropdownVal);
			
			
			

			sitesPage.clickOnMoreSetting(folderName);
			docLibPg.clickSelectedItemsMenu();
			if (docLibPg.commonMethodToCheckOnSelectedItemsMenuOption(attachLifecycleoptions)) {
				report.updateTestLog("Verify Attach lifecycle option should be displayed in french in Selected items options for which a lifecycle is not yet attached folders",
						" " + attachLifecycleoptions + " is displayed in Selected items options", Status.PASS);
			} else {
				report.updateTestLog("Verify Attach lifecycle option should be displayed in french in Selected items options for which a lifecycle is not yet attached folders",
						" " + attachLifecycleoptions + " is not displayed in Selected items options", Status.FAIL);
			
			}

			sitesPage.clickOnMoreSetting(folderName);
			
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.clickSelectedItemsMenu();
			if (docLibPg.commonMethodToCheckOnSelectedItemsMenuOption(detachLifeCycleoptions)) {
				report.updateTestLog("Verify Detach lifecycle option should be displayed in french in Selected items options for which a lifecycle is already attached folders",
						" " + detachLifeCycleoptions + " is displayed in Selected items options", Status.PASS);
			} else {
				report.updateTestLog("Verify Detach lifecycle option should be displayed in french in Selected items options for which a lifecycle is already attached folders",
						" " + detachLifeCycleoptions + " is not displayed in Selected items options", Status.FAIL);
			
			}
			sitesPage.clickOnMoreSetting(fileName);
			
			//Copy Operation
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, copyOperation);
			docLibPg.performCopyToOperation(sourceSiteName, folderName);
			
			myFiles.createFolder(folderDetails);
			
			//Move Operation
			
			sitesPage.clickOnMoreSetting(folderName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(folderName, moveOperation);
			docLibPg.performMoveToOperation(sourceSiteName,destmoveFolder);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
			
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}