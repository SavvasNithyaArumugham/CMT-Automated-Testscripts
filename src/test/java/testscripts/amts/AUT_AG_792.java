package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_792 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_010() {
		testParameters
				.setCurrentTestDescription("To Verify whether system allows the user to convert the image to greyscale.");
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

		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		String profName = dataTable.getData("Media_Transform", "ProfName");
		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");
		String imageType = dataTable.getData("Media_Transform",
				"FileFormatType");
		String profDesc = dataTable.getData("Media_Transform", "ProfDesc");
		String macCode = dataTable.getData("Media_Transform", "macCode");
		String filePathForUploadFile = dataTable.getData("Media_Transform",
				"filePath");
		String fileNameForUpload = dataTable.getData("Media_Transform",
				"fileName");
		mediaTransPage.navigateToMediaTransPage();
		//mediaTransPage.navigateToProfilePage(profName);
		mediaTransPage.deleteProfileFrmMediaTransPg(profName);
		UIHelper.waitFor(driver);
		mediaTransPage.clickOnCreateImageProfBtn();

		// To Enter data in Create Profile
		mediaTransPage.commonMethodForEnterImageProfDetails(profName, profDesc,
				macCode, subAsstCode, filePathForUploadFile, fileNameForUpload);
		mediaTransPage.clickOnAddImageProfBtn();
		mediaTransPage.selectImgFileFormatType(imageType);
		mediaTransPage.convertToGrayScale();
		mediaTransPage.getSaveNotificationMessage();

	}

	@Override
	public void tearDown() {

	}

}
