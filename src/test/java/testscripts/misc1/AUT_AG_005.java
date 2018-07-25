package testscripts.misc1;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_005 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_005() {
		testParameters.setCurrentTestDescription(
				"Verify the user is able to add Site Relationships Dashlet to existing site<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via site relationship dashlet when user is member of greater than or equal to 250 sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify only 250 user sites are listed in relationship document picker upon adding relationship via site relationship dashlet when user is member of greater than or equal to 250 sites and there are no folders in repository browser-sites<br>"
						+ "ALFDEPLOY-1997_4889_Verify the available user sites are listed in relationship document picker upon adding relationship via site relationship dashlet when user is member of less than 250 sites");
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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		AlfrescoSitesPageTest sitesPageTestObj = new AlfrescoSitesPageTest(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		String sitesCount = dataTable.getData("MyFiles", "ExpectedSitesCount");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String addRelationSite2 = dataTable.getData("Sites", "TargetSiteName");
		
		ArrayList<String> mysitesList=homePage.getSitesFromMySitesDashletInHomePage();
		
		sitesPage.openSiteFromRecentSites(addRelationSite2);
		homePage.navigateToSitesTab();
		sitesPage.siteFinder(siteName);

		// AlfrescoSitesDashboardPageTest sitesDashboardPageTest = new
		// AlfrescoSitesDashboardPageTest(scriptHelper);
		sitesDashboardPg.customizeSiteDashboard();
		sitesDashboardPg.removeCustomeSiteDashlet();
		sitesDashboardPg.customizeSiteDashboard();
		sitesDashboardPg.addDashletInCustomDashBoard();

		// sitesDashboardPageTest.verifyAddDashletInCustomDashBrd();
		String dashletNmetoAddTest = dataTable.getData("Home", "DashletName");
		String colNoofAddDashletTest = dataTable.getData("Home", "ColumnNoofAddDashlet");

		if (sitesDashboardPg.isSiteRelationshipDashletAdded()) {
			report.updateTestLog(
					"Verify Added Dashlet", "<b>Dashlet:</b>" + dashletNmetoAddTest + " added to Column No "
							+ colNoofAddDashletTest + " using Add Dashlet Functionality in Custom Dashboard",
					Status.PASS);
		} else {
			report.updateTestLog(
					"Verify Added Dashlet", "<b>Dashlet:</b>" + dashletNmetoAddTest + " failed to add to Column No "
							+ colNoofAddDashletTest + " using Add Dashlet Functionality in Custom Dashboard",
					Status.FAIL);
		}

		sitesDashboardPg.clickAddRelationshipFromSiteRelationshipDashlet();

		sitesPage.selectRelationshipNameAndclickOnAddRelationshipBtn(relationshipName, siteName);
		int expectedSitesCount = Integer.parseInt(sitesCount.replace("'", ""));
		sitesPageTestObj.verifySiteNamesInSelectWindowForAddRelationship(expectedSitesCount);
		ArrayList<String> relationshipPickerSitesList=sitesPage.getSitesFromRelationshipPicker();

		int matchingCount = 0, nonMatchingCount = 0;
		for (int i = 0; i < relationshipPickerSitesList.size(); i++) {
			if (relationshipPickerSitesList.contains(mysitesList.get(i))) {
				//	UIHelper.compareTwoDiffSizeOfLists(mysitesList, relationshipPickerSitesList)
				matchingCount++;
			} else {
				nonMatchingCount++;
			}

		}
		report.updateTestLog("Verify sitesList in relationship picker matches with my sites list",
				"SitesList in relationship picker matches with my sites list are " + matchingCount
						+ " and not matches with my sites list are " + nonMatchingCount,
				Status.PASS);
		
		sitesPage.addRelationshipBtwAssetAndSite(relationshipName, siteName, addRelationSite2);

		if (docDetailsPage.isRelationshipAddedForSite()) {
			if (addRelationSite2.equalsIgnoreCase(docDetailsPage.getRelationshipTableValFromList(addRelationSite2))
					&& (relationshipName
							.equalsIgnoreCase(docDetailsPage.getRelationshipValFromList(relationshipName)))) {
				report.updateTestLog("Verify Relationship Added between Sites is listed in Site Relationships Dashlet",
						"Relationship Added is listed in Site Relationships Dashlet successfully"
								+ "</br><b>SourceSite Name:</b> " + siteName + "</br><b>Relationship Name:</b> "
								+ docDetailsPage.getRelationshipValFromList(relationshipName)
								+ "</br><b>TargetSite Name:</b> "
								+ docDetailsPage.getRelationshipTableValFromList(addRelationSite2),
						Status.PASS);
			} else {
				report.updateTestLog("Verify Relationship Added between Sites is listed in Site Relationships Dashlet",
						"Relationship added is not listed" + "</br><b>SourceSite Name:</b> " + siteName
								+ "</br><b>Relationship Name:</b> "
								+ docDetailsPage.getRelationshipValFromList(relationshipName)
								+ "</br><b>TargetSite Name:</b> "
								+ docDetailsPage.getRelationshipTableValFromList(addRelationSite2),
						Status.FAIL);
			}
		} else {
			report.updateTestLog("Verify Relationship Added between Sites is listed in Site Relationships Dashlet",
					"Relationship is not Added" + "</br><b>SourceSite Name:</b> " + siteName
							+ "</br><b>Relationship Name:</b> "
							+ docDetailsPage.getRelationshipValFromList(relationshipName)
							+ "</br><b>TargetSite Name:</b> "
							+ docDetailsPage.getRelationshipTableValFromList(addRelationSite2),
					Status.FAIL);
		}
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}