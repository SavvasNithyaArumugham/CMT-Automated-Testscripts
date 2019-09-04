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
public class AUT_AG_020 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_011()
	{
		testParameters.setCurrentTestDescription("Verify the user is able to publish EPS file/Folder and check the green indicator.");
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
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
				
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
			
		//Check EPS ID for file 
		
		sitesPage.enterIntoDocumentLibrary();
		epsPg.checkEPSID(fileName2);
		
		UIHelper.waitFor(driver);
		epsPg.checkEPSID(fileName1);
		}
		
		
		/*
		  //Check EPS ID for folder
		
		sitesPage.clickOnMoreSetting(fileName2);	
		
		epsPg.publishbutton(fileName2, moreSettingsOptionName);
		String EPSURL1 = (UIHelper.findAnElementbyXpath(driver, "//*[@id='prompt']/div[2]/table/tbody/tr[2]/td[5]/u/a").getText()).substring(60,98);
        driver.findElement(By.xpath(".//button[text()='Cancel']")).click();
		
        collectionPg.commonMethodForClickOnMoreSettingsOption(fileName2,"Edit Properties");
        String EPSURL2 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'prop_cm_description')]").getText()).substring(8,46);
        driver.findElement(By.xpath("//div[@class='yui-module yui-overlay yui-panel']/a")).click();
        UIHelper.waitFor(driver);
        
       if( EPSURL1.equals(EPSURL2)){
        	report.updateTestLog("EPS ID match check",	"EPS ID matchs" + fileName2,Status.PASS);
        }
        else
        {
        	report.updateTestLog("EPS ID match check",	"EPS ID does not match" +fileName2 ,Status.FAIL);
        }
        UIHelper.waitFor(driver);
        
     //Check EPS ID for folder
        UIHelper.waitFor(driver);
        sitesPage.clickOnMoreSetting(fileName1);	
		epsPg.publishbutton(fileName1, moreSettingsOptionName);
		String EPSURL3 = (UIHelper.findAnElementbyXpath(driver, "//*[@id='prompt']/div[2]/table/tbody/tr[2]/td[5]/u/a").getText()).substring(60,98);
		System.out.println("EPS ID from publish URL" + EPSURL3);
		driver.findElement(By.xpath(".//button[text()='Cancel']")).click();
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileName1,"Edit Properties");
        String EPSURL4 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'prop_cm_description')]").getText()).substring(8,46);
        driver.findElement(By.xpath("//div[@class='yui-module yui-overlay yui-panel']/a")).click();
        UIHelper.waitFor(driver); 
        
        if( EPSURL3.equals(EPSURL4)){
        	report.updateTestLog("EPS ID match check",	"EPS ID matchs" + fileName1,Status.PASS);
        }
        else
        {
        	report.updateTestLog("EPS ID match check",	"EPS ID does not match" +fileName1 ,Status.FAIL);
        }
        */
       			

	
	@Override
	public void tearDown() {

	}

}