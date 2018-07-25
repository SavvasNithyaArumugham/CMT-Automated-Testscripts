package testscripts.objectmodel;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kumar Salla
 *
 */
public class AUT_AG_017P4 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_017()
	{
		testParameters.setCurrentTestDescription("1.Verify ALT text and Long Desc fields are added when Content Asset Aspect is added via Manage Aspect for a file created by self or others -Manager.Also Input the fields added <br>"
				+"2.Verify ALT text and Long Desc fields are added when Content Asset Aspect is added via Manage Aspect for a file created by self or others -Collaborator.Also Input the fields added<br>"
				+"3. Verify ALT text and Long Desc fields are added when Content Asset Aspect is added via Manage Aspect for a folder created by self or others -Coordinator.Also Input the fields added<br>"
				+"4. Verify ALT text and Long Desc fields are added when Content Asset Aspect is added via Manage Aspect for a folder created by self -Contributor.Also Input the fields added<br>"
				+"Part 1:: Validate Coordinator Role");
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
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		

	
		String aspectName = dataTable.getData("Document_Details", "AspectName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);

		String siteName=sitesPage.getCreatedSiteName();
		
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
	
		myFiles.createFolder(folderDetails);
		  
		sitesPage.clickOnViewDetails(folderName);
		
		docDetailsPage.performManageAspectsDocAction();
		docDetailsPage.addAspectsAndApllyChangesToAFile();
		
		docDetailsPage.performManageAspectsDocAction();
		docDetailsPageTestObj.verifyAppliedAspects(aspectName);
		sitesPage.clickOnCancelBtnInManageAspectsPopup();
		
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnEditProperties(folderName);
		docDetailsPage.clickAllProperties();
		
		docDetailsPage.enterDataAndSaveIntoEditProperties();
		
		 sitesPage.clickOnViewDetails(folderName);
		
		docDetailsPageTestObj.verifyAttributeAndValuesInDocProperties();
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
