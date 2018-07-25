package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC002 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_002() {
		testParameters
				.setCurrentTestDescription("8	Verify the warning message is displayed if user does not provide the value for Title field and verify if user is not able to upload without providing the title."
						+ "9	Verify if user provides value with spaces or other unsupported characters for the Reference ID field then the user is returned to the Upload File section and an alert should be displayed as Reference ID cannot contain <problem character>."
						+ "10	Verify if the Reference ID already exists within the database, the user is returned to the Upload File section and an alert appears: This Reference ID already exists. Please use a unique Reference ID."
						+ "<br> AVDELIVERY-20_Mandatory check for Title field"
						+ "<br> ALFDEPLOY-3594_Verify if Alphanumeric characters are accepted in Title field for Streaming Video"
						+"<br>  AVDELIVERY-20_Reference id field validation for unsupported characters"
						+ "<br> Verify if Alphanumeric characters are accepted in Reference ID field for Streaming Video");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		 String siteName = sitesPage.getCreatedSiteName();
//		String siteName = "AutoAVTRandomSite20517203740";
		sitesPage.siteFinder(siteName);
		// Navigate to document library
		sitesPage.enterIntoDocumentLibrary();

		// upload video files and verify supported format for MOV file
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.uploadFileInUploadFilesTab(filePath, "MOV.mov");
		alfrescoAVTPage.clickOnSaveAndUpload();
		
//		verify error message asks for *title field
		alfrescoAVTPage.verifyErrorMessageForEmptyTitle();

//		input Title
		alfrescoAVTPage.inputTitle("MOV");
		
//		input invalid ReferenceId
		alfrescoAVTPage.inputReferenceID("  .!@");
		alfrescoAVTPage.clickOnSaveAndUpload();
		
//		verify error message for invalid reference
		alfrescoAVTPage.verifyErrorMessageForInvalideReference();
		
//		input valid ReferenceId
		String previousReferenceId=alfrescoAVTPage.inputReferenceID("MOV");
		alfrescoAVTPage.clickOnSaveAndUpload();
		
		alfrescoAVTPage.verifyStreamingUploadProgressAndClickOkOnTranscodingInProgressPromptBox();
		
//		input duplicate reference ID
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		alfrescoAVTPage.inputTitle("MOV");
		alfrescoAVTPage.inputReferenceIDwithoutRandomNumber(previousReferenceId);
		alfrescoAVTPage.clickOnSaveAndUpload();		
		alfrescoAVTPage.verifyPopUpMessageForDuplicateReferenceID();
		

	}

	@Override
	public void tearDown() {

	}
}
