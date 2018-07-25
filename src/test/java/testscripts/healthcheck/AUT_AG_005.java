package testscripts.healthcheck;

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
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_005 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_001()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is not able to see detach option from Selected Items Menu if he select any non-attached lifecycle state file"
				+ "<br>2. Verify that user is able to see 'Attach Lifecycle' Option for any files from Selected Items dropdown"
				+ "<br>3. Verify that user is able to see detach option from Selected Items Menu when he selects only attached lifecycle state files"
				+ "<br>4. Verify that the user is able to 'Attach lifecycle State' from the selected Items dropdown"
				+ "<br>5. Verify that options like Promote and demote are not available on the 'Selected Items' Dropdown on attaching lifecycle state to any folder and files");
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
		
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoSitesPageTest sitePgTestObj = new AlfrescoSitesPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");

		ArrayList<String> createdFileNames = myFilesTestObj
				.getCreatedFileNames(fileDetails);

		for (String fileName : createdFileNames) {
			myFiles.deleteUploadedFile(fileName);

			myFiles.createFile(fileDetails);
			
			docDetailsPageTest.verifyCreatedFile(fileName, "");

			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			sitesPage.clickOnSelectedItems();
			
			String lifecycleOptName = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
			sitePgTestObj.verifySelectedItemsMenuOptionForNegativeCase(lifecycleOptName);
			
			//docDetailsPageObj.unCheckTheSelectedFile(fileName);
		
			//myFiles.selectMultipleFilesOrFolders(fileName);
			//docLibPg.selectAllFilesAndFolders();
			
			//sitesPage.clickOnSelectedItems();
			
			String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
			sitePgTestObj.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
			AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);

			docLibPg.commonMethodForClickOnSelectedItemsMenuOption(selectedItemMenuOptVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			myFiles.openUploadedOrCreatedFile(fileName,"");
		
			docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
			
			docDetailsPageObj.backToFolderOrDocumentPage("");
			
			//docDetailsPageObj.unCheckTheSelectedFile(fileName);
			
			//myFiles.selectMultipleFilesOrFolders(fileName);
			
			sitesPage.clickOnSelectedItems();
			
			sitePgTestObj.verifySelectedItemsMenuOption(lifecycleOptName);
			
			myFilesTestObj.verifyLifeCyclePromoteAndDemoteOptions();
		}
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}