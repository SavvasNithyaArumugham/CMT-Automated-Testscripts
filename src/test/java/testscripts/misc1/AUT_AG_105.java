package testscripts.misc1;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * 
 * @author Naresh Kuamr Salla
 *
 */
public class AUT_AG_105 extends TestCase
{
	private FunctionalLibrary functionalLibrary;
	
	@Test
	public void MISC1_013()
	{
		testParameters.setCurrentTestDescription("Verify tooltip information is displayed correctly instead of javascript label on hovering the 'Use Current Page' sub menu present under 'Username' menu."
				+"Verify tooltip information is displayed correctly instead of javascript label on hovering the 'Use My Dashboard' sub menu present under 'Username' menu."+
				"Verify tooltip information is displayed correctly instead of javascript label on hovering the 'Create Site' sub menu present under ''Sites' menu."+
				"Verify tooltip information is displayed correctly instead of javascript label on hovering the 'Favorites' sub menu present under 'Sites' menu");
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
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);
		AlfrescoHomePage homePage = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
	
		AlfrescoSitesDashboardPage sitesDashboardPg = new AlfrescoSitesDashboardPage(
				scriptHelper);

		WebElement e1=driver.findElement(By.id("HEADER_USER_MENU_POPUP_text"));
	   
		/*
		
		if (UIHelper.checkForAnWebElement(e1))
		{
			UIHelper.highlightElement(driver, e1);
		report.updateTestLog("Verify user menu is clicked", "User menu is clicked:",
			 Status.PASS);
	}
		report.updateTestLog("Verify user menu is clicked", "User menu is clicked:"
			, Status.FAIL);
			*/
		
		e1.click();
		 report.updateTestLog("Verify user menu is clicked", "User menu is clicked",
				 Status.PASS);
		UIHelper.waitFor(driver);
		
	//checking for tooltip of Use Current Page	

	/*	Actions action = new Actions(driver);*/
		WebElement e2=driver.findElement(By.xpath("//*[@alt='Use Current Page']"));
		WebElement e3=driver.findElement(By.id("HEADER_USER_MENU_SET_CURRENT_PAGE_AS_HOME_text"));
		UIHelper.mouseOveranElement(driver, e3);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		String title1 =e2.getAttribute("title");
		System.out.println(title1);
		//action.moveToElement(e2).build().perform();
		UIHelper.waitFor(driver);
		Assert.assertEquals("Use Current Page",title1);
		report.updateTestLog("Verify on mouse hover Use Current Page tooltip information is getting displayed ", "Use Current Page tooltip information is displayed"
			, Status.PASS);

		UIHelper.waitFor(driver);
		
		//checking for tooltip of Use My Dashboard
		WebElement dashboard=driver.findElement(By.xpath("//*[@alt='Use My Dashboard']"));
		WebElement e4=driver.findElement(By.id("HEADER_USER_MENU_SET_DASHBOARD_AS_HOME_text"));
		UIHelper.mouseOveranElement(driver, e4);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		String title2 =dashboard.getAttribute("title");
		System.out.println(title2);
		//action.moveToElement(e2).build().perform();
		UIHelper.waitFor(driver);
		Assert.assertEquals("Use My Dashboard",title2);
		report.updateTestLog("Verify on mouse hover Use My Dashboard tooltip information is getting displayed ", "Use My Dashboard tooltip information is displayed"
			, Status.PASS);
		UIHelper.waitFor(driver);
		
		//checking for tooltip of Create Site
		
		WebElement site =driver.findElement(By.id("HEADER_SITES_MENU_text"));
		site.click();
		 report.updateTestLog("Verify Site menu is clicked", "Site menu is clicked",
				 Status.PASS);
		UIHelper.waitFor(driver);
		
		WebElement Createsite=driver.findElement(By.xpath("//*[@alt='Create Site']"));
		WebElement e5=driver.findElement(By.id("HEADER_SITES_MENU_CREATE_SITE_text"));
		UIHelper.mouseOveranElement(driver, e5);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		String title3 =Createsite.getAttribute("title");
		System.out.println(title3);
		
		UIHelper.waitFor(driver);
		Assert.assertEquals("Create Site",title3);
		report.updateTestLog("Verify on mouse hover Create site tooltip information is getting displayed ", "Craete site tooltip information is displayed"
			, Status.PASS);
		UIHelper.waitFor(driver);
		
		//checking for tooltip of Favorites
		
		WebElement Favourites=driver.findElement(By.xpath("//*[@alt='Favorites']"));
		WebElement e6=driver.findElement(By.id("HEADER_SITES_MENU_FAVOURITES"));
		UIHelper.mouseOveranElement(driver, e6);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		String title4 =Favourites.getAttribute("title");
		System.out.println(title4);
		
		UIHelper.waitFor(driver);
		Assert.assertEquals("Favorites",title4);
		report.updateTestLog("Verify on mouse hover Favourite tooltip information is getting displayed ", "Favourite tooltip information is displayed"
			, Status.PASS);
		UIHelper.waitFor(driver);
		
		//checking for tooltip of Remove current site from Favorites
		
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		sitesPage.siteFinder(siteassertValue);
		WebElement site1 =driver.findElement(By.id("HEADER_SITES_MENU_text"));
		site1.click();
		UIHelper.waitFor(driver);
		
		WebElement Recent=driver.findElement(By.xpath("//*[@alt='Remove current site from Favorites']"));
		WebElement e7=driver.findElement(By.id("HEADER_SITES_MENU_REMOVE_FAVOURITE_text"));
		UIHelper.mouseOveranElement(driver, e7);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		String title5 =Recent.getAttribute("title");
		System.out.println(title5);
		
		UIHelper.waitFor(driver);
		Assert.assertEquals("Remove current site from Favorites",title5);
		report.updateTestLog("Verify on mouse hover Remove current site from Favorites tooltip information is getting displayed ", "Remove current site from Favorites tooltip information is displayed"
			, Status.PASS);
		UIHelper.waitFor(driver);
		
	}
	
	
		
		
	
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}