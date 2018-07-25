package testscripts.epm;

import org.testng.annotations.Test;

import com.pearson.automation.alfresco.functionllibs.FunctionalLibrary;
import com.pearson.automation.alfresco.pages.AlfrescoEPMToolPage;
import com.pearson.automation.alfresco.tests.AlfrescoEPMToolPageTest;
import com.pearson.automation.utils.DriverScript;
import com.pearson.automation.utils.FileUtil;
import com.pearson.automation.utils.TestCase;
import com.pearson.framework.IterationOptions;
import com.pearson.framework.Status;

public class AUT_AG_011P2 extends TestCase {
	private FunctionalLibrary functionalLibrary;

	@Test
	public void runAUT_AG_011P2() {
		testParameters
				.setCurrentTestDescription("Verify the aspects of an archive folder matches with the EPM record when attaching an EPM record to the folder");
		testParameters.setIterationMode(IterationOptions.RunOneIterationOnly);

		driverScript = new DriverScript(testParameters);
		driverScript.driveTestExecution();
	}

	@Override
	public void setUp() {
		functionalLibrary = new FunctionalLibrary(scriptHelper);
		report.addTestLogSection("Setup");

		driver.get(properties.getProperty("EPMUrl"));
		report.updateTestLog(
				"Invoke Application",
				"Invoke the application under test @ "
						+ properties.getProperty("ApplicationUrl"), Status.DONE);

	}

	@Override
	public void executeTest() {
		
		AlfrescoEPMToolPage epmToolPgObj = new AlfrescoEPMToolPage(scriptHelper);
		
		String searchContent = dataTable.getData("EPM",
				"ListTitlesWithFieldValue");
		String SearchType = dataTable.getData("EPM", "SearchContentType");
		epmToolPgObj.performSearchInEPMTool(searchContent.replace("'",""), SearchType);
		
		String folderISBNValStoreFilePath = "src/test/resources/AppTestData/TestOutput/FolderISBNData.txt";
		
		String isbnValue = "";
		try {
			isbnValue = new FileUtil().readDataFromFile(folderISBNValStoreFilePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		epmToolPgObj.clickOnTitleInEPMTool(isbnValue);
		
		AlfrescoEPMToolPageTest epmToolPgTestObj = new AlfrescoEPMToolPageTest(scriptHelper);
		epmToolPgTestObj.verifyEPMApplicationFieldDetailsInAlfresco();
	}

	@Override
	public void tearDown() {
		// Nothing to do
	}
}