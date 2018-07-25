package testscripts.misc1;

import java.util.StringTokenizer;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoWorkflowPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_060 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_050()
	{
		testParameters.setCurrentTestDescription("User should be able to calculate folder size for the folder present in 'Document Library','My Files','Shared Files','Repository', etc. as per user access in the alfresco.");
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
		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String workflowFrom = dataTable.getData("Workflow", "DoWorkflowFrom");
		StringTokenizer token = new StringTokenizer(workflowFrom, ",");
		
		while (token.hasMoreElements()) {
			String actionToDo = token.nextElement().toString();
			switch (actionToDo) {
			
			case "DocumentLibrary":
				sitesPage.openSiteFromRecentSites(siteNameValue);
				sitesPage.enterIntoDocumentLibrary();
				createFolderAndCalculateSize(folderDetails, folderName, actionToDo);
				break;
			
			case "MyFiles":
				homePage.navigateToMyFilesTab();
				createFolderAndCalculateSize(folderDetails, folderName, actionToDo);
				break;
			
			case "SharedFiles":
				homePage.navigateToSharedFilesTab();
				createFolderAndCalculateSize(folderDetails, folderName, actionToDo);
				break;
				
			case "Repository":
				homePage.navigateToRepositoryTab();
				sitesPage.openFolder("Sites");
				AlfrescoWorkflowPage workFlowPage = new AlfrescoWorkflowPage(scriptHelper);
				workFlowPage.selectDocumentViewType("Name");
				sitesPage.openFolder(siteNameValue.toLowerCase());
				sitesPage.openFolder("documentLibrary");
				createFolderAndCalculateSize(folderDetails, folderName, actionToDo);
				break;
				
			default:					
				break;
			}
		}
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
	
	private void doCalculateSize(String type){
		try{
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper);
			
			String docActionVal = dataTable.getData("Sites", "SelectedItemsMenuOption");
			sitesPage.clickViewDetails();
	        docDetailsPage.clickDocAction(docActionVal);
	        
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForPageToLoad(driver);
			String docProperty = dataTable.getData("Document_Details", "DocProperties");
	        if(docDetailsPage.isDocumentPropertyAvailable(docProperty)){
	        	report.updateTestLog("Verify 'Size' property in "+type,
						"Property verified successfully"
								+ "</br><b>Property Verified:</b> " + docProperty,
						Status.PASS);
	        }else{
	        	report.updateTestLog("Verify 'Size' property in "+type,
						"Property not verified"
								+ "</br><b>Property Verified:</b> " + docProperty,
						Status.FAIL);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void createFolderAndCalculateSize(String folderDetails, String folderName, String actionToDo){
		try{
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			if(sitesPage.Checkdocument(folderName)){
				doCalculateSize(actionToDo);
			}else{
				myFiles.createFolder(folderDetails);
				UIHelper.waitFor(driver);
				doCalculateSize(actionToDo);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}