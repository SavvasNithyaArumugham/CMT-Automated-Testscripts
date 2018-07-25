package testscripts.amts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.server.handler.CaptureScreenshot;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_014 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_089() {
		testParameters
				.setCurrentTestDescription("Verify that subasset code is retained in transformed file when filename based transformation is done for folder containing image file with filename(filename_subassetcode_)");
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

		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String subAsstCode = dataTable.getData("Media_Transform", "subAsstCode");
		String audioSettingsData = dataTable.getData("Media_Transform", "VdoSettings");
		
		
		
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		
		UIHelper.waitFor(driver);
		
		mediaTransPage.clickOnCreateaudioProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc,
				macCode, subAsstCode);
	
		
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify audio profile details",
					"Audio Profile details displayed successfully", Status.PASS);	
			mediaTransPage.enteraudioSettingsDetails(audioSettingsData);
			mediaTransPage.clickSaveBtn();

		} else {
			report.updateTestLog("Verify audio Profile creation",
					"Failed to create audio profile", Status.FAIL);
		}
		
		mediaTransPage.clickOnCreateaudioProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName, profDesc,
				macCode, subAsstCode);
		
		if(mediaTransPage.checkDuplicateMessageOnAddDplicateVideoProf())
		{
			report.updateTestLog("Verify duplicate audio profile",
					"Warining message displayed successfully", Status.PASS);
			
		}else{
			report.updateTestLog("Verify duplicate audio profile",
					"Duplicate profile created.", Status.FAIL);
		}
		
		mediaTransPage.clickOnCreateaudioProfBtn();
		mediaTransPage.commonMethodForEnterVideoProfDetails(profName+"1", profDesc+"1",
				"", "");
		if (mediaTransPage.clickOnAddImageProfBtn()) {

			report.updateTestLog("Verify audio profile details",
					"Audio Profile details displayed successfully", Status.PASS);	
			mediaTransPage.enteraudioSettingsDetails(audioSettingsData);
			mediaTransPage.clickSaveBtn();

		} else {
			report.updateTestLog("Verify audio Profile creation",
					"Failed to create audio profile", Status.FAIL);
		}
		
		if(mediaTransPage.checkMediaProfAvail(profName+"1")){
			report.updateTestLog("Verify audio profile",
					"audio Profile displayed successfully", Status.PASS);
		}
		else{
			report.updateTestLog("Verify Image profile",
					"Failed to display Image Profile", Status.FAIL);
		}
		
	}

	@Override
	public void tearDown() {

	}

}
