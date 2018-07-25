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

public class AUT_AG_125 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void search_124() {
        testParameters.setCurrentTestDescription(
                "Verify that User is able to select all Items on scrolling search Result  Page by  clicking 'All' Option from Select Dropdown");
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
		/*appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[1]);
		UIHelper.waitFor(driver);
		appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[2]);
		UIHelper.waitFor(driver);*/
		int cnt1 = appSearchPg.retCntFilesChkd();
		WebElement ele = driver.findElement(By.xpath(".//*[@id='ALF_SHARE_FOOTER']/footer/span/a/img"));
		UIHelper.scrollToAnElement(ele);
		UIHelper.waitFor(driver);
		appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[0]);
		UIHelper.waitFor(driver);
		int cnt = appSearchPg.retCntFilesChkd();
		System.out.println(""+cnt+"nect"+cnt1);
		if(cnt>cnt1)
		{
			report.updateTestLog(
					"Verify Select ALL Options when user Scroll down",
					" All Files are Selected successfully as expected when Users Scrolls downs"
					+"<br /><b> No of Files Selected before Scrolling using ALL option : </b>" +cnt1
					+"<br /><b> No of Files Selected after Scrolling using ALL option : </b>" +cnt,Status.PASS);
		}
		else
		{
			report.updateTestLog(
					"Verify Select ALL Options when user Scroll down",
					" Files are NOT selected successfully using ALL option as expected. Please verify files are loaded after scrolling down",Status.FAIL);
		}
		
    }

	@Override
	public void tearDown() {
	    // Nothing to do
	}
}


