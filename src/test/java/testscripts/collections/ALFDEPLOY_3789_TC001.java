package testscripts.collections;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_3789_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3789_TC001() {
		testParameters.setCurrentTestDescription("ALFDEPLOY_3789_TC001 - Verify upload file to Project Files folder" + 
				"<br>ALFDEPLOY_3789_TC002 - Verify the relationship from Collections Content Object to uploaded file)");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);	
			String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
			String errorfile = dataTable.getData("MyFiles", "Subfolders1");
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);			
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");					
			// Log in Pearson Schools project
			functionalLibrary.loginAsValidUser(signOnPage);
							
			//Create site							
			//Create site
			UIHelper.waitForLong(driver);
			homePageObj.navigateToSitesTab();
			String siteNameValue =  dataTable.getData("Sites", "SiteName");				
			sitesPage.siteFinder(siteNameValue);			
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();
					
					// go to Project Files folder
					myFiles.openCreatedFolder(folderNames[0]);
					UIHelper.waitForPageToLoad(driver);
					
					// Case 1 : upload pdf file in Project Files folder					
					collectionPg.uploadFileInCollectionSite(filePath, fileName);		
									
					//Case 2 : Add the uploaded file as a child reference in any of the content object		
										
					sitesPage.enterIntoDocumentLibrary();
					
					//go to Content Objects
					
					myFiles.openCreatedFolder(folderNames[1]);					
					myFiles.openCreatedFolder(folderNames[2]);		
					collectionPg.clickOnMoreSetting(folderNames[3]);
					collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[3], "Child references");
					
					// add child references to that content object					
					collectionPg.addPDFChildReference(folderNames[0], fileName);
				
					
					
					}		
					
		

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub

		}
	}


