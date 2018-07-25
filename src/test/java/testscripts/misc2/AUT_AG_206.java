package testscripts.misc2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class AUT_AG_206 extends TestCase{

	private FunctionalLibrary functionalLibrary;

	@Test
	public void MISC2_039()
	{
		testParameters.setCurrentTestDescription("To verify ,for user who is not in site owner group,Sharebox user group, Repository Manager group is not getting below options under More options"+
													"<br>Manage Permission"+
													"<br>Share Folder Externally"+
													"<br>Copy to Repository"+
													"<br>Publish"+
													"<br>Transformation Profile"
													);
								
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
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(scriptHelper); 
		AlfrescoDocumentLibPageTest doc1=new AlfrescoDocumentLibPageTest(scriptHelper);
		AlfrescoSitesPage sitesPage = homePage.navigateToSitesTab();
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingsOption[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String moreSettingsOption1[] = dataTable.getData("MyFiles", "PerformedActivitiesOnDocument").split(",");
		String folder = dataTable.getData("MyFiles", "CreateFolder");
		String foldername = dataTable.getData("MyFiles", "Version");
		
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(
				scriptHelper);
		
	       	homePage.navigateToSitesTab();
		 
		     sitesPage.siteFinder(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		
		//Checking for folder
		myFiles.createFolder(folder);
		 UIHelper.waitForPageToLoad(driver);
		 
		 /*WebElement element1= driver.findElement(By.xpath("//*[@class='filename']//*[contains(text(),'AutoTest')]"));
		 UIHelper.mouseOveranElement(driver,
					 element1);*/
		 
		 sitesPage.clickOnMoreSetting(foldername);
       	report.updateTestLog(
                "Verify More options is clicked "
                        + foldername, "More options is clicked."
                        + "<br><b>foldername :AutoTest </br>", Status.PASS);
        UIHelper.waitForPageToLoad(driver);
		
        for (String name:moreSettingsOption){
			
		doc1.verifyOptionInMoreLinkOptions(foldername,name);
		}
		
		UIHelper.waitForPageToLoad(driver);	
	    UIHelper.waitForPageToLoad(driver);
	    
		//Checking for file
	    myFiles.uploadFileInMyFilesPage(filePath, fileName);
		myFilesTestObj.verifyUploadedFile(fileName,"");
		UIHelper.waitForPageToLoad(driver);

	     sitesPage.clickOnMoreSetting(fileName);
	     for (String name1:moreSettingsOption1){
				
	 		doc1.verifyOptionInMoreLinkOptions(fileName,name1);
	 		}
	
		UIHelper.waitForPageToLoad(driver);	
		

		}
	

	@Override
	public void tearDown() {
		
	}

}
