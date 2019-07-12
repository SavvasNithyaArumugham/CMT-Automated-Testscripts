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
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_020P2 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_004()
	{
		testParameters.setCurrentTestDescription("ALFDEPLOY-4207_Verify user able to add relationship for a video file with clippable aspect<br>"+
	"ALFDEPLOY-4207_Verify user able to start workflow for a video file with clippable aspect<br>"+
				"ALFDEPLOY-4207_Verify user able to apply clippable aspect for a video file in sharedfiles<br>"+
	"ALFDEPLOY-4207_Verify user with collaborator role can view the metadata properties for a video file with clippable aspect");
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
	
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String aspectprop = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String destinationFolder = dataTable.getData("Sites", "TargetFolder");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		homePageObj.navigateToSitesTab();
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
		docDetailsPageObj.commonMethodForPerformDocAction("Start Workflow");
	

		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		
		docDetailsPageObj.commonMethodForPerformDocAction("Add Relationship");
		
		sitesPage.addRelationship(relationshipName, destinationFolder);

		docDetailsPageTestObj.verifyAddedRelationshipData(destinationFolder);
	
	    homePage.navigateToSharedFilesTab();
		UIHelper.waitForPageToLoad(driver);
		UIHelper.waitFor(driver);
		
	
		if(sitesPage.Checkdocument(destinationFolder)){
			sitesPage.documentdetails(destinationFolder);
			docLibPage.deleteAllFilesAndFolders();
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
		}else{
			myFiles.createFolder(folderDetails);
			sitesPage.documentdetails(destinationFolder);
			myFiles.uploadFileInMyFilesPage(filePath, fileName);
			
		}
		
		//myFiles.uploadFileInMyFilesPage(filePath, fileName);
		
	       sitesPage.documentdetails(fileName);
	        
	        docDetailsPageObj.performManageAspectsDocAction();
	        
	        docDetailsPageTestObj.verifyAspectsAvailable();
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
		
			String ActualAspectType1 = appSearchPg.getMetadata(fileName,
					"Clips:");

			
			
			if(ActualAspectType1.equals(aspectprop)) {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are successfully displayed in Property Section" + "<br/><b>Property Values:</b>"
								+ ActualAspectType1,
						Status.PASS);
			}else {
				report.updateTestLog("Verify Properties in document details Page",
						"Properties are failed to display in Property Section" + "<br/><b>Property Values:</b>"
								+ aspectprop,
						Status.FAIL);
			}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
