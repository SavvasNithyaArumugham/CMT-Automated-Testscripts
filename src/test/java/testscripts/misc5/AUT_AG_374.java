package testscripts.misc5;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoShareboxPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DateUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_374 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC5_03()
	{
		
		testParameters.setCurrentTestDescription("1.Able to preview the Abobe illustrator file from the document library in the destination folder after the file is copied to another repository."
				+"<br> 2. Able to preview the Abobe illustrator file from the document library in the destination folder after the relationship is applied to the file."
				+"<br> 3. Able to preview the Abobe illustrator file from the document library in the after workflow is assigned."
				+"<br>3.Able to preview the Abobe illustrator file from the document library in the destination folder after the file gets moved.");
		
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
		functionalLibrary.loginAsValidUser(signOnPage);
		

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		String folderName = dataTable.getData("MyFiles", "CreateFolder");
		String folderDetails = dataTable.getData("MyFiles", "Version");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		String edit = dataTable.getData("Sites", "InviteUserName");

		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPge.deleteAllFilesAndFolders();
		
		
		String fileName = dataTable.getData("MyFiles", "FileName");

		String filePath = dataTable.getData("MyFiles", "FilePath");
		myFiles.createFolder(folderDetails);
	
		myFiles.uploadFileInMyFilesPage(filePath, fileName);

		sitesPage.clickOnMoreSetting(fileName);
	
		docLibPge.commonMethodForClickOnMoreSettingsOption(fileName, edit);
		
		AlfrescoWorkflowPage wfPageObj = new AlfrescoWorkflowPage(scriptHelper);
		String taskType = dataTable.getData("Workflow", "Tasktype");
		String Message = dataTable.getData("Workflow", "Message");
		String DueDate = dataTable.getData("Workflow", "DueDate");
		String Priority = dataTable.getData("Workflow", "Priority");
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = sdf.format(date);
		
		String Msg = Message + "_" + strDate;
		
	//	wfPageObj.startWorkflow();
		wfPageObj.worflowInput(taskType, Msg, DueDate, Priority);
		wfPageObj.selectReviewer();
		wfPageObj.clickstartWorkflowBtn();
		
		sitesPage.documentdetails(fileName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
		
		sitesPage.enterIntoDocumentLibrary();
		
		/*String docActionName = dataTable.getData("MyFiles", "BrowseActionName");
	
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
				docActionName);
		
		docDetailsPageObj.copytoRepository("Recent Sites",siteName, folderName);
		
		
	
		
		//docDetailsPageTest.verifyCreatedFile(fileName, "");
		
		sitesPage.enterIntoDocumentLibrary();
		*/
		sitesPage.clickOnMoreSetting(fileName);
		docLibPg.commonMethodForClickOnMoreSettingsOption(fileName,
				moreSettingsOptionName);

		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		sitesPage.addRelationship(relationshipName, folderName);
				
		myFiles.openAFile(fileName);
		
		docDetailsPageTest.verifyAddedRelationshipData(folderName);
		
		docDetailsPageTest.verifyCreatedFile(fileName, "");
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}