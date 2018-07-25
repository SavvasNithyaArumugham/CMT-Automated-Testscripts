package testscripts.marklogic;

import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMarklogicPageTest;
import com.pearson.automation.utils.CC_API_Helper;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Naresh Kumar Salla
 */
public class AUT_AG_107 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void runAUT_AG_107()
	{
		testParameters.setCurrentTestDescription("Verify the user who is member of 'Metadata Synchronization Controller' group is able to "
									+"</br>1. View the Add Watched content option for folder"
									+"</br>2. View the 'Remove from Watched content items' on folders where watched content is already added to that folder"
									+"</br>3. View either 'Add to watched content' or 'Remove from Watched content items' on folders at a time"
									+"</br>4. View either 'Add to watched content' or 'Remove from Watched content items' on folders at a time in view details page of folder");
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
		
		//	String auth_token = CC_API_Helper.generate_PI_AuthToken(URL);
		//	System.out.println(auth_token);			
			//retriveValuefromJson("$..count", Answer.getBody().asString());
		//	System.out.println(Answer.getBody().asString());
			
			/*API TC
			 * String URL = "https://identity-internal-test.pearson.com/auth/json/pearson/authenticate";
			AlfrescoMarklogicPageTest marklogic = new AlfrescoMarklogicPageTest(scriptHelper);
			Response auth = marklogic.verifyLoginAuth();
			
			String token = CC_API_Helper.retriveValuefromJson("$..tokenId", auth.getBody().asString());
			Response Answer = CC_API_Helper.getManifestationReturnresponse(token, "s");

			marklogic.verifygetManifestationResponse(Answer);
			System.out.println(CC_API_Helper.retriveValuefromJson("$..dateCreated", Answer.getBody().asString()));
			marklogic.verifyresponseValue(Answer, "dateCreated","15 Nov 2016 11:01:02");*/
			
			
			
			
		/*AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);*/
		/*String auth_token = CC_API_Helper.generate_PI_AuthToken();
		System.out.println(auth_token);
		
		Response Answer = CC_API_Helper.getManifestationReturnresponse(auth_token, "work/dc73515a-fcee-4cd1-a368-3ba1b565cb9a");
		//retriveValuefromJson("$..count", Answer.getBody().asString());
		System.out.println(Answer.getBody().asString());
		*/
		
		/*Naresh Work*/
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoDocumentDetailsPage docPage = new AlfrescoDocumentDetailsPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String folderDetails = dataTable.getData("MyFiles", "CreateFolder");
		String folderName = dataTable.getData("MyFiles", "Version");
		String moreSetOpt = dataTable.getData("MyFiles", "MoreSettingsOption");
		String[] moreSetOptions = moreSetOpt.split(",");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		
		sitesPage.openSiteFromRecentSites(siteNameValue);
		sitesPage.enterIntoDocumentLibrary();
		
		docLibPage.deleteAllFilesAndFolders();
        myFiles.createFolder(folderDetails);
        
        sitesPage.clickOnMoreSetting(folderName);
        if(sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0])){
        	report.updateTestLog("Verify '"+moreSetOptions[0]+"' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
        	 sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSetOptions[0]);
        	 UIHelper.waitFor(driver);
        	 if(docLibPage.isCategoryTagAvailable(folderName, "Watched Content")){
        		 report.updateTestLog("Verify '"+moreSetOptions[0]+"' aspect applied for empty folder",
     					"Applied Successfully", Status.PASS);
             }else{
             	report.updateTestLog("Verify '"+moreSetOptions[0]+"' aspect applied for empty folder",
     					"Not able to Apply", Status.FAIL);
             }
        }else{
        	report.updateTestLog("Verify '"+moreSetOptions[0]+"' aspect for empty folder under 'More' option",
					"Not able to Verify", Status.FAIL);
        }
        
        sitesPage.clickOnMoreSetting(folderName);
        if(sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[1])){
        	report.updateTestLog("Verify '"+moreSetOptions[1]+"' aspect for empty folder under 'More' option",
					"Verified Successfully", Status.PASS);
        	sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSetOptions[1]);
        	UIHelper.waitFor(driver);
       	 	if(!docLibPage.isCategoryTagAvailable(folderName, "Watched Content")){
       		 report.updateTestLog("Verify '"+moreSetOptions[1]+"' aspect removed for empty folder",
    					"Removed Successfully", Status.PASS);
            }else{
            	report.updateTestLog("Verify '"+moreSetOptions[1]+"' aspect removed for empty folder",
    					"Not able to Remove", Status.FAIL);
            }
        }else{
        	report.updateTestLog("Verify '"+moreSetOptions[1]+"' aspect applied for empty folder",
					"Not able to Apply", Status.FAIL);
        }
        
        sitesPage.clickOnMoreSetting(folderName);
        if((sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0]))
        		&& !(sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[1]))){
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
        			"'"+moreSetOptions[0]+"' option only displyed Successfully"
        			+"</br> <b> Option Verified : </b>" +moreSetOptions[0] , Status.PASS);
        	sitesPage.commonMethodForClickOnMoreOptionLink(folderName, moreSetOptions[0]);
        	UIHelper.waitFor(driver);
        }else{
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
					"Verification failed", Status.FAIL);
        }
        
        UIHelper.waitFor(driver);
        sitesPage.clickOnMoreSetting(folderName);
        if((sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[1]))
        		&& !(sitesPage.checkMoreSettingsOption(folderName, moreSetOptions[0]))){
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
        			"'"+moreSetOptions[1]+"' option only displyed Successfully"
							+"</br> <b> Option Verified : </b>" +moreSetOptions[1], Status.PASS);
        }else{
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in more options",
					"Verification failed", Status.FAIL);
        }
        
        sitesPage.clickOnViewDetails(folderName);
        if((docPage.checkFolderActionName(moreSetOptions[1]))
        		&& !(docPage.checkFolderActionName(moreSetOptions[0]))){
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
        			"'"+moreSetOptions[1]+"' option only displyed Successfully"
        			+"</br> <b> Option Verified : </b>" +moreSetOptions[1] , Status.PASS);
        	docPage.clickDocAction(moreSetOptions[1]);
        	
        }else{
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
					"Verification failed", Status.FAIL);
        }
        
        if((docPage.checkFolderActionName(moreSetOptions[0]))
        		&& !(docPage.checkFolderActionName(moreSetOptions[1]))){
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
        			"'"+moreSetOptions[0]+"' option only displyed Successfully"
        			+"</br> <b> Option Verified : </b>" +moreSetOptions[0] , Status.PASS);
        	
        }else{
        	report.updateTestLog("Verify only one option displyed either 'Add to' or 'Remove from' Watched content folder in Folder Actions",
					"Verification failed", Status.FAIL);
        }
        
	}
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}