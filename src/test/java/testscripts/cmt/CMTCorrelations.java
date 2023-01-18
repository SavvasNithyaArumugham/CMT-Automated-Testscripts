package testscripts.cmt;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.cmt.functionllibs.FunctionalLibrary;
import com.pearson.automation.cmt.pages.CmtLoginPage;
import com.pearson.automation.cmt.pages.CmtManagePage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class CMTCorrelations extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"To validate the Functionality of intermediary alignments");
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
		String Discipline = dataTable.getData("MyFiles", "CreateFileDetails");
		String Country = dataTable.getData("MyFiles", "CreateFolder");
		String Curriculum = dataTable.getData("MyFiles", "Version");
		String Grade=dataTable.getData("MyFiles", "CreateChildFolder");
		String Discipline1 = dataTable.getData("MyFiles", "Sort Options");
		String Country1 = dataTable.getData("MyFiles", "AccessToken");
		String Curriculum1 = dataTable.getData("MyFiles", "PopUpMsg");
		String Grade1=dataTable.getData("MyFiles", "StatusReportValue");
		String fileName=dataTable.getData("MyFiles", "FileName");
				
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickCorrelations();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Correlations Reporting Dashboard", "Correlations Reporting Dashboard page displayed", Status.PASS);
			
			//		Select Discipline
					Select selectDiscipline = new Select(driver.findElement(By.xpath("//*[@id='left-standard-tab']/div[1]/select"))); 
					UIHelper.waitFor(driver);
					selectDiscipline.selectByVisibleText(Discipline);
					UIHelper.waitFor(driver);
				//	Select Country
					Select selectCountry = new Select(driver.findElement(By.xpath("//*[@id='left-standard-tab']/div[2]/select"))); 
					UIHelper.waitFor(driver);
					selectCountry.selectByVisibleText(Country);
					UIHelper.waitFor(driver);
				//	Select Curriculum/Standard
					Select selectCurriculum = new Select(driver.findElement(By.xpath("//*[@id='left-standard-tab']/div[3]/select"))); 
					UIHelper.waitFor(driver);
					selectCurriculum.selectByVisibleText(Curriculum);
					UIHelper.waitFor(driver);
				//	Select Grade
					Select selectGrade = new Select(driver.findElement(By.xpath("//*[@id='left-standard-tab']/div[4]/select"))); 
					UIHelper.waitFor(driver);
					selectGrade.selectByVisibleText(Grade);
					UIHelper.waitFor(driver);
								
			// Click on "SELECT" button
				driver.findElement(By.xpath("//*[@id='left-standard-tab-content']/div/div[2]/div/input")).click(); 
				UIHelper.waitFor(driver);	
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
					
				//	Select Discipline
					Select selectDiscipline1 = new Select(driver.findElement(By.xpath("//*[@id='right-standard-tab']/div[1]/select"))); 
					UIHelper.waitFor(driver);
					selectDiscipline1.selectByVisibleText(Discipline1);
					UIHelper.waitFor(driver);
				//	Select Country
					Select selectCountry1 = new Select(driver.findElement(By.xpath("//*[@id='right-standard-tab']/div[2]/select"))); 
					UIHelper.waitFor(driver);
					selectCountry1.selectByVisibleText(Country1);
					UIHelper.waitFor(driver);
				//	Select Curriculum/Standard
					Select selectCurriculum1 = new Select(driver.findElement(By.xpath("//*[@id='right-standard-tab']/div[3]/select"))); 
					UIHelper.waitFor(driver);
					selectCurriculum1.selectByIndex(2);
					UIHelper.waitFor(driver);	
				//	Select Grade
					Select selectGrade1 = new Select(driver.findElement(By.xpath("//*[@id='right-standard-tab']/div[4]/select"))); 
					UIHelper.waitFor(driver);
					selectGrade1.selectByIndex(2);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
				//  Click on "ADD" button
					driver.findElement(By.xpath("//*[@id='right-standard-tab-content']/div/div[2]/div/input")).click();
					UIHelper.waitFor(driver);	
			
				// 	
					driver.findElement(By.xpath("//span[@class='posRelative']//input[@type='text']")).sendKeys("AutoamtionTest");
					UIHelper.waitFor(driver);
					
					
					driver.findElement(By.xpath("//div[@class='correlation-save-container posRelative']//input[@type='button']")).click();
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					
			// Success message Check		
					String successMessage=driver.findElement(By.xpath(".//*[@id='confirmationPopup'][@data-ng-show='successMessage']")).getText();
					System.out.println(successMessage);
					if (successMessage.contains("We will notify you shortly") )
					{
						report.updateTestLog("Correlation report generation", "Correlation report generation was successful" + "||" +successMessage, Status.PASS);
					}
					else
					{
						report.updateTestLog("Correlation report generation ", "Correlation report generation was not successful", Status.FAIL);
					}
					
					
			} 
		catch (Exception e) 
		{
			report.updateTestLog("CMT Manage Page", "In Manage Page,the New Curriculum Draft creation encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
