package testscripts.cmt;

import java.util.List;

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

public class CMTManageUnApprovalProcess extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome72() {
		testParameters.setCurrentTestDescription("Check the UnApproval Process for a curriculum");
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
				String xpath1="//*[contains(text(),"+"'"+curriculumOptions[1]+"'"+")]";
							  
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
				UIHelper.waitFor(driver);UIHelper.waitFor(driver);
			
			
			  List<WebElement> tdlist = driver.findElements(By.cssSelector("table[class='curriculumTable'] tr"));
			  for(WebElement el: tdlist) { 
				  System.out.println(el.getText());
			  }
			 
			
				 
				UIHelper.waitFor(driver);UIHelper.waitFor(driver);
			}catch(Exception e) {
					e.printStackTrace();
					  report.updateTestLog("Curriculum Approval","The curriculum is not in the Approved Tab", Status.FAIL);
			}
	}

	@Override
	public void tearDown() {

	}
}
