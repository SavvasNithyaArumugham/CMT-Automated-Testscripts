package testscripts.search;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoAdminToolsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesDashboardPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentDetailsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoDocumentLibPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSearchPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoSitesDashboardPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

/**
 * Test for login with invalid user credentials
 * 
 * @author Moshin
 */
public class AUT_AG_156 extends TestCase {
private FunctionalLibrary functionalLibrary;
	
	@Test
	public void search_155()
	{
		testParameters.setCurrentTestDescription("VVerify that user is able to view the content on "
				+ "Search result page with classifiable aspect through Aspect Search in Advance Search.");
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
		
		AlfrescoDocumentDetailsPage docDetailsPage = new AlfrescoDocumentDetailsPage(
				scriptHelper);
		AlfrescoDocumentLibPage docLibPge = new AlfrescoDocumentLibPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		String siteassertValue = dataTable.getData("Sites", "SiteName");
		String fileName = dataTable.getData("MyFiles", "FileName");
		sitesPage.siteFinder(siteassertValue);
	
		sitesPage.enterIntoDocumentLibrary();
		docLibPge.deleteAllFilesAndFolders();
		
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			String fileDetails = dataTable.getData("MyFiles", "CreateFileDetails");
			String rootCategory = dataTable.getData("Admin", "EditCategoryName");
			String category = dataTable.getData("Admin", "CategoryName");
			
			myFiles.createFile(fileDetails);
			docDetailsPage.performManageAspectsDocAction();
			docDetailsPage.addAspectsAndApllyChangesToAFile();
			docDetailsPage.performEditPropertiesDocAction();
			//docDetailsPage.enterDataAndSaveIntoEditProperties();
			
		AlfrescoAdminToolsPage appAdminToolsPg = new AlfrescoAdminToolsPage(scriptHelper);
		appAdminToolsPg.addNewCatMgmt();
		appAdminToolsPg.saveTag();
	
		AlfrescoSearchPage appSearchPg = new AlfrescoSearchPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		homePageObj.navigateToAdvSearch();
		
		appSearchPg.clickShowMore();
	//	appSearchPg.inputAspectAdvSearch();
		
		
		try {
			UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver,
					appSearchPg.aspectPanelXpath);
			WebElement dropdownEle = UIHelper.findAnElementbyXpath(driver,
					appSearchPg.AspectDropDownXpath);
			String Aspect = dataTable.getData("Search", "Aspect");
			String AspectProp = dataTable.getData("Search", "AspectProp");
			String Aspectvalue = dataTable.getData("Search", "Query");
			Select selectBox = new Select(dropdownEle);
			selectBox.selectByValue("cm:generalclassifiable");
			Select selectpropBox = new Select(driver.findElement(By
					.xpath(appSearchPg.AspectPropDropDownXpath)));
			selectpropBox.selectByVisibleText(AspectProp);
			UIHelper.highlightElement(driver,
					UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectValueXpath));
			UIHelper.sendKeysToAnElementByXpath(driver, appSearchPg.AspectValueXpath,
					Aspectvalue);
			if (UIHelper.findAnElementbyXpath(driver, appSearchPg.AspectPropDropDownXpath)
					.isEnabled()) {
				report.updateTestLog("Input Aspect Name",
						"Search using Aspect Name "
								+ "<br /><b>Aspect Name :</b>" + Aspect
								+ "<br /><b>Aspect Properties :</b>"
								+ AspectProp + "<br /><b>Aspect Value :</b>"
								+ Aspectvalue, Status.PASS);
			} else {
				report.updateTestLog("Input Aspect Name",
						"Input aspect value in Adv search Failed ", Status.FAIL);
			}

		} catch (Exception e) {
			report.updateTestLog("Input Aspect Name",
					"Input aspect value in Adv search Failed ", Status.FAIL);
		}
		appSearchPg.clickSearch();
		
		
		
		
		
		
	}
	
	@Override
	public void tearDown()
	{
		// Nothing to do
	}
}