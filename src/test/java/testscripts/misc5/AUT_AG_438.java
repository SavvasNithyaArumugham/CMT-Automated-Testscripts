package testscripts.misc5;

import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_438 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void newRelationshipFromMorOptFrench()
	{
		testParameters.setCurrentTestDescription("1.Verify lables and button in new realtionship popup window via Document library page alos perfrom user is able to add relationship for file"
				    +"<br>2.Verify the user message Cannot Create Relationship. You have selected same file in french language while trying to add relationship via document library, document actions &  home page dashlet"
				   );
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
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest= new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest =new AlfrescoDocumentDetailsPageTest(scriptHelper);
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String[] fileName = dataTable.getData("MyFiles", "FileName").split(",");
		String moreSettingsOption=dataTable.getData("MyFiles", "MoreSettingsOption");
		String [] tempExpVal=dataTable.getData("MyFiles", "PopUpMsg").split(",");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		ArrayList<String> expVal=new ArrayList<String>(Arrays.asList(tempExpVal));
		String[] expMsg=dataTable.getData("MyFiles", "Sort Options").split(",");
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.uploadFileInMyFilesPage(filePath, fileName[0]);
		myFiles.uploadFileInMyFilesPage(filePath, fileName[1]);
		
		// Create relationship  from more options
		sitesPage.clickOnMoreSetting(fileName[0]);
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], moreSettingsOption);
		docDetailsPageTest.verifyRelationShipFields(expVal);
		sitesPage.addRelationshipForMessageCheck(relationshipName, fileName[1]);
		docLibPageTest.verifyLoadingMessage(expMsg[0]);
		UIHelper.waitFor(driver);
		myFiles.openAFile(fileName[0]);
		docDetailsPageTest.verifyAddedRelationshipData(fileName[1]);
		
		// Cannot Create Relationship message from doc details
		docDetailsPage.clickDocAction(moreSettingsOption);
		sitesPage.addRelationshipForMessageCheck(relationshipName, fileName[0]);
		docLibPageTest.verifyLoadingMessage(expMsg[1]);
		UIHelper.waitFor(driver);
		sitesPage.clickOnCancelButtonInNewRelationship();
		
		// Cannot Create Relationship message from doc lib
		sitesPage.enterIntoDocumentLibrary();
		sitesPage.clickOnMoreSetting(fileName[0]);
		sitesPage.commonMethodForClickOnMoreOptionLink(fileName[0], moreSettingsOption);
		sitesPage.addRelationshipForMessageCheck(relationshipName, fileName[0]);
		docLibPageTest.verifyLoadingMessage(expMsg[1]);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
