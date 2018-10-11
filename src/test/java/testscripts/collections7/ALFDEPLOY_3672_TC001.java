package testscripts.collections7;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
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

public class ALFDEPLOY_3672_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void ALFDEPLOY_3672_TC001() {
		testParameters
				.setCurrentTestDescription("ALFDEPLOY_3672_TC001: Validate Import successful with no errors reported <br>"
				+ "ALFDEPLOY_3672_TC002: Validate Instruction property is present and editable on sequence object <br> "
				+ "ALFDEPLOY_3672_TC003: Validate Instruction property is present and editable on Container object <br> "
				+ "ALFDEPLOY_3672_TC004: Validate Edited Instruction property is appeared in export CSV <br> ");
				
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
		try{
			AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
			AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
			AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
			AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
			AlfrescoCollectionsPageTest collectionPgTest = new AlfrescoCollectionsPageTest(scriptHelper);
			AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
			AlfrescoDocumentLibPage docLibPg = new AlfrescoDocumentLibPage(scriptHelper);
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
			boolean flag = false;
			// Log in Pearson Schools project
			functionalLibrary.loginAsValidUser(signOnPage);							
			
					//Create site
					//UIHelper.waitForLong(driver);					
					homePageObj.navigateToSitesTab();	
					UIHelper.waitFor(driver);
					//Modified as part of NALS
					//sitesPage.siteFinder("qa3672");
					String siteNameValue =  dataTable.getData("Sites", "SiteName");				
					sitesPage.createSite(siteNameValue, "Yes");
					UIHelper.waitFor(driver);
					sitesPage.enterIntoDocumentLibrary();
				
					myFiles.openCreatedFolder("Courses");	
					myFiles.deleteCreatedFolder("ALFEDPLOY-3672 UAT Test Course");
					UIHelper.waitFor(driver);	
					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder("Data Exports");	
					myFiles.openCreatedFolder("Realize Export");
					myFiles.deleteCreatedFolder("ALFEDPLOY-3672 UAT Test Course");
					UIHelper.waitFor(driver);	
				
					
					// Go to collection UI
					sitesPage.enterIntoDocumentLibrary();
					
					// go to Course plan
					
					myFiles.openCreatedFolder(folderNames[0]);
					UIHelper.waitForPageToLoad(driver);
					myFiles.openCreatedFolder(folderNames[1]);
					UIHelper.waitForPageToLoad(driver);
					UIHelper.waitFor(driver);
				
					
					// upload course plan
					collectionPg.uploadFileInCollectionSite(filePath, fileName);				
					UIHelper.waitForLong(driver);						
					UIHelper.pageRefresh(driver);
					UIHelper.waitForInvisibilityOfAjaxImgByXpath(driver, processingindicatorxpath);	
								
					//go to course object 1
					
					sitesPage.enterIntoDocumentLibrary();
					UIHelper.waitFor(driver);					
					myFiles.openCreatedFolder(folderNames[0]);
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(folderNames[2]);
					UIHelper.waitFor(driver);
					myFiles.openCreatedFolder(currentDate.substring(0, 4));
					myFiles.openCreatedFolder(currentDate.substring(5, 7));
					myFiles.openCreatedFolder(currentDate.substring(8, 10));
					UIHelper.waitFor(driver);
					report.updateTestLog("Check whether CSV file is uploaded" , "Status: "  +"CSV file is uploaded", Status.PASS);
					
					flag = collectionPg.CheckOptionsForClickOnMoreSettingsOption(fileName,"View Error Report");
					if(flag){
						report.updateTestLog("View Error Report :", "View Error Report is present "
								+ "for "+"<b> "+fileName, Status.FAIL);		
					}else{
						report.updateTestLog("View Error Report:", "View Error Report is not presented "
								+ "for "+"<b> "+fileName, Status.PASS);			
					}			
										
					//case 2: Sequence Object Instruction Property validation 
					sitesPage.enterIntoDocumentLibrary();
					UIHelper.waitFor(driver);
					//Go to courses
					myFiles.openCreatedFolder("Courses");
					UIHelper.waitForPageToLoad(driver);
					myFiles.openCreatedFolder("ALFEDPLOY-3672 UAT Test Course");					
					UIHelper.waitFor(driver);
					//Edit Collection 
					collectionPgTest.verifyEditCollectionOption();
					collectionPg.clickOnEditCollectionButton();				
					UIHelper.waitForPageToLoad(driver);
				
					//Click on edit properties
					
					collectionPg.clickOnMouseOverMenu("Test Sequence Object","Edit Properties");
					UIHelper.waitFor(driver);
					UIHelper.click(driver, allProperties); 	
					UIHelper.waitFor(driver);
					
					//verify property value -Instruction Property
					collectionPg.VerifyPropertyValue("Instruction:", "Please follow these steps");	
					UIHelper.waitFor(driver);
					
					//Edit property value - Instruction Property
					
					collectionPg.VerifyPropertyValueValidData("Instruction:", "Edited: follow these steps");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver); 
					
					//Case 3 Container Object Instruction Property validation
					collectionPg.clickOnMouseOverMenu("Test Sequence Object","View Details");
					UIHelper.waitFor(driver);
					collectionPg.openObjectsInViewDetails();
					String editinViewPage = "//a[@title='Edit Properties']//span";
					UIHelper.click(driver, editinViewPage);					
					UIHelper.waitFor(driver);
					
					//verify property value -Instruction Property
					collectionPg.VerifyPropertyValue("Instruction:", "Please follow these steps");	
					UIHelper.waitFor(driver);
					
					//Edit property value - Instruction Property
					
					collectionPg.VerifyPropertyValueValidData("Instruction:", "Edited: follow these steps");
					UIHelper.waitFor(driver);
					UIHelper.waitFor(driver);
					
					//case 4: Verify Edited values in export csv
					
					//Apply version state filter
					sitesPage.enterIntoDocumentLibrary();
					myFiles.openCreatedFolder("Programs");
					myFiles.openCreatedFolder("Program");
					collectionPg.clickOnEditCollectionButton();	
					
					
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
								
				
					//String filedownloaded = "C:\\Users\\669868\\Downloads\\ALFEDPLOY-3672 UAT Test Course-2018-03-16-1020.csv ";	
					
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
														//Added for NALS
														System.out.println("splitedRow[69]=="+splitedRow[69]);
														if(values.equals(splitedRow[69].replace("\"", ""))){												
															count1++;
															break;
														}else{
															
														}
													}
													if(count1>0){
														report.updateTestLog("Verify User Can able to export the Csv only for the selected Instruction Property  ", "User can able to export the CSV only for selected Instruction Property  <br>Options: "+values, Status.PASS);									
													}else{
														report.updateTestLog("Verify User Can able to export the Csv only for the selected Instruction Property  ", "User is not able to export the CSV only for selected Instruction Property  <br>Options: "+values, Status.FAIL);									
													}
													filtercount++;
										}
										}
									}
							
									
							}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		

		@Override
		public void tearDown() {
			// TODO Auto-generated method stub

		}
	}


