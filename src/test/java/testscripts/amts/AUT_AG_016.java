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

public class AUT_AG_016 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AUT_AG_016() {
		
		testParameters
		.setCurrentTestDescription("ALFDEPLOY-5071_Verify the presence of lifecycle name-Image Optimization upon clicking Attach lifecycle for normal folder from More option or file preview page or selected items of Document library");
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

		
		
		String sourceSiteName = dataTable.getData("Sites", "InviteUserName");

		String moreSettingsOption = dataTable.getData("MyFiles", "MoreSettingsOption");
		String attachLifeCycleDropdownVal = dataTable.getData("Document_Details", "Title");
		String defaultPromoteStateVal=dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
		String[] folderNonOptimized=dataTable.getData("MyFiles", "Version").split(",");
		String[] folderDetails = dataTable.getData("MyFiles", "CreateFolder").split(";");;
		
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
	
		//Verify the presence of Parent folder
		if(sitesPage.documentAvailable(folderNonOptimized[0]))
		{
			sitesPage.documentdetails(folderNonOptimized[0]);		
		}
		else
		{
			myFiles.createFolder(folderDetails[0]);
			sitesPage.documentdetails(folderNonOptimized[0]);	
		}
		docLibPge.deleteAllFilesAndFolders();
		
		for(int i=1;i<folderNonOptimized.length;i++)
		{
			if(sitesPage.documentAvailable(folderNonOptimized[i]))
			{
             System.out.println("Folder"+" "+folderNonOptimized[i]+ "is available");
			}
			else
			{
				myFiles.createFolder(folderDetails[i]);

			}
	
				if(i%2!=0)
	
				{			
					//Perform attach lifecycle via more option
					sitesPage.clickOnMoreSetting(folderNonOptimized[i]);
					docLibPge.commonMethodForClickOnMoreSettingsOption(folderNonOptimized[i], moreSettingsOption);
					docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
					sitesPage.clickOnViewDetails(folderNonOptimized[i]);

					
					String ActualLifecycle = appSearchPg.getMetadata(folderNonOptimized[i],"Lifecycle Name:");
					
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
					String defaultState = appSearchPg.getMetadata(folderNonOptimized[i],
							"State:");

					if(defaultState.equals(defaultPromoteStateVal)) {
						report.updateTestLog("Verify Default LifeCycle State is displayed in Property section of document details Page",
								"DefaultLifeCycle State is successfully displayed in Property Section" + "<br/><b>LifeCycle Sate:</b>"
										+ defaultState,Status.PASS);
					}else {
						report.updateTestLog("Verify Default LifeCycle State is displayed in Property section of document details Page",
								"Default LifeCycle State is failed to display in Property Section" + "<br/><b>LifeCycle State:</b>"
										+ defaultState,Status.FAIL);
					}				
					
				}
				else
				{
					System.out.println("It is even");
				}
				docDetailsPg.backToFolderOrDocumentPage(folderNonOptimized[i]);
			
			}
			
		}
		

	@Override
	public void tearDown() {

	}

}