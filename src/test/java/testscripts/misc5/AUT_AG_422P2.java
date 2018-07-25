package testscripts.misc5;

import java.io.File;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_422P2 extends TestCase {

	@Test
	public void sharebox_416P2() {
		testParameters.setCurrentTestDescription("ALFDEPLOY-3490_Verify user should not be able to Downloaded Zip "
				+ "package via External sharebox from Root folder level when the RF contains 10 level deep folders "
				+ "and 6 files in each subfolders - Part 2");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		String message = dataTable.getData("Sharebox", "Message");
		String fileName = dataTable.getData("Sharebox", "ShareboxFieldsData");
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		ArrayList<String> filesList = new ArrayList<String>();
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		FileUtil fileUtil = new FileUtil();
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		shareboxPg.navigateToShareboxLinkFromMail(fileName);
		String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
		String folderNameToNavigate = dataTable.getData("MyFiles", "CreateFileDetails");
		
		/*if(folderNameToNavigate.contains(",")){
			String[] folderNames = folderNameToNavigate.split(",");
			for (int i = 0; i < folderNames.length; i++) {
				shareboxPg.navigateShareUI(folderNames[i]);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
			}
		}else{
			shareboxPg.navigateShareUI(folderNameToNavigate);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		}*/
		
		String downloadFromFolderName = dataTable.getData("MyFiles", "CreateFolder");
		String downloadZipFileName = downloadFromFolderName+".zip";
		
		
		boolean isFileDownloaded = shareboxPg.performDownloadZipInShareboxPageAndReturnBoolean(downloadZipFileName, fileDownloadPath);
		
		if(isFileDownloaded){
			report.updateTestLog("Verify file not downloadable using 'Download ZIP' option",
					"File: " + downloadZipFileName + " downloaded successfully", Status.FAIL);
		}else{
			report.updateTestLog("Verify file not downloadable using 'Download ZIP' option",
					"File: " + fileName + " failed to download", Status.PASS);
		}
		
		/*filesList = fileUtil.readZip(fileDownloadPath+ "\\" + downloadZipFileName);
		String fileNames = dataTable.getData("MyFiles", "Version");
		String[] finalFileNames = fileNames.split(",");
		
		for (int i = 0; i < finalFileNames.length; i++) {
			boolean flag = false;
			for (int j = 0; j< filesList.size()-1; j++) {
	    		if(filesList.get(j).contains(finalFileNames[i])){
	    			flag = true;
	    			break;
	    		}
		}
	    	if(flag){
	    		report.updateTestLog("Verify files are available in downloaded zip", "File: " + finalFileNames[i] + " present in downloaded zip", Status.PASS);
	    	}
	    	else{
    			report.updateTestLog("Verify files are available in downloaded zip", "File: " + finalFileNames[i] + " not present in downloaded zip", Status.FAIL);
		}
	}*/
	}
	
	

	@Override
	public void tearDown() {
		// Nothing to do
	}
	
}
