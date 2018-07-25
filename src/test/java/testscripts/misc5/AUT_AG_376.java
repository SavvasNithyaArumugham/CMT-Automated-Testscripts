package testscripts.misc5;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
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
public class AUT_AG_376 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runTC128() {
		testParameters
				.setCurrentTestDescription("Verify UI Content Fullfilment");
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
		
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		String fileName = dataTable.getData("MyFiles", "FileName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		sitesPage.documentdetails(fileName);
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Message");
		String wfDetails = dataTable.getData("Workflow", "UserName");
		boolean flag = false;
		wfPageObj.startWorkflow();
		wfPageObj.selectWorkFlow(taskType);
		String inputname = dataTable
				.getData("MyFiles", "CreateFileDetails");
		ArrayList<String> inputList = myFiles.getFolderNames(inputname);
		System.out.println(inputList);
		for (String input : inputList) {
			System.out.println(input);
			String finalcontentlabel = wfPageObj.contentfulllabel.replace(
					"CRAFT", input);
			UIHelper.waitForVisibilityOfEleByXpath(driver, finalcontentlabel);
			if (UIHelper.checkForAnElementbyXpath(driver, finalcontentlabel)) {

				UIHelper.highlightElement(driver, finalcontentlabel);
				flag = true;

			} else {
				System.out.println(input + "adfswe");
				flag = false;
				report.updateTestLog("Verify UI Content Fullfilment",
						"Verify UI Content Fullfilment failed", Status.FAIL);
				break;
			}
		}

		if (flag) {
			report.updateTestLog("Verify UI Content Fullfilment",
					"Verify UI Content Fullfilment passed", Status.PASS);
		}
		
		wfPageObj.Updateallcontent(wfDetails);
		wfPageObj.contentfulstartWFBtn(fileName);
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}