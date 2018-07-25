package com.pearson.automation.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.pearson.framework.*;
import com.pearson.framework.ReportThemeFactory.Theme;
import com.pearson.framework.selenium.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Driver script class which encapsulates the core logic of the framework
 * 
 * @author Cognizant
 */
public class DriverScript {
	private TestCase testCase;
	private int currentIteration;
	private Date startTime, endTime;

	private CraftliteDataTable dataTable;
	private ReportSettings reportSettings;
	private SeleniumReport report;
	private WebDriver driver;
	private ScriptHelper scriptHelper;

	private Properties properties;
	private ExecutionMode executionMode;
	private final FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
	private Boolean testExecutedInUnitTestFramework = true;
	private Boolean linkScreenshotsToTestLog = true;
	private String testStatus;

	private final SeleniumTestParameters testParameters;
	private String reportPath;

	private String filePath = "src/test/resources/Framework/ScenarioDetails.txt";

	/**
	 * Function to indicate whether the test is executed in JUnit/TestNG or not
	 * 
	 * @param testExecutedInUnitTestFramework
	 *            Boolean variable indicating whether the test is executed in
	 *            JUnit/TestNG
	 */
	public void setTestExecutedInUnitTestFramework(Boolean testExecutedInUnitTestFramework) {
		this.testExecutedInUnitTestFramework = testExecutedInUnitTestFramework;
	}

	/**
	 * Function to confugure the linking of screenshots to the corresponding
	 * test log
	 * 
	 * @param linkScreenshotsToTestLog
	 *            Boolean variable indicating whether screenshots should be
	 *            linked to the corresponding test log
	 */
	public void setLinkScreenshotsToTestLog(Boolean linkScreenshotsToTestLog) {
		this.linkScreenshotsToTestLog = linkScreenshotsToTestLog;
	}

	/**
	 * Function to get the status of the test case executed
	 * 
	 * @return The test status
	 */
	public String getTestStatus() {
		return testStatus;
	}

	/**
	 * DriverScript constructor
	 * 
	 * @param testParameters
	 *            A {@link SeleniumTestParameters} object
	 */
	public DriverScript(SeleniumTestParameters testParameters) {
		this.testParameters = testParameters;
	}

	/**
	 * Function to execute the given test case
	 */
	public void driveTestExecution() {
		startUp();
		initializeTestIterations();
		initializeWebDriver();
		initializeTestReport();
		initializeDatatable();
		initializeTestScript();

		try {
			testCase.setUp();
			executeTestIterations();
		} catch (FrameworkException fx) {
			exceptionHandler(fx, fx.errorName);
		} catch (Exception ex) {
			exceptionHandler(ex, "Error");
		} finally {
			testCase.tearDown(); // tearDown will ALWAYS be called
		}

		quitWebDriver();
		wrapUp();
	}

	private void startUp() {
		startTime = Util.getCurrentTime();

		properties = Settings.getInstance();

		setDefaultTestParameters();
	}

	private void setDefaultTestParameters() {
		if (testParameters.getIterationMode() == null) {
			testParameters.setIterationMode(IterationOptions.RunAllIterations);
		}

		if (System.getProperty("Browser") != null) {
			testParameters.setBrowser(Browser.valueOf(System.getProperty("Browser")));
		} else {
			if (testParameters.getBrowser() == null) {
				testParameters.setBrowser(Browser.valueOf(properties.getProperty("DefaultBrowser")));
			}
		}

		if (System.getProperty("BrowserVersion") != null) {
			testParameters.setBrowserVersion(System.getProperty("BrowserVersion"));
		}

		if (System.getProperty("Platform") != null) {
			testParameters.setPlatform(Platform.valueOf(System.getProperty("Platform")));
		} else {
			if (testParameters.getPlatform() == null) {
				testParameters.setPlatform(Platform.valueOf(properties.getProperty("DefaultPlatform")));
			}
		}
	}

