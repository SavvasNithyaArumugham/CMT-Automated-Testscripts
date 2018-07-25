package testscripts.eps;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.Zip;
import org.testng.annotations.Test;

import java.util.zip.*;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_059 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_051()
	{
		testParameters.setCurrentTestDescription(
				
		"[1]ALFDEPLOY-3982_Verify user able to publish a file inside a "
		+ "folder which contains already published linked file linked from same site for site of level Site type"
		+"[1]ALFDEPLOY-3982_Verify user able to publish a sco zip file inside a Sco"
		+ " folder which contains already published linked fil linked from same site for site of level Site type"
		+"[1]ALFDEPLOY-3982_Verify user able to publish a sco zip file inside a Sco folder which "
		+ "contains already published linked sco zip file linked from same site for site of level Site type"
		
				);	
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
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoRepositoryPage repositoryPage = new AlfrescoRepositoryPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
		String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(";");	
		String folderName[] = dataTable.getData("MyFiles", "Version").split(",");
		String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");		
		String propertyName = dataTable.getData("Sites", "FilePropertyName");
		
		functionalLibrary.loginAsValidUser(signOnPage);	 

		sitesPage.siteFinder(sourceSiteName);
		// Script Started: 
		try{
		//Value Declaration
				
				
	
				
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();		
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				
				// Creation of SCO folder:
				myFiles.createFolder(folderDetails[0]);
				myFiles.openFolder(folderName[0]);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				
				// Creation of Level1 folder:
				myFiles.createFolder(folderDetails[1]);			
				myFiles.createFolder(folderDetails[2]);
				/*myFiles.createFolder(folderDetails[3]);*/
				
				myFiles.openFolder(folderName[2]);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				/*sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[3]);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);*/
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[1]);
				
					
				myFiles.createFolder(folderDetails[5]);
				/*myFiles.createFolder(folderDetails[6]);*/
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				myFiles.openFolder(folderName[5]);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				/*sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[1]);
				myFiles.openFolder(folderName[6]);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);*/
				
			
			
				// Level 2 Batch Publishing
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[1]);
				
				
				docLibPg.selectAllFilesAndFolders();
				sitesPage.clickOnSelectedItems();
				UIHelper.waitForPageToLoad(driver);
				sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
				
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton)))
				{
				UIHelper.click(driver, epsPg.batchPublishButton);
				UIHelper.waitFor(driver);
				
				epsPg.batchpublishurlplayer(fileName[0],Option[0]);
				
				myFiles.openFolder(folderName[5]);
				epsPg.batchpublishurlzip(fileName[0],Option[0]);
				
				/*sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[1]);
				myFiles.openFolder(folderName[6]);
				epsPg.batchpublishurlzip(fileName[0],Option[0]);*/
				}
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[1]);
				collectionPg.clickOnMoreSetting(fileName[0]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[0],"Delete Folder");
				UIHelper.click(driver, epsPg.deletedocumentXpath);		
				docLibPg.deleteAllFilesAndFolders();		
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				
				// Level 1 Batch Publishing
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				
				UIHelper.waitFor(driver);
				//myFiles.deleteCreatedFolder(folderDetails[1]);
				collectionPg.clickOnMoreSetting(folderName[1]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderName[1],"Delete Folder");
				UIHelper.click(driver, epsPg.deletedocumentXpath);
				
				docLibPg.selectAllFilesAndFolders();
				sitesPage.clickOnSelectedItems();
				UIHelper.waitForPageToLoad(driver);
				sitesPage.selectItemFromSelectedItemsMenuOption("Batch Publish");	
				
				UIHelper.waitForPageToLoad(driver);
				UIHelper.waitForVisibilityOfEleByXpath(driver, epsPg.batchPublishButton);
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.batchPublishButton)))
				{
				UIHelper.click(driver, epsPg.batchPublishButton);
				UIHelper.waitFor(driver);
				epsPg.batchpublishurlplayer(fileName[0],Option[0]);
				
				myFiles.openFolder(folderName[2]);
				epsPg.batchpublishurlzip(fileName[0],Option[0]);
			/*	
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);
				myFiles.openFolder(folderName[3]);
				epsPg.batchpublishurlzip(fileName[0],Option[0]);*/
				}
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openFolder(folderName[0]);	
				collectionPg.clickOnMoreSetting(fileName[0]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[0],"Delete Folder");
				UIHelper.click(driver, epsPg.deletedocumentXpath);
				docLibPg.deleteAllFilesAndFolders();		
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_59 Status", "EPS 59 Testcases has been failed", Status.FAIL);	
			}
		
		
		
}
	
	
	
	
	

	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}