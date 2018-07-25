package testscripts.misc4;

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

/**
 * @author Naresh Kumar Salla
 */
public class AUT_AG_349 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_228()
	{
		testParameters.setCurrentTestDescription("Verify that Upload Window is closed automatically once file are uploaded");
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
		docLibPage.deleteAllFilesAndFolders();
		UIHelper.waitFor(driver);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
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
		int index = 0;
		ArrayList<String> fileNameList = new ArrayList<String>(); 
		StringTokenizer tokenFileName = new StringTokenizer(fileName, ",");
		while (tokenFileName.hasMoreElements()) {
			
			if(index > 2){
				break;
			}
			docLibPage.selectAllFilesAndFolders();
			sitesPage.clickOnSelectedItems();
			docLibPage.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(1));
			String newVersionfileName = tokenFileName.nextToken();
			fileNameList.add(newVersionfileName);
			newVersionfileName = newVersionfileName.replace(".", ".");
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPage.uploadNewVersionFileInDocumentDetailsPage(newVersionfileName);
			UIHelper.waitFor(driver);
			
			index++;
		}
				
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyForDuplicateFiles(fileNameList);
		
		docDetailsPageTest.verifyUploadWindowVisibility();
	}

	@Override
	public void tearDown() {
		
	}

}
