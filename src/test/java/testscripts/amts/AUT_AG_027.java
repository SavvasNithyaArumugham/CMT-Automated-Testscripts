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

public class AUT_AG_027 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AUT_AG_027() {
		
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-5071_Verify the optimized file gets updated with minor version upon promoting lifecycle state to Optimize for PNG for a image file present inside rule applied folder when optimized file is uploaded manually");
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
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoMediaTransformPage mediaTransformPage = new AlfrescoMediaTransformPage(scriptHelper);
		
		String sourceSiteName = dataTable.getData("Sites", "InviteUserName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String assetFolder = dataTable.getData("MyFiles", "CreateFolder");
		String optimizedFolder = dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String attachLifeCycleDropdownVal = dataTable.getData("Document_Details", "Title");
		String lifecycleAction = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
		String changedPromoteStateVal=dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
		String finalFileimg=dataTable.getData("Sites", "Role");
		String dashletName = dataTable.getData("Home", "DashletName");
		String initialVersion=dataTable.getData("MyFiles", "Version");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		
		
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(optimizedFolder);
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, finalFileimg);
		sitesPage.documentdetails(finalFileimg);
		mediaTransformPage.checkVersionOfTransformedFile(initialVersion);
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.documentdetails(assetFolder);
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
		//Perform attach lifecycle via document details page
		sitesPage.documentdetails(fileName);
		docDetailsPg.commonMethodForPerformDocAction(moreSettingsOption);
		docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);		

		
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
			
		//Promote Lifecycle via more option
		docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
		docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);	

		String ActualLifecycleState = appSearchPg.getMetadata(fileName,
				"State:");

		if(ActualLifecycleState.equals(changedPromoteStateVal)) {
			report.updateTestLog("Verify LifeCycle State is displayed in Property section of document details Page",
					"LifeCycle State is successfully displayed in Property Section" + "<br/><b>LifeCycle Sate:</b>"
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
			report.updateTestLog("Verify the optimized file is present in Optimized folder",
					"Optimized file is present in Optimized folder" + "<br><b> Transformed File Name : </b>" + finalFileimg, Status.PASS);
			sitesPage.documentdetails(finalFileimg);
			mediaTransformPage.checkVersionOfTransformedFile(versionDetails);
		
		} else {
			report.updateTestLog("Verify the optimized file is present in Optimized folder",
					"Optimized file is failed to display in Optimized folder" + "<br><b> Transformed File Name : </b>" + finalFileimg, Status.FAIL);
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
	}

	@Override
	public void tearDown() {

	}

}
