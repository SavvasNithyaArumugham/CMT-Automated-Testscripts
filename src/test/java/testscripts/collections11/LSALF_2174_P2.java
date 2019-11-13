package testscripts.collections11;

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

public class LSALF_2174_P2 extends TestCase {

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
		UIHelper.waitFor(driver);
		String filename2 = mediaTransPage.RetreiveFilename(fileName1);		
		collectionPg.clickOnMoreSetting(filename2);
		collectionPg.commonMethodForClickOnMoreSettingsOption(filename2, "Download");
		UIHelper.waitFor(driver);
		final String filePath1 = "C:\\Automation\\Alfresco\\DownloadFiles\\"+filename2;
		UIHelper.waitFor(driver);
		FileReader reader = new FileReader(filePath1);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
		JSONArray ProductSettings = (JSONArray) jsonObject.get("Product Settings");        
        Iterator itrr = ProductSettings.iterator();
		if(itrr!=null) {
			System.out.println("1");
		while (itrr.hasNext()) {
			System.out.println("2");
			Object slide = itrr.next();
			System.out.println("3");
			JSONObject jsonObject2 = (JSONObject) slide;
			System.out.println("4");
			JSONArray Children2 = (JSONArray) jsonObject2.get("Product Settings");
			System.out.println("5");
			Iterator itr1 = Children2.iterator();
			System.out.println("6");
			report.updateTestLog("Verify Children2 present in the Course json file", "Children2:::::" + Children2, Status.DONE);	
			while (itr1.hasNext()) {
				System.out.println("7");
				JSONObject innerObj2 = (JSONObject) itr1.next();
				System.out.println("8");
				System.out.println(innerObj2.get("Product Type").toString());
				System.out.println("9");
			}
		}
		}
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	@Override
	public void tearDown() {
		// TODO Auto-generated method stub

	}
}
