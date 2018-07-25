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



public class AUT_AG_048  extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void search_047() {
        testParameters.setCurrentTestDescription(
                "Verify that user is able to see all the dashlets after clicking on search manager");
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
       
       /* AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
        functionalLibrary.loginAsValidUser(signOnPage);

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
    	homePageObj.navigateToAdvSearch();    	
    	AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		appSearchPg.searchManager();
		appSearchPg.VerifyAppletHeader();
		appSearchPg.sorttable();*/
		
    	AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		
		homePageObj.navigateToAdvSearch();

	
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		
		appSearchPg.clickOnSearchManager();
		AlfrescoSearchPageTest appSearchPgTest = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPgTest.verifySearchManagerUIElements();
    }

@Override
public void tearDown() {
    // Nothing to do
}
}
