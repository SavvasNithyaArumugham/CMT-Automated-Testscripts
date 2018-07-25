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
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_022 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AUT_AG_022() {
		
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-5071_Verify the presence of lifecycle name-Image Optimization upon clicking Attach lifecycle for image file which is present outside the rule applied folder from More option or file preview page or selected items of My files");
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
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homepage=new AlfrescoHomePage(scriptHelper);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
	    String files = dataTable.getData("MyFiles", "FileName");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderNonOptimized=dataTable.getData("MyFiles", "Version");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String attachLifeCycleDropdownVal = dataTable.getData("Document_Details", "Title");
		String defaultPromoteStateVal=dataTable.getData("Document_Details", "LifecycleActionNameForDemote");


		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);	
		homepage.navigateToMyFilesTab();
		
		if(sitesPage.documentAvailable(folderNonOptimized))
		{
			sitesPage.documentdetails(folderNonOptimized);
		}
		else
		{
			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(folderNonOptimized);
		}
		
		docLibPge.deleteAllFilesAndFolders();
	
		myFiles.uploadFileInMyFilesPage(filePath, files);
				
		//Perform attach lifecycle via selected items menu
		docLibPge.selectAllFilesAndFolders();
		docLibPge.clickSelectedItemsMenu();
		docLibPge.commonMethodForClickOnSelectedItemsMenuOption(moreSettingsOption);
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
	for(int i=0;i<fileName.length;i++)
	{
		sitesPage.documentdetails(fileName[i]);
		String ActualLifecycle = appSearchPg.getMetadata(fileName[i],
				"Lifecycle Name:");	
		
		if(ActualLifecycle.equals(attachLifeCycleDropdownVal)) {
			report.updateTestLog("Verify LifeCycle Name is displayed in Property section of document details Page",
					"LifeCycle name is successfully displayed in Property Section" + "<br/><b>LifeCycle:</b>"
							+ ActualLifecycle,
					Status.PASS);
		}else {
			report.updateTestLog("Verify LifeCycle Name is displayed in Property section of document details Page",
					"LifeCycle name is failed to display in Property Section" + "<br/><b>LifeCycle:</b>"
							+ ActualLifecycle,
					Status.FAIL);
		}
		
		//Get the default lifecycle state of Image optimization lifecycle
		String defaultState = appSearchPg.getMetadata(fileName[i],
				"State:");

		if(defaultState.equals(defaultPromoteStateVal)) {
			report.updateTestLog("Verify Default LifeCycle State is displayed in Property section of document details Page",
					"DefaultLifeCycle State is successfully displayed in Property Section" + "<br/><b>LifeCycle Sate:</b>"
							+ defaultState,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Default LifeCycle State is displayed in Property section of document details Page",
					"Default LifeCycle State is failed to display in Property Section" + "<br/><b>LifeCycle State:</b>"
							+ defaultState,
					Status.FAIL);
		}	

		docDetailsPg.backToFolderOrDocumentPage(folderNonOptimized);
	}
	}
		



	@Override
	public void tearDown() {

	}

}
