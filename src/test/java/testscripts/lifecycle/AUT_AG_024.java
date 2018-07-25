package testscripts.lifecycle;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_024 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void LIFECYCLE_024()
	{
		testParameters.setCurrentTestDescription("Verify whether user is able to sort assets based on life cycle status in document library");
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
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		homePageObj.navigateToSitesTab();
		
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteName = dataTable.getData("Sites", "SiteName");
		sitesPage.openSiteFromRecentSites(siteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");		
		String fldrList = dataTable.getData("MyFiles", "FileName");
		String attachLifeCycleDropdownVal = dataTable.getData("Sites", "LifeCycleDropdownValue");
		String sortDrpDwnVal = dataTable.getData("MyFiles", "Sort Options");
		//String splitVal[] = new String[3]; 
		//splitVal =	fldrList.split(",");
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		docLibPge.deleteAllFilesAndFolders();
        myFiles.createFolder(folderDetails);
        AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);		
		sitesPage.enterIntoDocumentLibrary();	
		
		sitesPage.attachLifeCycle(attachLifeCycleDropdownVal);
		sitesPage.verifyAttachedLifeCycle(fldrList, attachLifeCycleDropdownVal);
		
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.clickSortDirectBtn();
		
		docLibPge.slctSortOptFromDocLib(sortDrpDwnVal);
		
		AlfrescoDocumentLibPageTest docLibTest = new AlfrescoDocumentLibPageTest(
				scriptHelper);	
		docLibTest.verifyFileSortedAsLfeCycle(fldrList);
		
		
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}