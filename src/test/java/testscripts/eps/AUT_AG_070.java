package testscripts.eps;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_070 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void EPS_001() {
		testParameters.setCurrentTestDescription(
				"R2.0.2_ALFDEPLOY-5696_Verify the presence of Optimized folder and folder applied with optimization and auto publish rule in the document library of the CITE Site"
						+ "<br>R2.0.2_ALFDEPLOY-5696_Verify optimized file is auto created and auto published in Optimized folder upon promoting the lifecycle state of an image to Optimize for JPG from Ready for Optimization via More or preview page"
						+ "<br>R2.0.2_ALFDEPLOY-5696_Verify optimized file is auto-created and auto published in Optimized folder upon Promoting the lifecycle state to Optimize for PNG from Optimization completed via More or preview page for JPG optimization completed file"
						+ "<br>R2.0.2_ALFDEPLOY-5696_Verify new version of optimized file is auto-created and auto published in Optimized folder while promoting the lifecycle state of the file again to Optimize for JPG from Optimization completed"
						+ "<br>R2.0.2_ALFDEPLOY-5696_Verify new version of optimized file is auto-created and auto published in Optimized folder while promoting the lifecycle state of the file again to Optimize for JPG from Optimization completed"
						+ "<br>R2.0.2_ALFDEPLOY-5696_Verify new version of optimized file is auto-created and auto published in Optimized folder while promoting the lifecycle state of the file again to Optimize for PNG from Optimization completed");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);

		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage doclib = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoEPSPage epsPage = new AlfrescoEPSPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoMediaTransformPage mediaTransformPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPage = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new AlfrescoSitesDashboardPageTest(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);

		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		String rootFolder = dataTable.getData("MyFiles", "BrowseActionName");
		String assetFolder = dataTable.getData("MyFiles", "CreateFolder");
		String optimizedFolder = dataTable.getData("MyFiles", "Version");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");

		String attachLifeCycleState = dataTable.getData("Document_Details", "Title");

		String defaultStateVal = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
		String[] promotOrDemoteOrDetachStateDropdownValue = dataTable
				.getData("Document_Details", "ChangedLifecyclePropValuesForPromote").split(",");
		String changedPromoteStateVal = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
		String[] finalFileimg = dataTable.getData("Sites", "Role").split(",");
		String dashletName = dataTable.getData("Home", "DashletName");
		String[] updatedVersion = dataTable.getData("Document_Details", "AspectName").split(",");

		String attachLifeCycle = dataTable.getData("MyFiles", "Sort Options");
		String delete = dataTable.getData("MyFiles", "CreateFileDetails");
		homePage.navigateToSitesTab();
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		if (sitesPage.documentAvailable(rootFolder)) {
			sitesPage.documentdetails(rootFolder);
			if (sitesPage.documentAvailable(assetFolder) && sitesPage.documentAvailable(optimizedFolder)) {
				report.updateTestLog(
						"Verify the presence of Optimized folder and folder applied with optimization and auto publish rule in the document library of the CITE Site",
						"Optimized folder" + " " + optimizedFolder + " "
								+ "and folder applied with optimization and auto publish rule" + " " + assetFolder + " "
								+ "are present in the document library of the CITE Site",
						Status.PASS);
			}
		} else {
			report.updateTestLog(
					"Verify the presence of Optimized folder and folder applied with optimization and auto publish rule in the document library of the CITE Site",
					"Optimized folder" + " " + optimizedFolder + " "
							+ "and folder applied with optimization and auto publish rule" + " " + assetFolder + " "
							+ "are not configured/displayed in the document library of the CITE Site",
					Status.FAIL);

		}
		sitesPage.documentdetails(optimizedFolder);
		if(sitesPage.documentAvailable(finalFileimg[0]))
		{
		sitesPage.clickOnMoreSetting(finalFileimg[0]);
		doclib.commonMethodForClickOnMoreSettingsOption(finalFileimg[0], delete);
		doclib.deletefolderwithMoreactions();
		}
		if(sitesPage.documentAvailable(finalFileimg[1]))
		{
		sitesPage.clickOnMoreSetting(finalFileimg[1]);
		doclib.commonMethodForClickOnMoreSettingsOption(finalFileimg[1], delete);
		doclib.deletefolderwithMoreactions();
		}
		doclib.clickOnBreadCrumbLinkDocLibPg(rootFolder);
		sitesPage.documentdetails(assetFolder);
		myFiles.deleteUploadedFile(fileName);

		myFiles.uploadFile(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName, "");

		sitesPage.clickOnMoreSetting(fileName);
		doclib.commonMethodForClickOnMoreSettingsOption(fileName, attachLifeCycle);
		docDetailsPg.changeLifeCycleSate(attachLifeCycleState);
		sitesPage.documentdetails(fileName);
		String ActualLifecycle = appSearchPg.getMetadata(fileName, "Lifecycle Name:");

		// Get the default lifecycle name of Image optimization lifecycle
		if (ActualLifecycle.equals(attachLifeCycleState)) {
			report.updateTestLog(
					"Verify default LifeCycle Name is displayed in Property section of document details Page after attaching Image optimization lifecycle",
					"default LifeCycle name is successfully displayed in Property Section" + "<br/><b>LifeCycle:</b>"
							+ ActualLifecycle,
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify default LifeCycle Name is displayed in Property section of document details Page after attaching Image optimization lifecycle",
					"LifeCycle name is failed to display in Property Section" + "<br/><b>LifeCycle:</b>"
							+ ActualLifecycle,
					Status.FAIL);
		}
		String defaultState = appSearchPg.getMetadata(fileName, "State:");
		// Get the default lifecycle state of Image optimization lifecycle
		if (defaultState.equals(defaultStateVal)) {
			report.updateTestLog(
					"Verify default LifeCycle state is displayed in Property section of document details Page after attaching Image optimization lifecycle",
					"Default LifeCycle State is successfully displayed in Property Section"
							+ "<br/><b>LifeCycle Sate:</b>" + defaultState,
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify default LifeCycle state is displayed in Property section of document details Page after attaching Image optimization lifecycle",
					"Default LifeCycle State is failed to display in Property Section" + "<br/><b>LifeCycle State:</b>"
							+ defaultState,
					Status.FAIL);
		}

		for (int i = 0; i < promotOrDemoteOrDetachStateDropdownValue.length; i++) {
			// Promote Lifecycle via document details page
			docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
			docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue[i]);
			String ActualLifecycleState = appSearchPg.getMetadata(fileName, "State:");
			if (ActualLifecycleState.equals(changedPromoteStateVal)) {
				report.updateTestLog(
						"Change lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[i]
								+ " from default lifeycle state",
						"Changed lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[i]
								+ " from default lifeycle state" + "<br/><b>LifeCycle Sate:</b>" + ActualLifecycleState,
						Status.PASS);
			} else {
				report.updateTestLog(
						"Change lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[i]
								+ " from default lifeycle state",
						"Unable to change lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[i]
								+ " from default lifeycle state" + "<br/><b>LifeCycle State:</b>"
								+ ActualLifecycleState,
						Status.FAIL);
			}
			// Verify the optimized file in Optimized folder
			docDetailsPg.clickOnBreadCrumbLink(rootFolder);
			sitesPage.documentdetails(optimizedFolder);
			if (doclib.isFileIsAvailable(finalFileimg[i])) {
				report.updateTestLog(
						"Verify the optimized file is present in Optimized folder for Promote "
								+ promotOrDemoteOrDetachStateDropdownValue[i],
						"Optimized file is present in Optimized folder for Promote- "
								+ promotOrDemoteOrDetachStateDropdownValue[i] + "<br><b> Transformed File Name : </b>"
								+ finalFileimg[i],
						Status.PASS);
				UIHelper.pageRefresh(driver);
				UIHelper.pageRefresh(driver);
				epsPage.verifypublishicon(finalFileimg[i], "published", "published");
				sitesPage.documentdetails(finalFileimg[i]);
				mediaTransformPage.checkVersionOfTransformedFile(updatedVersion[i]);
			} else {
				report.updateTestLog(
						"Verify the optimized file is present in Optimized folder for Promote- "
								+ promotOrDemoteOrDetachStateDropdownValue[i],
						"Optimized file is failed to display in Optimized folder for Promote- "
								+ promotOrDemoteOrDetachStateDropdownValue[i] + "<br><b> Transformed File Name : </b>"
								+ finalFileimg[i],
						Status.FAIL);
			}

			// Verify the transformation status in Async dashlet
			sitesDashboardPage.navigateToSiteDashboard();
			if (!sitesDashboardPage.checkDashletInSiteDashboard(dashletName)) {
				sitesDashboardPage.customizeSiteDashboard();
				sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
			}
			String expectedHeaderNames = dataTable.getData("Site_Dashboard", "AsyncDashletHeaderNames");
			sitesDashboardPageTest.verifyHeaderNamesInAsyncDashlet(expectedHeaderNames);
			String expectedQueuedNamesForEPSJob = dataTable.getData("Site_Dashboard", "QueuedStatusForEPSJob");
			sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob, fileName);
			sitesDashboardPageTest.verifyNumberOfEntriesInAsyncDashlet();

			sitesPage.enterIntoDocumentLibrary();
			sitesPage.documentdetails(rootFolder);
			sitesPage.documentdetails(assetFolder);
			sitesPage.documentdetails(fileName);
		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}