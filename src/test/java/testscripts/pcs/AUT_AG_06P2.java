package testscripts.pcs;

import java.util.ArrayList;
import java.util.Collections;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
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
public class AUT_AG_06P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_06() {
		testParameters
		.setCurrentTestDescription("1. Verify whether the Search Result count in Alfresco PCS dashlet and PCS site are same - PCS Site"
				+ "<br>2. Verify the values corresponds to each field of PCS record in Alfresco PCS Dashlet and PCS Site matches for same search input - PCS Site");

		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		String pcssite = dataTable.getData("Parametrized_Checkpoints", "Help URL");

		driver.get(pcssite);
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ pcssite, Status.DONE);

	}

	@Override
	public void executeTest() {

		try {
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			ArrayList<String> dashletList = new ArrayList<String>();
			ArrayList<String> pcsresultList = new ArrayList<String>();

			AlfrescoPCSPage pcsObj = new AlfrescoPCSPage(scriptHelper);

			signOnPage.loginAsValidDifferentUser();

			pcsObj.pcsSitetabNavigate("Product", "Title Info", "0132931281");
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String inputlabel = dataTable.getData("MyFiles",
					"MoreSettingsOption");
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			ArrayList<String> labelList = myFiles.getFolderNames(inputlabel);
			UIHelper.waitFor(driver);
			Thread.sleep(6000);

			pcsresultList.add(UIHelper.findAnElementbyXpath(driver,
					pcsObj.pcscopyright).getAttribute("value"));
			pcsresultList.add(UIHelper.findAnElementbyXpath(driver,
					pcsObj.pcsISBN10).getAttribute("value"));
			pcsresultList.add(UIHelper.findAnElementbyXpath(driver,
					pcsObj.pcsISBN13).getAttribute("value"));

			for (String input : labelList) {

				String finallabelValueXpath = pcsObj.pcsvalues.replace("CRAFT",
						input);
				String finallabelValue = pcsObj.pcsvalues1.replace("CRAFT",
						input);
				if (UIHelper.findAnElementbyXpath(driver, finallabelValueXpath)
						.getText().length() > 0) {
					pcsresultList.add(UIHelper
							.findAnElementbyXpath(driver, finallabelValueXpath)
							.getText().trim());
				} else {
					pcsresultList.add(UIHelper
							.findAnElementbyXpath(driver, finallabelValue)
							.getAttribute("value").trim());
				}
			}

			String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/PCSDetails.txt";
			dashletList = new FileUtil()
					.readListOFDataFromFile(testOutputFilePath);

			Collections.sort(pcsresultList);
			Collections.sort(dashletList);
			boolean check = false;
			for (String value : pcsresultList) {

				if (dashletList.contains(value)) {
					check = true;
				
				} else {
					check = false;
					break;
				}
				
				
			}	if (check) {
			
				report.updateTestLog(
						"Verify the aspects of an archive file/folder matches with the PCS record",
						"Verify the aspects of an archive file/folder matches with the PCS record successfully"
								+ pcsresultList, Status.PASS);
			} else {
				report.updateTestLog(
						"Verify the aspects of an archive file/folder matches with the PCS record",
						"Verify the aspects of an archive file/folder matches with the PCS record failed",
						Status.FAIL);
			}

		} catch (Exception e) {

			report.updateTestLog(
					"Verify the aspects of an archive file/folder matches with the PCS record",
					"Verify the aspects of an archive file/folder matches with the PCS record failed",
					Status.FAIL);
		}
		 
		
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}