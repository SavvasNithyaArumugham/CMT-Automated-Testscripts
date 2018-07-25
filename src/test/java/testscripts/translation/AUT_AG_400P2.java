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
public class AUT_AG_400P2 extends TestCase {

	@Test
	public void translation_3465_09() {
		testParameters.setCurrentTestDescription("ALFDEPLOY-3465_Verify a German translation for all a external "
				+ "sharebox UI when a language is switched from french to German - Part 2");
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

		String openfolderName = dataTable.getData("MyFiles", "CreateFileDetails");

		String shareboxLabels = dataTable.getData("Sharebox", "ShareboxLabels");
		String shareboxEnglishLabels = dataTable.getData("Sharebox", "EnglishLabels");

		String[] shareboxLabelsSplit = shareboxLabels.split(",");

		String externalshareboxLableText = dataTable.getData("Sharebox", "ExternalShareboxLabels");
		String inputLableText[] = dataTable.getData("Sharebox", "LabelInput").split(",");

		ArrayList<String> expectedExternalShareboxTextEnglishItems = new ArrayList<String>();
		ArrayList<String> expectedExternalShareboxGermanTextItems = new ArrayList<String>();

		ArrayList<String> actualExternalShareboxFrenchTextItems = new ArrayList<String>();
		ArrayList<String> actualExternalShareboxGermanTextItems = new ArrayList<String>();
		ArrayList<String> tempExternalShareboxTextItems= new ArrayList<String>();
		

		shareboxPg.navigateToShareboxLinkFromMail(openfolderName);

		// Need to write few more code once gmail mail not receiving issue has
		// been fixed
		// verify from this
		
		//Click on French
		shareboxPg.clickLanguageInExternalSharebox("French");
		shareboxPg.clickUploadInExternalShareBox();

		for (String labelItem : inputLableText) {

			tempExternalShareboxTextItems=shareboxPgTest.getExcludeShareboxTranslationText(labelItem);
			
		}
		actualExternalShareboxFrenchTextItems.addAll(tempExternalShareboxTextItems);
		
		
		expectedExternalShareboxTextEnglishItems = myFilesTest.getUploadedFileDetails(shareboxEnglishLabels);

		if (!actualExternalShareboxFrenchTextItems.contains(expectedExternalShareboxTextEnglishItems)) {
			report.updateTestLog("Verify Labels after switching from English to French",
					"No Labels are present in English", Status.PASS);
		} else {
			report.updateTestLog("Verify Labels after switching from English to French",
					"Labels are present in English", Status.FAIL);
		}
		// switch to German
		shareboxPg.clickLanguageInExternalSharebox("Allemand");

		shareboxPg.verifyFilesLabelText(shareboxLabelsSplit[0]);
		shareboxPg.verifyUploadLabelText(shareboxLabelsSplit[1]);

		shareboxPg.clickUploadInExternalShareBox();
		shareboxPgTest.verifySelectFilesUploadLabel(shareboxLabelsSplit[2]);
		shareboxPgTest.verifySelectFilesButtonText(shareboxLabelsSplit[3]);
		shareboxPgTest.verifyDownloadCurrentFolderText(shareboxLabelsSplit[4]);
		AlfrescoShareboxPageTest.labelItems.clear();
		
		for (String labelItem : inputLableText) {

			tempExternalShareboxTextItems=shareboxPgTest.getExcludeShareboxTranslationText(labelItem);
		}
		actualExternalShareboxGermanTextItems.addAll(tempExternalShareboxTextItems);
		
		if (!(actualExternalShareboxGermanTextItems.contains(actualExternalShareboxFrenchTextItems))) {
			report.updateTestLog("Verify Labels after switching from French to German",
					"No Labels are present in French", Status.PASS);
		} else {
			report.updateTestLog("Verify Labels after switching from French to German", "Labels are present in French",
					Status.FAIL);
		}
		
		expectedExternalShareboxTextEnglishItems.clear();
		expectedExternalShareboxGermanTextItems = myFilesTest.getUploadedFileDetails(externalshareboxLableText);
      
		if (UIHelper.compareTwoSimilarLists(expectedExternalShareboxGermanTextItems,
				actualExternalShareboxGermanTextItems)) {
			report.updateTestLog("Verify German Label text in Externalsharebox page",
					"<b> " + expectedExternalShareboxGermanTextItems + " </b>" + "is present", Status.PASS);
		} else {
			report.updateTestLog("Verify German Label text in Externalsharebox page",
					"<b> " + expectedExternalShareboxGermanTextItems + " </b>" + "is not present", Status.FAIL);

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}

}
