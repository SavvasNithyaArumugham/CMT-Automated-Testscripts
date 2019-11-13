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
public class AUT_AG_002 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_001()
	{
		testParameters.setCurrentTestDescription("Verify that custom search can be performed by Course");
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
		String[] compositeObj = dataTable.getData("MyFiles", "CreateFileDetails").split(",");
		functionalLibrary.loginAsValidUser(signOnPage);
		homePageObj.navigateToCustomSearch();
		UIHelper.waitFor(driver);
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		appSearchPg.customSearchWithObjectType(siteNameValue,courseName,contentObj[0],contentObj[1],contentObj[2]);
		UIHelper.waitFor(driver);
		appSearchPg.customSearchWithObjectType(siteNameValue,courseName,lb[0],lb[1],lb[2]);
		UIHelper.waitFor(driver);
		//appSearchPg.customSearchWithObjectType(siteNameValue,courseName,compositeObj[0],compositeObj[1],"");
		
		 Actions builder = new Actions(driver);
		 
		 WebElement to = driver.findElement(By.id("yui-dt0-th-qnamePath-liner"));
		 UIHelper.waitFor(driver);
		 WebElement from = driver.findElement(By.id("yui-dt0-th-nodeRef-liner")); 
		 UIHelper.waitFor(driver);
		 //Perform drag and drop
		 builder.dragAndDrop(from, to).perform();
		 UIHelper.waitFor(driver);
		 //builder.dragAndDrop(to, from).perform();
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}