	private void initializeTestIterations() {
		switch (testParameters.getIterationMode()) {
		case RunAllIterations:
			String datatablePath = frameworkParameters.getRelativePath() + Util.getFileSeparator() + "Datatables";
			ExcelDataAccess testDataAccess = new ExcelDataAccess(datatablePath, testParameters.getCurrentScenario());
			testDataAccess.setDatasheetName(properties.getProperty("DefaultDataSheet"));
			int nIterations = testDataAccess.getRowCount(testParameters.getCurrentTestcase(), 0);
			testParameters.setEndIteration(nIterations);

			currentIteration = 1;
			break;

		case RunOneIterationOnly:
			currentIteration = 1;
			break;

		case RunRangeOfIterations:
			if (testParameters.getStartIteration() > testParameters.getEndIteration()) {
				throw new FrameworkException("Error", "StartIteration cannot be greater than EndIteration!");
			}
			currentIteration = testParameters.getStartIteration();
			break;

		default:
			throw new FrameworkException("Unhandled Iteration Mode!");
		}
	}

	private void initializeWebDriver() {
		executionMode = ExecutionMode.valueOf(properties.getProperty("ExecutionMode"));

		switch (executionMode) {
		case Local:
			driver = WebDriverFactory.getDriver(testParameters.getBrowser());
			break;

		case Remote:
			driver = WebDriverFactory.getDriver(testParameters.getBrowser(), properties.getProperty("RemoteUrl"));
			break;

		case Grid:
			driver = WebDriverFactory.getDriver(testParameters.getBrowser(), testParameters.getBrowserVersion(),
					testParameters.getPlatform(), properties.getProperty("RemoteUrl"));
			break;

		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}

		//driver.manage().window().maximize();
	}

	private void initializeTestReport() {
		initializeReportSettings();
		ReportTheme reportTheme = ReportThemeFactory
				.getReportsTheme(Theme.valueOf(properties.getProperty("ReportsTheme")));

		report = new SeleniumReport(reportSettings, reportTheme);

		report.initialize();
		report.setDriver(driver);
		report.initializeTestLog();
		createTestLogHeader();
	}

	private void initializeReportSettings() {
		if (System.getProperty("ReportPath") != null) {
			reportPath = System.getProperty("ReportPath");
		} else {
			reportPath = TimeStamp.getInstance();
		}

		reportSettings = new ReportSettings(reportPath,
				testParameters.getCurrentScenario() + "_" + testParameters.getCurrentTestcase());

		reportSettings.setDateFormatString(properties.getProperty("DateFormatString"));
		reportSettings.setLogLevel(Integer.parseInt(properties.getProperty("LogLevel")));
		reportSettings.setProjectName(properties.getProperty("ProjectName"));
		reportSettings.generateExcelReports = Boolean.parseBoolean(properties.getProperty("ExcelReport"));
		reportSettings.generateHtmlReports = Boolean.parseBoolean(properties.getProperty("HtmlReport"));
		reportSettings.takeScreenshotFailedStep = Boolean
				.parseBoolean(properties.getProperty("TakeScreenshotFailedStep"));
		reportSettings.takeScreenshotPassedStep = Boolean
				.parseBoolean(properties.getProperty("TakeScreenshotPassedStep"));
		reportSettings.consolidateScreenshotsInWordDoc = Boolean
				.parseBoolean(properties.getProperty("ConsolidateScreenshotsInWordDoc"));
		if (testParameters.getBrowser().equals(Browser.HtmlUnit)) {
			// Screenshots not supported in headless mode
			reportSettings.linkScreenshotsToTestLog = false;
		} else {
			reportSettings.linkScreenshotsToTestLog = this.linkScreenshotsToTestLog;
		}
	}

	private void createTestLogHeader() {
		report.addTestLogHeading(reportSettings.getProjectName() + " - " + reportSettings.getReportName()
				+ " Automation Execution Results");
		report.addTestLogSubHeading("Date & Time",
				": " + Util.getCurrentFormattedTime(properties.getProperty("DateFormatString")), "Iteration Mode",
				": " + testParameters.getIterationMode());
		report.addTestLogSubHeading("Start Iteration", ": " + testParameters.getStartIteration(), "End Iteration",
				": " + testParameters.getEndIteration());

		switch (executionMode) {
		case Local:
			report.addTestLogSubHeading("Browser", ": " + testParameters.getBrowser(), "Executed on",
					": " + "Local Machine");
			break;

		case Remote:
			report.addTestLogSubHeading("Browser", ": " + testParameters.getBrowser(), "Executed on",
					": " + properties.getProperty("RemoteUrl"));
			break;

		case Grid:
			String browserVersion = testParameters.getBrowserVersion();
			if (browserVersion == null) {
				browserVersion = "Not specified";
			}
			report.addTestLogSubHeading("Browser", ": " + testParameters.getBrowser(), "Version",
					": " + browserVersion);
			report.addTestLogSubHeading("Platform", ": " + testParameters.getPlatform().toString(), "Executed on",
					": " + "Grid @ " + properties.getProperty("RemoteUrl"));
			break;

		default:
			throw new FrameworkException("Unhandled Execution Mode!");
		}

		report.addTestLogTableHeadings();
	}

