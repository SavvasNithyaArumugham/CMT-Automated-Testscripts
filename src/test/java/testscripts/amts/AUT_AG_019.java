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

public class AUT_AG_019 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AUT_AG_019() {
		
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-5071_Verify the absence of optimization rule applied folder and optimized folder in My files");
		
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homepage=new AlfrescoHomePage(scriptHelper);
		
		String assetFolder = dataTable.getData("MyFiles", "CreateFolder");
		String optimizedFolder = dataTable.getData("MyFiles", "CreateFileDetails");
				
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		//Check the absence of optimization rule applied folder  and optimized folder in My files 
		homepage.navigateToMyFilesTab();
		if(!sitesPage.Checkdocument(optimizedFolder))
		{
			report.updateTestLog("Verify optimized folder is not present in my files",
					"Optimized folder is not displayed in my files",
					Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify optimized folder is not present in my files",
					"Optimized folder is displayed in my files",
					Status.FAIL);
		}
		if(!sitesPage.Checkdocument(assetFolder))
		{
			report.updateTestLog("Verify Asset folder is not present in my files",
					"Asset folder is not displayed in my files",
					Status.PASS);
		}
		else
		{
			report.updateTestLog("Asset folder is not present in my files",
					"Asset folder is displayed in my files",
					Status.FAIL);
		}
	
	
	}

	@Override
	public void tearDown() {

	}

}
