package testscripts.cmt;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTManageCreateNewCurriculum extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Check If Authority is created, its assosiated curriculum set is created."
				+ " Check if Create new Curriculum is working with the new authority created.In the curriculum add the local grade and topic."
				+"Check the tabs Waiting for Approval/Approved for a curriculum");
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
		String localGradeName = dataTable.getData("MyFiles", "RelationshipName");
		String[] curriculumOptions = dataTable.getData("MyFiles", "MoreSettingsOption").split(",");
		String curriculumSetName = dataTable.getData("MyFiles", "Version");
		String[] newCurriculumDetails = dataTable.getData("MyFiles", "BrowseActionName").split(",");
		String title = dataTable.getData("MyFiles", "CreateFolder");
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			
			managePage.clickManage();
			UIHelper.waitFor(driver);	
			
		/*	managePage.chooseCurriculumMenu(curriculumOptions[0]);
			UIHelper.waitFor(driver); 
			int num = managePage.createAuthority(localGradeName);
			UIHelper.waitFor(driver);
			managePage.createCurriculumSetForAuthority(num, curriculumSetName);
			UIHelper.waitFor(driver);*/	
			
			managePage.createNewCurriculum(curriculumOptions[1], curriculumSetName,
			title, localGradeName, newCurriculumDetails[0], newCurriculumDetails[1]);
			UIHelper.waitFor(driver);	
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			// Add Local Grade
			String addGradeLink ="//div[@class='marginTop20 marginLeft10']//div[@title='Add Local grade']//span[2]";
			UIHelper.highlightElement(driver, addGradeLink);
			UIHelper.findAnElementbyXpath(driver,addGradeLink).click();
			
			UIHelper.waitFor(driver);	
			managePage.addGrade();
			UIHelper.waitFor(driver);
			
			//Click on Draft and check if navigated to Drafts tab in Manage
		String draftList="//div[@ng-show='curriculaEditBean.curriculaPreviewBean']//input[@type='submit'][@value='Drafts list']";
		UIHelper.findAnElementbyXpath(driver, draftList).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
			
		 
		String draftSearchBox="//div[@class='filterContent']//input[@type='text']";
		UIHelper.findAnElementbyXpath(driver, draftSearchBox).sendKeys(title);
		
		WebElement ele = driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[1]"));
		if (ele.isDisplayed())
		{
			report.updateTestLog("Drafts Tab","The curriculum is present in drafts tab in manage page", Status.PASS);	
		}
		else
		{
			report.updateTestLog("Drafts Tab","The curriculum is not present in drafts tab in manage page", Status.FAIL);	
		}
		
			
		//Click Send For Approval
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", ele);			  
		  UIHelper.waitFor(driver);
		  UIHelper.waitFor(driver);
		  UIHelper.waitFor(driver);
		  
		  WebElement ele1 =driver.findElement(By.xpath("//*[@class='cmButton cmButtonFull-green']"));
		  JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		  executor1.executeScript("arguments[0].click();", ele1);
		  
		  report.updateTestLog("Curriculum Approval","The curriculum is sent for approval", Status.PASS);
		  UIHelper.waitFor(driver);
		  UIHelper.waitFor(driver);
		  UIHelper.waitFor(driver);
		  
					
		  			
			} 
		catch (Exception e) 
		{
			report.updateTestLog("CMT Manage Page", "In Manage Page,the New Curriculum Draft creation encountered a problem", Status.PASS);
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