	private void initializeDatatable() {

		String datatablePath = "";

		//if (properties.getProperty("ApplicationUrl").contains("qa")) {

			datatablePath = frameworkParameters.getRelativePath() + Util.getFileSeparator() + "QADatatables";

	/*	} else if (properties.getProperty("ApplicationUrl").contains("app")) {
			datatablePath = frameworkParameters.getRelativePath() + Util.getFileSeparator() + "Datatables";
		} else {
			
			 * datatablePath = frameworkParameters.getRelativePath() +
			 * Util.getFileSeparator() + "Datatables";
			 

			datatablePath = frameworkParameters.getRelativePath() + Util.getFileSeparator() + "QADatatables";

		}*/

		String runTimeDatatablePath;
		Boolean includeTestDataInReport = Boolean.parseBoolean(properties.getProperty("IncludeTestDataInReport"));
		if (includeTestDataInReport) {

	//		if (properties.getProperty("ApplicationUrl").contains("qa")) {

				runTimeDatatablePath = reportPath + Util.getFileSeparator() + "QADatatables";

		/*	} else if (properties.getProperty("ApplicationUrl").contains("app")) {
				runTimeDatatablePath = reportPath + Util.getFileSeparator() + "Datatables";
			} else {
				runTimeDatatablePath = reportPath + Util.getFileSeparator() + "QADatatables";
			}
*/
			new FileUtil().writeTextToFile(testParameters.getCurrentScenario(), filePath);
			File runTimeDatatable = new File(
					runTimeDatatablePath + Util.getFileSeparator() + testParameters.getCurrentScenario() + ".xls");
			if (!runTimeDatatable.exists()) {
				File datatable = new File(
						datatablePath + Util.getFileSeparator() + testParameters.getCurrentScenario() + ".xls");

				try {
					FileUtils.copyFile(datatable, runTimeDatatable);
				} catch (IOException e) {
					e.printStackTrace();
					throw new FrameworkException(
							"Error in creating run-time datatable: Copying the datatable failed...");
				}
			}

			File runTimeCommonDatatable = new File(
					runTimeDatatablePath + Util.getFileSeparator() + "Common Testdata.xls");
			if (!runTimeCommonDatatable.exists()) {
				File commonDatatable = new File(datatablePath + Util.getFileSeparator() + "Common Testdata.xls");

				try {
					FileUtils.copyFile(commonDatatable, runTimeCommonDatatable);
				} catch (IOException e) {
					e.printStackTrace();
					throw new FrameworkException(
							"Error in creating run-time datatable: Copying the common datatable failed...");
				}
			}
		} else {
			runTimeDatatablePath = datatablePath;
		}

		dataTable = new CraftliteDataTable(runTimeDatatablePath, testParameters.getCurrentScenario());
		dataTable.setDataReferenceIdentifier(properties.getProperty("DataReferenceIdentifier"));

		// Initialize the datatable row in case test data is required during the
		// setUp()
		dataTable.setCurrentRow(testParameters.getCurrentTestcase(), currentIteration);
	}

	private void initializeTestScript() {
		scriptHelper = new ScriptHelper(dataTable, report, driver);

		// currentTest = new TC1();
		testCase = getTestCaseInstance();
		testCase.initialize(scriptHelper);
	}

	private TestCase getTestCaseInstance() {
		Class<?> testScriptClass;
		try {
			testScriptClass = Class.forName("testscripts." + testParameters.getCurrentScenario().toLowerCase() + "."
					+ testParameters.getCurrentTestcase());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException("The specified test script is not found!");
		}

		try {
			return (TestCase) testScriptClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FrameworkException("Error while instantiating the specified test script");
		}
	}

