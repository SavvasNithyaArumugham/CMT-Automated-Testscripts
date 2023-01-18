package testscripts.cmtregression;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class CMTintermediaries extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Ensure Upload intermedairy and Export Intermediary options are available");
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
		String uploadBtnXpath="//*[@class='cmButton cmButton-green']";
		String filepath =dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "CreateFolder");
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			//Click on Manage tab
			managePage.clickManage();
			UIHelper.waitFor(driver);
			//Choose Manage Product
			Select selectBox = new Select(driver.findElement(By.cssSelector("li:nth-child(6) > select:nth-child(3)")));
			selectBox.selectByVisibleText("Upload/Export Intermediary");
			UIHelper.waitFor(driver);
			
			//Check if list of disciplines is available
			String selectDisc ="//select[@ng-model='injectCurriculumBean.curriculumUploadBean.subject']";
			Select selectDiscipline = new Select(driver.findElement(By.xpath(selectDisc)));
			selectDiscipline.selectByVisibleText("Mathematics");
			report.updateTestLog("List of disciplines in Intermediary page","User is able to select list of disciplines in the Intermediary page", Status.PASS);
			//Click on Export for Re-Ingestion
			String exportXpath="//div[@data-ng-click='exportIntermediary()']";
		UIHelper.findAnElementbyXpath(driver, exportXpath).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		//Download the generated file and wait for file download
		String downloadXpath="//div[@data-ng-click='downloadFile()']//a[text()='Download file']";
		UIHelper.findAnElementbyXpath(driver, downloadXpath).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String filename = dataTable.getData("MyFiles", "FileName");
		File downloadedFile = null;
		 Date date = new Date();
		 String currentDate = new SimpleDateFormat("ddMMMyyyy").format(date);
		// CSVfile presence and filename
		String fileName1 = filename + currentDate +".xls";
		downloadedFile = new File(downloadFilePath + "/" + fileName1);
		System.out.println(downloadedFile);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		if (downloadedFile.exists()&& !fileName1.equalsIgnoreCase("File Not Found"))
		{
			report.updateTestLog("Verify download file", "File: " + fileName1
					+ " downloaded sucessfully", Status.PASS);
		}
		else
		{
			report.updateTestLog("Verify download file", "File: " + fileName1
					+ "file not downloaded ", Status.FAIL);	
		}
					
			UIHelper.waitFor(driver);
			selectDiscipline.selectByVisibleText("Social Studies - Digital");
			UIHelper.waitFor(driver);
			managePage.uploadIntermediary(uploadBtnXpath,filepath,fileName);
			UIHelper.waitFor(driver);
			String uploadXpath ="//button[@type='submit']";
			UIHelper.findAnElementbyXpath(driver,uploadXpath).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			String successXpath="//div[@id='confirmationPopup']";
			String successMessage = driver.findElement(By.xpath(successXpath)).getText();
			System.out.println(successMessage);
			if (successMessage.contains("uploaded successfully."))
			{
			report.updateTestLog("select and upload an Intermediary spreadsheet","Upload is successful", Status.PASS);	
				
			}
			else
			{
			report.updateTestLog("select and upload an Intermediary spreadsheet","Upload is not successful", Status.FAIL);	
			}
		
			
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
