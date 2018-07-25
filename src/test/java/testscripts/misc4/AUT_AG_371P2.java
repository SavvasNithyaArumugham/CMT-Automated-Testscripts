package testscripts.misc4;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoTasksPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoWorkflowPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_371P2 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void misc4_063()
	{
		testParameters.setCurrentTestDescription("Verify the file/folder name is reflecting  properly in edit task page after modifying the files"
				+ "<br>Part2: Verify modified file/folder name is reflecting properly in edit task page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoTasksPage taskPage = new AlfrescoTasksPage(scriptHelper);
		AlfrescoTasksPageTest taskPageTest = new AlfrescoTasksPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
				
		homePage.navigateToTasksTab();
		
		taskPage.navigateToMyTasksMenu();
		
		String taskName = dataTable.getData("Workflow", "Message");
		String currentDate = new DateUtil().getCurrentDate();
		String finalTaskName = taskName+"_"+currentDate;
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String existingAssetName="";
		String newAssetName="";
		if(fileName.contains(","))
		{
			String splittedFileNames[] = fileName.split(",");
			
			if(splittedFileNames!=null &splittedFileNames.length>1)
			{
				existingAssetName=splittedFileNames[0];
				newAssetName=splittedFileNames[1];
			}
		}
		
		taskPageTest.verifyAttachedFileNameForTask(finalTaskName, existingAssetName);
		
		taskPage.openCreatedOrAssignedTask();
		
		taskPage.removeAssetFromEditTaskPage(existingAssetName);
		
		docDetailsPage.clickOnAddItemsButton();
		
		docDetailsPage.addMoreItem(newAssetName);
		
		docDetailsPage.clickOnAddItemsOkButton();
		
		taskPage.clickOnSaveAndCloseBtn();
		
		taskPageTest.verifyAttachedFileNameForTask(finalTaskName, newAssetName);
	}

	@Override
	public void tearDown() {
		
	}

}
