package testscripts.amts;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_793 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AMTS_011() {
		testParameters
				.setCurrentTestDescription("To Verify whether system allow the user to change the file format of the image between GIF, JPG, PNG,tiff");
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

		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");

		String profName = dataTable.getData("Media_Transform", "ProfName");

		String subAsstCode = dataTable
				.getData("Media_Transform", "subAsstCode");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		String type = dataTable.getData("Media_Transform", "FileFormatType");

		if (type.contains(",")) {
			String variousTypes[] = type.split(",");
			for (String eachType : variousTypes) {
				System.out.println(eachType);
				profName = profName + sdf.format(cal.getTime());
				profName = profName.replaceAll(":", "");
				subAsstCode = subAsstCode + sdf.format(cal.getTime());
				subAsstCode = subAsstCode.replaceAll(":", "");

				mediaTransPage.navigateToMediaTransPage();
				mediaTransPage.clickOnCreateImageProfBtn();

				// To Enter data in Create Profile
				mediaTransPage.enterImageProfDetailsWithUniqueNameAndSubAsst(
						profName, subAsstCode);

				// To upload Image in Create Profile
				mediaTransPage.uploadImageInCreateProfile(filePath, fileName);

				// To Click on Transformation Rule button
				mediaTransPage.clickOnAddTransformationRulesBtn();

				mediaTransPage.selectImgFileFormatType(eachType);

				mediaTransPage.getSaveNotificationMessage();

			}
		}
	}

	@Override
	public void tearDown() {

	}

}
