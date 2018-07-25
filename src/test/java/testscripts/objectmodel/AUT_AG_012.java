package testscripts.objectmodel;

import java.util.ArrayList;

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
public class AUT_AG_012 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void objectmodel_012()
	{
		testParameters.setCurrentTestDescription("1. Verify that User is able to see the existing custom aspects to any folder :- "
				+"<br> a) Program"
				+"<br> b) Program Component"
				+"<br> c) Product Store Component"
				+"<br> d) QS Product Store Component"
				+"<br> e) Content Asset"
				+"<br> f) ISBN"
				+ "<br>2. Verfiy user is able to view new aspect 'Archive Aspect' aspect for any folder present in the site"
				+ "<br>3. Verfiy user is able to provide values for all the attributes of Archive Aspect aspect"
				+ "<br>4. Verfiy user is able to apply 'Archive Aspect' aspect in My Files/Shared Files section by hovering on folder."
				+ "<br>5. Verfiy that 'Author' Field is renamed as 'Contributors' Field for any file or folder when 'Archive' Aspect is appiled");
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTestObj = new AlfrescoDocumentDetailsPageTest(scriptHelper);
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String aspectNames = dataTable.getData("Document_Details", "AspectName");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPage.deleteAllFilesAndFolders();
		
        myFiles.createFolder(folderDetails);
		
		ArrayList<String> folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnViewDetails(folderName);
			
			docDetailsPage.performManageAspectsDocAction();
			
			
			
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			
			docDetailsPage.performManageAspectsDocAction();
			
			docDetailsPageTestObj.verifyListOfAspectsAvailable(aspectNames);
			
			sitesPage.enterIntoDocumentLibrary();
			
			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();
			
			docDetailsPageTestObj.verifyAllEditProperties();
			
			docDetailsPage.enterDataAndSaveIntoEditProperties();
			
			sitesPage.clickOnViewDetails(folderName);
			
			docDetailsPageTestObj.verifyAttributeAndValuesInDocProperties();
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();
		
        myFiles.createFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{			
			sitesPage.clickOnMoreSetting(folderName);
			
			sitesPage.clickOnMoreOptionLink(folderName);
			
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			
			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();
			
			docDetailsPageTestObj.verifyRenamedField("Author","Contributors");
			
			docDetailsPage.enterDataAndSaveIntoEditProperties();
			
			sitesPage.clickOnViewDetails(folderName);
			
			docDetailsPageTestObj.verifyAttributeAndValuesInDocProperties();
		}
		
		homePage.navigateToMyFilesTab();
		
		docLibPage.deleteAllFilesAndFolders();
		
        myFiles.createFolder(folderDetails);
		
		for(String folderName:folderNamesList)
		{
			sitesPage.clickOnMoreSetting(folderName);
			
			sitesPage.clickOnMoreOptionLink(folderName);
			
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			
			sitesPage.clickOnEditProperties(folderName);
			docDetailsPage.clickAllProperties();
			
			docDetailsPageTestObj.verifyAllEditProperties();
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
