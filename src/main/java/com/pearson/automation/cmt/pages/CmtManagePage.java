package com.pearson.automation.cmt.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Ordering;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * @author 167346
 *
 */
public class CmtManagePage  extends ReusableLibrary  {

	public CmtManagePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	private String fieldXpath ="//a[@class='ng-binding'][text()='CRAFT']";
	private String objectXpath ="//option[@value='CRAFT']";
	private String curriculumFieldXpath ="//label[text()='CRAFT']";
	// Click the Manage Tab in the CMT Page
	public void clickManage() {		
		try {		
			UIHelper.waitFor(driver);			
			List<WebElement> listOfElements = driver.findElements(By.xpath("//a[@data-ng-bind-template='Manage']"));
			for (int i=0; i<listOfElements.size();i++){
					if(listOfElements.get(i).getText().equalsIgnoreCase("Manage")) {
						listOfElements.get(i).click();
						report.updateTestLog("CMT Page", "Manage button is clicked.", Status.PASS);
						break;
					}
								      
			    }			
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			report.updateTestLog("CMT Page", "Manage button is not clicked.", Status.FAIL);
			e.printStackTrace();
		}		
	}
	
	public boolean isObjectFieldAvailable(String objectFieldName) {
		boolean flag = false;
		try {
			String finalLabelForFieldXapth = fieldXpath.replace("CRAFT", objectFieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,finalLabelForFieldXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalLabelForFieldXapth)))
			{
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalLabelForFieldXapth));
				UIHelper.highlightElement(driver,finalLabelForFieldXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify cmt manage tab object fields",
					"Verify cmt manage tab object fields Failed", Status.FAIL);
		}

		return flag;
	}

	public boolean isObjectOptionAvailable(String objectFieldName) {
		boolean flag = false;
		try {
			String finalLabelForFieldXapth = objectXpath.replace("CRAFT", objectFieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,finalLabelForFieldXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalLabelForFieldXapth)))
			{
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalLabelForFieldXapth));
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver,finalLabelForFieldXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Cmt manage tab  fields",
					"Verify Cmt manage tab fields Failed", Status.FAIL);
		}

		return flag;
	}
	
	public boolean iscurriculumFieldsAvailable(String objectFieldName) {
		boolean flag = false;
		try {
			String finalLabelForFieldXapth = curriculumFieldXpath.replace("CRAFT", objectFieldName);
			UIHelper.waitForVisibilityOfEleByXpath(driver,finalLabelForFieldXapth);
			if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver, finalLabelForFieldXapth)))
			{
				UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, finalLabelForFieldXapth));
				UIHelper.waitFor(driver);
				UIHelper.highlightElement(driver,finalLabelForFieldXapth);
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			report.updateTestLog("Verify Cmt manage tab  curriculum fields",
					"Verify Cmt manage tab curriculum fields Failed", Status.FAIL);
		}

		return flag;
	}

	
	// Click the Intermediary Align in the CMT Page
		public void clickIntermediaryAlign() {		
			try {		
				UIHelper.waitFor(driver);			
				List<WebElement> listOfElements = driver.findElements(By.xpath("//nav[contains(@class,'menuPanelLeft')]//a[contains(@class,'menuPanelItem ng-binding')]"));
				for (int i=0; i<listOfElements.size();i++){
						if(listOfElements.get(i).getText().equalsIgnoreCase("Intermediary Align")) {
							listOfElements.get(i).click();
							report.updateTestLog("CMT Page", "Intermediary Align button is clicked.", Status.PASS);
							break;
							
							
							
						}
									      
				    }			
				UIHelper.waitFor(driver);
			} catch (Exception e) {
				report.updateTestLog("CMT Page", "Intermediary Align button is not clicked.", Status.FAIL);
				e.printStackTrace();
			}		
		}
		
		// Click the Correlations in the CMT Page
				public void clickCorrelations () {		
					try {		
						UIHelper.waitFor(driver);			
						List<WebElement> listOfElements = driver.findElements(By.xpath("//nav[contains(@class,'menuPanelLeft')]//a[contains(@class,'menuPanelItem ng-binding')]"));
						for (int i=0; i<listOfElements.size();i++){
								if(listOfElements.get(i).getText().equalsIgnoreCase("Correlations")) {
									listOfElements.get(i).click();
									report.updateTestLog("CMT Page", "Correlations button is clicked.", Status.PASS);
									break;
								}
											      
						    }			
						UIHelper.waitFor(driver);
					} catch (Exception e) {
						report.updateTestLog("CMT Page", "Intermediary Align button is not clicked.", Status.FAIL);
						e.printStackTrace();
					}		
				}
	
	public void chooseCurriculumMenu(String option) {
		try {
			UIHelper.waitFor(driver);			
			  Select selectBox = new Select(driver.findElement(By.cssSelector("li:nth-child(6) > select:nth-child(1)"))); 
			  UIHelper.waitFor(driver);
			  selectBox.selectByVisibleText(option);
			  UIHelper.waitFor(driver);			 
			  report.updateTestLog("CMT Manage Page", "In Manage Page,the option "+option+"is clicked", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,the option "+option+"is not clicked", Status.FAIL);
			e.printStackTrace();
		}
	}
	
	public void chooseProductsMenu(String option) {
		try {
			UIHelper.waitFor(driver);			
			  Select selectBox = new Select(driver.findElement(By.cssSelector("li:nth-child(6) > select:nth-child(2)"))); 
			  UIHelper.waitFor(driver);
			  selectBox.selectByVisibleText(option);
			  UIHelper.waitFor(driver);			 
			  report.updateTestLog("CMT Manage Page", "In Manage Page,the option "+option+"is clicked", Status.PASS);
		} catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,the option "+option+"is not clicked", Status.FAIL);
			e.printStackTrace();
		}
	}
	
	public int createAuthority(String localGradeName) {
	try {
		
		UIHelper.waitFor(driver);
		WebElement element = driver.findElement(By.xpath
					("//*[@id='https://data.savvas.com/country/IND#this_anchor']/span/span/span"));
		element.click();
		int randomNum=ThreadLocalRandom.current().nextInt(100, 1000);
		UIHelper.waitFor(driver);
		UIHelper.findAnElementbyXpath		
			(driver, "//*[@id='CreateProgramPopup']/div/div/div[3]/div[1]/div[1]/input").sendKeys("IndianBoard"+randomNum);
		UIHelper.waitFor(driver);
		UIHelper.findAnElementbyXpath(driver,"//*[@id='localGradeName']").sendKeys(localGradeName);
		UIHelper.waitFor(driver);
		UIHelper.findAnElementbyXpath(driver,"//*[@id='CreateProgramPopup']/div/div/div[3]/div[2]/input").click();
		UIHelper.waitFor(driver);
		report.updateTestLog("CMT Manage Page", "In Manage Page,Authority is created", Status.PASS);
		return randomNum;
	}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Manage Page", "In Manage Page,Authority is not created", Status.FAIL);
		e.printStackTrace();
		return 0;
	}
	}
	
	public void createCurriculumSetForAuthority(int num,String curriculumSetName) {
		try {	
			
			UIHelper.findAnElementbyXpath(driver, "//*[@id='https://data.savvas.com/country/IND#this']/i").click();
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, "//*[@id='https://data.savvas.com/authority/IND_INDIANBOARD"+num+"#this_anchor']/span/span/span[1]").click();
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, "//*[@id='CreateProgramPopup']/div/div/div[3]/div[1]/div/input").sendKeys(curriculumSetName);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver,"//*[@id='CreateProgramPopup']/div/div/div[3]/div[2]/input").click();
			UIHelper.waitFor(driver);
			report.updateTestLog("CMT Manage Page", "In Manage Page,Curriculum Set for Authority is created", Status.PASS);
		}catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,Curriculum Set for Authority is not created", Status.FAIL);
			e.printStackTrace();
		}
	}
	
	public void createNewCurriculum(String option,String curriculumSetName,String title,String url,String Subject,String Country) {
		try {			
			UIHelper.waitFor(driver);
			chooseCurriculumMenu(option);
			UIHelper.waitFor(driver);
			
			
			//Choose the Subject
			Select selectBox = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.subject']"))); 
			UIHelper.waitFor(driver);
			selectBox.selectByVisibleText(Subject);
			UIHelper.waitFor(driver);
			
			//Choose the country
			Select selectBox1 = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.country']"))); 
			UIHelper.waitFor(driver);
			selectBox1.selectByVisibleText("USA");
			UIHelper.waitFor(driver);
			
			//Choose the Authority
			Select selectBox2 = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.authority']"))); 
			UIHelper.waitFor(driver);
			selectBox2.selectByVisibleText("Alabama Education Association");
			UIHelper.waitFor(driver);	
			
			//Choose the CurriculumSet
			Select selectBox3 = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.curriculumSet']"))); 
			UIHelper.waitFor(driver);
			selectBox3.selectByIndex(1);
			UIHelper.waitFor(driver);
			
			//Choose State & State code
			Select selectBoxState = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.state']"))); 
			UIHelper.waitFor(driver);
			selectBoxState.selectByIndex(1);
			UIHelper.waitFor(driver);
			
			//Adopted Year
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.year']").sendKeys("2022");
			UIHelper.waitFor(driver);
			//Source URL
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.sourceUrl']").sendKeys(url);
			UIHelper.waitFor(driver);
			//Curriculum Info URL
			
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.lastUpdatedUrl']").sendKeys(url);
			UIHelper.waitFor(driver);
			
			//Checkbox the Append title
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.appendCustom']").click();
		
			
			//CurriculumTitle
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.customTitle']").sendKeys(title);
			UIHelper.waitFor(driver);
			
			//Click on Save
			UIHelper.findAnElementbyXpath(driver,"//input[@class='cmButton cmButtonFull-green cmButton-large']").click();
			UIHelper.waitFor(driver);
			report.updateTestLog("CMT Manage Page", "In Manage Page,the New Curriculum Draft is created", Status.PASS);
			
		}catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,New Curriculum Draft is not created", Status.FAIL);
			e.printStackTrace();
		}
	}
	
	public void addLocalGrade() {
		try {
			UIHelper.findAnElementbyXpath(driver, "/html/body/div[1]/div[4]/div[3]/div/div/div[3]/div[2]/div/span[2]").click();
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			Select minGrade = new Select(driver.findElement(By.xpath("//*[@id=\"CreateTagsPopup\"]/div/div/div[2]/div[1]/div[2]/select")));
			minGrade.selectByVisibleText("PK");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			Select maxGrade = new Select(driver.findElement(By.xpath("//*[@id=\"CreateTagsPopup\"]/div/div/div[2]/div[1]/div[3]/select")));
			maxGrade.selectByVisibleText("13");
			UIHelper.waitFor(driver);UIHelper.waitFor(driver);
			
			UIHelper.findAnElementbyXpath(driver, "//*[@id=\"localGradeName\"]").sendKeys("LG");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.findAnElementbyXpath(driver, "//*[@id=\"CreateTagsPopup\"]/div/div/div[2]/div[2]/input").click();
			report.updateTestLog("CMT Manage Page", "In Manage Page,the Local Grade is added to the New Curriculum Draft.", Status.PASS);
		}catch(Exception e) {
			e.printStackTrace();
			report.updateTestLog("CMT Manage Page", "In Manage Page,the Local Grade is not added to the New Curriculum Draft.", Status.FAIL);
		}
	}
	
	public void addTopic() 
	{
		try {
			//Click Add Topic
			WebElement ele = driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/div/div/div[2]/div[3]/div[2]/div[1]/span[2]"));
			JavascriptExecutor executor = (JavascriptExecutor)driver;
			executor.executeScript("arguments[0].click();", ele);			  
			Thread.sleep(3000);
			//Goto the text area
			WebElement ele1 = driver.findElement(By.xpath("//*[contains(@id,'taTextElement')]"));
			JavascriptExecutor executor1 = (JavascriptExecutor)driver;
			executor1.executeScript("arguments[0].click();", ele1);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			//Enter the topic contents
			UIHelper.findAnElementbyXpath(driver, "//*[contains(@id,'taTextElement')]").sendKeys("LGTopic");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			//Enter the standard Code
			UIHelper.findAnElementbyXpath(driver, "//*[@id='standardCode']").sendKeys("LGCode");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			//Click the save button
			WebElement ele2 =driver.findElement(By.xpath("//*[@class='cmButton cmButtonFull-green cmButton-standard']"));
			JavascriptExecutor executor2 = (JavascriptExecutor)driver;
			executor2.executeScript("arguments[0].click();", ele2);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			String xpath="//div[@ng-repeat='gradePreviewBean in standardGradeBean.gradeBeanList']//span[contains(@class,'cursorPointer ng-binding')]";
			if (xpath.contains("LG"));
			{
			report.updateTestLog("CMT Manage Page", "In Manage Page,the topic is added to the New Curriculum Draft.", Status.PASS);
			}
			
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	
	public void addGrade() 
	{
		try {
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			//Select Min Grade 
			WebElement minGradeElement =driver.findElement(By.xpath("//select[@ng-model='createItemBean.gradeMinIRI']"));
			
			Select selectBoxMin = new Select(minGradeElement); 
			UIHelper.waitFor(driver);
			selectBoxMin.selectByVisibleText("PK");
			UIHelper.waitFor(driver);
			
			
			//Select Max Grade 
			WebElement maxGradeElement =driver.findElement(By.xpath("//select[@ng-model='createItemBean.gradeMaxIRI']"));
			
			Select selectBoxMax = new Select(maxGradeElement); 
			UIHelper.waitFor(driver);
			selectBoxMax.selectByVisibleText("PK");
			UIHelper.waitFor(driver);
			
			//Select localGradeName 
			WebElement localGradeName =driver.findElement(By.xpath("//input[@id='localGradeName']"));
			localGradeName.sendKeys("TestLocalGrade");			
			UIHelper.waitFor(driver);
			
			
			//Click the save button
			WebElement ele2 =driver.findElement(By.xpath("//input[@value='Save'][@ng-click='saveLocalGrade()']"));
			JavascriptExecutor executor2 = (JavascriptExecutor)driver;
			executor2.executeScript("arguments[0].click();", ele2);
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			report.updateTestLog("Add Local grade","Min and Max grade is selected and clicked on Save", Status.PASS);
						
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	
	public void addProgram(String program) 
	{
	try
	
	{
		//Click on plus command before the listed discipline - mathematics 
		
			String chooseDiscXpath="//*[@id='http://schema.savvas.com/ns/subject#mathematics_anchor']/span/span/span";
			UIHelper.findAnElementbyXpath(driver, chooseDiscXpath).click();
			UIHelper.waitFor(driver);
			//Enter Program Name and click on save
		    Actions action = new Actions(driver);
		    String programNameXpath ="//input[@id='localGradeName']";
		    WebElement programName = driver.findElement(By.xpath(programNameXpath));
			action.moveToElement(programName).click().sendKeys(program).build().perform();
			UIHelper.waitFor(driver);
			String saveXpath ="//div[@ng-init='programinit()']//input[@type='submit']";
			WebElement save = driver.findElement(By.xpath(saveXpath));
			action.moveToElement(save).click().build().perform();
			report.updateTestLog("Clicked on Save","User clicked on save and new Program is created", Status.PASS);
			UIHelper.waitFor(driver);
	}
	
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	}
	
	public void editProgram(String program) 
	{
	try
	
	{
		//Click on plus command before the listed discipline - mathematics 
		
			String chooseDiscXpath="//*[@id='http://schema.savvas.com/ns/subject#mathematics_anchor']/span";
			UIHelper.findAnElementbyXpath(driver, chooseDiscXpath).click();
			UIHelper.waitFor(driver);
			String programEditXpath ="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']//span[2]";
			UIHelper.findAnElementbyXpath(driver, programEditXpath).click();
			report.updateTestLog("Clicked on Edit Program","User clicked on edit and Program is edited", Status.PASS);
			

			UIHelper.waitFor(driver);
			String popUp ="//*[@id='CreateProgramPopup']";
			UIHelper.highlightElement(driver,popUp );
			
			
			//Enter Program Name and click on save
			
				    
		    String programNameXpath ="//div[@class='modal fade ng-scope in']//div[2]//div[1]//div//input[@type='text']";
		    WebElement programName = driver.findElement(By.xpath(programNameXpath));
		    Actions action = new Actions(driver);
		    action.moveToElement(programName).click(programName).sendKeys("").build().perform();
			UIHelper.waitFor(driver);
			
			String saveXpath ="//div[@ng-init='programinit()']//input[@type='submit']";
			WebElement save = driver.findElement(By.xpath(saveXpath));
			action.moveToElement(save).click().build().perform();
			report.updateTestLog("Clicked on Save","User clicked on save and Program is edited", Status.PASS);
			UIHelper.waitFor(driver);
		    
		   /* String cancelXpath ="//*[@id='CreateProgramPopup']/div/div/div[2]/div[2]/a";
		    WebElement cancel = driver.findElement(By.xpath(cancelXpath));
			action.moveToElement(cancel).click().build().perform();
			report.updateTestLog("Clicked on Save","User clicked on save and Program is edited", Status.PASS);
			UIHelper.waitFor(driver);*/
			
	}
	
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	}
	
	public void deleteProgram(String program) 
	{
	try
	
	{
		//Click on plus command before the listed discipline - mathematics 
		
			
			
	}
	
	catch(Exception e) 
	{
		e.printStackTrace();
	}
	}
	
	public void addCourse(String course) 
	{
		try
		{
		//Select the created Program and Enter Course Name and click on save
				String expandXpath ="//*[@id='http://schema.savvas.com/ns/subject#mathematics']/i";
				UIHelper.findAnElementbyXpath(driver, expandXpath).click();
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				String courseAddXpath="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']//span[1]";
				UIHelper.findAnElementbyXpath(driver, courseAddXpath).click();
				UIHelper.waitFor(driver);
				// Give course name and save
				 String courseNameXpath ="//input[@id='localGradeName']";
				 Actions action = new Actions(driver);
				 WebElement courseName = driver.findElement(By.xpath(courseNameXpath));
				 action.moveToElement(courseName).click().sendKeys(course).build().perform();
				 String saveXpath ="//div[@ng-init='programinit()']//input[@type='submit']";
				 WebElement save = driver.findElement(By.xpath(saveXpath));
				 action.moveToElement(save).click().build().perform();
				 report.updateTestLog("Clicked on Save","User clicked on save and new Course is created", Status.PASS);
				 UIHelper.waitFor(driver);
	}
	
	catch(Exception e) 
	{
		e.printStackTrace();
	}
}

	public void editCourse(String course) 
	{
		try
		{
		//Select the created Program and Enter Course Name and click on save
			
			
			String chooseDiscXpath="//*[@id='http://schema.savvas.com/ns/subject#mathematics_anchor']/span";
			UIHelper.findAnElementbyXpath(driver, chooseDiscXpath).click();
			UIHelper.waitFor(driver);
			
				String expandXpath ="//*[@id='http://schema.savvas.com/ns/subject#mathematics']/i";
				UIHelper.findAnElementbyXpath(driver, expandXpath).click();
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				
				String courseEditXpath="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']//span[2]";
				UIHelper.findAnElementbyXpath(driver, courseEditXpath).click();
				UIHelper.waitFor(driver);
				// Give course name and save
				 String courseNameXpath ="//input[@id='localGradeName']";
				 Actions action = new Actions(driver);
				 WebElement courseName = driver.findElement(By.xpath(courseNameXpath));
				 action.moveToElement(courseName).click().sendKeys(course).build().perform();
				 String saveXpath ="//div[@ng-init='programinit()']//input[@type='submit']";
				 WebElement save = driver.findElement(By.xpath(saveXpath));
				 action.moveToElement(save).click().build().perform();
				 report.updateTestLog("Clicked on Save","User clicked on save and Course is edited", Status.PASS);
				 UIHelper.waitFor(driver);
	}
	
	catch(Exception e) 
	{
		e.printStackTrace();
	}
}
	
	public void deleteCourse(String course) 
	{
		try
		{
			String chooseDiscXpath="//*[@id='http://schema.savvas.com/ns/subject#mathematics_anchor']/span";
			UIHelper.findAnElementbyXpath(driver, chooseDiscXpath).click();
			UIHelper.waitFor(driver);
			
			String chooseProgramXpath="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']";
			UIHelper.findAnElementbyXpath(driver, chooseProgramXpath).click();
			UIHelper.waitFor(driver);
			
					
		String deleteProduct ="//span[@class='span12 browse-course-color']//span[2]";
		driver.findElement(By.xpath(deleteProduct)).click();
		UIHelper.waitFor(driver);
		
		String popUp ="//*[@id='AreYouSurePopup']";
		UIHelper.highlightElement(driver,popUp );
		
		
		String okButton ="//div[@class='modal fade ng-scope in']//div[2]//input[@id='buttonOk']";
		Actions action = new Actions(driver);
		UIHelper.waitFor(driver);
		WebElement okClick = driver.findElement(By.xpath(okButton));
		//okClick.click();
		 action.moveToElement(okClick).click().build().perform();
		 UIHelper.waitFor(driver);
		 UIHelper.waitFor(driver);
				 UIHelper.waitFor(driver);
	}
	
	catch(Exception e) 
	{
		e.printStackTrace();
	}
}
	
	public void addProduct(String product,String country,String state) 
	{
		try
	{
		// Expand the Program to view created course
		String expandProgram="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']";
		UIHelper.findAnElementbyXpath(driver, expandProgram).click();
		UIHelper.waitFor(driver);
		//Expand the Course to view created course
		String addProductXpath="//span[@class='span12 browse-course-color'][text()='AutoTestCourse']//span[1]";
		UIHelper.findAnElementbyXpath(driver, addProductXpath).click();
		UIHelper.waitFor(driver);
		// In the modal window add product details
		
		String productTitleXpath ="//input[@id='ProductTitle']";
		String countryXpath ="//input[@id='Country']";
		String stateXpath ="//input[@id='Sate']";
		Actions action = new Actions(driver);
		 WebElement productClick = driver.findElement(By.xpath(productTitleXpath));
		 WebElement countryClick = driver.findElement(By.xpath(countryXpath));
		 WebElement stateClick = driver.findElement(By.xpath(stateXpath));
		 action.moveToElement(productClick).click().sendKeys(product).build().perform();
		 action.moveToElement(countryClick).click().sendKeys(country).build().perform();
		 action.moveToElement(stateClick).click().sendKeys(state).build().perform();
		 UIHelper.waitFor(driver);
		 String saveXpath ="//div[@ng-init='programinit()']//input[@type='submit']";
		 WebElement save = driver.findElement(By.xpath(saveXpath));
		 action.moveToElement(save).click().build().perform();
		 report.updateTestLog("Clicked on Save","User clicked on save and new Product is created", Status.PASS);
		 UIHelper.waitFor(driver);
		
	}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void editProduct(String product,String country,String state) 
	{
		try
	{
		// Expand the Program to view created course
		String expandProgram="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']";
		UIHelper.findAnElementbyXpath(driver, expandProgram).click();
		UIHelper.waitFor(driver);
		//Expand the Course to view created course
		String editProductXpath="//span[@class='span12 browse-course-color'][text()='AutoTestCourse']";
		UIHelper.findAnElementbyXpath(driver, editProductXpath).click();
		UIHelper.waitFor(driver);
		//Edit the product
		String editProduct ="//span[@class='span12 browse-product-color'][text()='AutoTestProduct']//span[1]";
		UIHelper.findAnElementbyXpath(driver, editProduct).click();
		UIHelper.waitFor(driver);
		
	// In the modal window edit product details
		
		String productTitleXpath ="//input[@id='ProductTitle']";
		String countryXpath ="//input[@id='Country']";
		String stateXpath ="//input[@id='Sate']";
		Actions action = new Actions(driver);
		 WebElement productClick = driver.findElement(By.xpath(productTitleXpath));
		 WebElement countryClick = driver.findElement(By.xpath(countryXpath));
		 WebElement stateClick = driver.findElement(By.xpath(stateXpath));
		 action.moveToElement(productClick).click().sendKeys(product).build().perform();
		 action.moveToElement(countryClick).click().sendKeys(country).build().perform();
		 action.moveToElement(stateClick).click().sendKeys(state).build().perform();
		 UIHelper.waitFor(driver);
		 String saveXpath ="//div[@ng-init='programinit()']//input[@type='submit']";
		 WebElement save = driver.findElement(By.xpath(saveXpath));
		 action.moveToElement(save).click().build().perform();
		 report.updateTestLog("Clicked on Save","User clicked on save and Product is edited", Status.PASS);
		 UIHelper.waitFor(driver);
		
	}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deleteProduct() 
	{
		try
	{
			
			String chooseDiscXpath="//*[@id='http://schema.savvas.com/ns/subject#mathematics_anchor']/span";
			UIHelper.findAnElementbyXpath(driver, chooseDiscXpath).click();
			UIHelper.waitFor(driver);
			
			String chooseProgramXpath="//span[@class='span12 browse-program-color'][text()='AutoTestProgram']";
			UIHelper.findAnElementbyXpath(driver, chooseProgramXpath).click();
			UIHelper.waitFor(driver);
			
			String chooseCourseXpath="//span[@class='span12 browse-course-color'][text()='AutoTestCourse']";
			UIHelper.findAnElementbyXpath(driver, chooseCourseXpath).click();
			UIHelper.waitFor(driver);
			
		String deleteProduct ="//span[@class='span12 browse-product-color']//span[2]";
		driver.findElement(By.xpath(deleteProduct)).click();
		UIHelper.waitFor(driver);
		
		String popUp ="//*[@id='AreYouSurePopup']";
		UIHelper.highlightElement(driver,popUp );
		
		
		String okButton ="//div[@class='modal fade ng-scope in']//div[2]//input[@id='buttonOk']";
		Actions action = new Actions(driver);
		UIHelper.waitFor(driver);
		WebElement okClick = driver.findElement(By.xpath(okButton));
		//okClick.click();
		 action.moveToElement(okClick).click().build().perform();
		 UIHelper.waitFor(driver);
		 UIHelper.waitFor(driver);
		
	}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	public void chooseFilterOptions(String subject, String country) {
		 //Choose the subject 
		Select selectBox = new
	  Select(driver.findElement(By.xpath("//div[@class='curriculamFilter clearfix']//select[@ng-model='selectedSubjects']")));
	  UIHelper.waitFor(driver); 
	  selectBox.selectByVisibleText(subject);
	  UIHelper.waitFor(driver); 
	  //Choose the country 
	  Select selectBox1 = new Select(driver.findElement(By.xpath("//div[@class='curriculamFilter clearfix']//select[@ng-model='selectedCountry']")));
	  UIHelper.waitFor(driver); 
	  selectBox1.selectByVisibleText(country);
	  UIHelper.waitFor(driver);
	}
	
	public void uploadNewCurriculum(String uploadBtnXpath,String option,String curriculumSetName,
				String title,String url,String Subject,String Country,String filepath,String filename) {
		try {			
			UIHelper.waitFor(driver);
			chooseCurriculumMenu(option);
			UIHelper.waitFor(driver);
			
			//Choose the Subject
			Select selectBox = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.subject']"))); 
			UIHelper.waitFor(driver);
			selectBox.selectByVisibleText(Subject);
			UIHelper.waitFor(driver);
			
			//Choose the country
			Select selectBox1 = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.country']"))); 
			UIHelper.waitFor(driver);
			selectBox1.selectByVisibleText(Country);
			UIHelper.waitFor(driver);
			
			//Choose the Authority
			Select selectBox2 = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.authority']"))); 
			UIHelper.waitFor(driver);
			selectBox2.selectByVisibleText("Central Board of Secondary Education");
			UIHelper.waitFor(driver);	
			
			//Choose the CurriculumSet
			Select selectBox3 = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.curriculumSet']"))); 
			UIHelper.waitFor(driver);
			selectBox3.selectByIndex(1);
			UIHelper.waitFor(driver);
			
			//Choose State & State code
			Select selectBoxState = new Select(driver.findElement(By.xpath("//*[contains(@class,'cmDropDown')][@ng-model='injectCurriculumBean.curriculumUploadBean.state']"))); 
			UIHelper.waitFor(driver);
			selectBoxState.selectByIndex(1);
			UIHelper.waitFor(driver);
			
			//Adopted Year
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.year']").sendKeys("2022");
			UIHelper.waitFor(driver);
			
			//Source URL
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.sourceUrl']").sendKeys(url);
			UIHelper.waitFor(driver);
			//Curriculum Info URL
			
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.lastUpdatedUrl']").sendKeys(url);
			UIHelper.waitFor(driver);
			
			//Checkbox the Append title
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.appendCustom']").click();
			//CurriculumTitle
			
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.customTitle']").sendKeys("AutoUploadNewCurriculum");
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
					
			//Select Upload Type
			
			//Custom Excel 
			UIHelper.findAnElementbyXpath(driver, "//input[@ng-model='injectCurriculumBean.curriculumUploadBean.fileType'][@value='Custom Excel']").click();
			
			//Include Intermediary Alignments
			UIHelper.findAnElementbyXpath(driver,"//input[@ng-model='injectCurriculumBean.curriculumUploadBean.includeIntermediaryAlignments']").click();
			
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
			
			
			
		}catch (Exception e) {
			report.updateTestLog("CMT Manage Page", "In Manage Page,the Upload New Curriculum Draft is not created", Status.FAIL);
			e.printStackTrace();
		}
	}
	
	public void updateCurriculum(String filepath,String filename,String uploadBtnXpath) 
	{
	try {			
		
		UIHelper.waitFor(driver);
	//Upload Curriculum
		String finalFilePath = System.getProperty("user.dir") + filepath;
		System.out.println(finalFilePath);
		UIHelper.waitFor(driver);
		uploadFileWithRobot(finalFilePath,filename);
		UIHelper.waitFor(driver);
		report.updateTestLog("CMT Manage Page", "In Manage Page,the Upload New Curriculum Draft is created", Status.PASS);
		
	}catch (Exception e) {
		report.updateTestLog("CMT Manage Page", "In Manage Page,the Upload New Curriculum Draft is not created", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void uploadIntermediary(String uploadBtnXpath,String filepath,String filename) 
	{
	try {			
		
		//Upload Intermediary
		String finalFilePath = System.getProperty("user.dir") + filepath;
		System.out.println(finalFilePath);
		UIHelper.waitFor(driver);
		uploadFileWithRobot(finalFilePath,filename);
		UIHelper.waitFor(driver);
		report.updateTestLog("CMT Intermediary Page", "In Intermediary Page,the upload an Intermediary spreadsheet is done", Status.PASS);
	}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Intermediary Page", "In Intermediary Page,the upload an Intermediary spreadsheet is not done", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void alignmentCurriculumStandard(String Discipline,String Country,String Curriculum,String Grade) 
	{
	try {			
		
//		Select Discipline
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
				
				report.updateTestLog("User click on Create Alignment","User clicked on Create Alignment successfully", Status.DONE);
				
				
			String pageTitle ="//label[@class='mapping-title-label ng-binding']";	
			String title = driver.findElement(By.xpath(pageTitle)).getText();
			if (title.contains(Curriculum))
			{
			report.updateTestLog("Create Alignment Page","User navigated to Create Alignment Page successfully ", Status.PASS);	
			}
			else
			{
			report.updateTestLog("Create Alignment Page","Create Alignment Page error", Status.FAIL);	
			}
			UIHelper.waitFor(driver);
			UIHelper.waitFor(driver);
					
		}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Intermediary Align Page", "Intermediary Align Page error", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void clickAlignAndCartValidation() 
	{
	try {			
		String central =".//div[@data-ng-bind-template='CENTRAL']";
		String peripheral =".//div[@data-ng-bind-template='PERIPHERAL']";
		String alignXpath="//button[@data-ng-bind-template='ALIGN']";
		String expandAllXpath="//button[@data-ng-bind-template='EXPAND ALL']";
		String cartXpath="//*[@id='cart-tab-head']/span[1]";
		String intermediaryXpath="//*[@id='intermediary-tab-head']";
		String saveXpath="//*[@id='cart-data-tab']/div/div[2]/div[1]";
		
		// Click on Expand all button 
					driver.findElement(By.xpath(expandAllXpath)).click();
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					driver.findElement(By.xpath("//*[@id='https://data.savvas.com/curriculum/loc/ed8ea5b5-3fef-4bce-bc4b-64e8d2b5e0af_anchor']/i[1]")).click();
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
												
					// Click on Align button	
					driver.findElement(By.xpath(alignXpath)).click();
					UIHelper.waitFor(driver);
					// Click on Intermediary List
					driver.findElement(By.xpath(intermediaryXpath)).click();
					UIHelper.waitFor(driver);
					// Click on Cart
					driver.findElement(By.xpath(cartXpath)).click();
					UIHelper.waitFor(driver);
					report.updateTestLog("Cart selected ", "User click on Cart was successful", Status.PASS);
					
					driver.findElement(By.xpath("//*[@id='knowledgeCartId']/div/div[1]/div[1]/input")).click();
					UIHelper.waitFor(driver);
					//Select CENTRAL/PERIPHERAL/KEY/NOTE
								
					driver.findElement(By.xpath(peripheral)).click();
					report.updateTestLog("knowledge cart section", "User clicked on PERIPHERAL", Status.PASS);
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);							
					// Click on save	
					UIHelper.highlightElement(driver, saveXpath);
					driver.findElement(By.xpath(saveXpath)).click();
					UIHelper.waitFor(driver);
					report.updateTestLog("Click Save", "User clicked on Save button", Status.PASS);
					
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

			UIHelper.waitFor(driver);
				
				
				
		}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Intermediary Align", "Intermediary Align cart validation fail", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void printDigitalAttribute() 
	{
	try {			
		String printDigitalRadioButton="//input[@type='radio'][@value='printDigital']";
		String printRadioButton="//input[@type='radio'][@value='print']";
		String digitalRadioButton="//input[@type='radio'][@value='digital']";
		String printDigitalCheck="//span[@class='intermediary-alignment-type-container print-PD-icon']";
		String digitalCheck="//span[@class='intermediary-alignment-type-container print-D-icon']";
		String printCheck="//span[@class='intermediary-alignment-type-container print-P-icon']";
		String expandAllXpath="//button[@data-ng-bind-template='EXPAND ALL']";
		String cartXpath="//*[@id='cart-tab-head']/span[1]";
		String intermediaryXpath="//*[@id='intermediary-tab-head']";
		String alignXpath="//button[@data-ng-bind-template='ALIGN']";
		// Click on Expand all button 
		driver.findElement(By.xpath(expandAllXpath)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		driver.findElement(By.xpath("//*[@id='https://data.savvas.com/curriculum/loc/ed8ea5b5-3fef-4bce-bc4b-64e8d2b5e0af_anchor']/i[1]")).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
									
		// Click on Align button	
		driver.findElement(By.xpath(alignXpath)).click();
		UIHelper.waitFor(driver);
		// Click on Intermediary List
		driver.findElement(By.xpath(intermediaryXpath)).click();
		UIHelper.waitFor(driver);
		// Click on Cart
		driver.findElement(By.xpath(cartXpath)).click();
		UIHelper.waitFor(driver);
		report.updateTestLog("Cart selected ", "User click on Cart was successful", Status.PASS);
		//Select intermediary statement
		driver.findElement(By.xpath("//*[@id='knowledgeCartId']/div/div[1]/div[1]/input")).click();
		UIHelper.waitFor(driver);
		//Click on Print ,Digital , PRINT & DIGITAL 
		//Click Digital 
		driver.findElement(By.xpath(digitalRadioButton)).click();
		if(driver.findElements(By.xpath(digitalCheck)).size() > 0) 
		{
		report.updateTestLog("Apply Digital attribute to the alignment before saving.", "Apply Digital attribute to the alignment was successful", Status.PASS);	
  		}
		else
		{
		report.updateTestLog("Apply Digital attribute to the alignment before saving.", "Apply Digital attribute to the alignment was successful", Status.FAIL);	
		}
		//Click PRINT & DIGITAL   
		driver.findElement(By.xpath(printDigitalRadioButton)).click();
		if(driver.findElements(By.xpath(printDigitalCheck)).size() > 0) 
		{
		report.updateTestLog("Apply Print&Digital attribute to the alignment before saving.", "Apply Print&Digital attribute to the alignment was successful", Status.PASS);	
  		}
		else
		{
		report.updateTestLog("Apply Print&Digital attribute to the alignment before saving.", "Apply Print&Digital attribute to the alignment was successful", Status.FAIL);	
		}
		//Click PRINT   
		driver.findElement(By.xpath(printRadioButton)).click();
		if(driver.findElements(By.xpath(printCheck)).size() > 0) 
		{
		report.updateTestLog("Apply Print attribute to the alignment before saving.", "Apply Print attribute to the alignment was successful", Status.PASS);	
  		}
		else
		{
		report.updateTestLog("Apply Print attribute to the alignment before saving.", "Apply Print attribute to the alignment was successful", Status.FAIL);	
		}
		
		UIHelper.waitFor(driver);
		}
	catch (Exception e) 
	{
		report.updateTestLog("Print & Digital Alignment Attribute Validation", "Print & Digital Alignment Attribute Validation fail", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void copyAlignmentsValidation() 
	{
	try {			
		
		String copyAlignXpath="//button[@title='Copy Alignments']";
		String expandAllXpath="//button[@data-ng-bind-template='EXPAND ALL']";
		
		// Click on Expand all button 
		driver.findElement(By.xpath(expandAllXpath)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		driver.findElement(By.xpath("//*[@id='https://data.savvas.com/curriculum/loc/ed8ea5b5-3fef-4bce-bc4b-64e8d2b5e0af_anchor']/i[1]")).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		driver.findElement(By.xpath(copyAlignXpath)).click();
		UIHelper.waitFor(driver);
		//Check success message										
		String successMessage=driver.findElement(By.xpath(".//*[@id='confirmationPopup'][@data-ng-show='successMessage']")).getText();
		System.out.println(successMessage);
		if (successMessage.contains("Alignment(s) copied successfully") )
		{
		report.updateTestLog("Alignment creation ", "Alignment Creation was successful" + "||"+successMessage, Status.PASS);
		}
		else
		{
		report.updateTestLog("Alignment creation ", "Alignment Creation was not successful", Status.FAIL);
		}
		UIHelper.waitFor(driver);
		}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Intermediary Align", "Intermediary Align cart validation fail", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void Align() 
	{
	try {			
		String alignXpath="//button[@data-ng-bind-template='ALIGN']";	
		String select ="//*[@id='https://data.savvas.com/curriculum/loc/4e9ede06-2d68-4446-a361-eb491a431ac1_anchor']/i[1]";
		String addIcon ="//*[@id='infiniteScrollParentId']/div[1]/div[1]/div/span[3]/img";
		String cartXpath="//*[@id='cart-tab-head']/span[1]";
		String peripheral =".//div[@data-ng-bind-template='PERIPHERAL']";
		String saveXpath="//*[@id='cart-data-tab']/div/div[2]/div[1]";
		String backToSelection="//a[@class='standard-back-label ng-binding']";
		String clickAlign="//*[@id='advancedsearch']";
		
		driver.findElement(By.xpath(select)).click();
		UIHelper.waitFor(driver);
		//Click on Align
		driver.findElement(By.xpath(alignXpath)).click();
		UIHelper.waitFor(driver);
		
		//Maths Intermediary List
		
		driver.findElement(By.xpath(addIcon)).click();
		UIHelper.waitFor(driver);
		// Click on Cart
		
		driver.findElement(By.xpath(cartXpath)).click();
		UIHelper.waitFor(driver);
		report.updateTestLog("Cart selected ", "User click on Cart was successful", Status.PASS);
		//Select intermediary statement
		driver.findElement(By.xpath("//*[@id='knowledgeCartId']/div/div[1]/div[1]/input")).click();
		UIHelper.waitFor(driver);
		//Select CENTRAL/PERIPHERAL/KEY/NOTE
		
		driver.findElement(By.xpath(peripheral)).click();
		report.updateTestLog("knowledge cart section", "User clicked on PERIPHERAL", Status.PASS);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);							
		// Click on save	
		
		UIHelper.highlightElement(driver, saveXpath);
		driver.findElement(By.xpath(saveXpath)).click();
		UIHelper.waitFor(driver);
		report.updateTestLog("Click Save", "User clicked on Save button", Status.PASS);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		//Click Back to selection
		
		driver.findElement(By.xpath(backToSelection)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
				
		driver.findElement(By.xpath(clickAlign)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Intermediary Align", "Intermediary Align cart validation fail", Status.FAIL);
		e.printStackTrace();
	}
}
	
	
	public void selectAllAndUnAlign() 
	{
	try {			
		String selectAllXpath ="//button[@data-ng-click='selectAllIntermediaries(true)']";
		String unAlignXpath="//button[@class='intermediary-cm intermediary-btn'][text()='UNALIGN']";
		String okXpath="//div[@class='form-horizontal childTwoPop']//input[@id='buttonOk']";
		String alignXpath="//button[@data-ng-bind-template='ALIGN']";	
			
		// Click on Expand all button 
		driver.findElement(By.xpath(selectAllXpath)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);	
		UIHelper.waitFor(driver);	
		// Click on UnAlign button	
		driver.findElement(By.xpath(unAlignXpath)).click();
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
		report.updateTestLog("Click on Unalign", "User clicked on Unalign button and 'Are you sure you want to unalign selected intermediaries?' pop up displayed", Status.PASS);
		//Are you sure you want to unalign selected intermediaries? pop up
		Actions action = new Actions(driver);
		WebElement okButton = driver.findElement(By.xpath(okXpath));
		action.moveToElement(okButton).click().build().perform();
		report.updateTestLog("Click Ok Button in unalign pop up", "User clicked on Ok button", Status.PASS);
		UIHelper.waitFor(driver);
				
		String successMessage=driver.findElement(By.xpath(".//*[@id='confirmationPopup'][@data-ng-show='successMessage']")).getText();
		System.out.println(successMessage);
		if (successMessage.contains("Intermediaries successfully Unaligned") )
		{
		report.updateTestLog("UnAlignment", "Alignment Creation was successful" + "||"+successMessage, Status.PASS);
		}
		else
		{
		report.updateTestLog("UnAlignment ", "Alignment Creation was not successful", Status.FAIL);
		}

		UIHelper.waitFor(driver);
		
		}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Intermediary Align", "Intermediary Align cart validation fail", Status.FAIL);
		e.printStackTrace();
	}
}
	
	
	public void curriculumStandard(String Discipline,String Country,String Curriculum,String Grade) 
	{
	try {	
		String discipline ="//*[@id='left-standard-tab']/div[1]/select";
		String country ="//*[@id='left-standard-tab']/div[2]/select";
		String curriculum ="//*[@id='left-standard-tab']/div[3]/select";
		String grade ="//*[@id='left-standard-tab']/div[4]/select";
		//Select Discipline
		Select selectDiscipline = new Select(driver.findElement(By.xpath(discipline))); 
		UIHelper.waitFor(driver);
		selectDiscipline.selectByVisibleText(Discipline);
		UIHelper.waitFor(driver);
	//	Select Country
		Select selectCountry = new Select(driver.findElement(By.xpath(country))); 
		UIHelper.waitFor(driver);
		selectCountry.selectByVisibleText(Country);
		UIHelper.waitFor(driver);
	//	Select Curriculum/Standard
		Select selectCurriculum = new Select(driver.findElement(By.xpath(curriculum))); 
		UIHelper.waitFor(driver);
		selectCurriculum.selectByVisibleText(Curriculum);
		UIHelper.waitFor(driver);
	//	Select Grade
		Select selectGrade = new Select(driver.findElement(By.xpath(grade))); 
		UIHelper.waitFor(driver);
		selectGrade.selectByVisibleText(Grade);
		UIHelper.waitFor(driver);
	// Click on "SELECT" button
		driver.findElement(By.xpath("//*[@id='left-standard-tab-content']/div/div[2]/div/input")).click(); 
		UIHelper.waitFor(driver);	
		UIHelper.waitFor(driver);
					
		}
	catch (Exception e) 
	{
		report.updateTestLog("Correlation Tab", "Correlation Tab Page error", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void rightCurriculumStandard(String Discipline,String Country,String Curriculum,String Grade) 
	{
	try {	
		String discipline ="//*[@id='right-standard-tab']/div[1]/select";
		String country ="//*[@id='right-standard-tab']/div[2]/select";
		String curriculum ="//*[@id='right-standard-tab']/div[3]/select";
		String grade ="//*[@id='right-standard-tab']/div[4]/select";
		String addXpath="//*[@id='right-standard-tab-content']/div/div[2]/div/input";
		//Select Discipline
		Select selectDiscipline = new Select(driver.findElement(By.xpath(discipline))); 
		UIHelper.waitFor(driver);
		selectDiscipline.selectByVisibleText(Discipline);
		UIHelper.waitFor(driver);
	//	Select Country
		Select selectCountry = new Select(driver.findElement(By.xpath(country))); 
		UIHelper.waitFor(driver);
		selectCountry.selectByVisibleText(Country);
		UIHelper.waitFor(driver);
	//	Select Curriculum/Standard
		Select selectCurriculum = new Select(driver.findElement(By.xpath(curriculum))); 
		UIHelper.waitFor(driver);
		selectCurriculum.selectByVisibleText(Curriculum);
		UIHelper.waitFor(driver);
	//	Select Grade
		Select selectGrade = new Select(driver.findElement(By.xpath(grade))); 
		UIHelper.waitFor(driver);
		selectGrade.selectByVisibleText(Grade);
		UIHelper.waitFor(driver);
	// Click on "ADD" button
		driver.findElement(By.xpath(addXpath)).click(); 
		UIHelper.waitFor(driver);	
		UIHelper.waitFor(driver);
					
		}
	catch (Exception e) 
	{
		report.updateTestLog("Correlation Tab", "Correlation Tab Page error", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void product(String Discipline,String Program,String Course,String Grade,String Product) 
	{
	try {	
		String productXpath ="//*[@id='right-product-tab-head']";
		String discipline ="//*[@id='right-product-tab']/div[1]/select";
		String program ="//*[@id='right-product-tab']/div[2]/select";
		String course ="//*[@id='right-product-tab']/div[3]/select";
		String grade ="//*[@id='right-product-tab']/div[4]/select";
		String product ="//*[@id='right-product-tab']/div[5]/select";
		String add="//*[@id='content-list-panel']/div[2]/div/input";
	 //  Click on Product
		UIHelper.findAnElementbyXpath(driver, productXpath).click();
     // Select Discipline
		Select selectDiscipline = new Select(driver.findElement(By.xpath(discipline))); 
		UIHelper.waitFor(driver);
		selectDiscipline.selectByVisibleText(Discipline);
		UIHelper.waitFor(driver);
	//	Select Program
		Select selectProgram = new Select(driver.findElement(By.xpath(program))); 
		UIHelper.waitFor(driver);
		selectProgram.selectByVisibleText(Program);
		UIHelper.waitFor(driver);
	//	Select Course
		Select selectCourse = new Select(driver.findElement(By.xpath(course))); 
		UIHelper.waitFor(driver);
		selectCourse.selectByIndex(1);
		UIHelper.waitFor(driver);	
	//	Select Grade
		Select selectGrade = new Select(driver.findElement(By.xpath(grade))); 
		UIHelper.waitFor(driver);
		selectGrade.selectByIndex(1);
		UIHelper.waitFor(driver);
//		Select Product
		Select selectProduct = new Select(driver.findElement(By.xpath(product))); 
		UIHelper.waitFor(driver);
		selectProduct.selectByIndex(1);
		UIHelper.waitFor(driver);
	//  Click on "ADD" button
		driver.findElement(By.xpath(add)).click();
		UIHelper.waitFor(driver);				
		
		}
	catch (Exception e) 
	{
		report.updateTestLog("Correlation Tab", "Correlation Tab Page error", Status.FAIL);
		e.printStackTrace();
	}
}
	
	public void leftProduct(String Discipline,String Program,String Course,String Grade,String Product) 
	{
	try {	
		String productXpath ="//*[@id='left-product-tab-head']";
		String discipline ="//*[@id='left-product-tab']/div[1]/select";
		String program ="//*[@id='left-product-tab']/div[2]/select";
		String course ="//*[@id='left-product-tab']/div[3]/select";
		String grade ="//*[@id='left-product-tab']/div[4]/select";
		String product ="//*[@id='left-product-tab']/div[5]/select";
		String select="//*[@id='left-product-tab-content']/div/div[2]/div/input";
	 //  Click on Product
		UIHelper.findAnElementbyXpath(driver, productXpath).click();
     // Select Discipline
		Select selectDiscipline = new Select(driver.findElement(By.xpath(discipline))); 
		UIHelper.waitFor(driver);
		selectDiscipline.selectByVisibleText(Discipline);
		UIHelper.waitFor(driver);
	//	Select Program
		Select selectProgram = new Select(driver.findElement(By.xpath(program))); 
		UIHelper.waitFor(driver);
		selectProgram.selectByVisibleText(Program);
		UIHelper.waitFor(driver);
	//	Select Course
		Select selectCourse = new Select(driver.findElement(By.xpath(course))); 
		UIHelper.waitFor(driver);
		selectCourse.selectByIndex(1);
		UIHelper.waitFor(driver);	
	//	Select Grade
		Select selectGrade = new Select(driver.findElement(By.xpath(grade))); 
		UIHelper.waitFor(driver);
		selectGrade.selectByIndex(1);
		UIHelper.waitFor(driver);
//		Select Product
		Select selectProduct = new Select(driver.findElement(By.xpath(product))); 
		UIHelper.waitFor(driver);
		selectProduct.selectByIndex(1);
		UIHelper.waitFor(driver);
	//  Click on "ADD" button
		driver.findElement(By.xpath(select)).click();
		UIHelper.waitFor(driver);				
		
		}
	catch (Exception e) 
	{
		report.updateTestLog("Correlation Tab", "Correlation Tab Page error", Status.FAIL);
		e.printStackTrace();
	}
}
	
	
	
	public void uploadNewTOC(String option,String discipline,
			String program,String course,String product) {
	try {			
		UIHelper.waitFor(driver);
		chooseProductsMenu(option);
		UIHelper.waitFor(driver);
		//Choose the Discipline 
		Select selectBox = new Select(driver.findElement(By.xpath("//div[contains(@class,'product-textbox')]//div[1]//select[@ng-model='injectCurriculumBean.curriculumUploadBean.subject']"))); 
		UIHelper.waitFor(driver);
		selectBox.selectByVisibleText(discipline);
		UIHelper.waitFor(driver);
		//Choose the Program 
		Select selectBox1 = new Select(driver.findElement(By.xpath("//div[contains(@class,'product-textbox')]//select[@data-ng-model='selectedList.program']"))); 
		UIHelper.waitFor(driver);
		selectBox1.selectByVisibleText(program);
		UIHelper.waitFor(driver);			 
		//Choose the Course 
		Select selectBox2 = new Select(driver.findElement(By.xpath("//div[contains(@class,'product-textbox')]//select[@data-ng-model='selectedList.course']"))); 
		UIHelper.waitFor(driver);
		selectBox2.selectByIndex(1);
		UIHelper.waitFor(driver);			
		//Choose the Product 
		Select selectBox3 = new Select(driver.findElement(By.xpath("//div[contains(@class,'product-textbox')]//select[@data-ng-model='selectedList.product']"))); 
		UIHelper.waitFor(driver);
		selectBox3.selectByIndex(1);
		UIHelper.waitFor(driver);
		UIHelper.waitFor(driver);
				
	}
	catch (Exception e) 
	{
		report.updateTestLog("CMT Manage Page", "In Manage Page,the Upload New Curriculum Draft is not created", Status.FAIL);
		e.printStackTrace();
	}
}
		
		
		//File upload by Robot Class
	    public void uploadFileWithRobot (String filePath,String fileName) 
	    {
	    	
	    	/*UIHelper.waitFor(driver);
			UIHelper.click(driver, uploadBtnXpath);
			report.updateTestLog("Upload","The Select File button is clicked successfully",
					Status.PASS);
			*/
			UIHelper.waitFor(driver);
			
	        StringSelection stringSelection = new StringSelection(filePath+fileName);
	        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	        clipboard.setContents(stringSelection, null);
	 
	        Robot robot = null;
	 
	        try {
	            robot = new Robot();
	        } catch (AWTException e) {
	            e.printStackTrace();
	        }
	 
	        robot.delay(250);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	        robot.keyPress(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_V);
	        robot.keyRelease(KeyEvent.VK_CONTROL);
	        robot.keyPress(KeyEvent.VK_ENTER);
	        robot.delay(150);
	        robot.keyRelease(KeyEvent.VK_ENTER);
	    }

	    //Method to Upload updated curriculum
		public void updateNewCurriculum(String option,String curriculum,String filepath,String filename,String updateUploadBtnXpath ) {
			
			UIHelper.waitFor(driver);
			chooseCurriculumMenu(option);
			UIHelper.waitFor(driver);
			//Choose the curriculum
			Select selectBox1 = new Select(driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/div[2]/div/div[1]/div[1]/select"))); 
			UIHelper.waitFor(driver);
			selectBox1.selectByVisibleText(curriculum);
			//Upload Curriculum
			String finalFilePath = System.getProperty("user.dir") + filepath;
			System.out.println(finalFilePath+filename);
			UIHelper.waitFor(driver);UIHelper.waitFor(driver);UIHelper.waitFor(driver);
			uploadFileWithRobot(finalFilePath,filename);
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath("/html/body/div[1]/div[4]/div[3]/div[2]/div/div[2]/div[3]/form/uploader/div/div[1]/div[2]/button")).click();			
			UIHelper.waitForLong(driver);			
			report.updateTestLog("CMT Manage Page", "In Manage Page,the Upload Updated Curriculum Draft is created", Status.PASS);
		}
	
}
