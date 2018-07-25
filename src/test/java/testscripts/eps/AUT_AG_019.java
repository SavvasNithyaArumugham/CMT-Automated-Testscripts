package testscripts.eps;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_019 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_010()
	{
		testParameters.setCurrentTestDescription("1) Verify the 'Published URL' field displayed with valid URL value for a ZIP file after publishing with selecting "
			+ "'Please select the checkbox to publish as zip itself' checkbox."
										  + "</br>2) Verify the 'Published URL' field displayed with valid URL value for a ZIP files after to publishing without selecting "
				+ "'Please select the checkbox to publish as zip itself' checkbox.");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String folderName = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String format = dataTable.getData("MyFiles", "CreateFileDetails");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);	
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		
		if (sitesPage.Checkdocument(folderName)) {
			UIHelper.waitFor(driver);
			myFiles.openCreatedFolder(folderName);
		} else {
			myFiles.createFolder(folderDetails);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			myFiles.openCreatedFolder(folderName);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		}

		
		testcase(fileName, filePath, null, format);
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		testcase(fileName, filePath, relationshipName, format);
	}
	
	private void testcase(String fileName, String filePath, String relationshipName, String format){
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);	
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		String Option = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptName = dataTable.getData("Sites", "SelectedItemsMenuOption");
		if (sitesPage.Checkdocument(fileName)) {
			sitesPage.clickOnMoreSetting(fileName);
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, Option);
			docLibPg.clickDeletePopup();
		}
		myFiles.uploadFile(filePath, fileName);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(fileName);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName, moreSettingsOptName);		
		docLibPg.clickPublishPopupOpt("check");
		
		UIHelper.pageRefresh(driver);
		UIHelper.waitFor(driver);
		sitesPage.clickOnEditProperties(fileName);
		if(relationshipName == null){
			sitesPage.checkPublishedURL(format, fileName);
		}else{
			sitesPage.checkPublishedURL(format, relationshipName);
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}