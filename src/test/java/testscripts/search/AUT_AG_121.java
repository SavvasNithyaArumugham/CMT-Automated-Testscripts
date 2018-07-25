package testscripts.search;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.util.List;

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

public class AUT_AG_121 extends TestCase {

    private FunctionalLibrary functionalLibrary;

    @Test
    public void search_120() {
        testParameters.setCurrentTestDescription(
                "Verify that the Options available on the Select Dropdown on Search Result Page");
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
        boolean flag=false;
        String slctDropVals = dataTable.getData("Search", "SelectDrpDpwnVals");
        String[] slctDrpValsSplit = slctDropVals.split(",");
        functionalLibrary.loginAsValidUser(signOnPage);
        
        AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
    	homePageObj.navigateToAdvSearch();
    	AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
    	AlfrescoSearchPageTest appSearchPgTst = new AlfrescoSearchPageTest(scriptHelper);
		appSearchPg.inputFileNameAdvSearch();
		appSearchPg.clickSearch();
		flag=appSearchPgTst.verifySelectDrpDownVals(slctDrpValsSplit);
		
		//appSearchPg.selValFrmSerchResultDrpDow("All");
		//appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[0]);
		
		
       /* List<WebElement> actSlctDrpVals = appSearchPg.selValFrmSerchResultDrpDow(slctDrpValsSplit[0]);
        System.out.println(""+actSlctDrpVals.size());
        System.out.println(""+actSlctDrpVals.equals(slctDrpValsSplit));*/
        
        /*for(WebElement availValLst:actSlctDrpVals)
		{
			
			int j=0;
			
			System.out.println("actual"+availValLst.getText().trim()+"expected"+slctDrpValsSplit[j]);
			if(availValLst.getText().trim().equalsIgnoreCase(slctDrpValsSplit[j]))
			{
				flag=true;
				
			}
			else
			{
				flag = false;
			}
			j++;
		}*/
        
        
        
        
        
       /* for(int i=0;i<actSlctDrpVals.size();i++){
        	System.out.println(""+actSlctDrpVals.get(i).getText().trim()+"expected"+slctDrpValsSplit[i]);
        	if(actSlctDrpVals.get(i).getText().trim().equalsIgnoreCase(slctDrpValsSplit[i]))
        	{
        		
        		flag=true;
        	}
        	else
        	{
        		flag = false;
        		break;
        	}
        }*/
        
        if(flag)
        {
        	report.updateTestLog(
					"Verify the values of the Select Dropdown",
					"Dropdown values are verified successfully and the values displayed are "+slctDrpValsSplit[0]+slctDrpValsSplit[1]+slctDrpValsSplit[2],
					Status.PASS);
        }
        
        else
        {
        	report.updateTestLog(
					"Verify the values of the Select Dropdown",
					"Dropdown values are not displayed correctly ",
					Status.FAIL);
        }
		
    }

	@Override
	public void tearDown() {
	    // Nothing to do
	}
}


