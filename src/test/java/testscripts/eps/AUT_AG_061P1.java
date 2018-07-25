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
public class AUT_AG_061P1 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_060()
	{
		testParameters.setCurrentTestDescription(
				
		"ALFDEPLOY-3884_Verify the error message in email if the html file has tags with style attributes while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file has unapproved tags while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file does not have H3 tags while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file has tags with style attributes, "
		+ "unapproved tags and missing H3 tags while upload"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ "with style attributes"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ "with unapproved tags"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ " out H3 tags"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file"
		+ " has tags with style attributes, unapproved tags and missing H3 tags"
		+"ALFDEPLOY-3884_Verify user able to upload multiple html content in sourse folder - 3 html files "
		+ "with unapproved tags and 3 more html files with out unapproved tags"
		+"ALFDEPLOY-3884_Verify user able to upload new version of html content in source folder"
		
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
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String CorrectfilePath = dataTable.getData("MyFiles", "CreateFileDetails");	
		String folderName[] = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
		String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");
		
		functionalLibrary.loginAsValidUser(signOnPage);	 
		UIHelper.waitFor(driver);
		String url= properties.getProperty("ApplicationUrl");
		if(url.contains("usppewip.pearsoncms.com")||url.contains("usppewip.cms.pearson.com")){
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();		
		myFiles.openFolder(folderName[0]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		docLibPg.deleteAllFilesAndFolders();		
		epsPg.DeleteFolderandFile();
		
		//Unapprovedtags.html,Styleattributes.html,Missing H1tag.html,Allinone.html
		collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[1]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[2]);
		collectionPg.uploadFileInCollectionSite(filePath, fileName[3]);
		
		}else{
			report.updateTestLog("Applicable for usppewip env only", "Applicable for usppewip env only", Status.DONE);	
				
		}
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_61P1 Status", "EPS 61P1 Testcases has been failed", Status.FAIL);	
			}		
		
}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}