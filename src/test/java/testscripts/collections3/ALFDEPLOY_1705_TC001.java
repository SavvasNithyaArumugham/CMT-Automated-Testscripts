package testscripts.collections3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoHomePage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;


public class ALFDEPLOY_1705_TC001 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_049() {
		testParameters
				.setCurrentTestDescription("Generic JSON Export of Collections Course Plan");
		
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
		/*AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		AlfrescoHomePage homePageObj = new AlfrescoHomePage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		AlfrescoMyFilesPage myFiles = new AlfrescoMyFilesPage(scriptHelper);
		String downloadFilePath = properties.getProperty("DefaultDownloadPath");
		String siteNameValue = dataTable.getData("Sites", "SiteName");
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		String[] folderNames = dataTable.getData("MyFiles", "Version").split(",");
		String Option[] = dataTable.getData("MyFiles", "MoreSettingsOption").split(";");
		String filePath = dataTable.getData("MyFiles", "FilePath");
		String fileName = dataTable.getData("MyFiles", "FileName");
		File downloadedFile = null;
		
		String	fileExport[] = dataTable.getData("MyFiles", "CreateFileDetails").split(",");
	
		String progress[]  = dataTable.getData("MyFiles", "SelectDropdownValue").split(",");
		//  login
		functionalLibrary.loginAsValidUser(signOnPage);
				
		// goto site > document lib
		homePageObj.navigateToSitesTab();
		
		sitesPage.createSite(siteNameValue, "Yes");
		sitesPage.enterIntoDocumentLibrary();

				// go to Course plan and upload course
				myFiles.openCreatedFolder("Data Imports");
				myFiles.openCreatedFolder("Course Plan");
				collectionPg.uploadFileInCollectionSite(filePath, fileName);
				UIHelper.waitFor(driver);	
				UIHelper.waitFor(driver);
				UIHelper.waitFor(driver);
				// Generate Realize Csv
				
				sitesPage.enterIntoDocumentLibrary();
				myFiles.openCreatedFolder(folderNames[0]);
				myFiles.openCreatedFolder(folderNames[1]);
				collectionPg.clickOnEditCollectionButton();
				
				// Click on Generate JSON for course object
				
				collectionPg.clickOnMoreSetting(folderNames[2]);
				collectionPg.commonMethodForClickOnMoreSettingsOption(folderNames[2], Option[0]);
		homePageObj.navigateToSitesTab();
		sitesPage.openSiteFromRecentSites("AutoReferCollectionSite1461218152506");	
		
				homePageObj.navigateToRepositoryTab();
				
				myFiles.openCreatedFolder("Data Exports");
				myFiles.openCreatedFolder("JSON Export");
				myFiles.openCreatedFolder(folderNames[2]);
				
				 Date date = new Date();
					String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
					sitesPage.documentdetails(currentDate);
					
					String fileName1 = folderNames[2] + "-" + currentDate;
									
				sitesPage.commonMethodForPerformBrowseOptionJson(fileName1, "Download");
				String jsonFileName=myFiles.getFileOrFolderNameFromDocLib(folderNames[2]);
				System.out.println(jsonFileName);
							
				downloadedFile = new File(downloadFilePath + "/" + jsonFileName);
				
				if (downloadedFile.exists()
						&& !folderNames[2].equalsIgnoreCase("File Not Found")) {
					report.updateTestLog("Verify download file", "File: " + jsonFileName
							+ " downloaded sucessfully", Status.PASS);
				}
						
				String downloadedJSONFileANmeWithPath = downloadFilePath + "/" + jsonFileName;
				System.out.println(downloadedJSONFileANmeWithPath);
				
				String RespValue = CC_API_Helper.retriveValuefromJson(downloadedJSONFileANmeWithPath, "Contrib Source");
				System.out.println(RespValue);
				
				report.updateTestLog("Value in Json", "File: " + RespValue
						+ " sucessfully", Status.PASS);
				
				
				report.updateTestLog("Value in Json", "File: " + RespValue
						+ " sucessfully", Status.PASS);
				
				
		*/
		
		 try {
		       // read the json file

		           FileReader reader = new FileReader("C:\\Automation\\Alfresco\\DownloadFiles\\CCP Test Course 4 - ALFDEPLOY-3440-2018-12-07-0651.json");

		           JSONParser jsonParser=new JSONParser();
		           org.json.simple.JSONObject jsonObject = null;
		           jsonObject= (JSONObject) jsonParser.parse(reader);
								
				 
		           // get a String from the JSON object

		           String firstName = (String) jsonObject.get("Contrib Source");

		           System.out.println("json: " + firstName);

		} catch (FileNotFoundException ex) {

		           ex.printStackTrace();

		       } catch (IOException ex) {

		           ex.printStackTrace();
		       }
		           catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

		       
		       }
	
	
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}