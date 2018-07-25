package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC014P3 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_02() {
		testParameters
		.setCurrentTestDescription("Verify user(Manager/Coordinator/Collaborator/Contributor) is able to view Streaming media option under create when user is member of Media Developers group <br>"
				+ "Verify user(Manager/Coordinator/Collaborator/Contributor) is able to view Streaming media option for JSON file created by self when user is member of Media Developers group <br>"
				+ "Verify user(Manager/Coordinator/Collaborator/Contributor) is not able to view Streaming media option under create when user is not member of Media Developers group<br>"
				+ "Verify user(Manager/Coordinator/Collaborator) is able to view Streaming media option for JSON file created by others when user is not member of Media Developers group <br>"
				+ "Part 3 : User who is non member of media group cant see create Streaming media link but can see Streaming Media for existing json file");
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

		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoDocumentDetailsPageTest docDetailsTest = new
					AlfrescoDocumentDetailsPageTest(scriptHelper);

			AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String type = dataTable.getData("MyFiles", "Version");
/*		
			String title = dataTable.getData("Media_Transform", "ProfDesc");
			String referenceid = dataTable.getData("Media_Transform", "macCode");
			*/
			// Login alf cms
			functionalLibrary.loginAsValidUser(signOnPage);

			// From the site Type dropdown select 'Collaboration Site '.

			 String siteName = sitesPage.getCreatedSiteName();

			sitesPage.siteFinder(siteName);

			// Navigate to document library
			sitesPage.enterIntoDocumentLibrary();
			
			String finalXpathForMoreOptionLink = myFiles.createLinkXpath
					.replace("CRAFT", type);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, myFiles.createLinkXpath);
			UIHelper.click(driver,myFiles.createLinkXpath);
			
			if(UIHelper.checkForAnElementbyXpath(driver, finalXpathForMoreOptionLink)){
				
				report.updateTestLog("Verify" + type + "Element available",
						type + " Element is not displayed for non member of Media Developer Group", Status.PASS);
				
			}else{
				report.updateTestLog("Verify" + type + "Element available",
						type + " Element is displayed for non member of Media Developer Group", Status.FAIL);
			}
			
			sitesPage.enterIntoDocumentLibrary();
			String	titleName = new FileUtil().readDataFromFile(alfrescoAVTPage.testOutputFilePath);
			
			sitesPage.documentdetails(titleName);
			
			sitesPage.clickOnMoreSetting(titleName+"."+"json");
			AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(
					scriptHelper);
			sitesPageTestObj
					.verifyMoreSettingsOptionForFileOrFolderItem(
							titleName+"."+"json", "Streaming Media");
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
			
	}

	@Override
	public void tearDown() {

	}
}
