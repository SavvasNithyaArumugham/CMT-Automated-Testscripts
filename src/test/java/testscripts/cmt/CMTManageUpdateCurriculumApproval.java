package testscripts.cmt;

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

public class CMTManageUpdateCurriculumApproval extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome72() {
		testParameters.setCurrentTestDescription("Check the tabs Waiting for Approval/Approved for a curriculum");
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
				String[] curriculumOptions = dataTable.getData("MyFiles", "FileName").split(",");
				try {
				UIHelper.waitFor(driver);
				functionalLibrary.loginAsValidUser(signOnPage);
				UIHelper.waitFor(driver);
				managePage.clickManage(); 
				UIHelper.waitFor(driver);
				managePage.chooseFilterOptions("Science","India");			 
				
				String xpath1="//*[contains(text(),"+"'"+curriculumOptions[1]+"'"+")]";
				
			  if(driver.findElement(By.xpath(xpath1)).isDisplayed()){ 
				  //Click Send For Approval
			  WebElement ele = driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[1]"));
			  JavascriptExecutor executor = (JavascriptExecutor)driver;
			  executor.executeScript("arguments[0].click();", ele);			  
			  UIHelper.waitFor(driver);UIHelper.waitFor(driver);UIHelper.waitFor(driver);
			  
			  WebElement ele1 =driver.findElement(By.xpath("//*[@class=\"cmButton cmButtonFull-green\"]"));
			  JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			  executor1.executeScript("arguments[0].click();", ele1);
			  report.updateTestLog("Curriculum Approval","The curriculum is sent for approval", Status.PASS);
			  }else {
				  report.updateTestLog("Curriculum Approval","The curriculum is not sent for approval", Status.FAIL);
			  }
			  UIHelper.waitFor(driver);			 
			  //Click Awaiting Approval 
				WebElement ele2 = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/div[1]/div/ul/li[2]/a")); 
				JavascriptExecutor executor2 = (JavascriptExecutor)driver;
				executor2.executeScript("arguments[0].click();", ele2); 
			  //Choose the subject
				try {
					Select selectBox2 = new Select(driver.findElement
							(By.xpath("/html/body/div[1]/div[4]/div[3]/div[2]/div[1]/div/div[1]/select")));
					UIHelper.waitFor(driver); 
					selectBox2.selectByVisibleText("Science");
					}
				catch(org.openqa.selenium.StaleElementReferenceException ex)
					{
					Select selectBox2 = new Select(driver.findElement
							(By.xpath("/html/body/div[1]/div[4]/div[3]/div[2]/div[1]/div/div[1]/select")));
					UIHelper.waitFor(driver); 
					selectBox2.selectByVisibleText("Science");
					}
			  UIHelper.waitFor(driver); 
			  //Choose the country 
			  Select selectBox3 = new
			  Select(driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/div[2]/div[1]/div/div[2]/select")));
			  UIHelper.waitFor(driver); 
			  selectBox3.selectByVisibleText("India");
			  
			  UIHelper.waitFor(driver);
			  
			  if(driver.findElement(By.xpath(xpath1)).isDisplayed()){
				  UIHelper.waitFor(driver);
				  //Click Approve				  
				  WebElement ele = driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[2]"));
				  JavascriptExecutor executor = (JavascriptExecutor)driver;
				  executor.executeScript("arguments[0].click();", ele);
				  UIHelper.waitFor(driver);UIHelper.waitFor(driver);UIHelper.waitFor(driver);
				//Click Approve pop up
				  WebElement ele1 =driver.findElement(By.xpath("//*[@class=\"cmButton cmButtonFull-green\"]"));
				  JavascriptExecutor executor1 = (JavascriptExecutor)driver;
				  executor1.executeScript("arguments[0].click();", ele1);
				  report.updateTestLog("Curriculum Approval","The curriculum is in Awaiting for approval tab", Status.PASS);
			  }else {
				  report.updateTestLog("Curriculum Approval","The curriculum is not in Awaiting for approval tab", Status.FAIL);
			  }
			  UIHelper.waitFor(driver);
		
			  //Click Approved Tab 
			  WebElement ele3 = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/div[1]/div/ul/li[3]/a")); 
				JavascriptExecutor executor3 = (JavascriptExecutor)driver;
				executor3.executeScript("arguments[0].click();", ele3); 
				UIHelper.waitFor(driver);
				
				managePage.chooseFilterOptions("Science","India");
				
				UIHelper.waitFor(driver);UIHelper.waitFor(driver);
				
				if(driver.findElement(By.xpath(xpath1)).isDisplayed()){
					  UIHelper.waitFor(driver);
					  report.updateTestLog("Curriculum Approval","The curriculum is in the Approved Tab", Status.PASS);
				}else {
					  report.updateTestLog("Curriculum Approval","The curriculum is not in the Approved Tab", Status.FAIL);
				}
			}catch(Exception e) {
					e.printStackTrace();
					  report.updateTestLog("Curriculum Approval","The curriculum is not in the Approved Tab", Status.FAIL);
			}
	}

	@Override
	public void tearDown() {

	}
}
