package com.pearson.automation.alfresco.allocator;

import java.util.Date;

import com.pearson.automation.utils.DriverScript;
import com.pearson.framework.FrameworkParameters;
import com.pearson.framework.Util;
import com.pearson.framework.selenium.*;


/**
 * Class to facilitate parallel execution of test scripts
 * @author Cognizant
 */
public class ParallelRunner implements Runnable
{
	private final SeleniumTestParameters testParameters;
	private final ResultSummaryManager resultSummaryManager;
	
	
	/**
	 * Constructor to initialize the details of the test case to be executed
	 * @param testParameters The {@link SeleniumTestParameters} object (passed from the {@link Allocator})
	 * @param resultSummaryManager The {@link ResultSummaryManager} object (passed from the {@link Allocator})
	 */
	public ParallelRunner(SeleniumTestParameters testParameters, ResultSummaryManager resultSummaryManager)
	{
		super();
		
		this.testParameters = testParameters;
		this.resultSummaryManager = resultSummaryManager;
	}
	
	@Override
	public void run()
	{
		Date startTime = Util.getCurrentTime();
		String testStatus = invokeTestScript();
		Date endTime = Util.getCurrentTime();
		String executionTime = Util.getTimeDifference(startTime, endTime);
		resultSummaryManager.updateResultSummary(testParameters.getCurrentScenario(),
									testParameters.getCurrentTestcase(),
									testParameters.getCurrentTestDescription(),
									executionTime, testStatus);
	}
	
	private String invokeTestScript()
	{
		String testStatus;
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		
		if(frameworkParameters.getStopExecution()) {
			testStatus = "Aborted";
		} else {
			DriverScript driverScript = new DriverScript(this.testParameters);
			driverScript.setTestExecutedInUnitTestFramework(false);
			driverScript.driveTestExecution();
			testStatus = driverScript.getTestStatus();
		}
		
		return testStatus;
	}
}