package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_058 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVSMARTLINK_008() {
		testParameters.setCurrentTestDescription(	
				"ALFDEPLOY-3873_3996_Verify the absence of 3PI vendor and Optimized for use on mobile? columns "
				+ "in the multi smart link csv template of Image External or Video External "
				+ "or Audio External or External website or PDF or Metrodigi or MD pop up link"
				+"ALFDEPLOY-3995_Verify user can provide image preview filename field when we have preview images in different site of same repository"
				);
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
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);

		String filePath = dataTable.getData("MyFiles", "FilePath");
		String csvfiles[] = dataTable.getData("MyFiles", "FileName").split(",");
		String siteName = dataTable.getData("Sites", "SiteName");
		String sitetarget = dataTable.getData("Sites", "TargetSiteName");
		String extractTo = dataTable.getData("Document_Details", "ExtractTo");
		String image = dataTable.getData("MyFiles", "Version");
		String image2 = dataTable.getData("MyFiles", "Version");
		String smartlinks = dataTable.getData("MyFiles", "CreateFileDetails");
		String headerlist = dataTable.getData("MyFiles", "RelationshipName");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		
		

		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		
		
		
		ArrayList<String> header = new ArrayList<String>();
		header = myFiles.getFileNames(headerlist);

		String downloadFilePath = properties.getProperty("DefaultDownloadPath");

		functionalLibrary.loginAsValidUser(signOnPage);
	

		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();

		docLibPg.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, image);
		
		for(String fileName:csvfiles){
			
		myFiles.uploadFileInMyFilesPage(filePath, fileName);	
	
		
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(
				fileName, moreSettingsOption);

		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitForVisibilityOfEleByXpath(driver, "//*[text()='AudioSmartLink1']");
		
		new FileUtil().deleteIfFileExistsInDownloadPath(downloadFilePath, fileName);
		sitesPage.commonMethodForPerformBrowseOption(fileName, "Download");

		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, fileName);
		downloadedFile = new File(downloadFilePath + "/" + fileName);
		if (downloadedFile.exists() && !fileName.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + fileName + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + fileName);
			
			String headsplitedRow[] = null;
			headsplitedRow = csvFileRowDataList.get(0).split(",");
			int i=0;

			/*for (String csvRow : csvFileRowDataList) {

				String splitedRow[] = null;
				splitedRow = csvRow.split(",");*/
			
				for(String row:headsplitedRow){
					
					if(row.contains("3PI") ||row.contains("Optimized") ){					
						i++;
						continue;
					}else{
						
						continue;
					}				
				}
				
				if(i==0){
					System.out.println("pass");
					report.updateTestLog("Verify the absence of 3PI vendor and Optimized for use on mobile? columns in the multi smart link csv template", "3PI vendor and Optimized for use on mobile? columns are not presented in the multi smart link "+fileName+" csv template", Status.PASS);
				}else{
					System.out.println("fail");
					report.updateTestLog("Verify the absence of 3PI vendor and Optimized for use on mobile? columns in the multi smart link csv template", "3PI vendor and Optimized for use on mobile? columns are presented in the multi smart link "+fileName+" csv template", Status.FAIL);
					
				}
				
				if(fileName.contains("sl_audio.csv")){
					String firstsplitedRow[]=null;
					firstsplitedRow = csvFileRowDataList.get(1).split(",");	
					System.out.println(firstsplitedRow[7]);
					if(firstsplitedRow[7].contains("workspace")){
						 
						 report.updateTestLog("verify user can provide image preview filename field "
						 		+ "when we have preview images in different site of same repository", "user can provide "
						 				+ "image preview filename field when we have preview images in different "
						 				+ "site of same repository and node ref is"+firstsplitedRow[7], Status.PASS);
							
					}else{
					 report.updateTestLog("verify user can provide image preview filename field "
						 		+ "when we have preview images in different site of same repository", "user cannot provide "
						 				+ "image preview filename field when we have preview images in different "
						 				+ "site of same repository and node ref is"+firstsplitedRow[7], Status.FAIL);
					}
				}
				
			
		} else {

			report.updateTestLog("Verify download file", "File: " + fileName + " failed to download", Status.FAIL);
		}
		}

	}

	@Override
	public void tearDown() {

	}
}
