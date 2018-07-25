package testscripts.collections;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.GmailVerificationPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * @author ASHOK
 */
public class ALFDEPLOY_2762_TC001 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_028() {
		testParameters
				.setCurrentTestDescription("Can go to the quick edit and full edit page of a course, sequence object, container and asset and see the order as specified herer");

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
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(
				scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);

		// login
		functionalLibrary.loginAsValidUser(signOnPage);

		// create random site and enter into doc lib
		homePageObj.navigateToSitesTab();
		//String siteNameValue = dataTable.getData("Sites", "SiteName");
		//sitesPage.createSite(siteNameValue, "Yes");
		String siteName = sitesPage.getCreatedSiteName();
		sitesPage.openSiteFromRecentSites(siteName);
		sitesPage.enterIntoDocumentLibrary();


		// enter collection UI
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(
				",");
		myFiles.openCreatedFolder(folderNames[0]);
		myFiles.openCreatedFolder(folderNames[1]);
		collectionPg.clickOnEditCollectionButton();
		
		// enter into default course object
		String collectionObjectName = dataTable.getData("MyFiles",
				"CreateMenuItemsForCollection");
		collectionPg.openCollectionObject(collectionObjectName);
		UIHelper.waitForPageToLoad(driver);
		
		collectionPg.verifyQuickEditPropertiesDialogBoxFieldsForCollectionObjects();
		collectionPg.verifyFullEditPropertiesDialogBoxFieldsForCollectionObjects();
		collectionPg.verifyCreateDialogBoxFieldsForCollectionObjects();
		
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}