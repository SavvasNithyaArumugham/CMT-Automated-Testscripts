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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
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
public class AUT_AG_138 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC2_034()
	{
		testParameters.setCurrentTestDescription("Verify user is able to delete a folder with aspects applied through delete option from selected items drop down menu");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		folderNamesList = myFiles.getFolderNames(folderDetails);
		

		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
		myFiles.createFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);			
			sitesPage.clickOnMoreOptionLink(folderName);			
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
						
			UIHelper.waitFor(driver);
			docLibPageTest.verifyDeleteFolderWithAspectsApplied(folderName, "selectedItems");
		}
		
		//Verify user is able to delete a folder with aspects applied through delete option from Actions Menu
		sitesPage.enterIntoDocumentLibrary();
		myFiles.createFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);			
			sitesPage.clickOnMoreOptionLink(folderName);			
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			
			sitesPage.enterIntoDocumentLibraryWithoutReport();
						
			UIHelper.waitFor(driver);
			docLibPageTest.verifyDeleteFolderWithAspectsApplied(folderName, "actionItem");
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
