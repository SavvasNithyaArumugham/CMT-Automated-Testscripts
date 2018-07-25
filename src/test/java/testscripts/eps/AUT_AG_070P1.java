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

public class AUT_AG_070P1 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void EPS_001() {
		testParameters.setCurrentTestDescription(
				"R2.0.2_ALFDEPLOY-5696_Verify new version of optimized file is auto-created and auto published in Optimized folder for updated source(upload new version) file upon promoting the lifecycle state of the image to Optimize for JPG from Ready for Optimization"
				+"<br> R2.0.2_ALFDEPLOY-5696_Verify new version of optimized file is auto-created and auto published in Optimized folder for updated source(upload new version) file upon promoting the lifecycle state of the image to Optimize for PNG from Ready for Optimization");		
		
		
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
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		
		String attachLifeCycleState = dataTable.getData("Document_Details", "Title");

		String defaultStateVal = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
		String[] promotOrDemoteOrDetachStateDropdownValue = dataTable
				.getData("Document_Details", "ChangedLifecyclePropValuesForPromote").split(",");
		String changedPromoteStateVal = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
		String[] finalFileimg = dataTable.getData("Sites", "Role").split(",");
		String versionDetails = dataTable.getData("Document_Details", "Version");
		String dashletName = dataTable.getData("Home", "DashletName");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] newFiles = dataTable.getData("Document_Details", "FileName").split(",");
		String newFilePath = dataTable.getData("Document_Details", "FilePath");
		String newFileComments = dataTable.getData("Document_Details", "Comments");
		String newFileVersion = dataTable.getData("Document_Details", "DocPropertyValues");
		String[] updatedVersion = dataTable.getData("Document_Details", "AspectName").split(",");
		String delete = dataTable.getData("MyFiles", "CreateFileDetails");
		String attachLifeCycle = dataTable.getData("MyFiles", "Sort Options");
		
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
		 else {
			report.updateTestLog(
					"Verify the presence of Optimized folder and folder applied with optimization and auto publish rule in the document library of the CITE Site",
					"Optimized folder" + " " + optimizedFolder + " "
							+ "and folder applied with optimization and auto publish rule" + " " + assetFolder + " "
							+ "are not configured/displayed in the document library of the CITE Site",
					Status.FAIL);

		}
		}
		
		for(int j=0;j<fileName.length;j++)
		{
		sitesPage.documentdetails(optimizedFolder);
		if(sitesPage.documentAvailable(finalFileimg[j]))
		{
		sitesPage.clickOnMoreSetting(finalFileimg[j]);
		doclib.commonMethodForClickOnMoreSettingsOption(finalFileimg[j], delete);
		doclib.deletefolderwithMoreactions();
		}
		doclib.clickOnBreadCrumbLinkDocLibPg(rootFolder);
		sitesPage.documentdetails(assetFolder);
		myFiles.deleteUploadedFile(fileName[j]);
		myFiles.uploadFile(filePath, fileName[j]);
		myFilesTestObj.verifyUploadedFile(fileName[j], "");
		// upload new version
				sitesPage.clickOnMoreSetting(fileName[j]);
				doclib.commonMethodForClickOnMoreSettingsOption(fileName[j], moreSettingsOptionName);
				doclib.uploadNewVersionInDocLibPage(newFiles[j], newFilePath, newFileVersion, newFileComments);

				doclib.uploadNewVersionButton();		
				
				sitesPage.documentdetails(newFiles[j]);

				if (docDetailsPg.getCurrentVersion().equals(versionDetails)) {
					report.updateTestLog("Verify the file is updated with next major version",
							"file " + newFiles[j] + " is updated with next major version " + docDetailsPg.getCurrentVersion(),
							Status.PASS);
				} else {
					report.updateTestLog("Verify the file is updated with next major version",
							"file " + newFiles[j] + " is not updated with next major version" + docDetailsPg.getCurrentVersion(),
							Status.FAIL);
				}
				docDetailsPg.commonMethodForPerformDocAction(attachLifeCycle);
				docDetailsPg.changeLifeCycleSate(attachLifeCycleState);
				String ActualLifecycle = appSearchPg.getMetadata(fileName[j], "Lifecycle Name:");
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
				String defaultState = appSearchPg.getMetadata(fileName[j], "State:");
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
				docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
				docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue[j]);
				String ActualLifecycleState = appSearchPg.getMetadata(fileName[j], "State:");
				if (ActualLifecycleState.equals(changedPromoteStateVal)) {
					report.updateTestLog(
							"Change lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[j]
									+ " from default lifeycle state",
							"Changed lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[j]
									+ " from default lifeycle state" + "<br/><b>LifeCycle Sate:</b>" + ActualLifecycleState,
							Status.PASS);
				} else {
					report.updateTestLog(
							"Change lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[j]
									+ " from default lifeycle state",
							"Unable to change lifecycle state to " + promotOrDemoteOrDetachStateDropdownValue[j]
									+ " from default lifeycle state" + "<br/><b>LifeCycle State:</b>"
									+ ActualLifecycleState,
							Status.FAIL);
				}
				// Verify the optimized file in Optimized folder
				docDetailsPg.clickOnBreadCrumbLink(rootFolder);
				sitesPage.documentdetails(optimizedFolder);
				if (doclib.isFileIsAvailable(finalFileimg[j])) {
					report.updateTestLog(
							"Verify the optimized file is present in Optimized folder for Promote "
									+ promotOrDemoteOrDetachStateDropdownValue[j],
							"Optimized file is present in Optimized folder for Promote- "
									+ promotOrDemoteOrDetachStateDropdownValue[j] + "<br><b> Transformed File Name : </b>"
									+ finalFileimg[j],
							Status.PASS);
					UIHelper.pageRefresh(driver);
					UIHelper.pageRefresh(driver);
					epsPage.verifypublishicon(finalFileimg[j], "published", "published");
					sitesPage.documentdetails(finalFileimg[j]);
					mediaTransformPage.checkVersionOfTransformedFile(updatedVersion[j]);
				} else {
					report.updateTestLog(
							"Verify the optimized file is present in Optimized folder for Promote- "
									+ promotOrDemoteOrDetachStateDropdownValue[j],
							"Optimized file is failed to display in Optimized folder for Promote- "
									+ promotOrDemoteOrDetachStateDropdownValue[j] + "<br><b> Transformed File Name : </b>"
									+ finalFileimg[j],
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
				sitesDashboardPageTest.verifyPublishedFileQueuStatusInAsyncDashlet(expectedQueuedNamesForEPSJob, fileName[j]);
				sitesDashboardPageTest.verifyNumberOfEntriesInAsyncDashlet();

				sitesPage.enterIntoDocumentLibrary();
				sitesPage.documentdetails(rootFolder);

		}

	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}