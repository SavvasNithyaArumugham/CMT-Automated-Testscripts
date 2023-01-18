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

public class CMTManageNewCurriculumRejected extends TestCase {

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
				String title = dataTable.getData("MyFiles", "CreateFolder");
				try {
				UIHelper.waitFor(driver);
				functionalLibrary.loginAsValidUser(signOnPage);
				UIHelper.waitFor(driver);
				
				managePage.clickManage(); 
				UIHelper.waitFor(driver);
				
				//Click Awaiting Approval 
				WebElement ele2 = driver.findElement(By.xpath("//div[contains(@class,'curriculumMenu curriculumMenuPanel')]//a[text()='Awaiting approval']")); 
				JavascriptExecutor executor2 = (JavascriptExecutor)driver;
				executor2.executeScript("arguments[0].click();", ele2); 
				UIHelper.waitFor(driver);
				
				String draftSearchBox="//div[@class='filterContent']//input[@type='text']";
				UIHelper.findAnElementbyXpath(driver, draftSearchBox).sendKeys(title);
				
				  if(driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[3]")).isDisplayed())
				  {
					  UIHelper.waitFor(driver);
					  //Click Approve				  
					  WebElement ele3 = driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[3]"));
					  JavascriptExecutor executor3 = (JavascriptExecutor)driver;
					  executor3.executeScript("arguments[0].click();", ele3);
					  UIHelper.waitFor(driver);
					  UIHelper.waitFor(driver);
					  UIHelper.waitFor(driver);
					  
					//Click Reject pop up
					  WebElement ele4 =driver.findElement(By.xpath("//input[@class='cmButton cmButtonFull-green']"));
					  JavascriptExecutor executor4 = (JavascriptExecutor)driver;
					  executor4.executeScript("arguments[0].click();", ele4);
					  report.updateTestLog("Curriculum Reject","The curriculum is in rejected tab", Status.PASS);
				  }
				  else 
				  {
					  report.updateTestLog("Curriculum Reject","The curriculum is not in Rejected tab", Status.FAIL);
				  }
				  UIHelper.waitFor(driver);
				  
				  //Click Rejected Tab to check 
				  
				  WebElement ele3 = driver.findElement(By.xpath("//div[contains(@class,'curriculumMenu curriculumMenuPanel')]//a[text()='Rejected']")); 
					JavascriptExecutor executor3 = (JavascriptExecutor)driver;
					executor3.executeScript("arguments[0].click();", ele3); 
					UIHelper.waitFor(driver);
					
					UIHelper.findAnElementbyXpath(driver, draftSearchBox).sendKeys(title);
					
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					
					if(driver.findElement(By.xpath("//td[@class='curriculumButtonPanel']//input[1]")).isDisplayed())
					{
						  UIHelper.waitFor(driver);
						  report.updateTestLog("Curriculum Rejected","The curriculum is in the Rejected Tab", Status.PASS);
					}
					else
					{
						  report.updateTestLog("Curriculum Rejected","The curriculum is not in the Rejected Tab", Status.FAIL);
					}

					UIHelper.waitFor(driver);
		
			 
			}catch(Exception e) {
					e.printStackTrace();
					  report.updateTestLog("Curriculum Approval","The curriculum is not in the Approved Tab", Status.FAIL);
			}
	}

	@Override
	public void tearDown() {

	}
}
