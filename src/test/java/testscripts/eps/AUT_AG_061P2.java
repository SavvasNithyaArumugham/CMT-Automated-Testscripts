package testscripts.eps;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.Zip;
import org.testng.annotations.Test;

import java.util.zip.*;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoPearsonMailPage;
import com.pearson.automation.alfresco.pages.AlfrescoProofHQPage;
import com.pearson.automation.alfresco.pages.AlfrescoRepositoryPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Settings;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_061P2 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_060()
	{
		testParameters.setCurrentTestDescription(
				
		"ALFDEPLOY-3884_Verify the error message in email if the html file has tags with style attributes while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file has unapproved tags while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file does not have H3 tags while upload"
		+"ALFDEPLOY-3884_Verify the error message in email if the html file has tags with style attributes, "
		+ "unapproved tags and missing H3 tags while upload"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ "with style attributes"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ "with unapproved tags"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file "
		+ " out H3 tags"
		+"ALFDEPLOY-3884_Verify user able to complete sanitization by uploading new version for html file"
		+ " has tags with style attributes, unapproved tags and missing H3 tags"
		+"ALFDEPLOY-3884_Verify user able to upload multiple html content in sourse folder - 3 html files "
		+ "with unapproved tags and 3 more html files with out unapproved tags"
		+"ALFDEPLOY-3884_Verify user able to upload new version of html content in source folder"
		
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
		
		driver.get(properties.getProperty("GmailUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("GmailUrl"), Status.DONE);

		
	}
	
	@Override
	public void executeTest()
	{
		try{
			String messagetitle = "Unapproved tag is found in ";
			String fileName[] = dataTable.getData("MyFiles", "FileName").split(",");
			String sitename = dataTable.getData("Sites", "EPSSiteName");
			String message = messagetitle + fileName[0]+" in " +sitename;
			System.out.println(message);
			AlfrescoProofHQPage proofHQPgObj = new AlfrescoProofHQPage(scriptHelper);
			
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			signOnPage = functionalLibrary.loginAsValidUserforPearsonMail(signOnPage);	
			
			String url= properties.getProperty("ApplicationUrl");
			if(url.contains("usppewip.pearsoncms.com")||url.contains("usppewip.cms.pearson.com")){
			// Unapproved tags
			AlfrescoPearsonMailPage pearsonMailObj = new AlfrescoPearsonMailPage(scriptHelper);
			pearsonMailObj.searchMailWithSubjectTitle(message);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonMailObj.errorunapprovedtags);
			if(UIHelper.checkForAnElementbyXpath(driver, pearsonMailObj.errorunapprovedtags)){
				UIHelper.highlightElement(driver, pearsonMailObj.errorunapprovedtags);
				report.updateTestLog("HTML files errors",
						"the error message in email, the html file has unapproved tags while upload"
								, Status.PASS);
				
			} else{
				report.updateTestLog("HTML files errors",
						"the error message in email doesnot appear even when the"
						+ " html file have unapproved tags while upload"
								, Status.FAIL);
			}
			
			//style
			message = messagetitle + fileName[1]+" in " +sitename;
			System.out.println(message);
			pearsonMailObj.searchMailWithSubjectTitle(message);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonMailObj.errorstyle);
			if(UIHelper.checkForAnElementbyXpath(driver, pearsonMailObj.errorstyle)){
				UIHelper.highlightElement(driver, pearsonMailObj.errorstyle);
				report.updateTestLog("HTML files errors",
						"the error message in email, the html file has incorrect style tags while upload"
								, Status.PASS);
				
			} else{
				report.updateTestLog("HTML files errors",
						"the error message in email doesnot appear even when the"
						+ " html file has incorrect style tags while upload"
								, Status.FAIL);
			}
			
			
			// H1 tags
			message = messagetitle + fileName[2]+" in " +sitename;
			pearsonMailObj.searchMailWithSubjectTitle(message);
			System.out.println(message);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonMailObj.errorh1);
			if(UIHelper.checkForAnElementbyXpath(driver, pearsonMailObj.errorh1)){
				UIHelper.highlightElement(driver, pearsonMailObj.errorh1);
				report.updateTestLog("HTML files errors",
						"the error message in email, the html file does not have H1 tags while upload"
								, Status.PASS);
				
			} else{
				report.updateTestLog("HTML files errors",
						"the error message in email doesnot appear even when the"
						+ " html file does not have H1 tags while upload"
								, Status.FAIL);
			}

			//All inone
			message = messagetitle + fileName[3]+" in " +sitename;
			pearsonMailObj.searchMailWithSubjectTitle(message);
			System.out.println(message);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonMailObj.errorh1);
			UIHelper.waitForPageToLoad(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitForVisibilityOfEleByXpath(driver, pearsonMailObj.errorh1);
			
			if(UIHelper.checkForAnElementbyXpath(driver, pearsonMailObj.errorh1)
					&&UIHelper.checkForAnElementbyXpath(driver, pearsonMailObj.errorstyle)
					&&UIHelper.checkForAnElementbyXpath(driver, pearsonMailObj.errorunapprovedtags)){
				UIHelper.highlightElement(driver, pearsonMailObj.errorh1);
				UIHelper.highlightElement(driver, pearsonMailObj.errorstyle);
				UIHelper.highlightElement(driver, pearsonMailObj.errorunapprovedtags);
				report.updateTestLog("HTML files errors",
						"the error message in email, the html file does not have H1 tags , incorrect style and unapproved tags while upload"
								, Status.PASS);
				
			} else{
				report.updateTestLog("HTML files errors",
						"the error message in email doesnot appear even when the"
						+ " html file does not have H1 tags, incorrect style and unapproved tags while upload"
								, Status.FAIL);
			}
			
			
		
		
			}else{
				report.updateTestLog("Applicable for usppewip env only", "Applicable for usppewip env only", Status.DONE);	
				
			}
		
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_61P2 Status", "EPS 61P2 Testcases has been failed", Status.FAIL);	
			}		
		
}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}