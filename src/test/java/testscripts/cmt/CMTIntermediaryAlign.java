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

public class CMTIntermediaryAlign extends TestCase {

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
			
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickIntermediaryAlign();
			UIHelper.waitFor(driver);
			
		//	Select Discipline
			Select selectDiscipline = new Select(driver.findElement(By.xpath(".//div[@class='intermediary-list-panel']//select[contains(@class,'ntermediary-dropdown')][@data-ng-model='selectedList.subject']"))); 
			UIHelper.waitFor(driver);
			selectDiscipline.selectByVisibleText(Discipline);
			UIHelper.waitFor(driver);
		//	Select Country
			Select selectCountry = new Select(driver.findElement(By.xpath(".//div[@class='intermediary-list-panel']//select[contains(@class,'ntermediary-dropdown')][@data-ng-model='selectedList.country']"))); 
			UIHelper.waitFor(driver);
			selectCountry.selectByVisibleText(Country);
			UIHelper.waitFor(driver);
		//	Select Curriculum/Standard
			Select selectCurriculum = new Select(driver.findElement(By.xpath(".//div[@class='intermediary-list-panel']//select[contains(@class,'ntermediary-dropdown')][@data-ng-model='selectedList.curriculum']"))); 
			UIHelper.waitFor(driver);
			selectCurriculum.selectByVisibleText(Curriculum);
			UIHelper.waitFor(driver);
		//	Select Grade
			Select selectGrade = new Select(driver.findElement(By.xpath(".//div[@class='intermediary-list-panel']//select[contains(@class,'ntermediary-dropdown')][@data-ng-model='selectedList.grade']"))); 
			UIHelper.waitFor(driver);
			selectGrade.selectByVisibleText(Grade);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
		// Click on Create Alignment	
			driver.findElement(By.xpath("//div[@id='standard-bottom-link']")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			report.updateTestLog("User click on Create Alignment","User click on Create Alignment successfully", Status.DONE);

		
		// Click on Expand all button 
			driver.findElement(By.xpath(".//div[@class='standard-mapping-btn-container']//button[@title='EXPAND ALL']")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath("//*[@id='https://data.savvas.com/curriculum/loc/ed8ea5b5-3fef-4bce-bc4b-64e8d2b5e0af_anchor']/i[1]")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
							
				// Click on Align button	
					driver.findElement(By.xpath(".//div[@class='standard-mapping-btn-container']//button[@title='Align']")).click();
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
				
			// Click on Intermediary List
			driver.findElement(By.xpath("//*[@id='infiniteScrollParentId']/div[1]/div[1]/div/span[3]/img")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			// Click on Cart
			driver.findElement(By.xpath("//*[@id='cart-tab-head']/span[1]")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Cart selected ", "User click on Cart was successful", Status.PASS);
			
			driver.findElement(By.xpath("//*[@id='knowledgeCartId']/div/div[1]/div[1]/input")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			//Select CENTRAL/PERIPHERAL/KEY/NOTE
			
			driver.findElement(By.xpath(".//div[@data-ng-bind-template='CENTRAL']")).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
		
		// Click on save	
			driver.findElement(By.xpath("//*[@id='cart-data-tab']/div/div[2]/div[1]")).click();
			UIHelper.waitFor(driver);
					
			String successMessage=driver.findElement(By.xpath(".//*[@id='confirmationPopup'][@data-ng-show='successMessage']")).getText();
			System.out.println(successMessage);
			if (successMessage.contains("Mappings have been saved successfully") )
			{
				report.updateTestLog("Alignment creation ", "Alignment Creation was successful" + "||"+successMessage, Status.PASS);
			}
			else
			{
				report.updateTestLog("Alignment creation ", "Alignment Creation was not successful", Status.FAIL);
			}
			
			} 
		catch (Exception e) 
		{
			report.updateTestLog("CMT intermediary alignment", "In intermediary alignment page , alignment creation encountered a problem", Status.FAIL);
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {

	}
}
