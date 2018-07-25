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
public class AUT_AG_060 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_060()
	{
		testParameters.setCurrentTestDescription(
				
		"ALFDEPLOY-3884_Verify the Source folder structure which is used for html content publishing in EPS site"
		+"ALFDEPLOY-3884_Verify user able to upload html content in Role folder which is under below folder path" 
		+"Document library->Source->Language->Product->Role folder"
		+"ALFDEPLOY-3884_Verify the user able to see HTML and OUT folders in document library if the html "
		+ "content sanitization is successful for first time"
		+"ALFDEPLOY-3884_Verify the HTML and OUT folder structure after successful html content sanitization"
		+"ALFDEPLOY-3884_Verify user able to see converted JSON file in OUT folder after successful html "
		+ "content sanitization"
		+"ALFDEPLOY-3884_Verify user able to see sanitized html file in HTML folder after successful html"
		+ " content sanitization"
		
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
		try{
			
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);			
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		// Variables
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		String folderName[] = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
		String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");
		
		functionalLibrary.loginAsValidUser(signOnPage);	 
		
		
		String url= properties.getProperty("ApplicationUrl");
		if(url.contains("usppewip.pearsoncms.com")||url.contains("usppewip.cms.pearson.com")){
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();		
		myFiles.openFolder(folderName[0]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		report.updateTestLog("Verify the Source folder structure", "User able to see folder structure Document library->Source->EN->Product->Role", Status.PASS);	
		docLibPg.deleteAllFilesAndFolders();		
		epsPg.DeleteFolderandFile();
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		
		//Out and json 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName[4]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		String jsonfile=fileName[0].replace(".html", ".json");
		Boolean jsonflag=myFiles.isFileOrFolderDisplayed(jsonfile);
		System.out.println(jsonflag);
		if(jsonflag){
			report.updateTestLog("<b>OUT Folder", "user able to see converted JSON file "+jsonfile+" in OUT folder after successful html content sanitization", Status.PASS);	
			collectionPg.clickOnMoreSetting(jsonfile);				
			epsPg.publishbutton(jsonfile,Option[0]);
			epsPg.Publish(Instution[0], jsonfile,"one");	
			docLibPg.deleteAllFilesAndFolders();	
			epsPg.DeleteFolderandFile();
		}else{
			report.updateTestLog("<b>OUT Folder", "user not able to see converted JSON file "+jsonfile+" in OUT folder after successful html content sanitization", Status.FAIL);	
			
		}
		
		//html and html 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName[5]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		Boolean htmlflag=myFiles.isFileOrFolderDisplayed(fileName[0]);
		System.out.println(htmlflag);
		
		if(htmlflag){
			report.updateTestLog("<b>HTML Folder", "user able to see sanitized html file "+htmlflag+" in HTML folder after successful html content sanitization", Status.PASS);	
			docLibPg.deleteAllFilesAndFolders();	
			epsPg.DeleteFolderandFile();
		}else{
			report.updateTestLog("<b>HTML Folder", "user not able to see sanitized html file "+htmlflag+" in HTML folder after successful html content sanitization", Status.FAIL);	
			
		}
		}else{
			report.updateTestLog("Applicable for usppewip env only", "Applicable for usppewip env only", Status.DONE);	
				
		}
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_60 Status", "EPS 60 Testcases has been failed", Status.FAIL);	
			}		
		
}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}