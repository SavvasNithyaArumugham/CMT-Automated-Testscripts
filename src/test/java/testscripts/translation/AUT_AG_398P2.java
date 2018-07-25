package testscripts.translation;

import java.util.ArrayList;

import org.testng.annotations.Test;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoShareboxPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Cognizant
 */
public class AUT_AG_398P2 extends TestCase {

	@Test
	public void translation_3465_10() {
		testParameters.setCurrentTestDescription("ALFDEPLOY-3465-Verify fields and basic messages and warnings in "
				+ "External ShareBox are translated to German - Part 2");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("GmailUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		String message = dataTable.getData("Sharebox", "Message");

		String date = new DateUtil().getCurrentDate();
		message = message + "_" + date;
		GmailVerificationPage gmailobj = new GmailVerificationPage(scriptHelper);
		AlfrescoShareboxPage shareboxPg = new AlfrescoShareboxPage(scriptHelper);
		AlfrescoShareboxPageTest shareboxPgTest = new AlfrescoShareboxPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTest = new AlfrescoMyFilesPageTest(scriptHelper);

		gmailobj.enterCredentials();
		gmailobj.searchWFmessage(message);

		String gmailSubject = dataTable.getData("Sharebox", "GmailSubjectHeader");
		String openfolderName = dataTable.getData("MyFiles", "CreateFileDetails");
		String finalGmailSubject = gmailSubject.replace("folderName", openfolderName);
		String gmailExpire = dataTable.getData("Sharebox", "GmailLinkExpire");
		String gmailBestRegards = dataTable.getData("Sharebox", "GmailBestRegards");
		String shareboxLabels = dataTable.getData("Sharebox", "ShareboxLabels");
		String warningMsg = dataTable.getData("Sharebox", "EditNotifications");

		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] shareboxLabelsSplit = shareboxLabels.split(",");

		ArrayList<String> expectedExternalShareboxTextItems = new ArrayList<String>();
		ArrayList<String> actualExternalShareboxTextItems = new ArrayList<String>();

		gmailobj.verifyGmailsubject(finalGmailSubject);
		gmailobj.verifyGmailAccessExpire(gmailExpire);
		gmailobj.verifyBestRegards(gmailBestRegards);
		shareboxPg.navigateToShareboxLinkFromMail(openfolderName);
		shareboxPg.verifyFilesLabelText(shareboxLabelsSplit[0]);
		shareboxPg.verifyUploadLabelText(shareboxLabelsSplit[1]);

		// Need to write few more code once gmail mail not receiving issue has
		// been fixed

		shareboxPg.clickUploadInExternalShareBox();
		shareboxPgTest.verifySelectFilesUploadLabel(shareboxLabelsSplit[2]);
		shareboxPgTest.verifySelectFilesButtonText(shareboxLabelsSplit[3]);
		shareboxPgTest.verifyDownloadCurrentFolderText(shareboxLabelsSplit[4]);

		String externalshareboxLableText = dataTable.getData("Sharebox", "ExternalShareboxLabels");
		String inputLableText[] = dataTable.getData("Sharebox", "LabelInput").split(",");
		for (String labelItem : inputLableText) {

			//actualExternalShareboxTextItems = shareboxPgTest.getExcludeShareboxTranslationText(labelItem);
		}
		expectedExternalShareboxTextItems = myFilesTest.getUploadedFileDetails(externalshareboxLableText);
		if (UIHelper.compareTwoSimilarLists(expectedExternalShareboxTextItems, actualExternalShareboxTextItems)) {
			report.updateTestLog("Verify Label text in Externalsharebox page",
					"<b> " + expectedExternalShareboxTextItems + " </b>" + "is present", Status.PASS);
		} else {
			report.updateTestLog("Verify Label text in Externalsharebox page",
					"<b> " + expectedExternalShareboxTextItems + " </b>" + "is not present", Status.FAIL);

		}
		shareboxPg.verifyWarningAlertMsgOnUploadingDuplicateFile(filePath, fileName, warningMsg);
		

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}

}
