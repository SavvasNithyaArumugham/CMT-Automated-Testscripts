package testscripts.search;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
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

public class AUT_AG_124 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void search_123() {
        testParameters.setCurrentTestDescription(
                "Verify that User is able to invert  selected and Non selected Items(25 items that are loaded first ) on Search Result Page by  clicking 'Invert' Option from Select Dropdown");
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
       
        AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
        functionalLibrary.loginAsValidUser(signOnPage);
        
        AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
        AlfrescoDocumentLibPage docLibPage = new AlfrescoDocumentLibPage(scriptHelper);
      /*  homePage.navigateToMyFilesTab();
        docLibPage.deleteAllFilesAndFolders();
        AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
        for(int i=0;i<40;i++)
        {
        	myFiles.commonMethodForCreateFile("Plain Text", "a"+i, "a"+i, "a"+i, "a"+i);
        	UIHelper.waitForPageToLoad(driver);
        	homePage.navigateToMyFilesTab();
        	UIHelper.waitForPageToLoad(driver);
        }*/
        
        
        String slctDropVals = dataTable.getData("Search", "SelectDrpDpwnVals");
        String[] slctDrpValsSplit = slctDropVals.split(",");

        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
    	homePageObj.navigateToAdvSearch();
    	AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[0]);
		UIHelper.waitFor(driver);
		appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[1]);
		UIHelper.waitFor(driver);
		appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[2]);
		UIHelper.waitFor(driver);
		int cnt1 = appSearchPg.retCntFilesChkd();
		appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[2]);
		UIHelper.waitFor(driver);
		int cnt = appSearchPg.retCntFilesUnChkd();
		System.out.println(""+cnt+cnt1);
		UIHelper.waitFor(driver);
		if(cnt==cnt1 && cnt==25)
		{
			report.updateTestLog(
					"Verify no of Files checked/Unchecked or Inverted when Selecting Invert Option",+cnt+
					"  Files are Inverted successfully as expected when Selecting Invert option from Select Dropdown",Status.PASS);
		}
		else
		{
			report.updateTestLog(
					"Verify no of Files checked/Unchecked or Inverted when Selecting Invert Option",+cnt+
					" Files are Inverted successfully instead of 25 files when Selecting Invert option from Select Dropdown",Status.FAIL);
		}
		
    }

	@Override
	public void tearDown() {
	    // Nothing to do
	}
}


