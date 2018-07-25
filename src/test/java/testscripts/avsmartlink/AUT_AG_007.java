package testscripts.avsmartlink;

import org.testng.annotations.Test;

import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAVSmartLinkPage;
import com.pearson.automation.alfresco.pages.AlfrescoAVTPage;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSiteMembersPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoHomePageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoPCSPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSiteMembersPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_007 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void AV_002() {
		testParameters.setCurrentTestDescription("Verify user is able to edit external url and title of smartlink object via edit properties");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoAVSmartLinkPage avsmart = new AlfrescoAVSmartLinkPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoMyFilesPageTest myFilesTestObj = new AlfrescoMyFilesPageTest(scriptHelper);
		
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String siteName = dataTable.getData("Sites", "SiteName");
		String type = dataTable.getData("Home", "DashletName");
		String title = dataTable.getData("Document_Details", "FilePath");
		String extURLLink = dataTable.getData("Document_Details", "FileName");

		
		functionalLibrary.loginAsValidUser(signOnPage);
		
		sitesPage.siteFinder(siteName);

			sitesPage.enterIntoDocumentLibrary();
		
		docLibPg.deleteAllFilesAndFolders();
	
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
		
		avsmart.entersmarttypedata("imageExternalLink", title, extURLLink, "", "", "","", System.getProperty("user.dir")+filePath+fileName);
	
	
		avsmart.subcancelbtn("imageExternalLink","Submit");
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		
		sitesPage.enterIntoDocumentLibrary();
		
		sitesPage.documentdetails(title);
		
		myFilesTestObj.verifyUploadedFile(title, "");
		
		myFiles.createcontenttype(type);
		avsmart.clickSmartLinkType("Image External Link", "imageExternalLink");
		
		avsmart.entersmarttypedata("imageExternalLink", title, extURLLink, "", "", "","", System.getProperty("user.dir")+filePath+fileName);
	
		String cancelbtn = avsmart.cancelsubxpath.replace("CRAFT", "imageExternalLink").replace(
				"REPLACE", "Submit");
		UIHelper.click(driver, cancelbtn);
	//	avsmart.subcancelbtn("imageExternalLink",");
		
		try {
			UIHelper.waitForVisibilityOfEleByXpath(driver, avsmart.messageXpath);
			String getMessage = UIHelper.getTextFromWebElement(driver,
					avsmart.messageXpath);
			
			System.out.println(getMessage);
			
			if(getMessage.equalsIgnoreCase("SmartLink with the same name already exists.Use some other name")){
				
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link cannot be created in my files. <br><b>Message : <b> " +getMessage, Status.PASS);
			}else{
				report.updateTestLog(" Verify creation of Smart link",
						"Smart Link can be created in my files which is not execpted", Status.FAIL);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			report.updateTestLog(" Verify creation of Smart link",
					"Smart Link can be created in my files which is not execpted", Status.FAIL);
		}
		
		
	}
		

	@Override
	public void tearDown() {

	}
}
