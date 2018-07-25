package testscripts.objectmodel;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
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
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
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
public class AUT_AG_022 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_004()
	{
		testParameters.setCurrentTestDescription("[1]ALFDEPLOY-4956_Verify user is able to enter long desc field  up to 40000 characters without any issue<br>"+
	"[1]ALFDEPLOY-4956_Verify user is not able to enter long desc field with 40001 characters");
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
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		
		String fileNme = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		String aspectprop = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		String value1 = dataTable.getData("MyFiles", "CreateFileDetails");
		String value2 = dataTable.getData("MyFiles", "Version");
		
		String value = value1.concat(value2);
	
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		sitesPage.siteFinder(sourceSiteName);
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		myFiles.uploadFile(filePath, fileNme);
		
		 sitesPage.documentdetails(fileNme);
	        
	        docDetailsPageObj.performManageAspectsDocAction();
	        
	        docDetailsPageTestObj.verifyAspectsAvailable();
			docDetailsPageObj.addAspectsAndApllyChangesToAFile();
			
			
			docDetailsPageObj.commonMethodForPerformDocAction("Edit Properties");
			
		/*	sitesPage.enterIntoDocumentLibrary();
			sitesPage.clickOnEditProperties(fileNme);*/
			docDetailsPageObj.clickAllProperties();
			
			docDetailsPageObj.editInEditPropertiesTextarea("Long Description",value);
			collectionPg.clickOnSaveBtn();
			String ActualAspectTyped = appSearchPg.getMetadata(fileNme,
					"Long Description:");
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
