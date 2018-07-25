package testscripts.misc2;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_158 extends TestCase{

	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_054()
	{
		testParameters.setCurrentTestDescription("To verify that user is able to download single folder in ZIP Format from Document Actions");
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
		
		homePage.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		myFiles.deleteCreatedFolder(folderDetails);
		myFiles.createFolder(folderDetails);
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFolderNames(folderDetails);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		String moreOption=dataTable.getData("MyFiles","MoreSettingsOption");
		
		sitesPage.clickOnMoreSetting(folderNamesList.get(0));
		sitesPage.clickOnMoreLinkOptions(folderNamesList.get(0), moreOption);
		
		docLibPg.downloadAsZipInDocumentLibrary(folderNamesList.get(0));
		
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		docDetailsPageTest.verifyDownloadedFile(true, folderNamesList.get(0));
	}

	@Override
	public void tearDown() {
		
	}

}
