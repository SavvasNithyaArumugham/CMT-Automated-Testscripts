package testscripts.customsearchui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;
import com.pearson.framework.selenium.Browser;


/**
 * Test for login with invalid user credentials
 * @author Cognizant
 */
public class AUT_AG_003 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_001()
	{
		testParameters.setCurrentTestDescription("Verify that custom search can be performed by Course by clicking +button");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		testParameters.setBrowser(Browser.Chrome);
		
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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);		
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		String courseName = dataTable.getData("MyFiles", "Version");
		String[] contentObj = dataTable.getData("MyFiles", "FileName").split(",");
		String[] lb = dataTable.getData("MyFiles", "CreateFolder").split(",");
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToCustomSearch();
		UIHelper.waitFor(driver);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.customSearchWithAdd(siteNameValue,courseName,contentObj[0],contentObj[1],contentObj[2],lb[0],lb[1],lb[2]);
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}