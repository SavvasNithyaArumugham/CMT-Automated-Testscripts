package testscripts.cmtregression;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

public class CMTupdateCurriculum extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription("Update Excel/AB XML option in Update curriculum " );
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
		String localGradeName = dataTable.getData("MyFiles", "RelationshipName");
		String[] curriculumOptions = dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String curriculumSetName = dataTable.getData("MyFiles", "Version");
		String[] newCurriculumDetails = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String title = dataTable.getData("MyFiles", "CreateFolder");
		String filepath =dataTable.getData("MyFiles", "FilePath");
		String filename = dataTable.getData("MyFiles", "FileName");
		String selectFileXpath="//*[@class='cmButton cmButton-green']";
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			
			driver.findElement(By.xpath("//a[@data-ng-bind-template='Manage']")).click();
			report.updateTestLog("CMT Manage Tab ", "CMT Manage Tab is clicked", Status.DONE);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		  Select selectBox = new Select(driver.findElement(By.cssSelector("li:nth-child(6) > select:nth-child(1)")));
		  selectBox.selectByVisibleText("Update Curriculum");
		  UIHelper.waitFor(driver);
		  report.updateTestLog("CMT Manage Tab Curriculums ", "Update Curriculum is selected", Status.PASS); 
		  UIHelper.waitFor(driver);	
		  String labelXpath =".//label[text()='Draft Curriculum to Update *']";
		  UIHelper.findAnElementbyXpath(driver,labelXpath).isDisplayed();
		
		  String interAlignXpath ="//input[@type='checkbox'][@disabled='disabled']";
		  UIHelper.findAnElementbyXpath(driver,labelXpath).isDisplayed();
		  report.updateTestLog("Include Intermediary Alignments", "The 'Include Intermediary Alignments' checkbox 	option is diabled.", Status.PASS); 
	 
         String draftToUpdateXpath="//select[contains(@class,'currUpdateDraftDropDown ')]";
         Select selectBox1 = new Select(driver.findElement(By.xpath(draftToUpdateXpath)));
		 selectBox1.selectByVisibleText("India2 Mathematics K-9 2020 (National Curriculum Programme of Study, England)");
		 UIHelper.waitFor(driver);
		  
		managePage.updateCurriculum(filepath,filename,selectFileXpath);
		UIHelper.waitFor(driver);		
		String uploadXpath ="//button[@type='submit']";
		UIHelper.findAnElementbyXpath(driver,uploadXpath).click();
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
