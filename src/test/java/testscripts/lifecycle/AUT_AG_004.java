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
public class AUT_AG_004 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_004()
	{
		testParameters.setCurrentTestDescription("Verify that user is able is not able to promote lifecycle state when file is in last lifecycle stage from Document Actions");
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
		
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
			docDetailsPageTest.verifyDocAction(docActionVal);
		
			AlfrescoDocumentDetailsPage docDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			docDetailsPg.commonMethodForPerformDocAction(docActionVal);
		
			String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
			docDetailsPg.changeLifeCycleSate(attachLifeCycleDropdownVal);
		
			String propertyValues = dataTable.getData("Document_Details", "DocPropertyValues");
			docDetailsPageTest.verifyAttributesInPropertySec(propertyValues,"LifeCycle");
		
			String lifecycleAction = dataTable.getData("Document_Details", "LifecycleActionNameForPromote");
			String promotOrDemoteOrDetachStateDropdownValue = dataTable.getData("Sites", "LifecyclePromoteStateDropdownValue");
			
			if(promotOrDemoteOrDetachStateDropdownValue.contains(","))
			{
				String splittedPromoteDropdownVals[] = promotOrDemoteOrDetachStateDropdownValue.split(",");
				for(String promoteDropVal:splittedPromoteDropdownVals)
				{
					docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
					docDetailsPg.changeLifeCycleSate(promoteDropVal);
				}
			}
			else
			{
				docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
				docDetailsPg.changeLifeCycleSate(promotOrDemoteOrDetachStateDropdownValue);
			}
			
			docDetailsPg.commonMethodForPerformDocAction(lifecycleAction);
			
			String expectedMessageVal = dataTable.getData("Sites", "ExpectedConfirmationMessage");
			docLibPgTest.verifyConfirmationDailogMessage(expectedMessageVal);

			docDetailsPageObj.backToFolderOrDocumentPage("");
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}