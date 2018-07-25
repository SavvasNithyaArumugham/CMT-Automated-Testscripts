package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_054 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_045()
	{
		testParameters.setCurrentTestDescription("Verify user is able to delete a folder successfully if it has has no file that is checked out for editing purpose");
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
		ArrayList<String> folderNamesList = new ArrayList<String>();
		
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		homePageObj.navigateToSitesTab();
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);

		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			myFiles.openCreatedFolder(folderName);
			
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

			myFiles.createFile(fileDetails);
			UIHelper.waitFor(driver);
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			docLibPageTest.verifyUnLockedFilesOrFolderByDeleting(folderName, "hovering");
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
