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
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_440 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void newRelationshipFromSiteDashletFrench()
	{
		testParameters.setCurrentTestDescription("1.ALFDEPLOY-5164_24_Verify all the labels and button displayed in French language once dashet is added"
				    +"<br>2.ALFDEPLOY-5164_28_Verify the help message in french language in site relationship dashlet" 
				    +"<br>3.ALFDEPLOY-5164_23_Verify lables and button in new realtionship popup window via Document details page also perfrom user is able to add relationship for file");
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
		AlfrescoDocumentDetailsPage detailsPg=new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentDetailsPageTest docDetailsPageTest =new AlfrescoDocumentDetailsPageTest(scriptHelper);
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(scriptHelper);
		String siteToSelect=dataTable.getData("Sites", "SiteToSelect");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails=dataTable.getData("MyFiles", "CreateFolder");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String relationshipName = dataTable.getData("MyFiles", "RelationshipName");
		String loadingMsg=dataTable.getData("MyFiles", "BrowseActionName");
		String [] tempExpVal=dataTable.getData("MyFiles", "PopUpMsg").split(",");
		ArrayList<String> expVal=new ArrayList<String>(Arrays.asList(tempExpVal));
		String expectedMsg=dataTable.getData("MyFiles", "SelectDropdownValue");
		String dashletName=dataTable.getData("Home", "DashletName");
		String[] flow=dataTable.getData("MyFiles", "CreateChildFolder").split(",");
	    String actualMsg;
	    String[] selectValue=dataTable.getData("MyFiles", "Version").split(",");
	    
	    sitesPage.siteFinder(siteToSelect);
		sitesPage.enterIntoDocumentLibrary();
		docLibPage.deleteAllFilesAndFolders();
		myFiles.createFolder(folderDetails);
		myFiles.uploadFile(filePath, fileName);
		
	    sitesPage.siteFinder(siteNameValue);
	    if (!sitesDashboardPg.isSiteRelationshipDashletAdded()) {
	    	sitesDashboardPg.customizeSiteDashboard();
	    	sitesDashboardPg.addDashletInCustomDashBoard();
		} 

        actualMsg=sitesDashboardPg.getHelpContent(dashletName);
	    if(!actualMsg.isEmpty() && actualMsg.equalsIgnoreCase(expectedMsg)) {
	    	report.updateTestLog("Verify Help message","Help message same as expected <br><b>Expected</b>"+expectedMsg+"<br><b>Actual</b>"+actualMsg, Status.PASS);
	    }else {
	    	report.updateTestLog("Verify Help message","Help message not same as expected <br><b>Expected</b>"+expectedMsg+"<br><b>Actual</b>"+actualMsg, Status.FAIL);
	    }
	    
	    // Verify the French translation in Dashlet page
	    sitesDashboardPg.clickAddRelationshipFromSiteRelationshipDashlet();
	    docDetailsPageTest.verifyRelationShipFields(expVal);
	    sitesPage.clickOnCancelButtonInNewRelationship();
	    
	    // Verify relationships added for a site with folder,file and site.
	    if(detailsPg.isRelationshipAddedForSite()) {
	    	sitesDashboardPg.deleteAllRelationship();
	    }
	    
	    for(int i=0;i<flow.length;i++) {
	    	sitesDashboardPg.clickAddRelationshipFromSiteRelationshipDashlet();
		    sitesPage.clickOnAddRelationshipButtonInNewRelationShip(relationshipName);
		    sitesPage.clickOnNavigatorItemInSelectPrompt("Sites");
		    if(i==(Integer.parseInt(flow[0]))){
		    	UIHelper.waitFor(driver);
		    	sitesPage.selectFromLeftPanelofSelectPrompt(siteToSelect,loadingMsg);
		    }else  if(i==(Integer.parseInt(flow[1])) || i==(Integer.parseInt(flow[2]))) {
		    	sitesPage.navigateInsideLeftPanelofSelectPrompt(siteToSelect,loadingMsg);
		    	sitesPage.navigateInsideLeftPanelofSelectPrompt("documentLibrary",loadingMsg);
		    	sitesPage.selectFromLeftPanelofSelectPrompt(selectValue[i],loadingMsg);
		    }else {
		    	report.updateTestLog("Select relationship file/folder/site", "mismatch in select data for relationship",Status.FAIL);
		    }
		    UIHelper.waitFor(driver);
		    sitesPage.clickOnOkButtonInSelectPromptForNewRelationship();
			sitesPage.clickOnCreateButtonInNewRelationship();
			UIHelper.waitFor(driver);
			if(detailsPg.getRelationshipValueFromList(selectValue[i]).contains(selectValue[i])) {
				report.updateTestLog("Verify the message for relationship", "Relationship added successfully",Status.PASS);
			}else {
				report.updateTestLog("Verify relationship added", "Relationship not added",Status.FAIL);
			}
		    
	    }
	    
	    
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}
