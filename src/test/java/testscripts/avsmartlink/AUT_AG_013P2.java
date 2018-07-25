package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_013P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("Verify the Consumer is not able to create 'MD Pop up' smart link object in document library site. Part 2 - consumer create a Smart link file");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);

		
/*		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");*/
		
		String type = dataTable.getData("Home", "DashletName");
	/*	String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");*/
			
		functionalLibrary.loginAsValidUser(signOnPage);
	
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		UIHelper.waitForVisibilityOfEleByXpath(driver, myFiles.disablecreate);
	System.out.println(UIHelper.findAnElementbyXpath(driver,  myFiles.disablecreate).getAttribute("disabled"));
		
		if(UIHelper.findAnElementbyXpath(driver,  myFiles.disablecreate).getAttribute("disabled").equals("true")){
			
			report.updateTestLog("Verify" + type + "Element available",
					type + " Element is not displayed for reviewer role", Status.PASS);
			
		}else{
			report.updateTestLog("Verify" + type + "Element available",
					type + " Element is displayed for reviewer role", Status.FAIL);
		}
	
		
		
	}
		

	@Override
	public void tearDown() {

	}
}
