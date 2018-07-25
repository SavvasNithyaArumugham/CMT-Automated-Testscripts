package testscripts.misc5;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_413P2 extends TestCase {

	@Test
	public void linkedShareFolderDownloadInSharebox2() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY-3497_Verify user can download a shared"
						+ "folder contains linked shared folder in external sharebox UI using download zip option-Part2");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);

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
		String folderName = dataTable.getData("MyFiles", "Version").split(",")[0];
		String zipFileName=dataTable.getData("MyFiles", "Version").split(",")[1];
		String fileNameToFind=dataTable.getData("MyFiles", "CreateFileDetails");
		String fileDownloadPath=properties.getProperty("DefaultDownloadPath");
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		FileUtil fileUtil=new FileUtil();
		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;

		
		gmailobj.enterCredentials();	
		gmailobj.searchWFmessage(message);
		
		//Pass the folderName as same as "Name" provided in share folder externally popUp
		shareboxPg.navigateToShareboxLinkFromMail(folderName);	
	   
		// Pass the folderName as same as in ALfersco share append with zip
		shareboxPg.performDownloadZipInShareboxPage(zipFileName, fileDownloadPath);
		
		
		if(fileUtil.readContentsOfZipFile(fileDownloadPath, zipFileName, fileNameToFind)) {
			report.updateTestLog("Verify download zip contains the desired folder", "Downloaded zip contains desired folder",
					Status.PASS);
			
		}else {
			report.updateTestLog("Verify download zip contains the desired folder", "Downloaded zip doesn't contains desired folder",
					Status.FAIL);
			
		}
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}