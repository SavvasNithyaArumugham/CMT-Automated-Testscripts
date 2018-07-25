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

public class AUT_AG_018P1 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AUT_AG_018P1() {
		
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-5071_Verify new version of optimized file is auto-created in Optimized folder upon demoting the lifecycle state to Optimize for PNG from Optimization completed<br>"+
				"ALFDEPLOY-5071_Verify the lifecycle states when user is in last state of lifecycle -Image optimization for image file<br>"+
				"ALFDEPLOY-5071_Verify the operation of detach lifecycle when user is in last state of lifecycle -Image optimization for image file");
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

		

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoMediaTransformPage mediaTransformPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPage = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		
		String sourceSiteName = dataTable.getData("Sites", "InviteUserName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String assetFolder = dataTable.getData("MyFiles", "CreateFolder");
		String optimizedFolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String attachLifeCycleDropdownVal = dataTable.getData("Document_Details", "Title");
		String lifecycleAction = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
		String defaultPromoteStateVal=dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
		String changedPromoteStateVal=dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
		String finalFileimg=dataTable.getData("Sites", "Role");
		String dashletName = dataTable.getData("Home", "DashletName");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String lifecycleActionForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
		String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
		
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(assetFolder);
		sitesPage.documentdetails(fileName);
		
		String ActualLifecycle = appSearchPg.getMetadata(fileName,
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
		String defaultState = appSearchPg.getMetadata(fileName,
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
		
		//Promote Lifecycle via document details page
		docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
		
		//lifecycle states when user is in last state of lifecycle -Image optimization
		docDetailsPg.lifeCycleDropdown(lifecycleAction);
		docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
		
		String ActualLifecycleState = appSearchPg.getMetadata(fileName,
				"State:");

		if(ActualLifecycleState.equals(changedPromoteStateVal)) {
			report.updateTestLog("Verify LifeCycle State is displayed in Property section of document details Page",
					"LifeCycle State is successfully displayed in Property Section" + "<br/><b>LifeCycle State:</b>"
							+ ActualLifecycleState,
					Status.PASS);
		}else {
			report.updateTestLog("Verify LifeCycle State is displayed in Property section of document details Page",
					"LifeCycle State is failed to display in Property Section" + "<br/><b>LifeCycle State:</b>"
							+ ActualLifecycleState,
					Status.FAIL);
		}	
		
		
		//Verify the optimized file in Optimized folder
		sitesPage.enterIntoDocumentLibraryWithoutReport();
		sitesPage.documentdetails(optimizedFolder);	
		if (docLibPge.isFileIsAvailable(finalFileimg)) {
			report.updateTestLog("Verify the optimized file is updated in Optimized folder",
					"Optimized file is updated in Optimized folder" + "<br><b> Transformed File Name : </b>" + finalFileimg, Status.PASS);
			sitesPage.documentdetails(finalFileimg);
			mediaTransformPage.checkVersionOfTransformedFile(versionDetails);
		
			
		} else {
			report.updateTestLog("Verify the optimized file is updated in Optimized folder",
					"Optimized file is failed to update in Optimized folder" + "<br><b> Transformed File Name : </b>" + finalFileimg, Status.FAIL);
		}
		
		//Verify the transformation status in Async dashlet
        sitesDashboardPage.navigateToSiteDashboard();
		if(!sitesDashboardPage.checkDashletInSiteDashboard(dashletName))
		{
			sitesDashboardPage.customizeSiteDashboard();
			sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		}
		String expectedHeaderNames = dataTable.getData("Site_Dashboard", "AsyncDashletHeaderNames");
		sitesDashboardPageTest.verifyHeaderNamesInAsyncDashlet(expectedHeaderNames);
		String expectedQueuedNamesForEPSJob = dataTable.getData("Site_Dashboard", "QueuedStatusForEPSJob");
		sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob,
				fileName);
	    sitesDashboardPageTest.verifyNumberOfEntriesInAsyncDashlet();

	
	    //Detach lifecycle
	    sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(assetFolder);
		sitesPage.documentdetails(fileName);

		docDetailsPg.commonMethodForPerformDocAction(lifecycleActionForDetach);
		docLibPage.verifyConfirmationDailogMessage(expectedMessageVal);
		
		//Verify the absence of lifecycle name in properties
		String RemovedLifecycle = appSearchPg.getMetadata(fileName,
				"Lifecycle Name:");	
		
		if(RemovedLifecycle.equals("")) {
			report.updateTestLog("Verify LifeCycle Name is displayed in Property section of document details Page",
					"LifeCycle name is successfully displayed in Property Section" + "<br/><b>LifeCycle:</b>"
							+ RemovedLifecycle,
					Status.PASS);
		}else {
			report.updateTestLog("Verify LifeCycle Name is displayed in Property section of document details Page",
					"LifeCycle name is failed to display in Property Section" + "<br/><b>LifeCycle:</b>"
							+ RemovedLifecycle,
					Status.FAIL);
		}
		
		//Verify the absence of lifecycle state in properties
		String RemovedLifecycleState = appSearchPg.getMetadata(fileName,
				"State:");
	if(RemovedLifecycleState.equals("")) {
			report.updateTestLog("Verify LifeCycle State is removed in Property section of document details Page post detach lifecycle",
					"LifeCycle State is removed in Property section of document details Page post detach lifecycle" + "<br/><b>LifeCycle:</b>"
							+ RemovedLifecycleState,
					Status.PASS);
		}else {
			report.updateTestLog("Verify LifeCycle State is removed in Property section of document details Page post detach lifecycle",
					"LifeCycle State is failed to remove in Property section of document details Page post detach lifecycle" + "<br/><b>LifeCycle:</b>"
							+ RemovedLifecycleState,
					Status.FAIL);
		}

	}
		
	@Override
	public void tearDown() {

	}

}