	private void executeTestIterations() {
		while (currentIteration <= testParameters.getEndIteration()) {
			report.addTestLogSection("Iteration: " + Integer.toString(currentIteration));

			// Evaluate each test iteration for any errors
			try {
				testCase.executeTest();
			} catch (FrameworkException fx) {
				exceptionHandler(fx, fx.errorName);
			} catch (Exception ex) {
				exceptionHandler(ex, "Error");
			}

			currentIteration++;
			dataTable.setCurrentRow(testParameters.getCurrentTestcase(), currentIteration);
		}
	}

	private void exceptionHandler(Exception ex, String exceptionName) {
		// Error reporting
		String exceptionDescription = ex.getMessage();
		if (exceptionDescription == null) {
			exceptionDescription = ex.toString();
		}

		if (ex.getCause() != null) {
			report.updateTestLog(exceptionName, exceptionDescription + " <b>Caused by: </b>" + ex.getCause(),
					Status.FAIL);
		} else {
			report.updateTestLog(exceptionName, exceptionDescription, Status.FAIL);
		}
		ex.printStackTrace();

		// Error response
		if (frameworkParameters.getStopExecution()) {
			report.updateTestLog("CRAFTLite Info", "Test execution terminated by user! All subsequent tests aborted...",
					Status.DONE);
			currentIteration = testParameters.getEndIteration();
		} else {
			OnError onError = OnError.valueOf(properties.getProperty("OnError"));
			switch (onError) {
			// Stop option is not relevant when run from QC
			case NextIteration:
				report.updateTestLog("CRAFTLite Info",
						"Test case iteration terminated by user! Proceeding to next iteration (if applicable)...",
						Status.DONE);
				break;

			case NextTestCase:
				report.updateTestLog("CRAFTLite Info",
						"Test case terminated by user! Proceeding to next test case (if applicable)...", Status.DONE);
				currentIteration = testParameters.getEndIteration();
				break;

			case Stop:
				frameworkParameters.setStopExecution(true);
				report.updateTestLog("CRAFTLite Info",
						"Test execution terminated by user! All subsequent tests aborted...", Status.DONE);
				currentIteration = testParameters.getEndIteration();
				break;

			default:
				throw new FrameworkException("Unhandled OnError option!");
			}
		}
	}

	private void exception() {

		// Error response
		if (frameworkParameters.getStopExecution()) {
			report.updateTestLog("CRAFTLite Info", "Test execution terminated by user! All subsequent tests aborted...",
					Status.DONE);
			currentIteration = testParameters.getEndIteration();
		} else {
			OnError onError = OnError.valueOf(properties.getProperty("OnError"));
			switch (onError) {
			// Stop option is not relevant when run from QC
			case NextIteration:
				report.updateTestLog("CRAFTLite Info",
						"Test case iteration terminated by user! Proceeding to next iteration (if applicable)...",
						Status.DONE);
				break;

			case NextTestCase:
				report.updateTestLog("CRAFTLite Info",
						"Test case terminated by user! Proceeding to next test case (if applicable)...", Status.DONE);
				currentIteration = testParameters.getEndIteration();
				break;

			case Stop:
				frameworkParameters.setStopExecution(true);
				report.updateTestLog("CRAFTLite Info",
						"Test execution terminated by user! All subsequent tests aborted...", Status.DONE);
				currentIteration = testParameters.getEndIteration();
				break;

			default:
				throw new FrameworkException("Unhandled OnError option!");
			}
		}
	}

	private void quitWebDriver() {
		driver.quit();
	}

	private void wrapUp() {
		endTime = Util.getCurrentTime();
		closeTestReport();

		testStatus = report.getTestStatus();

		if (testExecutedInUnitTestFramework && testStatus.equalsIgnoreCase("Failed")) {
			Assert.fail(report.getFailureDescription());
		}
	}

	private void closeTestReport() {
		String executionTime = Util.getTimeDifference(startTime, endTime);
		report.addTestLogFooter(executionTime);

		if (reportSettings.consolidateScreenshotsInWordDoc) {
			report.consolidateScreenshotsInWordDoc();
		}
	}
}