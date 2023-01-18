package testscripts.cmtregression;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtHomePage;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTeditProgramCourseProduct extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Click on edit option available before each Program, Course and Product");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog("Invoke Application",
				"Invoke the application under test @ " + properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
		CmtLoginPage signOnPage = new CmtLoginPage(scriptHelper);
		CmtManagePage managePage = new CmtManagePage(scriptHelper);
		CmtHomePage cmtPage = new CmtHomePage(scriptHelper);
		String program = dataTable.getData("MyFiles", "CreateFolder");
		String course = dataTable.getData("MyFiles", "Version");
		String product = dataTable.getData("MyFiles", "CreateFileDetails");
		String country = dataTable.getData("MyFiles", "MoreSettingsOption");
		String state = dataTable.getData("MyFiles", "RelationshipName");
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			//Click on Manage tab
			managePage.clickManage();
			UIHelper.waitFor(driver);
			//Choose Manage Product
			Select selectBox = new Select(driver.findElement(By.cssSelector("li:nth-child(6) > select:nth-child(2)")));
			selectBox.selectByVisibleText("Manage Product");
			UIHelper.waitFor(driver);
			//Check if list of disciplines is available
			String listOfDisc ="//*[@id='productBrowseTree']";
			driver.findElement(By.xpath(listOfDisc)).isDisplayed();
			report.updateTestLog("List of disciplines in the Manage Product page","User is able to view list of disciplines in the Manage Product page", Status.PASS);
			
						
			//Add program
			managePage.editProgram(program);
			UIHelper.waitFor(driver);
			// Add Course
			managePage.editCourse(course);
			UIHelper.waitFor(driver);
			//Add Product
			managePage.editProduct(product,country,state);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		
			} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
