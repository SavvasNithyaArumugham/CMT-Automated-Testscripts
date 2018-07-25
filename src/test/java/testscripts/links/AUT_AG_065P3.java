package testscripts.links;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_065P3 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LINKS_017()
	{
		testParameters.setCurrentTestDescription("Verify that 'Link to' option is visible under 'Selected item' drop down with collaborator role on selecting a file");
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
		
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	
		
		String siteName=sitesPage.getCreatedSiteName();
		sitesPage.siteFinder(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		docLibPg.selectAllItems("File");
		
		sitesPage.clickOnSelectedItems();
	
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
	
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.selectAllItems("Folders");
		
		sitesPage.clickOnSelectedItems();
	
		sitePageTest.verifySelectedItemsMenuOption(selectedItemMenuOptVal);
		
	/*	sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(fileName);
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		if(docDetailsPage.isDocActionMenuAvailable(selectedItemMenuOptVal)){
			report.updateTestLog("Verify the 'Link To' option in Document Actions Menu",
					"Option available for Consumer role", Status.PASS);
		}else{
			report.updateTestLog("Verify the 'Link To' option in Document Actions Menu",
					"Option not available for Consumer role", Status.FAIL);
		}
		*/
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}