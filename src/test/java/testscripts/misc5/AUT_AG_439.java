package testscripts.misc5;

import java.util.ArrayList;
import java.util.Arrays;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
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


public class AUT_AG_439 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void newRelationshipFromMorOptFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_21_Verify labels and button in select window pop while performing add relationship via Document library page for folder"
				    +"<br>2.ALFDEPLOY-5164_26_Verify the user message Relationship cannt be created with in french language while trying to add relationship via document library document actions & home page dashlet"
				    +"<br>3.ALFDEPLOY-5164_29_Verify Please select a valid target location message in french for add relationship" );
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
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPageTest= new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest =new AlfrescoDocumentDetailsPageTest(scriptHelper);
		String siteToSelect=dataTable.getData("Sites", "SiteToSelect");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folder1Details=dataTable.getData("MyFiles", "CreateFolder");
		String folder2Details=dataTable.getData("MyFiles", "CreateFileDetails");
		String moreSettingsOption=dataTable.getData("MyFiles", "MoreSettingsOption");
		String [] tempExpVal=dataTable.getData("MyFiles", "PopUpMsg").split(",");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		ArrayList<String> expVal=new ArrayList<String>(Arrays.asList(tempExpVal));
		String[] expMsg=dataTable.getData("MyFiles", "Sort Options").split(",");
		String[] folderNames=dataTable.getData("MyFiles", "Version").split(",");
		String loadingMsg=dataTable.getData("MyFiles", "BrowseActionName");
		String[] expectedMsgcount=dataTable.getData("MyFiles", "CreateChildFolder").split(",");
		
		sitesPage.siteFinder(siteToSelect);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folder1Details);
		
		
		sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folder2Details);
		
		// Add relationship to a folder of different site and repeating the same to verify the error message
		for(int i=0;i<expMsg.length;i++) {
		   if(i==(Integer.parseInt(expectedMsgcount[0])) || i==(Integer.parseInt(expectedMsgcount[1])) ) {
			   sitesPage.clickOnMoreSetting(folderNames[1]);
			   sitesPage.commonMethodForClickOnMoreOptionLink(folderNames[1], moreSettingsOption);
			   docDetailsPageTest.verifyRelationShipFields(expVal);	
			   sitesPage.clickOnAddRelationshipButtonInNewRelationShip(relationshipName);
			   UIHelper.waitFor(driver);
			   sitesPage.clickOnNavigatorItemInSelectPrompt("Sites");
			   sitesPage.navigateInsideLeftPanelofSelectPrompt(siteToSelect,loadingMsg);
			   sitesPage.navigateInsideLeftPanelofSelectPrompt("documentLibrary",loadingMsg);
			   sitesPage.selectFromLeftPanelofSelectPrompt(folderNames[0],loadingMsg);
			   sitesPage.clickOnOkButtonInSelectPromptForNewRelationship();
			   sitesPage.clickOnCreateButtonInNewRelationship();
			   docLibPageTest.verifyLoadingMessage(expMsg[i]);
			   UIHelper.waitFor(driver);
			   if(i==(Integer.parseInt(expectedMsgcount[0]))) {
				   sitesPage.clickOnViewDetails(folderNames[1]);
				   docDetailsPageTest.verifyAddedRelationshipData(folderNames[0]);
				   sitesPage.enterIntoDocumentLibrary();
			   }
		   }else if(i==(Integer.parseInt(expectedMsgcount[2]))) {
			// Verify error for select a valid target location
		    	sitesPage.clickOnMoreSetting(folderNames[1]);
		    	sitesPage.commonMethodForClickOnMoreOptionLink(folderNames[1], moreSettingsOption);
		    	sitesPage.clickOnAddRelationshipButtonInNewRelationShip(relationshipName);
			    UIHelper.waitFor(driver);
			    sitesPage.clickOnNavigatorItemInSelectPrompt("Company Home");
			    sitesPage.selectFromLeftPanelofSelectPrompt("Sites",loadingMsg);
			    sitesPage.clickOnOkButtonInSelectPromptForNewRelationship();
			    docLibPageTest.verifyLoadingMessage(expMsg[i]);
		   }else {
			    report.updateTestLog("Verify the message for relationship", "Verification limited not in sync",Status.FAIL);
		   }
		
		}
		
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
