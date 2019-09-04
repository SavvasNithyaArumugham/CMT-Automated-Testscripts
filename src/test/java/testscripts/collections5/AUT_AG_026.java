package testscripts.collections5;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_026 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_005() {
		testParameters.setCurrentTestDescription(
				
				"<br>ALFDEPLOY-4051Verify User can add Version Country,State code,Version disttrict and Version statement for Composite object"
				+"<br>ALFDEPLOY-4051Verify User can add  Version Country,State code,Version disttrict and Version statement  for Content object"
				+"<br>ALFDEPLOY-4051Verify User can add Version Country,State code,Version disttrict and Version statement for Composite object"
				+"<br>ALFDEPLOY-4051Verify User can modify  Version Country,State code,Version disttrict and Version statement for learning bundle object"
				+"<br>ALFDEPLOY-4051Verify User can add Version Country,State code,Version disttrict and Version statement for learning bundle object"
				+"<br>ALFDEPLOY-4051Verify User can add Version Country,State code,Version disttrict and Version statement for Course object ."
				+"<br>ALFDEPLOY-4051Verify User can add Version Country,State code,Version disttrict and Version statement for Sequence object"
				+"<br>ALFDEPLOY-4051Verify User can add Version Country,State code,Version disttrict and Version statement for Container object"
				+"<br>ALFDEPLOY-4051Verify User Can able to export multiple values for Version Country,State code,Version disttrict and Version statement using Pipe delimiter for all the collection object."
				+"<br>ALFDEPLOY-4051Verify User can be able to select mulltiple version state"
				+"<br>ALFDEPLOY-4051Verify the filter is not working Boolean 'OR' when it is applied for indirect objects."
						

		);

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
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String createObjectData = dataTable.getData("MyFiles", "CollectionObjectBasicData");

		String browseActionName = dataTable.getData("MyFiles", "MoreSettingsOption");
		//String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		Date date = new Date();
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		File downloadedFile = null;
		ArrayList<String> csvFileRowDataList = null;
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String VersionCounxapth = "//select[contains(@id,'prop_cpnals_versionCountry')]";
		String versionstatexpath = "//select[contains(@id,'prop_cpnals_versionState')]";
		String coursexpath = "//*[@class='filename']//*[contains(text(),'Course Object 1')]";
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		String[] uploadName = dataTable.getData("MyFiles", "uploadpath").split("/");
		
		// Log in Pearson Schools project
				functionalLibrary.loginAsValidUser(signOnPage);
				
		//Create site
				
				UIHelper.waitForLong(driver);
				homePageObj.navigateToSitesTab();
				String siteNameValue =  dataTable.getData("Sites", "SiteName");				
				//sitesPage.siteFinder(siteNameValue);
				sitesPage.createSite(siteNameValue, "Yes");
		
		// Go to collection UI
				sitesPage.enterIntoDocumentLibrary();
				
		// go to Course plan
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");

		// upload course plan
				collectionPg.uploadFileInCollectionSite(filePath, fileName);

		// verify import process progress
				collectionPg.verifyImportProcessProgress(fileName);				
				sitesPage.enterIntoDocumentLibrary();
				
		//go to course object 1
				
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPgTest.verifyEditCollectionOption();
				collectionPg.clickOnEditCollectionButton();
				UIHelper.click(driver, coursexpath);
		
		// Verify custom icons by creating new collections objects
				collectionPg.createBasicCollectionObjectFromCreateMenuforcsv(createObjectData);

		// Get the Collections object
				ArrayList<String> listOfObjects = new ArrayList<String>();
				listOfObjects=collectionPg.getFoldersFromRightPanInCollectionUi();
				

				for (String listOfObjectsString : listOfObjects) 
				{
					
					if(listOfObjectsString.contains("Content Object 1") ||listOfObjectsString.contains("AutoCompositeObj") ||
							listOfObjectsString.contains("Sequence Object 1") ||listOfObjectsString.contains("Learning Bundle 1") ||
							listOfObjectsString.contains("AutoContainer") ||listOfObjectsString.contains("Composite Object") ||
							listOfObjectsString.contains("AutoAsset") )
					{
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,"Edit Properties");
						UIHelper.waitFor(driver);
						UIHelper.waitForPageToLoad(driver);
						UIHelper.selectbyVisibleText(driver, VersionCounxapth, "CAN");
						UIHelper.selectbyVisibleText(driver, VersionCounxapth, "USA");
						UIHelper.getAllSelectedOptions(driver, VersionCounxapth);
						UIHelper.waitForPageToLoad(driver);
						WebElement VC = driver.findElement(By.xpath(VersionCounxapth));
						Select VCse = new Select(VC);
						List<WebElement> VCli = VCse.getAllSelectedOptions();
						List<String> list1 = new ArrayList<String>();
						for (WebElement line:VCli){
							String values= line.getText();
							System.out.println(values);
							list1.add(values);
						}
						
						UIHelper.selectbyVisibleText(driver, versionstatexpath, "AK");
						UIHelper.selectbyVisibleText(driver, versionstatexpath, "AZ");
						UIHelper.selectbyVisibleText(driver, versionstatexpath, "AR");
						UIHelper.getAllSelectedOptions(driver, versionstatexpath);
						
						WebElement VS = driver.findElement(By.xpath(versionstatexpath));
						Select VSse = new Select(VS);
						List<WebElement> VSli1 = VSse.getAllSelectedOptions();
						List<String> list2 = new ArrayList<String>();
						for (WebElement line:VSli1){
							String values= line.getText();
							System.out.println(values);
							list2.add(values);
						}
						
						UIHelper.waitFor(driver);
						collectionPg.clickOnSaveBtn();
						UIHelper.waitFor(driver);
						collectionPg.clickOnMoreSetting(listOfObjectsString);
						collectionPg.commonMethodForClickOnMoreSettingsOption(listOfObjectsString,"Edit Properties");
						UIHelper.waitForPageToLoad(driver);
						WebElement VC1 = driver.findElement(By.xpath(VersionCounxapth));
						Select VCse1 = new Select(VC1);
						List<WebElement> VCli2 = VCse1.getAllSelectedOptions();
						
						for (WebElement line:VCli2){
							int count=0;
							String linevalues="";
							
								for(String k:list1)
								{									
										 linevalues= line.getText();
										
										if(linevalues.contentEquals(k)){
											System.out.println("values is same"+linevalues);
											count++;											
										}else{
											//System.out.println("Not matching");
										}				
							
							
								}
							if(count==1){
								report.updateTestLog(linevalues+" options is added for "+listOfObjectsString, "<br>Options: " + linevalues + " is added sucessfully for "+listOfObjectsString, Status.PASS);									
							}else{
								report.updateTestLog(linevalues+" options is added for "+listOfObjectsString, "<br>Options: " + linevalues + " is not added sucessfully for "+listOfObjectsString, Status.FAIL);								
							}
						}
						
						WebElement VS1 = driver.findElement(By.xpath(versionstatexpath));
						Select VSse3 = new Select(VS1);
						List<WebElement> VSli3 = VSse3.getAllSelectedOptions();
						for (WebElement line:VSli3){
							int count=0;
							String linevalues="";
							
							for(String k:list2){
								
								 linevalues= line.getText();
								
								if(linevalues.contentEquals(k)){
									
									count++;								
								}else{
									//System.out.println("Not matching");
								}											
								
							}
							if(count==1){
								report.updateTestLog(linevalues+" options is added for "+listOfObjectsString, "<br>Options: " + linevalues + " is added sucessfully for "+listOfObjectsString, Status.PASS);									
							}else{
								report.updateTestLog(linevalues+" options is added for "+listOfObjectsString, "<br>Options: " + linevalues + " is not added sucessfully for "+listOfObjectsString, Status.FAIL);								
							}
						}
						
						collectionPg.clickOnSaveBtn();
					}	
				}

		driver.navigate().back();
		collectionPgTest.verifyEditCollectionOption();
		collectionPg.clickOnEditCollectionButton();
		UIHelper.pageRefresh(driver);
		// Click on Generate Realize Csv for course object
		collectionPg.clickOnBrowseActionsInCollectionUI(folderNames[2], browseActionName);
		collectionPg.clickonrealizebox();
		// Navigate to Document library
		sitesPage.enterIntoDocumentLibrary();

		myFiles.openCreatedFolder(uploadName[0]);
		myFiles.openCreatedFolder(uploadName[1]);
		myFiles.openCreatedFolder(uploadName[2]);
		
		
		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);
		
		// CSVfile presence and filename
		String fileName1 = folderNames[2] + "-" + currentDate;	
		String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
		
		String filename2= collectionPg.CSVFilename(fileName1);
		
		collectionPg.clickOnMoreSetting(filename2);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
		
		new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
		downloadedFile = new File(downloadFilePath + "/" + filename2);
		if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
			report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
			csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2);

			
			String[] filterselection1 = dataTable.getData("MyFiles", "HeaderName").split(";");		
			String[] filterselection12 = dataTable.getData("MyFiles", "Filteredcontent").split(";");
			
																
				for (String fileValues : filterselection1) 
				{
					int filtercount =0;
					String splittedFileValues[] = fileValues.split(",");					
					
					for (String values:splittedFileValues){
						int count1=0;
						if (values != null) 
						{

								for (String csvRow : csvFileRowDataList) 
								{				
									String splitedRow[] = null;
									splitedRow = csvRow.split(",",-1);
									if(values.equals(splitedRow[94].replace("\"", ""))||values.equals(splitedRow[93].replace("\"", ""))){
									System.out.println("Value in 94"+splitedRow[94]);	
									System.out.println("Value in 93"+splitedRow[93]);	
										count1++;
										break;
									}else{
										
									}
								}
								
								if(count1>0){
									report.updateTestLog("Verify User Can able to export single and multiple values for Version Country,State code,Version disttrict and Version statement using Pipe delimiter ", "User can able to export single and multiple values for State code and Version country and value is <br>Options: "+values+" for "+filterselection12[filtercount], Status.PASS);									
								}else{
									report.updateTestLog("Verify User Can able to export single multiple values for Version Country,State code,Version disttrict and Version statement using Pipe delimiter ", "User not able to export single and multiple values for State code and version county and value is <br>Options: "+values+" for "+filterselection12[filtercount], Status.FAIL);									
								}
								filtercount++;
					}
				}
			}
													
		} else {
			report.updateTestLog("Verify download file", "File: " + filename2 + " failed to download", Status.FAIL);
		}
				
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
