package testscripts.eps.publish;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * @author 
 */
public class AUT_AG_027 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_011()
	{
		testParameters.setCurrentTestDescription("Publish - zip file outside SCO folder - expanded mode (without checking the checkbox");
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
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);	
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg =new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String moreSettingsOptionName = dataTable.getData("MyFiles", "MoreSettingsOption");
						
		homePage.navigateToSitesTab();	
		UIHelper.waitFor(driver);
		sitesPage.openSiteFromRecentSites(sourceSiteName);	
		sitesPage.enterIntoDocumentLibrary();
								
		myFiles.uploadFile(filePath, fileName);
		//Publish zip file within the sco level folder
				
				sitesPage.clickOnMoreSetting(fileName);	
				epsPg.publishbutton(fileName, moreSettingsOptionName);
				docLibPg.clickPublishPopup();
				UIHelper.waitFor(driver);
				UIHelper.pageRefresh(driver);
				docLibPg.checkPublishedTick(fileName);
				UIHelper.waitFor(driver);
				docLibPg.isDisplayedPublishedGreenTick(fileName);
				UIHelper.waitFor(driver);
				
				//Check EPS ID for zip file outside SCO folder-normal mode
				
		        sitesPage.clickOnMoreSetting(fileName);	
				epsPg.publishbutton(fileName, moreSettingsOptionName);
				String EPSURL1 = (UIHelper.findAnElementbyXpath(driver, "//*[@id='prompt']/div[2]/table/tbody/tr[2]/td[5]/u/a").getText()).substring(60,98);
				driver.findElement(By.xpath(".//button[text()='Cancel']")).click();
				collectionPg.commonMethodForClickOnMoreSettingsOption(fileName,"Edit Properties");
<<<<<<< HEAD
		        String EPSURL2 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'prop_cm_description')]").getText()).substring(8,46);
		        driver.findElement(By.xpath("//div[@class='yui-module yui-overlay yui-panel']/a")).click();
		        UIHelper.waitFor(driver);
=======
		        String EPSURL2 = (UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'default-editDetails-alf-id14_prop_cm_description')]").getText()).substring(8,46);
		        
>>>>>>> 2ef7837583cbe1598b1f6535fdf2758d25bdc2eb
		            
		        if( EPSURL1.equals(EPSURL2)){
		        	report.updateTestLog("EPS ID match check",	"EPS ID matchs" + fileName,Status.PASS);
		        }
		        else
		        {
		        	report.updateTestLog("EPS ID match check",	"EPS ID does not match" +fileName ,Status.FAIL);
		        }
		
	}
			
	
	@Override
	public void tearDown() {

	}

}