package testscripts.collections;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_2206_TC006 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_004() {
		testParameters.setCurrentTestDescription(
				"1. Can see that the following plain 'Asset' subfolders  were automatically included within the new Site from the Site Template"
						+ "<br> 2. Can see that the following plain 'Data Export 'subfolders were automatically included within the new Site from the Site Template"
						+ "<br> 3. Can see that the following 'program' type subfolder is automatically included within the new Site from the Site Template"
						+ "<br> 4. Can see that the following 'course' type subfolder is automatically included within the new Site from the Site Template");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		// String testOutputFilePath =
		// "src/test/resources/AppTestData/TestOutput/SiteDetails.txt";
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(",");
		String folderDetails1[] = dataTable.getData("MyFiles", "Subfolders1").split(",");
		String folderDetails2[] = dataTable.getData("MyFiles", "Subfolders2").split(",");
		String folderDetails3[] = dataTable.getData("MyFiles", "Subfolders3").split(",");

		homePageObj.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		try {

			AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String[] parentFolderName = dataTable.getData("MyFiles", "FileName").split(",");

			// sourceSiteName = new
			// FileUtil().readDataFromFile(testOutputFilePath);

			sitesPage.openSiteFromRecentSites(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();

			// Enter into Asset folder and verify its sub folder
			UIHelper.waitForPageToLoad(driver);
			myFiles.openCreatedFolder(parentFolderName[0]);

			System.out.println(parentFolderName[0]);
			String[] folders = folderDetails;

			for (String folderName : folders) {

				if (parentFolderName[0].equalsIgnoreCase("Assets")) {
					myFilesTestObj.verifyFolder(folderName);
				} else {
					System.out.println("invalid parent folder selection");
				}
			}

			// Enter into Data Export folder and verify its sub folder
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(parentFolderName[1]);
			System.out.println(parentFolderName[1]);
			String[] folders1 = folderDetails1;

			for (String folderName : folders1) {

				if (parentFolderName[1].equalsIgnoreCase("Data Exports")) {
					myFilesTestObj.verifyFolder(folderName);
				} else {
					System.out.println("invalid parent folder selection");
				}
			}

			// Enter into Programs folder and verify its sub folder
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(parentFolderName[2]);
			System.out.println(parentFolderName[2]);
			String[] folders2 = folderDetails2;

			for (String folderName : folders2) {

				if (parentFolderName[2].equalsIgnoreCase("Programs")) {
					myFilesTestObj.verifyFolder(folderName);
				} else {
					System.out.println("invalid parent folder selection");
				}
			}

			// Enter into Course folder and verify its sub folder
			sitesPage.enterIntoDocumentLibrary();
			myFiles.openCreatedFolder(parentFolderName[3]);
			System.out.println(parentFolderName[3]);
			String[] folders3 = folderDetails3;

			for (String folderName : folders3) {

				if (parentFolderName[3].equalsIgnoreCase("Courses")) {
					myFilesTestObj.verifyFolder(folderName);
				} else {
					System.out.println("invalid parent folder selection");
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		UIHelper.waitFor(driver);
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
