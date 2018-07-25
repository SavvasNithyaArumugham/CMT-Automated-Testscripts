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
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
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
public class AUT_AG_061P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_060()
	{
		testParameters.setCurrentTestDescription(
				
		"ALFDEPLOY-3884_Verify the error message in email if the html file has tags with style attributes while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file has unapproved tags while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file does not have H1 tags while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file has tags with style attributes, "
		+ "unapproved tags and missing H1 tags while upload"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ "with style attributes"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ "with unapproved tags"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ " out H1 tags"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file"
		+ " has tags with style attributes, unapproved tags and missing H1 tags"
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
			
		for(int i=0;i<=3;i++)
		{	
			sitesPage.clickOnMoreSetting(fileName[i]);
			String action = dataTable.getData("Document_Details", "Title");
			docLibPg.commonMethodForClickOnMoreSettingsOption(fileName[i], action);
			
			String versionDetails = dataTable.getData("Document_Details", "Version");
			String comments = dataTable.getData("Document_Details", "Comments");
			docLibPg.uploadNewVersionFileInDocLibPage(fileName[i],
					CorrectfilePath, versionDetails, comments);
			UIHelper.waitFor(driver);
		}
		
		
		//Out and json 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName[4]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		
		for(int i=0;i<=3;i++){
		String jsonfile=fileName[i].replace(".html", ".json");
		Boolean jsonflag=myFiles.isFileOrFolderDisplayed(jsonfile);
		System.out.println(jsonflag);
		if(jsonflag){
			report.updateTestLog("<b>OUT Folder", "user able to see converted JSON file "+jsonfile+" in OUT folder after successful html content sanitization", Status.PASS);	
		
		}else{
			report.updateTestLog("<b>OUT Folder", "user not able to see converted JSON file "+jsonfile+" in OUT folder after successful html content sanitization", Status.FAIL);	
			
		}
		}
		
		/*docLibPg.deleteAllFilesAndFolders();	
		epsPg.DeleteFolderandFile();
		*/
		
		//html and html 
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName[5]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		
		for(int i=0;i<=3;i++){
		Boolean htmlflag=myFiles.isFileOrFolderDisplayed(fileName[i]);
		System.out.println(htmlflag);
		
		if(htmlflag){
			report.updateTestLog("<b>HTML Folder", "user able to see sanitized html file "+htmlflag+" in HTML folder after successful html content sanitization", Status.PASS);	
			
		}else{
			report.updateTestLog("<b>HTML Folder", "user not able to see sanitized html file "+htmlflag+" in HTML folder after successful html content sanitization", Status.FAIL);	
			
		}
		}
		
		
		docLibPg.deleteAllFilesAndFolders();	
		epsPg.DeleteFolderandFile();
		
		sitesPage.enterIntoDocumentLibrary();
		myFiles.openFolder(folderName[4]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);

		docLibPg.deleteAllFilesAndFolders();	
		epsPg.DeleteFolderandFile();
		
		sitesPage.enterIntoDocumentLibrary();		
		myFiles.openFolder(folderName[0]);
		myFiles.openFolder(folderName[1]);
		myFiles.openFolder(folderName[2]);
		myFiles.openFolder(folderName[3]);
		

		docLibPg.deleteAllFilesAndFolders();	
		epsPg.DeleteFolderandFile();
			}else{
				report.updateTestLog("Applicable for usppewip env only", "Applicable for usppewip env only", Status.DONE);	
				
			}
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_61P3 Status", "EPS 61P3 Testcases has been failed", Status.FAIL);	
			}		
		
}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}