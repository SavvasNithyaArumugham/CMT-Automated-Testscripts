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
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
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
public class AUT_AG_055 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_051()
	{
		testParameters.setCurrentTestDescription(
				
		"[1]ALFDEPLOY-3910_Verify a user not able to download a sco zip file by "
		+ "altering a publication url with invalid inputs like changing UUID, by"
		+ " changing a publication url with unpublished content url"
		+"[1]ALFDEPLOY-3910_Verify user able to launch a downloaded sco zip file "
		+ "using a playerhtml file in local system"
						
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
		
		try{
		//Value Declaration
				String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
				AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
				String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
				String filePath = dataTable.getData("MyFiles", "FilePath");	
				String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
				String folderDetails[] = dataTable.getData("MyFiles", "CreateFolder").split(";");	
				String folderName[] = dataTable.getData("MyFiles", "Version").split(",");
				String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");
				// Script Started: 
				
	
				functionalLibrary.loginAsValidUser(signOnPage);	 
				UIHelper.waitFor(driver);
				sitesPage.siteFinder(sourceSiteName);
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();
				
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				
				myFiles.createFolder(folderDetails[0]);
				myFiles.openCreatedFolder(folderName[0]);
				
				collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);
				
				UIHelper.waitFor(driver);
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				epsPg.Publish(Instution[0], fileName[0],"one");		
				
				collectionPg.clickOnMoreSetting(fileName[0]);				
				epsPg.publishbutton(fileName[0],Option[0]);
				
				String url =epsPg.getPublishingURL("Publication", Instution[0]);
				epsPg.openurlintab("Publication", Instution[0]);
				
				if(Instution[0].equals("School Content")){
					url=url.replace("school/", "school/error007");
				}else{
					url=url.replace("alfesrco/", "alfesrco/error007");
				}
				
				System.out.println(url);
				new AlfrescoHomePage(scriptHelper).openNewTab(url);
				
				if(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, epsPg.notfoundxpath))){
					report.updateTestLog("Verify a user not able to download a sco zip file by altering "
							+ "a publication url with invalid inputs like changing UUID,", "user not able"
									+ " to download a sco zip file by altering a publication url with "
									+ "invalid inputs like changing UUID", Status.PASS);	
				}else{
					report.updateTestLog("Verify a user not able to download a sco zip file by altering "
							+ "a publication url with invalid inputs like changing UUID,", "user  able"
									+ " to download a sco zip file by altering a publication url with "
									+ "invalid inputs like changing UUID", Status.FAIL);
					
				}
				
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(0));	
				
				UIHelper.click(driver, epsPg.batchPublishcancelbutton);
				epsPg.DeleteFolderandFile();
				epsPg.DeleteFolderandFile();
				sitesPage.enterIntoDocumentLibrary();
				docLibPg.deleteAllFilesAndFolders();	
				epsPg.DeleteFolderandFile();
		
			
				String downloadedFilePath = Settings.getInstance().getProperty("DefaultDownloadPath");
				
				File downloadOutput = new File(downloadedFilePath);
				File downloadFile = new File(downloadedFilePath + "/" + fileName[0]);
				
				if (downloadFile.exists()) {
					
				Zip extract = new Zip();
				extract.unzip(downloadFile, downloadOutput);
				
				File Folder = new File(downloadedFilePath + "/" + "A0473088");
					if(Folder.exists()){
					System.out.println("extracted successfully");
					report.updateTestLog("File is extracted", fileName[0]+" file is extracted sucessfully", Status.PASS);	
					
					File playerhtml = new File(downloadedFilePath + "/" + "A0473088/player.html");	
						if(playerhtml.exists()){
							report.updateTestLog("Player HTML", "Player HTML file is presented in the location" +playerhtml, Status.PASS);	
				
							epsPg.deleteDir(Folder);
							
							downloadFile.deleteOnExit();
							
						}else{
							report.updateTestLog("Player HTML", "Player HTML file is not presented in the location" +playerhtml, Status.FAIL);	
							
						//	System.out.println("player html is not  there");
						}
					}else{
						report.updateTestLog("File is extracted", fileName[0]+" file is not extracted sucessfully", Status.FAIL);	
						
						//System.out.println("extracted failed");	
					}
					//System.out.println("pass");
				} else {
					report.updateTestLog("File is extracted", fileName[0]+" file is not downloaded", Status.FAIL);	
					
				//	System.out.println("failed");
				}
				
				
				
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_55 Status", "EPS 55 Testcases has been failed", Status.FAIL);	
			}
		
		
		
}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}