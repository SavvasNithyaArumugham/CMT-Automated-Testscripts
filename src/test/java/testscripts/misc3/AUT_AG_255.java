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
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_255 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc3_039()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to select Minor/Major version while check-in multiple files");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");

		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		try {
			docLibPage.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		try {
			docLibPage.deleteAllFilesAndFolders();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UIHelper.waitFor(driver);
		UIHelper.waitForPageToLoad(driver);
		
		
		myFiles.uploadFile(filePath, fileName);
		ArrayList<String> menuOptionList = new ArrayList<String>(); 
		String menuOption = dataTable.getData("Sites", "SelectedItemsMenuOption");
		StringTokenizer tokenMenuOption = new StringTokenizer(menuOption, ",");
		while (tokenMenuOption.hasMoreElements()) {
			menuOptionList.add(tokenMenuOption.nextToken());
		}
		
		docLibPage.selectAllFilesAndFolders();
		sitesPage.clickOnSelectedItems();
		docLibPage.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(0));
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		
		docLibPage.selectAllFilesAndFolders();
		ArrayList<String> fileNameList = new ArrayList<String>(); 
		StringTokenizer tokenFileName = new StringTokenizer(fileName, ",");
		while (tokenFileName.hasMoreElements()) {
			
			docLibPage.selectAllFilesAndFolders();
			sitesPage.clickOnSelectedItems();
			docLibPage.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(1));
			String newVersionfileName = tokenFileName.nextToken();
			fileNameList.add(newVersionfileName);
			//newVersionfileName = newVersionfileName.replace(".docx", " (Working Copy).docx");
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPage.uploadNewVersionFileInDocumentDetailsPage(newVersionfileName);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}		
				
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docLibPage.openAFile(fileNameList.get(0));
		docDetailsPageTest.verifyUploadedNewVersionFile();
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.openAFile(fileNameList.get(1));
		docDetailsPageTest.verifyUploadedNewVersionFile();
	}

	@Override
	public void tearDown() {
		
	}

}
