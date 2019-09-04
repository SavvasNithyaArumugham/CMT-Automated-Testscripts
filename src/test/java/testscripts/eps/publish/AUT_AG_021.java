package testscripts.eps.publish;

import java.io.File;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * @author 
 */
public class AUT_AG_021 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_011()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to republish already published file/Folder and check the green indicator.");
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
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		String fileName = dataTable.getData("MyFiles", "Subfolders1");
		String folderName = dataTable.getData("MyFiles", "Version");
		String fileName1 = dataTable.getData("MyFiles", "FileName");
		String fileName2 = dataTable.getData("MyFiles", "CreateFileDetails");
		String fileName3 = dataTable.getData("MyFiles", "RelationshipName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String moreSettingsOptionName1 = dataTable.getData("MyFiles", "BrowseActionName");
					
		homePage.navigateToSitesTab();	
		UIHelper.waitFor(driver);
		sitesPage.openSiteFromRecentSites(sourceSiteName);	
		sitesPage.enterIntoDocumentLibrary();
		//Create Folder and upload file
		myFiles.createFolder(folderDetails);
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFile(filePath, fileName);
		sitesPage.enterIntoDocumentLibrary();
		UIHelper.waitFor(driver);
		
		//Publish a folder
		sitesPage.clickOnMoreSetting(fileName1);	
		epsPg.publishbutton(fileName1, moreSettingsOptionName);
		docLibPg.clickPublishPopup();
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		docLibPg.checkPublishedTick(fileName1);
		UIHelper.waitFor(driver);
		
		// Check publish for File 
		
		myFiles.uploadFile(filePath, fileName2);
		sitesPage.clickOnMoreSetting(fileName2);	
		 
		epsPg.publishbutton(fileName2, moreSettingsOptionName);
		docLibPg.clickPublishPopup();
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		docLibPg.checkPublishedTick(fileName2);
		UIHelper.waitFor(driver);
		
		// Add new files into existing folder 
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openCreatedFolder(folderName);
		myFiles.uploadFile(filePath, fileName3);
		sitesPage.enterIntoDocumentLibrary();
		
		//republish a folder
		sitesPage.clickOnMoreSetting(fileName1);	
		epsPg.publishbutton(fileName1, moreSettingsOptionName);
		docLibPg.clickPublishPopup();
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		docLibPg.checkPublishedTick(fileName1);
		UIHelper.waitFor(driver);
		
		//Check EPS ID for republished folder
		
		
		
				/*sitesPage.clickOnMoreSetting(fileName1);	
				epsPg.publishbutton(fileName1, moreSettingsOptionName);
				String EPSURL1 = (UIHelper.findAnElementbyXpath(driver, "//*[@id='prompt']/div[2]/table/tbody/tr[2]/td[5]/u/a").getText()).substring(60,98);
		        driver.findElement(By.xpath(".//button[text()='Cancel']")).click();
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName1,"Edit Properties");
		        String EPSURL2 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'default-editDetails-alf-id14_prop_cm_description')]").getText()).substring(8,46);
		        
		            
		        if( EPSURL1.equals(EPSURL2)){
		        	report.updateTestLog("EPS ID match check",	"EPS ID matchs" + fileName1,Status.PASS);
		        }
		        else
		        {
		        	report.updateTestLog("EPS ID match check",	"EPS ID does not match" +fileName1 ,Status.FAIL);
		        }*/
		        
		
		
		//upload new version of existing file
			
		sitesPage.clickOnMoreSetting(fileName2);
		
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName2, moreSettingsOptionName1);
			
		String fileNameUploadNewVersion = dataTable.getData("Document_Details", "FileName");
		String filePathUploadNewVersion = dataTable.getData("Document_Details", "FilePath");
		String versionDetails = dataTable.getData("Document_Details","Version");
		String comments = dataTable.getData("Document_Details", "Comments");
		
		docLibPg.uploadNewVersionFileInDocLibPage(fileNameUploadNewVersion,filePathUploadNewVersion, versionDetails, comments);
				
		UIHelper.waitFor(driver);
		sitesPage.clickOnMoreSetting(fileName2);	
		 
		epsPg.publishbutton(fileName2, moreSettingsOptionName);
		docLibPg.clickPublishPopup();
		UIHelper.waitFor(driver);
		UIHelper.pageRefresh(driver);
		docLibPg.checkPublishedTick(fileName2);
		UIHelper.waitFor(driver);
			
		docLibPg.isDisplayedPublishedGreenTick(fileName1);
		docLibPg.isDisplayedPublishedGreenTick(fileName2);
		

		//Check EPS ID for republished file
		
				sitesPage.clickOnMoreSetting(fileName1);	
				epsPg.publishbutton(fileName1, moreSettingsOptionName);
				String EPSURL3 = (UIHelper.findAnElementbyXpath(driver, "//*[@id='prompt']/div[2]/table/tbody/tr[2]/td[5]/u/a").getText()).substring(60,98);
		        driver.findElement(By.xpath(".//button[text()='Cancel']")).click();
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName1,"Edit Properties");
		        String EPSURL4 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'prop_cm_description')]").getText()).substring(8,46);
		        driver.findElement(By.xpath("//div[@class='yui-module yui-overlay yui-panel']/a")).click();
		        UIHelper.waitFor(driver);
		            
		        if( EPSURL3.equals(EPSURL4)){
		        	report.updateTestLog("EPS ID match check",	"EPS ID matchs" + fileName2,Status.PASS);
		        }
		        else
		        {
		        	report.updateTestLog("EPS ID match check",	"EPS ID does not match" +fileName2 ,Status.FAIL);
		        }
	}
			
	
	@Override
	public void tearDown() {

	}

}