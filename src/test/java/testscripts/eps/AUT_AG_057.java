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
public class AUT_AG_057 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_051()
	{
		testParameters.setCurrentTestDescription(
				
		"[[1]ALFDEPLOY-3982_Verify user able to publish a file inside a folder "
		+ "which contains already published linked file linked from different site for site of level Site type"
		+"[1]ALFDEPLOY-3982_Verify user able to publish a file inside a folder which "
		+ "contains already published linked folder linked from different site for site of level Site type"
		
						
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
		
		try{
		//Value Declaration
				String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
				String TargetSiteName = dataTable.getData("Sites", "Role");
				String USPPETargetSiteName = dataTable.getData("Sites", "FileName");
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
				String filePath = dataTable.getData("MyFiles", "FilePath");	
				String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
				String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(";");	
				String folderName[] = dataTable.getData("MyFiles", "Version").split(",");
				String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");
				ArrayList<String> folderNamesList = new ArrayList<String>();
				String propertyName = dataTable.getData("Sites", "FilePropertyName");
				// Script Started: 
				
	
				functionalLibrary.loginAsValidUser(signOnPage);	 
				UIHelper.waitFor(driver);
				
				if(properties.getProperty("ApplicationUrl").contains("usppewip.pearsoncms.com")){
					TargetSiteName=USPPETargetSiteName;
				}
				System.out.println(TargetSiteName);
				
				// Source site:
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();		
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				myFiles.createFolder(folderDetails[0]);
				
				
				
				sitesPage.siteFinder(TargetSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();		
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				// Target Folder creation in different Site and publish the folder
				myFiles.createFolder(folderDetails[1]);			
				myFiles.openCreatedFolder(folderName[1]);
				collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);
				sitesPage.enterIntoDocumentLibrary();
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(folderName[1]);				
				epsPg.publishbutton(folderName[1],Option[0]);
				epsPg.Publish(Instution[0], folderName[1],"one");
		
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish(Instution[0], fileName[0],"one");
				
				// Link folder to folder
				folderNamesList = myFiles.getFolderNames(folderDetails[1]);
				for(String folderNme:folderNamesList)
				{
					sitesPage.clickOnMoreSetting(folderNme);
					sitesPage.clickOnMoreLinkOptions(folderNme, propertyName);
					break;
				}
						
				docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
				docDetailsPageObj.clickLinkBtnInLinkPopUp();
				
				//Link file to folder
				sitesPage.clickOnMoreSetting(fileName[0]);
				sitesPage.clickOnMoreLinkOptions(fileName[0], propertyName);
				docDetailsPageObj.selectFolderInLinkPopUp(sourceSiteName);
				docDetailsPageObj.clickLinkBtnInLinkPopUp();
				
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				
				// Check whether folder is linked
				myFiles.openFolder(folderName[0]);
				myFilesTestObj.verifyIsFileAvilabile(folderName[1],"");
				myFilesTestObj.verifyIsFileAvilabile(fileName[0],"");
				
				
				// upload a file inside linked folder and publish it
				collectionPg.uploadFileInCollectionSite(filePath, fileName[2]);
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(fileName[2]);				
				epsPg.publishbutton(fileName[2],Option[0]);
				epsPg.Publish(Instution[0], fileName[2],"one");
				
				collectionPg.clickOnMoreSetting(fileName[2]);				
				epsPg.publishbutton(fileName[2],Option[0]);
				epsPg.clickonurl("Publication", Instution[0], "Phoenix ELA", "First Read Guide");		
				UIHelper.click(driver, epsPg.batchPublishcancelbutton);
			
				// To unlink a single file
			/*	repositoryPage.commonMethodToSelectFileInRepository(folderName[1]);
				sitesPage.clickOnSelectedItems();*/
				
				//myFiles.openCreatedFolder("sco");// new code
				String selectedItemMenuOptVal = dataTable.getData("Sites",
						"SelectedItemsMenuOption");
				
				repositoryPage.commonMethodToSelectFileInRepository(folderName[1]); // new code
				sitesPage.clickOnSelectedItems();
				docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
				docLibPg.unlinkSingleLocation(); 
				UIHelper.pageRefresh(driver);
				repositoryPage.commonMethodToSelectFileInRepository("AlfrescoZipFile.zip"); // new code
				sitesPage.clickOnSelectedItems();
				docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
				docLibPg.unlinkSingleLocation(); 
				
				// new code
				
		/*		
			//	docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
				collectionPg.clickOnMoreSetting(fileName[0]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName[0],selectedItemMenuOptVal);
				docLibPg.unlinkSingleLocation();
				
				
				
				collectionPg.clickOnMoreSetting(folderName[1]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderName[1],selectedItemMenuOptVal);
				docLibPg.unlinkSingleLocation();*/
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();	
				epsPg.DeleteFolderandFile();
			
				
				sitesPage.siteFinder(TargetSiteName);
				sitesPage.enterIntoDocumentLibrary();
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_57 Status", "EPS 57 Testcases has been failed", Status.FAIL);	
			}
		
		
		
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}