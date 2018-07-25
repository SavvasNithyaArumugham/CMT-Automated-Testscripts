package testscripts.avt;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AVT_TC006 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AVT_006() {
		testParameters.setCurrentTestDescription("13	Verify that the supported files are uploaded for Caption field without any alert and user is getting warning message for unsupported file types" + "" + "" + "" + "");

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

		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoAVTPage alfrescoAVTPage = new AlfrescoAVTPage(scriptHelper);
		String filePath = dataTable.getData("MyFiles", "FilePath");

		// Login alf cms
		functionalLibrary.loginAsValidUser(signOnPage);

		// From the site Type dropdown select 'Collaboration Site '.
		homePageObj.navigateToSitesTab();
		String siteName = sitesPage.getCreatedSiteName();
		// String siteName = "AutoAVTRandomSite50517074206";
		sitesPage.openSiteFromRecentSites(siteName);

		// enter document library
		
		
//		upload video files and verify supported format for MOV file 
		sitesPage.enterIntoDocumentLibrary();
		alfrescoAVTPage.clickOnStreamingMedia();
		String movTitle=alfrescoAVTPage.inputTitle("MOV");
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadCaptionsFile(filePath, "ChapteringXML.xml",	"Spanish");
		alfrescoAVTPage.verifyCompatibilityOfCaptionFileNegative("ChapteringXML.xm");
		driver.navigate().refresh();
		alfrescoAVTPage.verifyRelatedFilesTabFields();
		alfrescoAVTPage.uploadCaptionsFile(filePath, "VTT.vtt", "English");
		alfrescoAVTPage.addCaptionsFile(filePath, "ChapteringXML.xml", "Spanish");
		alfrescoAVTPage.verifyCompatibilityOfCaptionFileNegative("ChapteringXML.xm");

	

	
		


	}

	@Override
	public void tearDown() {

	}
}
