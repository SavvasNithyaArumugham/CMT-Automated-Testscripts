package testscripts.collections;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class ALFDEPLOY_3191_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3191_TC001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY_3191_TC001: Validate new Leveled Reader properties through new course CSV Import");
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
		try{
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			String errorfile = dataTable.getData("MyFiles", "Subfolders1");
			String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);			
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";		
			// Log in Pearson Schools project
			functionalLibrary.loginAsValidUser(signOnPage);
							
					
					//Create site
					UIHelper.waitForLong(driver);
					homePageObj.navigateToSitesTab();
					String siteNameValue =  dataTable.getData("Sites", "SiteName");				
					sitesPage.siteFinder(siteNameValue);			
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();
			
					// go to Course plan
					
					myFiles.openCreatedFolder(folderNames[0]);
					UIHelper.waitForPageToLoad(driver);
					myFiles.openCreatedFolder(folderNames[1]);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);				
					UIHelper.waitFor(driver);
					
					// upload course plan
					collectionPg.uploadFileInCollectionSite(filePath, fileName);				
					UIHelper.waitForLong(driver);						
					UIHelper.pageRefresh(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
								
					//go to course object 1
					
					sitesPage.enterIntoDocumentLibrary();
					UIHelper.waitFor(driver);
					Date date = new Date();
					String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);	
					myFiles.openCreatedFolder(folderNames[0]);
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(folderNames[2]);
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(currentDate.substring(0, 4));
					myFiles.openCreatedFolder(currentDate.substring(5, 7));
					myFiles.openCreatedFolder(currentDate.substring(8, 10));
					UIHelper.waitFor(driver);
					collectionPg.clickOnMoreSetting(fileName);
					UIHelper.waitFor(driver);
					
					collectionPg.commonMethodForClickOnMoreSettingsOption(fileName, "View Error Report");
					UIHelper.waitFor(driver);					
					new FileUtil().waitUptoAllFilesDownloadComplete(fileDownloadPath,errorfile);

					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					homePageObj.openNewTab(fileDownloadPath + "\\" + errorfile);	
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					collectionPg.errormessagecontains("Leveled Reader Test - Invalid Text Features","Invalid Text Features");				
					collectionPg.errormessagecontains("Leveled Reader Test - Invalid Content Areas","Invalid Content Areas");	
					collectionPg.errormessagecontains("Leveled Reader Test - Invalid Comprehension Skills","Invalid Comprehension Skills");
					//Added for NALS 
					//collectionPg.errormessagecontains("Leveled Reader Test - Invalid Lexile","Invalid Lexile");
					collectionPg.errormessagecontains("Leveled Reader Test - Invalid DRA","Invalid DRA");
					collectionPg.VerifyErrorNotINUI("Guided Reading");
					collectionPg.VerifyErrorNotINUI("Reading Maturity Metric");
					collectionPg.VerifyErrorNotINUI("Quantile");
					collectionPg.VerifyErrorNotINUI("ISBN");
					collectionPg.VerifyErrorNotINUI("Author");
					
					
					
					
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub

		}
	}
