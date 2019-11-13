package testscripts.collections11;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_2174_P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_03() {
		testParameters.setCurrentTestDescription(
				
				"");

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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
	
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		homePageObj.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");	
		// From the site Type dropdown select 'Collection Site'.
		sitesPage.createSite(siteNameValue, "Yes");	
		
		
		
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split(",");	
		String fileNames[] = dataTable.getData("MyFiles", "FileName").split(",");
		String uploadPath = dataTable.getData("MyFiles", "uploadpath");
		String allProperties = ".//a[contains(text(),'All Properties...')]";
		sitesPage.enterIntoDocumentLibrary();
		
				UIHelper.waitFor(driver);
				// Navigate to Document library
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(filePath[0]);
				UIHelper.waitFor(driver);
				myFiles.openCreatedFolder(filePath[1]);
				UIHelper.waitFor(driver);
				myFiles.uploadFileInMyFilesPage(uploadPath, fileNames[0]);
				UIHelper.waitFor(driver);				
				/*myFiles.uploadFileInMyFilesPage(uploadPath, fileNames[1]);
				UIHelper.waitFor(driver);*/	
				
				UIHelper.click(driver, "//*[@class='filter-change']");
				UIHelper.waitFor(driver);
				for (String listOfObjectsString : fileNames) {
				collectionPg.clickOnMoreSetting(listOfObjectsString);
				collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,
						"Edit Properties");
				UIHelper.waitFor(driver);
				UIHelper.click(driver, allProperties);
				UIHelper.waitFor(driver);
				
				driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cm_title\"]")).sendKeys("Test");
				UIHelper.waitFor(driver);
								
				Select selectBox = new Select(driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_productType\"]")));
				selectBox.selectByIndex(20);
				UIHelper.waitFor(driver);
				Select selectBox1 = new Select(driver.findElement(By.xpath("//*[@id=\"template_x002e_edit-metadata_x002e_edit-metadata_x0023_default_prop_cpnals_discipline\"]")));
				selectBox1.selectByIndex(2);
				UIHelper.waitFor(driver);
				collectionPg.clickOnSaveBtn();
				UIHelper.waitFor(driver);
				
				}
				sitesPage.enterIntoDocumentLibrary();
				// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

				String filepathfile = dataTable.getData("MyFiles", "uploadpath");
				String filepathfilename = dataTable.getData("MyFiles", "CreateFolder");
				String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
				// upload course plan
				collectionPg.uploadFileInCollectionSite(filepathfile, filepathfilename); 
				UIHelper.waitFor(driver);
				// verify import process progress
				collectionPg.verifyImportProcessProgress(filepathfilename);
				
				sitesPage.enterIntoDocumentLibrary();

				// go to Course plan
				myFiles.openCreatedFolder(folderNames[0]);				
				collectionPg.clickOnMoreSetting(folderNames[1]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[1],
						"Child references");
				String refFolderXpath = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[contains(.,'CRAFT')]";
				//Added for NALS
				String refFolderXpaththumb = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[contains(.,'CRAFT')]";
				String refFolderXpaththumbFile = ".//*[contains(@id,'cpnals_outgoingReferences-cntrl-picker-results')]//table//tbody//tr//td//a[@title='Add']";
				String childRefOkXpath = ".//*[@type='button' and contains(.,'OK')]";

				//collectionPg.addImageChildReference("Assets","XML JSON","1");
				//Click on Assets
				String finalrefFolderXpath = refFolderXpath.replace("CRAFT","Assets");
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderXpath);
				UIHelper.click(driver, finalrefFolderXpath);
				UIHelper.waitFor(driver);
				//Click on Image
				String finalrefFolderthumbnailXpath = refFolderXpaththumb.replace("CRAFT","XML JSON");
				UIHelper.waitForVisibilityOfEleByXpath(driver, finalrefFolderthumbnailXpath);
				UIHelper.click(driver, finalrefFolderthumbnailXpath);
				UIHelper.waitFor(driver);
				
				//Click on 1
				//driver.findElement(By.linkText("I")).click();
				//UIHelper.click(driver, "//*[@class='theme-color-1 parent-template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-genericForm-alf-id96_assoc_cpnals_outgoingReferences-cntrl']");
				
				UIHelper.findAnElementbyXpath(driver, "//*[contains(@class,'theme-color-1 parent-template_x002e_documentlist_v2_x002e_documentlibrary_x0023_default-genericForm')]").click();
				
				UIHelper.waitFor(driver);
				
				UIHelper.waitForVisibilityOfEleByXpath(driver,refFolderXpaththumbFile);
				UIHelper.highlightElement(driver, refFolderXpaththumbFile);
				UIHelper.click(driver, refFolderXpaththumbFile);
				UIHelper.waitFor(driver);
								
				// Click on "OK"
				UIHelper.click(driver, childRefOkXpath);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				sitesPage.enterIntoDocumentLibrary();
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				//collectionPg.openCollectionObject(folderNames[2]);
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.clickMoreSettingsOptionWithProductType(folderNames[2], "Generate Bookbuild");
						
}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
