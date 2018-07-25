package testscripts.amts;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_107 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void AMTS_062()
	{
		testParameters.setCurrentTestDescription("Verify the UI of Create video transformation profile");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
 {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(
				scriptHelper);
		mediaTransPage.navigateToMediaTransPage();
		mediaTransPage.clickOnCreateVideoProfBtn();

		try {
			if (UIHelper.checkForAnElementbyXpath(driver,
					mediaTransPage.vdoMacroCodeXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							mediaTransPage.vdoProDescXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							mediaTransPage.vdoProNameXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							mediaTransPage.addImageProfBtnXpath)
					&& UIHelper.checkForAnElementbyXpath(driver,
							mediaTransPage.vdoSubAsstXpath)) {

				UIHelper.highlightElement(driver,
						mediaTransPage.vdoMacroCodeXpath);
				UIHelper.highlightElement(driver,
						mediaTransPage.vdoProDescXpath);
				UIHelper.highlightElement(driver,
						mediaTransPage.vdoProNameXpath);
				UIHelper.highlightElement(driver,
						mediaTransPage.addImageProfBtnXpath);
				UIHelper.highlightElement(driver,
						mediaTransPage.vdoSubAsstXpath);

				report.updateTestLog(
						"Verify the UI of Create video transformation profile",
						"Verify the UI of Create video transformation profile successfully",
						Status.PASS);

			} else {
				report.updateTestLog(
						"Verify the UI of Create video transformation profile",
						"Verify the UI of Create video transformation profile failed",
						Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(
					"Verify the UI of Create video transformation profile",
					"Verify the UI of Create video transformation profile failed",
					Status.FAIL);
		}

	}
	

	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}