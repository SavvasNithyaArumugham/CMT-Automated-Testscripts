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

public class CMTManageUploadTOCUpload extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Upload new TOC file, product tab validation ");
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
		String option = dataTable.getData("MyFiles", "CreateFileDetails");
		String discipline = dataTable.getData("MyFiles", "CreateFolder");
		String program = dataTable.getData("MyFiles", "Version");
		String filePath=dataTable.getData("MyFiles", "FilePath");
		String fileName=dataTable.getData("MyFiles", "FileName");
		String course = null;
		String product = null;
		
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickManage();
			UIHelper.waitFor(driver);			
			managePage.uploadNewTOC(option, discipline, program, course, product);
			UIHelper.waitFor(driver);
			//Click on "Select File"
	
			String uploadBtnXpath="//*[@class='cmButton cmButton-green']";
			String finalFilePath = System.getProperty("user.dir") + filePath;
			System.out.println(finalFilePath);
			UIHelper.waitFor(driver);
			managePage.uploadFileWithRobot(finalFilePath,fileName);
			UIHelper.waitFor(driver);
			
			UIHelper.findAnElementbyXpath(driver,".//button[@type='submit']").click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			WebElement eleOkButton =driver.findElement(By.xpath("//*[@class='form-horizontal childTwoPop']//input[@id='buttonOk']"));
			JavascriptExecutor executor2 = (JavascriptExecutor)driver;
			executor2.executeScript("arguments[0].click();", eleOkButton);
						
			String successMessage=driver.findElement(By.xpath("//div[contains(@class,'alert alert-success')][@data-ng-show='successMessage']")).getText();
			System.out.println(successMessage);
			if (successMessage.contains("Uploading...") )
			{
				report.updateTestLog("Uploading the TOC ", "Uploading the TOC is working as expected", Status.PASS);
			}
			else
			{
				report.updateTestLog("Uploading the TOC ", "Uploading the TOC is not working as expected", Status.FAIL);
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
