package testscripts.links;

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
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author Naresh Kumar Salla
 */
public class TC30 extends TestCase {
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_021()
	{
		testParameters.setCurrentTestDescription("Verify that user is able to upload new version at parent file of linked file from Document action menu");
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
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
	
		
		
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		homePage.navigateToMyFilesTab();
		docLibPage.deleteAllFilesAndFolders();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName").trim();		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		
		
	//	homePage.navigateToRepositoryTab();
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		ArrayList<String> menuOptionList = new ArrayList<String>(); 
		String menuOption = dataTable.getData("Sites", "SelectedItemsMenuOption").trim();
		StringTokenizer tokenMenuOption = new StringTokenizer(menuOption, ",");
		while (tokenMenuOption.hasMoreElements()) {
			menuOptionList.add(tokenMenuOption.nextToken());
			
		}
		
		System.out.println(menuOptionList);
		
		sitesPage.clickOnMoreSetting(fileName);			
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName, menuOptionList.get(0));
		docLibPage.linkFileToMyFiles(fileName);
		
	/*	sitesPage.clickOnSelectedItems();
		docLibPage.commonMethodForClickOnSelectedItemsMenuOption(menuOptionList.get(1));
		
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);*/
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		AlfrescoMyTasksPageTest taskPageTest = new AlfrescoMyTasksPageTest(scriptHelper);
		//Verify that the latest version listed (top of list) of assets associate with task
		taskPageTest.verifyUploadedFileWithLastVersion(fileName);
		
		docDetailsPage.uploadNewVersionFileInDocumentDetailsPage();
		
		taskPageTest.verifyUploadedFileWithLastVersion(fileName);
		
		AlfrescoDocumentDetailsPageTest docDetailsTestPage = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		//Verify that user is able select and view previous versions in an ordered list
		
		homePage.navigateToMyFilesTab();
		
		sitesPage.documentdetails(fileName);
				
		docDetailsTestPage.verifyPreviousVersionInOrderedList();
				
		//Verify that user is able to view date and user who checked-in for each version
				
		docDetailsTestPage.verifyCheckedInPersonAndDate();
				
		//Validate that all earlier versions will be retained
				
		docDetailsTestPage.verifyPreviousVersionsRetained();
		

	}
	
	@Override
	public void tearDown() {
		
	}

}
