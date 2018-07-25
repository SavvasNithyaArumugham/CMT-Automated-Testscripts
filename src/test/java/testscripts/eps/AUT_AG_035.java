package testscripts.eps;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoEPSPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoEPSPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Cognizant
 */
public class AUT_AG_035 extends TestCase
{
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void EPS_035()
	{
		testParameters.setCurrentTestDescription
		(			
		
		"Verify the auto generated delivery URL displays both SCO URL and normal URL properly "
		+ "when the 'if you publish' dropdown is selected with ZIP files for any institutions."
				
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
		
		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application", "Invoke the application under test @ " +
								properties.getProperty("ApplicationUrl"), Status.DONE);
		
	}
	
	@Override
	public void executeTest()
	{
		try{
			
		//Object Creation
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);	
		AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoEPSPage epsPg = new AlfrescoEPSPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoSitesPage sites = new AlfrescoSitesPage(scriptHelper);
		//Value Declaration
		String sourceSiteName = dataTable.getData("Sites", "EPSSiteName");
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String fileName = dataTable.getData("MyFiles", "FileName");
		String filePath = dataTable.getData("MyFiles", "FilePath");	
		
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");	
		String instution = dataTable.getData("MyFiles", "CreateFolder");
		String Name[] = dataTable.getData("MyFiles", "Version").split(";");
		String URLLink="";

		// Script Started: 
		functionalLibrary.loginAsValidUser(signOnPage);	
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		homePage.navigateToSitesTab();
		sitesPage.createSite(sourceSiteName, "Yes");
		String site=sitesPage.getCreatedSiteName();
		//	sitesPage.siteFinder(site);
		sitesPage.enterIntoDocumentLibrary();
		
		/*epsPg.DeleteFolderandFile();
		UIHelper.waitFor(driver);*/
		
		collectionPg.uploadFileInCollectionSite(filePath, fileName);
		UIHelper.waitFor(driver);
		collectionPg.clickOnMoreSetting(fileName);	
		collectionPg.commonMethodForClickOnMoreSettingsOption(fileName,Option[3]);
		UIHelper.waitForPageToLoad(driver);
		UIHelper.selectbyVisibleText(driver, epsPg.ifyoupublishDropdownxpath, fileName);	
		UIHelper.selectbyVisibleText(driver, epsPg.todropdownXpath, instution);
		UIHelper.waitForPageToLoad(driver);
		sites.getStringFromClipboard(epsPg.deliveryURLXpath);
		sites.getStringFromClipboard(epsPg.deliveryURLXpath);
		String url = sites.getStringFromClipboard(epsPg.deliveryURLXpath);
		UIHelper.waitForPageToLoad(driver);
	
		url= url.replace("\\n", " ");

		ArrayList<String> lil = new ArrayList<String>();
		
		url=url.replace("http://", "http:/");
	
		String[] b1= url.split("/");
		
		for(String b1b:b1){
			if(!b1b.matches(".*\\d+.*")){			
				lil.add(b1b);
				lil.add("/");		
		}
		}
		
		if(instution.contains("School Content")){
			URLLink="[URL if published as zip: http:, /, us-school-stg.pearsoned.com, /, school, /, "+fileName+" URL (if zip expanded when published): http:, /, us-school-stg.pearsoned.com, /, school, /]";
			
		}else if(instution.equalsIgnoreCase("Alfresco CDN")){
			URLLink="[URL if published as zip: http:, /, content.stg-openclass.com, /, eps, /, cdn, /, alfresco, /, "+fileName+" URL (if zip expanded when published): http:, /, content.stg-openclass.com, /, eps, /, cdn, /, alfresco, /]";
		}
		
		String Actual= lil.toString();
		
		if(Actual.contentEquals(URLLink)){
			report.updateTestLog("<b>Publishing Options URL:", "<b>"+lil +"url is generated", Status.PASS);				
		}else{
			report.updateTestLog("<b>Publishing Options URL:", "<b>"+lil + "url is not generated", Status.FAIL);			
		}
			
		
		UIHelper.click(driver, epsPg.sitenotcongiCloseXpath);	
		
	/*	epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();
		sitesPage.enterIntoDocumentLibrary();
		epsPg.DeleteFolderandFile();
		epsPg.DeleteFolderandFile();*/
		
		}catch(Exception e){
			e.printStackTrace();
			report.updateTestLog("EPS_35 Status", "EPS 35 Testcases has been failed", Status.FAIL);	
		}
	
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}