package com.pearson.automation.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.pearson.framework.ALMIntegration;
import com.pearson.framework.CraftliteDataTable;
import com.pearson.framework.FrameworkParameters;
import com.pearson.framework.Settings;
import com.pearson.framework.Util;
import com.pearson.framework.selenium.*;


/**
 * Abstract base class for all the test cases to be automated
 * @author Cognizant
 */
public abstract class TestCase
{
	/**
	 * The {@link SeleniumTestParameters} object to be used to specify the test parameters
	 */
	protected SeleniumTestParameters testParameters;
	/**
	 * The {@link DriverScript} object to be used to execute the required test case
	 */
	protected DriverScript driverScript;
	
	private ResultSummaryManager resultSummaryManager = new ResultSummaryManager();
	private Date startTime, endTime;
	
	/**
	 * The {@link CraftliteDataTable} object (passed from the Driver script)
	 */
	protected CraftliteDataTable dataTable;
	/**
	 * The {@link SeleniumReport} object (passed from the Driver script)
	 */
	protected SeleniumReport report;
	/**
	 * The {@link WebDriver} object (passed from the Driver script)
	 */
	protected WebDriver driver;
	/**
	 * The {@link ScriptHelper} object (required for calling one reusable library from another)
	 */
	protected ScriptHelper scriptHelper;
	/**
	 * The {@link Properties} object with settings loaded from the framework properties file
	 */
	protected Properties properties;
	
	private ALMIntegration aLMStatus=new ALMIntegration();
	
	/**
	 * Function to initialize the {@link ScriptHelper} object and in turn the objects wrapped by it,
	 * as well as load the {@link Properties} object using the {@link Settings} class
	 * @param scriptHelper The {@link ScriptHelper} object
	 */
	public void initialize(ScriptHelper scriptHelper)
	{
		this.scriptHelper = scriptHelper;
		
		this.dataTable = scriptHelper.getDataTable();
		this.report = scriptHelper.getReport();
		this.driver = scriptHelper.getDriver();
		
		properties = Settings.getInstance();
	}
	
	/**
	 * Function to do the required set-up activities before executing the overall test suite in TestNG
	 * @param testContext The TestNG {@link ITestContext} of the current test suite 
	 */
	@BeforeSuite
	public void suiteSetup(ITestContext testContext)
	{
		
		try {
			String statusFilePath = "src/test/resources/HPALM/TestcaseStatus.txt";
			String filePath = "src/test/resources/HPALM/TestcaseDetails.txt";
			PrintWriter pw = new PrintWriter(statusFilePath);
			PrintWriter pw1 = new PrintWriter(filePath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		resultSummaryManager.setRelativePath();
		resultSummaryManager.initializeTestBatch(testContext.getSuite().getName());
		
		int nThreads;
		if (testContext.getSuite().getParallel().equalsIgnoreCase("false")) {
			nThreads = 1;
		} else {
			nThreads = testContext.getCurrentXmlTest().getThreadCount();
		}
		resultSummaryManager.initializeSummaryReport(nThreads);
		resultSummaryManager.setupErrorLog();
	}
	
	/**
	 * Function to do the required set-up activities before executing each test case in TestNG
	 * @throws MessagingException 
	 */
	@BeforeMethod
	public void testMethodSetup() throws MessagingException
	{
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		if(frameworkParameters.getStopExecution()) {
			suiteTearDown();
			
			throw new SkipException("Aborting all subsequent tests!");
		} else {
			startTime = Util.getCurrentTime();
			
			String currentScenario =
					capitalizeFirstLetter(this.getClass().getPackage().getName().substring(12));
			String currentTestcase = this.getClass().getSimpleName();
			testParameters = new SeleniumTestParameters(currentScenario, currentTestcase);
		}
	}
	
	private String capitalizeFirstLetter(String myString)
	{
		StringBuilder stringBuilder = new StringBuilder(myString);
		stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
		return stringBuilder.toString();
	}
	
	/**
	 * Function to do required setup activities before starting the test execution
	 */
	public abstract void setUp();
	
	/**
	 * Function which implements the test case to be automated
	 */
	public abstract void executeTest();
	
	/**
	 * Function to do required teardown activities at the end of the test execution
	 */
	public abstract void tearDown();
	
	/**
	 * Function to do the required wrap-up activities after executing each test case in TestNG
	 */
	@AfterMethod
	public void testMethodTearDown()
	{
		
		
		String testStatus = driverScript.getTestStatus();
		endTime = Util.getCurrentTime();
		String executionTime = Util.getTimeDifference(startTime, endTime);
		String HPALMStatusUpdateVal=new ALMIntegration().loadProperties("HPALMStatusUpdate");
		if(HPALMStatusUpdateVal.equalsIgnoreCase("True")){
			resultSummaryManager.createTestStatusFile(testParameters.getCurrentScenario(),
					testParameters.getCurrentTestcase(),
					testParameters.getCurrentTestDescription(),
					executionTime, testStatus);
		}
		resultSummaryManager.updateResultSummary(testParameters.getCurrentScenario(),
									testParameters.getCurrentTestcase(),
									testParameters.getCurrentTestDescription(),
									executionTime, testStatus);
	}
	
	/**
	 * Function to do the required wrap-up activities after executing the overall test suite in TestNG
	 * @throws MessagingException 
	 */
	@AfterSuite
	public void suiteTearDown() throws MessagingException
	{
		resultSummaryManager.wrapUp(true);
		String HPALMStatusUpdateVal=new ALMIntegration().loadProperties("HPALMStatusUpdate");
		String HPALMBatchUpdateVal=new ALMIntegration().loadProperties("HPALMBatchUpdate");
		if(HPALMStatusUpdateVal.equalsIgnoreCase("True") && 
				HPALMBatchUpdateVal.equalsIgnoreCase("True")){
			resultSummaryManager.addFinalTestcaseStatusToFile();
			try {
				aLMStatus.pushStatusToALM();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}