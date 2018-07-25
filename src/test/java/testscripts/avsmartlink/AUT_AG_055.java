package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.util.ArrayList;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_055 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(
				"ALFDEPLOY-3995_Verify image previews available in unzipped folder will be auto-moved to smart link folder ,upon creating multi-smart link.");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoSitesPageTest sitesPageTests = new AlfrescoSitesPageTest(scriptHelper);	

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String folderName = dataTable.getData("MyFiles", "RelationshipName");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String csvfile = dataTable.getData("MyFiles", "Version");
		String newcsvfile = dataTable.getData("MyFiles", "CreateFolder");
		String action = dataTable.getData("MyFiles", "BrowseActionName");
		String csvfoldername =  dataTable.getData("MyFiles", "CreateFileDetails");
		String images[] =  dataTable.getData("MyFiles", "CreateChildFolder").split(",");
		
		String uploadFilePath = properties.getProperty("DefaultUploadPath");
		

		File downloadedFile = null;
		File newdownloadedFile = null;
	

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);

		sitesPage.siteFinder(siteName);

		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();

		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.documentdetails(fileName);

		docDetailsPageObj.performUnzipDocAction(extractTo);
		sitesPage.enterIntoDocumentLibrary();

		
		ArrayList<String> smartLinkcsv = new ArrayList<String>();
		smartLinkcsv = myFiles.getFileNames(csvfile);
		
		ArrayList<String> newsmartLinkcsv = new ArrayList<String>();
		newsmartLinkcsv = myFiles.getFileNames(newcsvfile);
		
			
		UIHelper.waitFor(driver);
		sitesPage.documentdetails(folderName);

		sitesPage.commonMethodForPerformBrowseOption(csvfile,
				action);

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath,
				csvfile);
		downloadedFile = new File(downloadFilePath + "/"
				+ csvfile);
		
		newdownloadedFile = new File(downloadFilePath + "/"
				+ newcsvfile);

		if (downloadedFile.exists()
				&& !csvfile.equalsIgnoreCase("File Not Found")) {
			new FileUtil().renamefile(downloadFilePath,
					csvfile, newcsvfile);

			myFiles.commonMethodForUploadFiles(uploadFilePath,
					newcsvfile);

			sitesPage.clickOnMoreSetting(csvfile);
			docLibPg.commonMethodForClickOnMoreSettingsOption(
					csvfile, moreSettingsOption);
	
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, "//*[text()='AudioSmartLink1']");
		}

			smartLinkcsv =  myFiles.getFileNames(smartlinks);
			
			for (String smartlink : smartLinkcsv) {
				myFilesTestObj.verifyUploadedFile(smartlink, "");
				myFiles.openFolder(smartlink);
				if(smartlink.equalsIgnoreCase("AudioSmartLink1")){
					Boolean flag =UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, "//*[text()='"+images[0]+"']"));
					if(flag){
						report.updateTestLog("<b>Verify images are automoved in smartlink", "Image: "+images[0]+" preview"
								+ " available in unzipped folder is auto-moved to smart link folder ", Status.PASS);
					}
					else{
					report.updateTestLog("<b>Verify images are automoved in smartlink", "Image: "+images[0]+" preview"
							+ " available in unzipped folder is not auto-moved to smart link folder ", Status.FAIL);							
						
					}
				}else if(smartlink.equalsIgnoreCase("AudioSmartLink2")){
					Boolean flag =UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, "//*[text()='"+images[1]+"']"));
					if(flag){
						report.updateTestLog("<b>Verify images are automoved in smartlink", "Image: "+images[1]+" preview"
								+ " available in unzipped folder is auto-moved to smart link folder ", Status.PASS);
					}
					else{
					report.updateTestLog("<b>Verify images are automoved in smartlink", "Image: "+images[1]+" preview"
							+ " available in unzipped folder is not auto-moved to smart link folder ", Status.FAIL);							
						
					}
				}else{
					System.out.println("fail");
				}
				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetails(folderName);
			}

			
			

	}

	@Override
	public void tearDown() {

	}
}
