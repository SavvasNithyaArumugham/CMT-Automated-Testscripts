package testscripts.search;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_034 extends TestCase{
	
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_033()
	{
		testParameters.setCurrentTestDescription("Verify that Folder breadcumb link is correct when user navigates from search Result Page");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		signOnPage = functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPageObj = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentLibPageTest docLibPgTest = new AlfrescoDocumentLibPageTest(scriptHelper);
		
		homePage.navigateToSitesTab();
		
		String sourceSiteName = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(sourceSiteName);
		
		sitesPage.enterIntoDocumentLibrary();
		
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		
		try{
			docLibPg.deleteAllFilesAndFolders();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int startVal=1;
		String previousFolderName="";
		if (folderDetails.contains(";")) {
			String splittedFolderDetailas[] = folderDetails.split(";");
			for (String folderValues : splittedFolderDetailas) {
				String splittedFolderValues[] = folderValues.split(",");

				String folderName = splittedFolderValues[0].replace(
						"FolderName:", "");
				String folderTitle = splittedFolderValues[1].replace(
						"Title:", "");
				String folderDescription = splittedFolderValues[2].replace(
						"Description:", "");

				myFiles.commonMethodForCreateFolder(folderName,
						folderTitle, folderDescription);
				
				myFiles.commonMethodForCreateFile("Plain Text", "AutomationFile"+startVal, "Auto Test Demo"+startVal,
						"AutoTextFile"+startVal, "FileDesc"+startVal);

				if(startVal==1)
				{
					myFiles.backToFolderOrDocumentPage();
				}
				else
				{
					myFiles.backToFolderOrDocumentPage(previousFolderName);
				}
				
				previousFolderName = folderName;
				
				myFiles.openCreatedFolder(folderName);
				
				startVal++;
			}
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		ArrayList<String> folderNamesList = new ArrayList<String>();
		folderNamesList = myFiles.getFolderNames(folderDetails);
		
		for(int index=0; index<folderNamesList.size(); index++)
		{
			if(index<(folderNamesList.size()-1))
			{
				docLibPg.naviageToFolder(folderNamesList.get(index), folderNamesList.get(index+1));
			}
			
		}
		
		String breadCumbFolderLinkDetails = dataTable.getData("MyFiles", "FolderDetailsForBreadCumb");
		
		if (breadCumbFolderLinkDetails.contains(";")) {
			String splittedBreadCumbDetails[] = breadCumbFolderLinkDetails.split(";");
			for (String breadCumbLinkVal : splittedBreadCumbDetails)
			{
				for(String folderName:folderNamesList)
				{
					if(breadCumbLinkVal.equalsIgnoreCase(folderName) || breadCumbLinkVal.equalsIgnoreCase("Documents"))
					{
						String folderNameForViewDetails = docLibPg.getFolderNameFromCurrentDirectory();
						
						ArrayList<String> folderItemsList = docLibPg.getFolderDetailsFromCurrentDirectory();
						
						if(folderItemsList!=null)
						{
							report.updateTestLog("Verify Current Folder Details",
									"Current Folder details:"+folderItemsList+" displayed successfully", Status.PASS);
						}
						else
						{
							report.updateTestLog("Verify Current Folder Details",
									"Current Folder details:"+folderItemsList+" failed to display", Status.FAIL);
						}
						
						sitesPage.clickOnViewDetails(folderNameForViewDetails);
						
						docDetailsPageObj.checkBreadCumbLinkXpath(folderNameForViewDetails);
						
						myFiles.backToFolderOrDocumentPage(breadCumbLinkVal);
						
						String dispFolderName = docLibPg.getFolderNameFromCurrentDirectory();
						
						myFiles.openCreatedFolder(dispFolderName);
						
						break;
					}
					else if(breadCumbLinkVal.equalsIgnoreCase("FirstFolder"))
					{
						sitesPage.enterIntoDocumentLibrary();
						
						String folderNameForViewDetails = docLibPg.getFolderNameFromCurrentDirectory();
						
						ArrayList<String> folderItemsList = docLibPg.getFolderDetailsFromCurrentDirectory();
						
						if(folderItemsList!=null)
						{
							report.updateTestLog("Verify Current Folder Details",
									"Current Folder details:"+folderItemsList+" displayed successfully", Status.PASS);
						}
						else
						{
							report.updateTestLog("Verify Current Folder Details",
									"Current Folder details:"+folderItemsList+" failed to display", Status.FAIL);
						}
						
						sitesPage.clickOnViewDetails(folderNameForViewDetails);
						
						docDetailsPageObj.checkBreadCumbLinkXpath(folderNameForViewDetails);
						
						break;
					}
					
				}
			}
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoSearchPage searchObj = new AlfrescoSearchPage(scriptHelper);
		AlfrescoSearchPageTest searchTestObj = new AlfrescoSearchPageTest(scriptHelper);
		
		for(String folderName:folderNamesList)
		{
			searchObj.commonMethodForPerformSimpleSearch(folderName);
			searchTestObj.commonMethodForVerifySearchResults(folderName);
			
			searchObj.openFileOrFolderFromSearchResultsPage(folderName);
			
			docLibPgTest.verifyDefaultBreadcumbLinkValue(folderName);
			
			ArrayList<String> folderItemsList = docLibPg.getFolderDetailsFromCurrentDirectory();
			
			if(folderItemsList!=null && !folderItemsList.isEmpty() && folderItemsList.size()>0)
			{
				report.updateTestLog("Verify Current Folder Details",
						"Current Folder details:"+folderItemsList+" displayed successfully", Status.PASS);
			}
			else if(folderItemsList==null || folderItemsList.isEmpty())
			{
				report.updateTestLog("Verify Current Folder Details",
						"No items available in Current Folder", Status.PASS);
			}
			else
			{
				report.updateTestLog("Verify Current Folder Details",
						"Current folder items are failed to display", Status.FAIL);
			}
		}
	}

	@Override
	public void tearDown() {
		
	}

}
