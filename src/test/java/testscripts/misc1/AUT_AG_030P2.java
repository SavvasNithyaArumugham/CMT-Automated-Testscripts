package testscripts.misc1;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_030P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_030()
	{
		testParameters.setCurrentTestDescription("Verify if sitemanager is able to become owner of multiple files or folders created by other users<br> - Part2:Check the presence of 'Become owner' option");
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

		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPageTest sitePageTest = new AlfrescoSitesPageTest(scriptHelper);

		String siteName = dataTable.getData("Sites", "SiteName");
		
		homePageObj.navigateToSitesTab();

		sitesPage.openSiteFromRecentSites(siteName);

		sitesPage.enterIntoDocumentLibrary();
		
		String selectDropdownItemType = dataTable.getData("MyFiles", "SelectDropdownValue");
		String selectedItemMenuOptVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
		
		String selctDropdownValues[]=null;
		if(selectDropdownItemType.contains(","))
		{
			selctDropdownValues = selectDropdownItemType.split(",");
		}
		
		docLibPg.selectDocumentLibItems(selctDropdownValues[0]);
		
		sitesPage.clickOnSelectedItems();
		
		sitePageTest.verifySelectedItemsMenuOptionForNegativeCase(selectedItemMenuOptVal);
		
		docLibPg.selectDocumentLibItems(selctDropdownValues[1]);
		
		docLibPg.selectDocumentLibItems(selctDropdownValues[2]);
		
		sitesPage.clickOnSelectedItems();
		
		sitePageTest.verifySelectedItemsMenuOptionForNegativeCase(selectedItemMenuOptVal);
		
		docLibPg.selectDocumentLibItems(selctDropdownValues[1]);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}