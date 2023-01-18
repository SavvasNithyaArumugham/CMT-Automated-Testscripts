package com.pearson.automation.cmt.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.google.common.collect.Ordering;
import com.pearson.automation.utils.ReusableLibrary;
import com.pearson.automation.utils.ScriptHelper;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.Status;

/**
 * @author 167346
 *
 */
public class CmtHomePage  extends ReusableLibrary  {

	public CmtHomePage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}
	
	// Check Menu Items in the CMT Home Page
	public boolean checkMenu() {
		boolean menucheck = false;
		try {		
			UIHelper.waitFor(driver);
			StringBuffer menuItem = new StringBuffer();
			List<WebElement> listOfElements = driver.findElements(By.xpath("//nav[contains(@class,'menuPanelLeft')]//a[contains(@class,'menuPanelItem ng-binding')]"));
			for (int i=0; i<listOfElements.size();i++){
					menuItem.append(listOfElements.get(i).getText());
					if(i==listOfElements.size()-1) {
						//do Nothing
					}else {
					menuItem.append(",");
					}			      
			    }
			report.updateTestLog("CMT Home Page", "Menu Items are " + menuItem, Status.DONE);
			if(listOfElements.size()>=4) {
				menucheck = true;
				report.updateTestLog("CMT Home Page", "Menu Items are as expected.", Status.PASS);
			}else {
				report.updateTestLog("CMT Home Page", "Menu Items are Incorrect.", Status.FAIL);
			}	
			UIHelper.waitFor(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return menucheck;
	}
	
	// checkWelcomeMessage in the CMT Home Page
		public boolean checkWelcomeMessage() {
			boolean messagecheck = false;
			try {		
				UIHelper.waitFor(driver);
				
				WebElement welcomeMessage = driver.findElement(By.xpath("//div[contains(@class,'searchResultNotInitiatedPanel')]/div[contains(@class,'searchResult')]"));
				String checkWelcomeMessage = welcomeMessage.getText();
				System.out.println(checkWelcomeMessage);
			
						if (checkWelcomeMessage.contains("Welcome to CMT"))
						{
							
					report.updateTestLog("CMT Home Page", "Messages displayed are as expected.", Status.PASS);
						}
						else
						{
					report.updateTestLog("CMT Home Page", "Messages displayed are Incorrect.", Status.FAIL);
						}	
				UIHelper.waitFor(driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return messagecheck;
		}
		
		//Check If Countries Displayed in Home are in Alphabetical order
		public boolean checkIfCountriesInSortedOrder() {
			try {
			StringBuffer countryList = new StringBuffer();
			ArrayList<String> countries = new ArrayList<String>();		
			UIHelper.highlightElement(driver, "//div[@class='countrySectionView']");
			
			List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='countrySectionView']"));
			for (int i=0; i<listOfElements.size();i++)
			{
				countryList.append(listOfElements.get(i).getText());
				if(i==listOfElements.size()-1)
				{
					//do Nothing
				}else 
				{
					countryList.append(",");
				}				
				countries.add(listOfElements.get(i).getText());
			}
			report.updateTestLog("CMT Home Page", "Countries displayed in Home Page: " + countryList , Status.DONE);
			
			if(Ordering.<String> natural().isOrdered(countries)) {
				report.updateTestLog("CMT Home Page", "Countries are displayed in Alphabetical Order.", Status.PASS);
				return true;
			}else {
				report.updateTestLog("CMT Home Page", "Countries are not displayed in Alphabetical Order.", Status.FAIL);
				return false;
			}
			}catch(Exception e) {
				report.updateTestLog("CMT Home Page", "Countries are not displayed in Alphabetical Order.", Status.FAIL);
				return false;
			}
	}
		
		//Check If View Button is Displayed For the selected Country
		public boolean checkViewButton() {
		/*
		 * List<WebElement> listOfElements =
		 * driver.findElements(By.xpath("//div[@class='curriculumCountryList']")); for
		 * (int i=0; i<listOfElements.size();i++){
		 * if(listOfElements.get(i).getText().equalsIgnoreCase("India")) {
		 * listOfElements.get(i).click(); UIHelper.waitForLong(driver); break; } }
		 */try {
			UIHelper.waitFor(driver);
			driver.findElement(By.xpath(".//*[@title='India']")).click();
			report.updateTestLog("CMT Home Page", "India is clicked", Status.DONE);
			UIHelper.waitFor(driver);UIHelper.waitFor(driver);UIHelper.waitFor(driver);
			if (driver.findElement(By.xpath(".//*[contains(@value,'View')]")).isDisplayed()){
				UIHelper.highlightElement(driver, ".//*[contains(@value,'View')]");
				report.updateTestLog("CMT Home Page", "View Button Displayed for the Selected Country.", Status.PASS);
				return true;
			}else {
				report.updateTestLog("CMT Home Page", "View Button not Displayed for the Selected Country.", Status.FAIL);
				return false;
			}
		}catch(Exception e) {
			report.updateTestLog("CMT Home Page", "View Button not Displayed for the Selected Country.", Status.FAIL);
			return false;
		}
	}
		
		public void checkCurriculaIsSortedBySubject() {			
			try {
				StringBuffer subjectList = new StringBuffer();
				ArrayList<String> subjects = new ArrayList<String>();					
				List<WebElement> listOfElements = driver.findElements(By.xpath("//div[@class='curriculumInfoPanel']"));
				for (int i=0; i<listOfElements.size();i++)
				{
					subjectList.append(listOfElements.get(i).getText());
					if(i==listOfElements.size()-1)
					{
						//do Nothing
					}else 
					{
						subjectList.append(",");
					}				
					subjects.add(listOfElements.get(i).getText());
				}
				report.updateTestLog("CMT Home Page", "Curriculums displayed in Home Page: " + subjectList , Status.DONE);
				/*
				if(Ordering.<String> natural().isOrdered(subjects)) 
				{
					report.updateTestLog("CMT Home Page", "Curricula is sorted by Subject correctly.", Status.PASS);					
				}else 
				{
					report.updateTestLog("CMT Home Page", "Curricula is sorted by Subject incorrectly.", Status.FAIL);					
				}*/
				}catch(Exception e) 
				{
					report.updateTestLog("CMT Home Page", "Curricula is sorted by Subject incorrectly.", Status.FAIL);					
				}
		}
		public void checkSubjectAndSeachFilter() {			
			try {
				String subjectLabel ="//div[@class='filterContent']//label[contains(.,'Subject')]";
				String searchLabel ="//div[@class='filterContent']//label[contains(.,'Search')]";
								
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,subjectLabel )))
				{
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, subjectLabel));
					UIHelper.highlightElement(driver,subjectLabel);
				
					report.updateTestLog("CMT Subject Filter", "User able to view 'Subject' filter for curriculum standard list ", Status.PASS);
				} 
				else 
				{
					report.updateTestLog("CMT Subject Filter", "User is not able to view 'Subject' filter for curriculum standard list", Status.FAIL);	
				}
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,searchLabel )))
				{
					UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, searchLabel));
					UIHelper.highlightElement(driver,searchLabel);
				
					report.updateTestLog("CMT Search Filter", "User able to view 'Search' filter for curriculum standard list ", Status.PASS);
				} 
				else 
				{
					report.updateTestLog("CMT Search Filter", "User is not able to view 'Search' filter for curriculum standard list", Status.FAIL);	
				}
				
				
			}
			catch(Exception e) 
				{
					report.updateTestLog("CMT Search and Suject Filter", "Search and Suject Filter not displayed ", Status.FAIL);					
				}
		}
		
		
		
		public void subjectFilter() {			
			try {
				String subjectDropdown ="//select[contains(@class,'curricFilterSelect')]";
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,subjectDropdown)))
				{
					UIHelper.highlightElement(driver,subjectDropdown);
					WebElement dropdown = driver.findElement(By.xpath(subjectDropdown)); 
			        Select select = new Select(dropdown); 
			       java.util.List<WebElement> options = select.getOptions(); 
			        for(WebElement item:options) 
			        { 
			        	String dropDownOptions = item.getText();
			            report.updateTestLog("CMT Subject Filter", "List of Subjects in DropDown:"+dropDownOptions, Status.DONE);
			        }
			    UIHelper.waitFor(driver);
				UIHelper.selectbyVisibleText(driver, subjectDropdown, "Mathematics");
				UIHelper.waitFor(driver);
			String curriculumCountXpath ="//div[@class='curriculumTotal ng-binding']";
			String curriculumListXpath="//div[@class='curriculumBackground']";
			String curriculumCount=UIHelper.findAnElementbyXpath(driver,curriculumCountXpath).getText();
			boolean curriculumList=UIHelper.findAnElementbyXpath(driver,curriculumListXpath).isDisplayed();
			
			report.updateTestLog("CMT Search Filter", "User able to view the list and count of curriculum standard corresponding to the subject filter applied." +":"+curriculumCount , Status.PASS);
				}
				else 
				{
					report.updateTestLog("CMT Subject Filter", "User not able to view the list of curriculum standard corresponding to the subject filter applied.", Status.FAIL);	
				}
			}
			catch(Exception e) 
				{
					report.updateTestLog("CMT Subject Filter", "Subject Filter not displayed ", Status.FAIL);					
				}
		}	
		
		
		public void searchFilter() {			
			try {
				String searchTextBox ="//input[contains(@class,'curricFilterSelect')]";
				if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,searchTextBox)))
				{
				UIHelper.highlightElement(driver,searchTextBox);
				UIHelper.findAnElementbyXpath(driver, searchTextBox).sendKeys("Math");
				report.updateTestLog("CMT Search TextBox", "Search TextBox is available and data can be entered", Status.PASS);
			    UIHelper.waitFor(driver);
			    String curriculumCountXpath ="//div[@class='curriculumTotal ng-binding']";
				String curriculumListXpath="//div[@class='curriculumBackground']";
				String curriculumCount=UIHelper.findAnElementbyXpath(driver,curriculumCountXpath).getText();
				boolean curriculumList=UIHelper.findAnElementbyXpath(driver,curriculumListXpath).isDisplayed();
				
				report.updateTestLog("CMT Search Filter", "User is able to view the list and count of curriculum standard corresponding to the keyword search"+":"+curriculumCount, Status.PASS);
				
				}
				else 
				{
					report.updateTestLog("CMT Search Filter", "User is not able to view the list of curriculum standard corresponding to the keyword search", Status.FAIL);	
				}
			}
			catch(Exception e) 
				{
					report.updateTestLog("CMT Search Filter", "Search  Filter not displayed ", Status.FAIL);					
				}
		}	
		
		public void curriculamReport() 
		{			
			try {
				String viewXpath ="//tr[1]//td[1]//input[contains(@class,'cmButton-green')]";
				UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,viewXpath));
				UIHelper.findAnElementbyXpath(driver, viewXpath).click();
				UIHelper.waitFor(driver);
				//Click on Generate and Send Excel
				String generateReportXpath= "//div[@class='currPreviewMetadataItemSourceUrlDownload']";
				UIHelper.highlightElement(driver,generateReportXpath);
				UIHelper.findAnElementbyXpath(driver,generateReportXpath).click();
				report.updateTestLog("Generate and Send Excel ", "User clicked on Generate and Send Excel ", Status.PASS);
				//UIHelper.waitFor(driver);
				
				/*//Do you want to include Taxonomy Terms in the report? pop up is displayed
				String yesXpath="//div[@class='form-horizontal childTwoPop']//input[@id='buttonOk']";
				WebElement clickOnYes = driver.findElement(By.xpath(yesXpath));
				Actions action = new Actions(driver);
				action.moveToElement(clickOnYes).click().build().perform();
				UIHelper.waitFor(driver);*/
				
				//Check if the success message displayed
				String successMessageXpath = "//div[@id='confirmationPopup']";
				String successMessage=UIHelper.findAnElementbyXpath(driver, successMessageXpath).getText();
				System.out.println(successMessage);
				if (successMessage.contains("Excel file will be generated"))
				{
				report.updateTestLog("Curriculum standard Generate Report ", "User able to view notifification at the top of the screen on submission of the report.", Status.PASS);					
				report.updateTestLog("Curriculum standard Generate Report ", "Success Message :"+successMessage, Status.DONE);
				}
				else
				{
				report.updateTestLog("Curriculum standard Generate Report", "User unable to view notifification at the top of the screen on submission of the report.", Status.FAIL);					
				}
				}	
			catch(Exception e) 
			{
				report.updateTestLog("Curriculum standard Generate Report", "Report generation error", Status.FAIL);					
			}
		}
		
			public void viewURI() 
			{			
				boolean flag = false;
				try
				{	
					String viewXpath ="//tr[1]//td[1]//input[contains(@class,'cmButton-green')]";
					UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,viewXpath));
					UIHelper.findAnElementbyXpath(driver, viewXpath).click();
					UIHelper.waitFor(driver);
					String stdURIXpath ="//span[@class='copyUriText'][text()='Standard URI:']";
					String gradeURIXpath ="//span[@class='copyUriText'][text()='Grade URI:']";
					String localGradeURIXpath ="//span[@class='copyUriText'][text()='Local Grade URI:']";
					
					if (UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,stdURIXpath))
					&&(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,gradeURIXpath ))
					&&(UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,localGradeURIXpath)))))
					{
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, stdURIXpath));
						UIHelper.highlightElement(driver,stdURIXpath);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, gradeURIXpath));
						UIHelper.highlightElement(driver,gradeURIXpath);
						UIHelper.scrollToAnElement(UIHelper.findAnElementbyXpath(driver, localGradeURIXpath));
						UIHelper.highlightElement(driver,localGradeURIXpath);
						flag = true;
						report.updateTestLog("URI Check", "Able to view the URI for the selected curriculum standard ,Grade level,Local Grade", Status.PASS);	
					} 
					else 
					{
						flag = false;
					}
					
				}
				
				catch(Exception e) 
				{
				report.updateTestLog("URI Check", "Not able to view URI", Status.FAIL);					
				}
			}
				
			public void copyURI() 
			{			
				try {
					String viewXpath ="//tr[1]//td[1]//input[contains(@class,'cmButton-green')]";
					UIHelper.isWebElementDisplayed(UIHelper.findAnElementbyXpath(driver,viewXpath));
					UIHelper.findAnElementbyXpath(driver, viewXpath).click();
					UIHelper.waitFor(driver);
					//Click on 'copy' button in Standard URI:
					String stdURICopyXpath= "//div[contains(@class,'PreviewHeaderTitlePanel')]//button";
					UIHelper.highlightElement(driver,stdURICopyXpath);
					UIHelper.findAnElementbyXpath(driver,stdURICopyXpath).click();
					report.updateTestLog("Generate and Send Excel ", "User clicked on Generate and Send Excel ", Status.PASS);
					//URI Copied Check -Check if the 'URI copied to clipboard' message is displayed
					String uriCopiedXpath = "//div[@class='uriCopiedClip uriClipView']";
					String successMessage=UIHelper.findAnElementbyXpath(driver, uriCopiedXpath).getText();
					System.out.println(successMessage);
					if (successMessage.contains("URI copied to clipboard"))
					{
					report.updateTestLog("URI Copied", "Standard URI:Copied", Status.PASS);					
					
					}
					else
					{
					report.updateTestLog("URI Copied", "Standard URI: not copied", Status.FAIL);					
					}
					
					//Click on 'copy' button in Local Grade URI: 
					String localgradeURICopyXpath= "//div[@class='currPreviewHeader']//div[3]//div//button";
					UIHelper.highlightElement(driver,localgradeURICopyXpath);
					UIHelper.findAnElementbyXpath(driver,localgradeURICopyXpath).click();
					report.updateTestLog("Generate and Send Excel ", "User clicked on Generate and Send Excel ", Status.PASS);
					//URI Copied Check -Check if the 'URI copied to clipboard' message is displayed
					String localGradeuriCopiedXpath = "//div[@class='uriCopiedClip uriClipView']";
					String successMessageGrade=UIHelper.findAnElementbyXpath(driver, localGradeuriCopiedXpath).getText();
					System.out.println(successMessageGrade);
					if (successMessageGrade.contains("URI copied to clipboard"))
					{
					report.updateTestLog("URI Copied", "Local Grade URI: Copied", Status.PASS);					
					
					}
					else
					{
					report.updateTestLog("URI Copied", "Local Grade URI: not copied", Status.FAIL);					
					}
				}	
				catch(Exception e) 
				{
					report.updateTestLog("URI Copied", "Error in copying URI", Status.FAIL);					
				}
			}		
		
		
	
}
