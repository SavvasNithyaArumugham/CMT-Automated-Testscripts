package testscripts.collections8;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;



/**
 * 
 * @author Subhashini G
 */
public class LSALF_1859_4 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void LSALF_1859_41() {
		testParameters.setCurrentTestDescription("Validation of completed folder creation again when the already available folder is removed once the valid excel file is uploaded into the rules applied folder");
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
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String ruleDetails = dataTable.getData("MyFiles", "Subfolders1");
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String createdFolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String MoreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String createRulesXPath="//*[@id=\"template_x002e_rules-none_x002e_folder-rules_x0023_default-body\"]/div[2]/div[1]/a";
		String createdFolderXpath="//*[@id=\"template_x002e_path_x002e_folder-rules_x0023_default\"]/div/div[1]/div/span[5]/a";
		String completedFolderXpath="//*[@id=\"yui-gen182\"]";
		try {
		homePageObj.navigateToSitesTab();
		String siteNameValue =  dataTable.getData("Sites", "SiteName");					
		sitesPage.createSite(siteNameValue, "Yes");
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.createFolder(folderDetails);
		collectionPg.clickOnMoreSetting(createdFolder);
		collectionPg.commonMethodForClickOnMoreSettingsOption(createdFolder,MoreSettingsOption);
		UIHelper.click(driver, createRulesXPath);
		collectionPg.clickOnCreateRules();
		collectionPg.createRulesBasicData(ruleDetails);
		try {
		UIHelper.click(driver,createdFolderXpath );
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
	
		UIHelper.waitFor(driver);
		UIHelper.click(driver, completedFolderXpath);
		report.updateTestLog("Complete Folder",
				"Completed folder is present", Status.PASS);
		}catch(Exception e) {
			report.updateTestLog("Complete Folder",
					"Completed folder is not present", Status.FAIL);
			e.printStackTrace();
		}
		
		try {
		UIHelper.click(driver,createdFolderXpath );
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
	
		UIHelper.waitFor(driver);
		UIHelper.click(driver, completedFolderXpath);
		report.updateTestLog("Complete Folder",
				"Completed folder is created again", Status.PASS);
		}catch(Exception e) {
			report.updateTestLog("Complete Folder",
					"Completed folder is not present", Status.FAIL);
			e.printStackTrace();
		}
	}

		catch(Exception e){
			e.printStackTrace();
}
		}
	

	@Override
	public void tearDown() {
		// Nothing to do
	}
}