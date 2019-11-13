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

public class LSALF_2192_P4 extends TestCase {

	private FunctionalLibrary functionalLibrary;

	@Test
	public void COLLECTIONS_1766_1() {
		testParameters.setCurrentTestDescription(
			"Confirm \"External ID Prefix And Separator\" property displays the actual value in the generated bookbuild JSON that is populated for the course object. "
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
			
			
			if (!filePath1.isEmpty()) {
			FileReader reader = new FileReader(filePath1);
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
            if(jsonObject.get("External ID Prefix and Separator").equals("Test@123")) {
            	report.updateTestLog("External ID Prefix and Separator is not blank","LSALF-2192 Include cpnals:externalIdPrefixAndSeparator property in JSON export",Status.PASS);
            }else {
            	report.updateTestLog("External ID Prefix and Separator is blank","LSALF-2192 Include cpnals:externalIdPrefixAndSeparator property in JSON export",Status.FAIL);
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
