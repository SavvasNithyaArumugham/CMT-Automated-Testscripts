package testscripts.lifecycle;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_002 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_002()
	{
		testParameters.setCurrentTestDescription("1. Verify that user is able to see 'Attach Lifecycle' Option for any files from Document Detail Page"
				+ "<br>2. Verify that the user is able to 'Attach lifecycle State' for any files in the Document Library"
				+ "<br>3. Verify that user is able to see lifecycle state(includes Name and State) on the Document Library Page on attaching lifecycle state for files"
				+ "<br>4. Verify that user is able to see Options like PromoteLifecycle, Demote Lifecycle and Detach Lifecycle of any file after Attaching Lifecycle State from Document Actions"
				+ "<br>5. Verify that user is able to  Promote lifecycle State for any file in the Document Library"
				+ "<br>6. Verify that user is able to demote lifecycle State of any file in the document Library"
				+ "<br>7. Verify that user is able to  detach  lifecycle State at any stage that file belongs to");
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
		AlfrescoDocumentDetailsPageTest docDetailsPageTest = new AlfrescoDocumentDetailsPageTest(
				scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		homePageObj.navigateToSitesTab();
		
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
		
			String docActionVal = dataTable.getData("Document_Details", "DocumentActionName");
			docDetailsPageTest.verifyDocAction(docActionVal);
		
			docDetailsPageObj.commonMethodForPerformDocAction(docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			docDetailsPageObj.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			String changedLifecycleStateDocActions = dataTable.getData("Document_Details", "ChangedLifecycleStateActionNames");
		
			if(changedLifecycleStateDocActions.contains(","))
			{
				String splittedDocActions[] = changedLifecycleStateDocActions.split(",");
				for(String docActionName:splittedDocActions)
				{
					docDetailsPageTest.verifyDocAction(docActionName);
				}
			}
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
		
			docDetailsPageObj.backToFolderOrDocumentPage("");
		
			AlfrescoDocumentLibPageTest docLibPageTest = new AlfrescoDocumentLibPageTest(scriptHelper);
			docLibPageTest.verifyLifecycleTags(propertyValues, "LifeCycle", fileName);
			
			myFiles.openUploadedOrCreatedFile(fileName,"");
			docDetailsPageTest.verifyUploadedFileIsOpened(fileName,"");
			
			String lifecycleActionForPromote = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			docDetailsPageObj.commonMethodForPerformDocAction(lifecycleActionForPromote);
		
			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
			docDetailsPageObj.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
		
			String changedLifecyclePropValuesForPromote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForPromote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForPromote,"LifeCycle");
			
			String lifecycleActionForDemote = dataTable.getData("Document_Details", "LifecycleActionNameForDemote");
			docDetailsPageObj.commonMethodForPerformDocAction(lifecycleActionForDemote);
			
			String deomoteStateDropdownValue = dataTable.getData("Sites", "LifecycleDemoteStateDropdownValue");
			docDetailsPageObj.changeLifeCycleSate(deomoteStateDropdownValue);
			
			String changedLifecyclePropValuesForDeomote = dataTable.getData("Document_Details", "ChangedLifecyclePropValuesForDemote");
			docDetailsPageTest.verifyAttributesInPropertySec(changedLifecyclePropValuesForDeomote,"LifeCycle");
			
			String lifecycleActionForDetach = dataTable.getData("Document_Details", "LifecycleActionNameForDetach");
			docDetailsPageObj.commonMethodForPerformDocAction(lifecycleActionForDetach);
			
			String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
			docLibPgTest.verifyConfirmationDailogMessage(expectedMessageVal);
			
			docDetailsPageTest.verifyDocAction(docActionVal);
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}