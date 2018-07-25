package testscripts.search;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoRulePage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_046 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void search_045() {
        testParameters.setCurrentTestDescription(
                "Verify that user is able to perform Filtered  Search based on Size.");
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
                "Invoke the application under test @ " + properties.getProperty("ApplicationUrl"),
                Status.DONE);

    }

    @Override
    public void executeTest()  {
       
        /*AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
        functionalLibrary.loginAsValidUser(signOnPage);

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
    	homePageObj.navigateToAdvSearch();
    	AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		appSearchPg.searchUsingFilter();	
		UIHelper.waitFor(driver);
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPgTest.verifyAspectSearchResults();*/
    
		
    	AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		/*String fileName = dataTable.getData("MyFiles", "FileName");
		String siteassertValue = dataTable.getData("Sites", "SiteName");
	
		sitesPage.siteFinder(siteassertValue);
		sitesPage.documentlib();
		if(sitesPage.Checkdocument(fileName)){
			appSearchPg.deletedocument(fileName);
			//sitesPage.documentdetails(fileName);
			
		}else {
			
		}
		
		String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
		myFiles.createFile(fileDetails);*/
		
		homePageObj.navigateToAdvSearch();

	
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		
		String filter = dataTable.getData("Search", "Actions");
		String value = dataTable.getData("Search", "AssetType");
		String type = dataTable.getData("Search", "Title");
		appSearchPg.selectfilters(filter,value,type);
		
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		String Actualcount = appSearchPgTest.verifysearchcount();
		
		/*appSearchPgTest.verifyFileTypeFilter();*/
		
		String finalselectFilterValXpath = appSearchPg.selectFilterValXpath.replace("FILTER",
				filter).replace("CRAFT",value);
		
		if(Actualcount.contains(UIHelper.findAnElementbyXpath(driver, finalselectFilterValXpath).getText())){
			report.updateTestLog("Verify search count based on filter",
					 "Count verified before and after search result." 
							 +"<br><b>Before Filter : </b> "+Actualcount
			+"<br><b>After Filter : </b> "+ UIHelper.findAnElementbyXpath(driver, finalselectFilterValXpath).getText()
			, Status.PASS);
		} else
		{
			report.updateTestLog("Verify search count based on filter",
					 "Count before and after search result are not same." 
							 +"<br><b>Before Filter : </b> "+Actualcount
			+"<br><b>After Filter : </b> "+ UIHelper.findAnElementbyXpath(driver, finalselectFilterValXpath).getText()
			, Status.FAIL);
		}
		
	/*	AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		String strDate = sdf.format(date);
		System.out.println(strDate);
		appSearchPgTest.verifySearchFilter(strDate);*/
				

}
	

   

@Override
public void tearDown() {
    // Nothing to do
}
}
	
	
