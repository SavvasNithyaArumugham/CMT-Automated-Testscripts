package testscripts.eps;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_051 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_051()
	{
		testParameters.setCurrentTestDescription(
				
				"[1]ALFDEPLOY-3910_Verify user able to download a level1 sco zip file in local "
				+ "system by altering a publication url"
				+"[1]ALFDEPLOY-3910_Verify user able to publish a level1 sco zip file"
				+ " in player.html url format"
				+	"[1]ALFDEPLOY-3910_Verify user able to download a level2 sco zip file in local "
				+ "system by altering a publication url"
				+"[1]ALFDEPLOY-3910_Verify user able to publish a level2 sco zip file"
				+ " in player.html url format"
				+	"<br>User can be able to delete the Content if the Content was Published"
				+"<br>Verify user able to delete the file if it was published as parent."
				+ "<br>Verfiy User can be able publish content in the publishable site."
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
		// Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		try {
			// Value Declaration
			String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			String[] fileName = dataTable.getData("MyFiles", "FileName").split(";");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
			String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
			String folderDetails1 = dataTable.getData("MyFiles", "CreateFileDetails");
			String folderDetails2 = dataTable.getData("MyFiles", "BrowseActionName");
			String[] folderName = dataTable.getData("MyFiles", "Version").split(",");
			String Instution[] = dataTable.getData("MyFiles", "RelationshipName").split(";");

			String downloadFilePath = properties.getProperty("DefaultDownloadPath");
			File downloadedFile = null;
			// Script Started:

			functionalLibrary.loginAsValidUser(signOnPage);
			AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
			homePage.navigateToSitesTab();
			/*sitesPage.createSite(sourceSiteName, "Yes");
			String site=sitesPage.getCreatedSiteName();*/
			sitesPage.siteFinder(sourceSiteName);
			sitesPage.enterIntoDocumentLibrary();
			
			docLibPg.deleteAllFilesAndFolders();
			
			epsPg.DeleteFolderandFile();
			epsPg.DeleteFolderandFile();

			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(folderName[0]);
			myFiles.createFolder(folderDetails1);
			sitesPage.documentdetails(folderName[1]);

			collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);

			// Publish the content

			collectionPg.clickOnMoreSetting(fileName[0]);
			epsPg.publishbutton(fileName[0], Option[0]);
			epsPg.Publish(Instution[0], fileName[0], "one");
			collectionPg.clickOnMoreSetting(fileName[0]);
			epsPg.publishbutton(fileName[0], Option[0]);
			/*String url = epsPg.getPublishingURL("Publication", Instution[0]);

			if (url != null && url.contains("player.html")) {
				report.updateTestLog("Reterieve Publication URL", "Published URL for Institute " + Instution[0]
						+ "and file/folder name " + fileName[0] + "<br><b>" + url + "</b>", Status.PASS);
			} else {
				report.updateTestLog("Reterieve Publication URL", "Published URL for Institute " + Instution[0]
						+ "and file/folder name " + fileName[0] + "failed", Status.FAIL);
			}

			url = url.replace("/player.html", ".zip");
			
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, fileName[0]);
			homePageObj.openNewTab(url);

			new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName[0]);
			downloadedFile = new File(downloadFilePath + "/" + fileName[0]);

			if (downloadedFile.exists() && !fileName[0].equalsIgnoreCase("File Not Found")) {
				report.updateTestLog("Verify download file", "File: " + fileName[0] + " downloaded sucessfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify download file", "File: " + fileName[0] + " download failed", Status.FAIL);
			}

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));

			UIHelper.click(driver, epsPg.batchPublishcancelbutton);
			
			
			collectionPg.clickOnMoreSetting(fileName[1]);
				boolean flag1 = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName[1],Option[3]);
				if(flag1){
					report.updateTestLog("Publishing Options:", "Publishing Option is presented "
							+ "for "+"<b> "+fileName[1], Status.FAIL);
				}else{
					report.updateTestLog("Publishing Options:", "Publishing Option is not presented "
							+ "for "+"<b> "+fileName[1], Status.PASS);
				}
			

			collectionPg.clickOnMoreSetting(fileName[0]);
			epsPg.publishbutton(fileName[0], Option[1]);
			docLibPg.deletefolderwithMoreactions();
		//	epsPg.DeleteFolderandFile();
			if(sitesPage.documentAvailable(fileName[0])){
				report.updateTestLog("<b>Delete the content if published:", "User not deleted the Content if the "+fileName[0]+" was Published", Status.FAIL);						
			}else{
				report.updateTestLog("<b>Delete the content if published:", "User deleted the Content if the "+fileName[0]+" was Published", Status.PASS);							
			}
			
			
			myFiles.createFolder(folderDetails2);
			sitesPage.documentdetails(folderName[2]);

			collectionPg.uploadFileInCollectionSite(filePath, fileName[0]);

			// Publish the content

			collectionPg.clickOnMoreSetting(fileName[0]);
			epsPg.publishbutton(fileName[0], Option[0]);
			epsPg.Publish(Instution[0], fileName[0], "one");

			collectionPg.clickOnMoreSetting(fileName[0]);
			epsPg.publishbutton(fileName[0], Option[0]);

			String url1 = epsPg.getPublishingURL("Publication", Instution[0]);

			if (url1 != null && url1.contains("player.html")) {
				report.updateTestLog("Reterieve Publication URL", "Published URL for Institute " + Instution[0]
						+ "and file/folder name " + fileName[0] + "<br><b>" + url + "</b>", Status.PASS);
			} else {
				report.updateTestLog("Reterieve Publication URL", "Published URL for Institute " + Instution[0]
						+ "and file/folder name " + fileName[0] + "failed", Status.FAIL);
			}

			url1 = url1.replace("/player.html", ".zip");
			
			new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, fileName[0]);
			
			homePageObj.openNewTab(url1);

			new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName[0]);
			downloadedFile = new File(downloadFilePath + "/" + fileName[0]);

			if (downloadedFile.exists() && !fileName[0].equalsIgnoreCase("File Not Found")) {
				report.updateTestLog("Verify download file", "File: " + fileName[0] + " downloaded sucessfully",
						Status.PASS);
			} else {
				report.updateTestLog("Verify download file", "File: " + fileName[0] + " download failed", Status.FAIL);
			}

			tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(0));*/

		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("EPS_51 Status", "EPS 51 Testcases has been failed", Status.FAIL);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}