package testscripts.collections;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentDetailsPage;
import com.pearson.automation.alfresco.pages.AlfrescoDocumentLibPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSearchPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.alfresco.pages.AlfrescoTasksPage;
import com.pearson.automation.alfresco.tests.AlfrescoCollectionsPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyFilesPageTest;
import com.pearson.automation.alfresco.tests.AlfrescoMyTasksPageTest;
import com.pearson.automation.utils.CSVUtil;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class ALFDEPLOY_4115_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_4115_TC001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY_4115_TC001:Validate RUMBA Program Name property values in Alfresco Properties forms <br>"
				+ "ALFDEPLOY_4115_TC002: Confirm new values applied on CSV output <br> ");
				
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);
		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("ApplicationUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);
	}

	@Override
	public void executeTest() {
	
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
			AlfrescoDocumentDetailsPage DocDetailsPg = new AlfrescoDocumentDetailsPage(scriptHelper);
			String errorfile = dataTable.getData("MyFiles", "Subfolders1");
			String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
			File downloadedFile = null;
			String fileDownloadPath = properties.getProperty("DefaultDownloadPath");
			new FileUtil().deleteIfFileExistsInDownloadPath(fileDownloadPath,errorfile);
			ArrayList<String> csvFileRowDataList = null;
			Date date = new Date();
			String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
			String[] uploadName = dataTable.getData("MyFiles", "uploadpath").split("/");
			String filePath = dataTable.getData("MyFiles", "FilePath");
			String fileName = dataTable.getData("MyFiles", "FileName");
			String processingindicatorxpath = "//*[@title='Processing import spreadsheet']";	
			String allProperties = ".//a[contains(text(),'All Properties...')]";
			String sqobject = "//*[@class='filename']//span";
			String downloadFilePath = properties.getProperty("DefaultDownloadPath");
			
			// Log in Pearson Schools project
			functionalLibrary.loginAsValidUser(signOnPage);							
					
					//Create site
					UIHelper.waitForLong(driver);				
					homePageObj.navigateToSitesTab();	
					UIHelper.waitFor(driver);
					sitesPage.siteFinder("qa4115");		
			
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();
					
					// go to Course plan					
					myFiles.openCreatedFolder("Data Imports");
					UIHelper.waitForPageToLoad(driver);
					myFiles.openCreatedFolder("Course Plan");
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);				
					
					// upload course plan
					collectionPg.uploadFileInCollectionSite(filePath, fileName);				
					UIHelper.waitForLong(driver);						
					UIHelper.pageRefresh(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);						
			
					//case 1: Validate RUMBA Program Name property values
					sitesPage.enterIntoDocumentLibrary();
					UIHelper.waitFor(driver);
					//Go to courses
					myFiles.openCreatedFolder("Courses");
					UIHelper.waitForPageToLoad(driver);
							
					//Click on edit properties
					UIHelper.waitFor(driver);								
					collectionPg.clickOnMoreSetting("ALFEDPLOY-3672 UAT Test Course");
					collectionPg.commonMethodForClickOnMoreSettingsOption("ALFEDPLOY-3672 UAT Test Course",
							"Edit Properties");
					UIHelper.click(driver, allProperties); 								
								
					//verify property value -RUMBA Program Name
					
					boolean isAvailable = collectionPg.isObjectFieldAvailable("RUMBA Program Name:");
					if(isAvailable = true)
					{
						report.updateTestLog("Verify Collection object fields",	"Verify Collection object fields Passed", Status.PASS);
					}else
					{
						report.updateTestLog("Verify Collection object fields", "Verify Collection object fields Failed", Status.FAIL);
					}
					UIHelper.waitFor(driver);
					
					//Edit property value - RUMBA Program Name with invalid and valid data
					collectionPg.VerifyPropertyValueInValidData("RUMBA Program Name:", "  ");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					collectionPg.VerifyPropertyValueValidData("RUMBA Program Name:", "Test Program");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver); 
									
					
					//Verify the edited value in view details page
					
					//collectionPg.clickOnMouseOverMenu("ALFEDPLOY-3672 UAT Test Course","View Details");	
					collectionPg.clickOnMoreSetting("ALFEDPLOY-3672 UAT Test Course");
					collectionPg.commonMethodForClickOnMoreSettingsOption("ALFEDPLOY-3672 UAT Test Course",
							"View Details");
					UIHelper.waitFor(driver);	
					UIHelper.waitFor(driver);							
					collectionPg.VerifyPropertyValueINviewDetails("RUMBA Program Name:","Test Program");
					//DocDetailsPg.performEditPropertiesDocAction();
					UIHelper.waitFor(driver);					
					
					//Case 2: Confirm new values applied on CSV export output					
					
					//Apply version state filter
					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder("Programs");
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder("Program");
					UIHelper.waitFor(driver);
					collectionPg.clickOnEditCollectionButton();	
					UIHelper.waitFor(driver);
					
					// Click on Generate Realize Csv for course object
					
							collectionPg.clickOnBrowseActionsInCollectionUI("ALFEDPLOY-3672 UAT Test Course", "Generate Realize CSVs");
							UIHelper.waitFor(driver);
							collectionPg.clickonrealizebox();
							UIHelper.waitFor(driver);
							
							// Navigate to Document library
							sitesPage.enterIntoDocumentLibrary();
							UIHelper.waitFor(driver);
							myFiles.openCreatedFolder(uploadName[0]);
							UIHelper.waitFor(driver);
							myFiles.openCreatedFolder(uploadName[1]);
							UIHelper.waitFor(driver);
							myFiles.openCreatedFolder(uploadName[2]);
							UIHelper.waitFor(driver);
							
							// Navigate to generated csv file path and check whether
							sitesPage.documentdetails(currentDate);
							
							// CSVfile presence and filename
							String fileName1 = "ALFEDPLOY-3672 UAT Test Course" + "-" + currentDate;	
							//String filenameCSV= mediaTransPage.RetreiveFilename(fileName1);
							
							String filename2= collectionPg.CSVFilename(fileName1);
							
							collectionPg.clickOnMoreSetting(filename2);
							collectionPg.commonMethodForClickOnMoreSettingsOption(filename2,"Download");
					
							new FileUtil().waitUptoFileDownloadComplete(downloadFilePath, filename2);
							downloadedFile = new File(downloadFilePath + "/" + filename2);
							if (downloadedFile.exists() && !filename2.equalsIgnoreCase("File Not Found")) {
								report.updateTestLog("Verify download file", "File: " + filename2 + " downloaded sucessfully", Status.PASS);
						
					
			
								csvFileRowDataList = new CSVUtil().readLinesOfDataFromCSVFile(downloadFilePath + "/" + filename2);						
								
								String[] filterselection1 = dataTable.getData("MyFiles", "HeaderName").split(";");		
								int filtercount =0;
								int count1=0;																		
									for (String fileValues : filterselection1) 
									{
										
										String splittedFileValues[] = fileValues.split(",");			
										
										for (String values:splittedFileValues){
											
											if (values != null) 
											{
													System.out.println("SplittedFilevalue: "+values);								
													for (String csvRow : csvFileRowDataList) 
													{				
														String splitedRow[] = null;
														splitedRow = csvRow.split(",",-1);
														System.out.println("splitedRow[3]=="+splitedRow[3]);
														if(values.equals(splitedRow[3].replace("\"", ""))){												
															count1++;
															break;
														}else{
															
														}
													}
													if(count1>0){
														report.updateTestLog("Verify User Can able to export the Csv with the modified RUMBA Program Name  ", " User is able to export the Csv with the modified RUMBA Program Name <br>Options: "+values, Status.PASS);									
													}else{
														report.updateTestLog("Verify User Can able to export the Csv with the modified RUMBA Program Name  ", "User is not able to export the Csv with the modified RUMBA Program Name  <br>Options: "+values, Status.FAIL);									
													}
													filtercount++;
										}
										}
									}
								
							}		
											
	}

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub

		}
	}


