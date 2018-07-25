package testscripts.pcs;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
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
public class AUT_AG_37P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void pcs_24() {
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

			pcsObj.pcsSitetabNavigate("Product", "Title Info", "0536169454");
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			String inputlabel = dataTable.getData("MyFiles",
					"Version");
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			ArrayList<String> labelList = myFiles.getFolderNames(inputlabel);
			UIHelper.waitFor(driver);
			Thread.sleep(6000);

			for (String input : labelList) {

				String finallabeltxtXpath = pcsObj.custompcsvalues.replace("CRAFT",
						input);
				String finallabelselXpath = pcsObj.custompcsselect.replace("CRAFT",
						input);
				if (UIHelper.checkForAnElementbyXpath(driver, finallabeltxtXpath)) {
					pcsresultList.add(UIHelper
							.findAnElementbyXpath(driver, finallabeltxtXpath)
							.getAttribute("value").trim());
				} else {
					pcsresultList.add(UIHelper
							.findAnElementbyXpath(driver, finallabelselXpath)
							.getText().trim());
				}
			}

			String testOutputFilePath = "src/test/resources/AppTestData/TestOutput/PCSDetails.txt";
			dashletList = new FileUtil()
					.readListOFDataFromFile(testOutputFilePath);

			Collections.sort(pcsresultList);
			Collections.sort(dashletList);
			
			System.out.println(pcsresultList);
			System.out.println(dashletList);
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
			
			
			pcsObj.pcsSitetab("Production", "Overview",
					"0536169454");
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String strDate = sdf.format(date);

			String siteassertValue = dataTable.getData("Sites", "SiteName");
			AlfrescoPCSPageTest pcsTest = new AlfrescoPCSPageTest(scriptHelper);
			pcsTest.verifycustompcssite(strDate, siteassertValue);
			
			UIHelper.waitForVisibilityOfEleByXpath(driver, pcsObj.pcsAlfrescoButton);
			UIHelper.click(driver, pcsObj.pcsAlfrescoButton);
			
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			homePageObj.switchtab(1);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
		/*	functionalLibrary.loginAsValidUser(signOnPage);*/
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
					scriptHelper);
			docDetailsPageTest.verifyUploadedFileIsOpened("AutoTest", "");

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