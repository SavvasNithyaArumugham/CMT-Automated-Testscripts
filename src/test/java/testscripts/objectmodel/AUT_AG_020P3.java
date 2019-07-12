package testscripts.objectmodel;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPCSPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_020P3 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_004()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-4207_Verify user with consumer role can view the metadata properties for a video file with clippable aspect<br>"+
	"ALFDEPLOY-4207_Verify user should not see clippable aspect in aspects selection window while click on manage aspect for a video file already having clippable aspect<br>"+
				"ALFDEPLOY-4207_Verify user able to remove clippable aspect from currently selected tab in acpect selection window<br>"+
	"ALFDEPLOY-4207_Verify user able to download metadata template using selected items drop down in search window for video files with clippable aspect<br>"+
				"ALFDEPLOY-4207_Verify user able to download All metadata template using selected items drop down in search window for video files with clippable aspect<br>"+
"ALFDEPLOY-4207_Verify user able to perform advance search using clippable aspect");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}
	
	@Override
	public void setUp()
	{
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		
	
		
		String fileName = dataTable.getData("MyFiles", "FileName");
		String fileName1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String aspectprop = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "CreateSiteTypeValues");
		String comments = dataTable.getData("Search", "CommentsForBulkDownload");
		new FileUtil().clearFile("src/test/resources/AppTestData/TestOutput/ConfMessages.txt");
		String expectedMessageVal = dataTable.getData("Sites", "CreateSiteLabelNames");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		
		functionalLibrary.loginAsValidUser(signOnPage);	
	
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteName);		
		sitesPage.enterIntoDocumentLibrary();
        sitesPage.documentdetails(fileName);
 
		String ActualAspectType = appSearchPg.getMetadata(fileName,
				"Clips:");

		if(ActualAspectType.equals(aspectprop)) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
							+ ActualAspectType,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
							+ aspectprop,
					Status.FAIL);
		}
	
		
		homePage.navigateToMyFilesTab();	
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName1);
	    sitesPage.documentdetails(fileName1);   
	    docDetailsPageObj.performManageAspectsDocAction();     
	    docDetailsPageTestObj.verifyAspectsAvailable();
	    docDetailsPageObj.addAspectsAndApllyChangesToAFile();
	    String ActualAspectType2 = appSearchPg.getMetadata(fileName1,
				"Clips:");

		if(ActualAspectType2.equals(aspectprop)) {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
							+ ActualAspectType2,
					Status.PASS);
		}else {
			report.updateTestLog("Verify Properties in document details Page",
					"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
							+ aspectprop,
					Status.FAIL);
		}
		
		homePageObj.navigateToAdvSearch();
		appSearchPg.fileNamesearch();	
		searchTestObj.commonMethodForVerifySearchResults(fileName1);		
		appSearchPg.commonMethodForPerformSelectedItemsOperation(fileName1, selectedItemMenuOptVal);				
		appSearchPg.performBulkOrMetadataTemplateDownload(comments+" via normal search");		
		searchTestObj.verifyConfirmationDailogMessage(expectedMessageVal);
		
		homePage.navigateToMyFilesTab();
		
		docDetailsPageObj.performManageAspectsDocAction();	
		docDetailsPageObj.commonMethodForDeleteAspects(aspectNames);

	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
