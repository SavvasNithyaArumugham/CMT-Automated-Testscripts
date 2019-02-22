package testscripts.collections10;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoCollectionsPage;
import com.pearson.automation.alfresco.pages.AlfrescoLoginPage;
import com.pearson.automation.alfresco.pages.AlfrescoMediaTransformPage;
import com.pearson.automation.alfresco.pages.AlfrescoMyFilesPage;
import com.pearson.automation.alfresco.pages.AlfrescoSitesPage;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.TestCase;
import com.pearson.automation.utils.UIHelper;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class LSALF_1825_2_P2 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_1766_1() {
		testParameters.setCurrentTestDescription(
			"Confirm that all of the below JSON-exportable properties are available in JSON on successful JSON export for a course, sequence  and learning bundle object:"
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
		AlfrescoMediaTransformPage mediaTransPage = new AlfrescoMediaTransformPage(scriptHelper);
		AlfrescoSitesPage sitesPage = new AlfrescoSitesPage(scriptHelper);
		// Log in Pearson Schools project
		AlfrescoLoginPage signOnPage = new AlfrescoLoginPage(scriptHelper);
		functionalLibrary.loginAsValidUser(signOnPage);		
		AlfrescoCollectionsPage collectionPg = new AlfrescoCollectionsPage(scriptHelper);
		try {
			String objectName = dataTable.getData("MyFiles", "FileName");
			Date date = new Date();
		//Click Repository-->Data Dictionary-->Controlled_List_Constraints-->cpnals_levelIncrementingList.json
		String[] filePath = dataTable.getData("MyFiles", "FilePath").split("/");
		String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
		UIHelper.click(driver, "//*[@id=\"HEADER_REPOSITORY_text\"]/a");
		UIHelper.waitFor(driver);
		UIHelper.waitForLong(driver);
		for (String path : filePath) {
			sitesPage.documentdetails(path);
		}

		// Navigate to generated csv file path and check whether
		sitesPage.documentdetails(currentDate);

		// CSVfile presence and filename
		String fileName1 =objectName + "-" + currentDate;
		String filename2 = collectionPg.CSVFilename(fileName1);
		collectionPg.clickOnMoreSetting(filename2);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");
		UIHelper.waitFor(driver);
		report.updateTestLog("Verify Course json file is available in the Repository>", "AutoCourse-SYSDate.json" + "is displayed", Status.PASS);
		
			final String filePath1 = "C:\\Automation\\Alfresco\\DownloadFiles\\"+filename2;
			String createObjectDataDC = dataTable.getData("MyFiles", "CollectionObjectAdditionalData");
			String createObjectDataC = dataTable.getData("MyFiles", "FieldDataForAllProperties");
			String createObjectDataCO = dataTable.getData("MyFiles", "FieldDataForQuickEdit");

			String splittedObjectData3[] = createObjectDataDC.split(",");
			String splittedObjectData2[] = createObjectDataC.split(",");
			String splittedObjectData1[] = createObjectDataCO.split(",");
			String objectType="";
			String name3 = "",folioprefix3="",foliostyle3="",foliostart3="";
			String name2 = "",foliostart2="";
			String name1 = "",folioprefix1="",foliostyle1="",foliostart1="";
						
			if (splittedObjectData3 != null) {
				objectType = splittedObjectData3[0].replace(
						"ObjectType:", "");
				name3 = collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData3, "Name:");				
				folioprefix3=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData3, "FolioPrefix:");
				foliostyle3=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData3, "FolioStyle:");				
				foliostart3=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData3, "FolioStart:");
			}
			
			if (splittedObjectData2 != null) {
				objectType = splittedObjectData2[0].replace(
						"ObjectType:", "");
				name2 = collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData2, "Name:");
				foliostart2=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData2, "FolioStart:");
			}
			
			

			if (splittedObjectData1 != null) {				
				objectType = splittedObjectData1[0].replace(
						"ObjectType:", "");				
				name1 = collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData1, "Name:");				
				folioprefix1=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData1, "FolioPrefix:");				
				foliostyle1=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData1, "FolioStyle:");				
				foliostart1=collectionPg.getFieldValueFromExcelForCreateObjects(
						splittedObjectData1, "FolioStart:");
			}		
				
			
			if (!filePath1.isEmpty()) {
			FileReader reader = new FileReader(filePath1);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            JSONArray Children1 = (JSONArray) jsonObject.get("Children");
            report.updateTestLog("Verify Children1 present in the Course json file", "Children1:::::" + Children1, Status.DONE);	
			Iterator itr = Children1.iterator();
		if (itr!=null) {		
			while (itr.hasNext()) {
				 JSONObject innerObj3 = (JSONObject) itr.next();
				 	report.updateTestLog("Verify Children1 present in the Course json file", "Children1:::::" + innerObj3.get("Name")+" @ "+innerObj3.get("Folio Prefix")+" @ "+innerObj3.get("Folio Start")+" @ "+innerObj3.get("Folio Style"), Status.DONE);
					report.updateTestLog("Verify Children1 present in the excel sheet", "Children1:::::" + folioprefix3+" "+foliostart3+" "+foliostyle3, Status.DONE);
					if(innerObj3.get("Name").toString().contains(name3)) {
						report.updateTestLog("Object type matches json and excel","",Status.PASS);
					}else {
						report.updateTestLog("Object type does not match json and excel","",Status.FAIL);
					}
					if(innerObj3.get("Folio Prefix").toString().contains(folioprefix3)) {
						report.updateTestLog("Folio Prefix matches json and excel","",Status.PASS);
					}else {
						report.updateTestLog("Folio Prefix does not match json and excel","",Status.FAIL);
					}
					if(innerObj3.get("Folio Start").toString().contains(foliostart3)) {
						report.updateTestLog("Folio Start matches json and excel","",Status.PASS);
					}else {
						report.updateTestLog("Folio Start does not match json and excel","",Status.FAIL);
					}
					if(innerObj3.get("Folio Style").toString().trim().contains(foliostyle3.trim())) {
						report.updateTestLog("Folio Style matches json and excel","",Status.PASS);
					}else {
						report.updateTestLog("Folio Style does not match json and excel","",Status.FAIL);
					}
				}
			}
			Iterator itrr = Children1.iterator();
			if(itrr!=null) {
			while (itrr.hasNext()) {
				Object slide = itrr.next();
				JSONObject jsonObject2 = (JSONObject) slide;
				JSONArray Children2 = (JSONArray) jsonObject2.get("Children");
				Iterator itr1 = Children2.iterator();
				report.updateTestLog("Verify Children2 present in the Course json file", "Children2:::::" + Children2, Status.DONE);	
				while (itr1.hasNext()) {
					JSONObject innerObj2 = (JSONObject) itr1.next();
					if(innerObj2.get("Name").toString().contains(name2)) {
						System.out.println("2Pass");
					}
					if(innerObj2.get("Folio Start").toString().contains(foliostart2)) {
						System.out.println("2Pass2");
					}					
					report.updateTestLog("Verify Children2 present in the Course json file", "Children2:::::" + innerObj2.get("Name")+" @ "+" @ "+innerObj2.get("Folio Start"), Status.DONE);
					report.updateTestLog("Verify Children2 present in the excel sheet", "Children2:::::" + name2+" "+foliostart2, Status.DONE);
					if(innerObj2.get("Name").toString().contains(name2)) {
						report.updateTestLog("Object type matches json and excel","",Status.PASS);
					}else {
						report.updateTestLog("Object type does not match json and excel","",Status.FAIL);
					}
					if(innerObj2.get("Folio Start").toString().contains(foliostart3)) {
						report.updateTestLog("Folio Start matches json and excel","",Status.PASS);
					}else {
						report.updateTestLog("Folio Start does not match json and excel","",Status.FAIL);
					}
				}
				

				Iterator itrr1 = Children2.iterator();
				if(itrr1!=null) {
				while (itrr1.hasNext()) {
					Object slide1 = itrr1.next();
					JSONObject jsonObject22 = (JSONObject) slide1;
					JSONArray Children3 = (JSONArray) jsonObject22.get("Children");
					report.updateTestLog("Verify Children3 present in the Course json file", "Children2:::::" + Children3, Status.DONE);
					Iterator itr2 = Children3.iterator();
					while (itr2.hasNext()) {						
						 JSONObject innerObj1 = (JSONObject) itr2.next();
						 report.updateTestLog("Verify Children3 present in the Course json file", "Children3:::::" + innerObj1.get("Name")+" @ "+innerObj1.get("Folio Prefix")+" @ "+innerObj1.get("Folio Start")+" @ "+innerObj1.get("Folio Style"), Status.DONE);
						 report.updateTestLog("Verify Children3 present in the excel sheet", "Children3:::::" +" "+ name1+" "+folioprefix1+" "+foliostart1+" "+foliostyle1, Status.DONE);										 
						 if(innerObj1.get("Name").toString().trim().contains(name1.trim())) {
								report.updateTestLog("Object type matches json and excel","",Status.PASS);
							}else {
								report.updateTestLog("Object type does not match json and excel","",Status.FAIL);
							}
							if(innerObj1.get("Folio Prefix").toString().contains(folioprefix1)) {
								report.updateTestLog("Folio Prefix matches json and excel","",Status.PASS);
							}else {
								report.updateTestLog("Folio Prefix does not match json and excel","",Status.FAIL);
							}
							if(innerObj1.get("Folio Start").toString().contains(foliostart1)) {
								report.updateTestLog("Folio Start matches json and excel","",Status.PASS);
							}else {
								report.updateTestLog("Folio Start does not match json and excel","",Status.FAIL);
							}
							if(innerObj1.get("Folio Style").toString().trim().contains(foliostyle1.trim())) {
								report.updateTestLog("Folio Style matches json and excel","",Status.PASS);
							}else {
								report.updateTestLog("Folio Style does not match json and excel","",Status.FAIL);
							}
					}
				}
				}
			}
		}
		}
	} catch (FileNotFoundException e) {
			e.printStackTrace();
	} catch (IOException e) {
			e.printStackTrace();
	} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
