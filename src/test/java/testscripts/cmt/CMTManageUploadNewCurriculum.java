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

public class CMTManageUploadNewCurriculum extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void CMTHome71() {
		testParameters.setCurrentTestDescription(
				"Upload new curriculum");
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
		String filepath =dataTable.getData("MyFiles", "FilePath");
		String filename = dataTable.getData("MyFiles", "FileName");
		String uploadBtnXpath="//*[@class='cmButton cmButton-green']";
		try {
			UIHelper.waitFor(driver);
			functionalLibrary.loginAsValidUser(signOnPage);
			UIHelper.waitFor(driver);
			managePage.clickManage();
			UIHelper.waitFor(driver);		
			
			managePage.uploadNewCurriculum(uploadBtnXpath,curriculumOptions[0], curriculumSetName,
			title, localGradeName, newCurriculumDetails[0], newCurriculumDetails[1],filepath,filename);
			UIHelper.waitFor(driver);		
			
			String uploadXpath ="//div[@class='uploader']//input[@class='cmButton cmButton-green uploadSelectBtn']";
			
			//Scroll to end of page so that "Upload" is visible
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
			WebElement showUpload = driver.findElement(By.xpath(uploadXpath));
			UIHelper.scrollToAnElement(showUpload);
			UIHelper.waitFor(driver);
			
			UIHelper.highlightElement(driver, uploadXpath);
			UIHelper.waitFor(driver);
			
			UIHelper.findAnElementbyXpath(driver,uploadXpath).click();
			UIHelper.waitFor(driver);
			
		//Upload Curriculum
			String finalFilePath = System.getProperty("user.dir") + filepath;
			System.out.println(finalFilePath);
			UIHelper.waitFor(driver);
			
			String uploadButton ="//div[@class='uploader']//div[@class='control-group']//div[2]//button";
			managePage.uploadFileWithRobot(finalFilePath,filename);
			UIHelper.waitFor(driver);
			
			UIHelper.findAnElementbyXpath(driver, uploadButton).click();
			
			UIHelper.waitForLong(driver);
			UIHelper.waitFor(driver);
			
			//Check send for approval button 
			
			String sendForApproval="//div[@class='currPreviewHeaderButtonPanel']//input[@value='Send for approval']";
			UIHelper.highlightElement(driver, sendForApproval);
			UIHelper.findAnElementbyXpath(driver, sendForApproval).click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			//Click Send For Approval popup
			 			  
			  WebElement ele1 =driver.findElement(By.xpath("//*[@class='cmButton cmButtonFull-green']"));
			  JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			  executor1.executeScript("arguments[0].click();", ele1);
			  
			  report.updateTestLog("Curriculum Approval","The curriculum is sent for approval", Status.PASS);
			  UIHelper.waitFor(driver);
			  UIHelper.waitFor(driver);
			  UIHelper.waitFor(driver);
			// page navigates to awaiting approval , click approve button in the page
			  String draftSearchBox="//div[@class='filterContent']//input[@type='text']";
				UIHelper.findAnElementbyXpath(driver, draftSearchBox).sendKeys("AutoUploadNewCurriculum");
				
				  if(driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[2]")).isDisplayed())
					  report.updateTestLog("CMT Manage Page", "In Manage Page,the Upload New Curriculum is present in Awaiting approval tab", Status.PASS);
				  {
					  UIHelper.waitFor(driver);
					  
					  //Click Approve				  
					  WebElement ele3 = driver.findElement(By.xpath(".//*[contains(@class,'curriculumTable')]//td[1]//input[2]"));
					  JavascriptExecutor executor3 = (JavascriptExecutor)driver;
					  executor3.executeScript("arguments[0].click();", ele3);
					  UIHelper.waitFor(driver);
					  
					  WebElement ele5 =driver.findElement(By.xpath("//*[@class='cmButton cmButtonFull-green']"));
					  JavascriptExecutor executor5 = (JavascriptExecutor)driver;
					  executor5.executeScript("arguments[0].click();", ele5);
			
					  UIHelper.waitFor(driver);
					  UIHelper.waitFor(driver);
					  
				  //Click Approved Tab to check 
					  
					  WebElement ele4 = driver.findElement(By.xpath("//div[contains(@class,'curriculumMenu curriculumMenuPanel')]//a[text()='Approved']")); 
						JavascriptExecutor executor4 = (JavascriptExecutor)driver;
						executor4.executeScript("arguments[0].click();", ele4); 
						UIHelper.waitFor(driver);
						
						UIHelper.findAnElementbyXpath(driver, draftSearchBox).sendKeys("AutoUploadNewCurriculum");
						
						UIHelper.waitFor(driver);
						UIHelper.waitFor(driver);
						
						if(driver.findElement(By.xpath("//td[@class='curriculumButtonPanel mbleButtonApprovedTab']//input[1]")).isDisplayed())
						{
							  UIHelper.waitFor(driver);
							  report.updateTestLog("Upload Curriculum Approved","The curriculum is in the approved Tab", Status.PASS);
						}
						else
						{
							  report.updateTestLog("Upload Curriculum Approved","The curriculum is not in the approved Tab", Status.FAIL);
						}

						UIHelper.waitFor(driver);
			
			
			
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
