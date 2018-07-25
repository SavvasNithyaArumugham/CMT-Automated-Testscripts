package com.pearson.framework.selenium;

import com.pearson.automation.utils.FileUtil;
import com.pearson.framework.ALMIntegration;
import com.pearson.framework.FrameworkException;
import com.pearson.framework.FrameworkParameters;
import com.pearson.framework.ReportSettings;
import com.pearson.framework.ReportTheme;
import com.pearson.framework.ReportThemeFactory;
import com.pearson.framework.Settings;
import com.pearson.framework.TimeStamp;
import com.pearson.framework.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class ResultSummaryManager {
	public static SeleniumReport summaryReport;
	public static ReportSettings reportSettings;
	public static String reportPath;
	public static Date overallStartTime;
	public static Date overallEndTime;
	public Properties properties;
	public FrameworkParameters frameworkParameters = FrameworkParameters
			.getInstance();

	String statusFilePath = "src/test/resources/HPALM/TestcaseStatus.txt";
	String filePath = "src/test/resources/HPALM/TestcaseDetails.txt";
	
	
	public void setRelativePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		if (relativePath.contains("supportlibraries")) {
			relativePath = new File(System.getProperty("user.dir")).getParent();
		}
		this.frameworkParameters.setRelativePath(relativePath);
	}

	public void initializeTestBatch(String runConfiguration) {
		overallStartTime = Util.getCurrentTime();

		this.properties = Settings.getInstance();

		this.frameworkParameters.setRunConfiguration(runConfiguration);
	}

	public void initializeSummaryReport(int nThreads) {
		initializeReportSettings();
		ReportTheme reportTheme = ReportThemeFactory
				.getReportsTheme(ReportThemeFactory.Theme
						.valueOf(this.properties.getProperty("ReportsTheme")));

		summaryReport = new SeleniumReport(reportSettings, reportTheme);

		summaryReport.initialize();
		summaryReport.initializeResultSummary();
		createResultSummaryHeader(nThreads);
	}

	private void initializeReportSettings() {
		if (System.getProperty("ReportPath") != null)
			reportPath = System.getProperty("ReportPath");
		else {
			reportPath = TimeStamp.getInstance();
		}

		reportSettings = new ReportSettings(reportPath, "");

		reportSettings.setDateFormatString(this.properties
				.getProperty("DateFormatString"));
		reportSettings.setProjectName(this.properties
				.getProperty("ProjectName"));
		reportSettings.generateExcelReports = Boolean
				.parseBoolean(this.properties.getProperty("ExcelReport"));
		reportSettings.generateHtmlReports = Boolean
				.parseBoolean(this.properties.getProperty("HtmlReport"));
		reportSettings.linkTestLogsToSummary = true;
	}

	private void createResultSummaryHeader(int nThreads) {
		summaryReport.addResultSummaryHeading(reportSettings.getProjectName()
				+ " - " + " Automation Execution Result Summary");
		summaryReport.addResultSummarySubHeading(
				"Date & Time",
				": "
						+ Util.getCurrentFormattedTime(this.properties
								.getProperty("DateFormatString")), "OnError",
				": " + this.properties.getProperty("OnError"));
		summaryReport.addResultSummarySubHeading("Run Configuration", ": "
				+ this.frameworkParameters.getRunConfiguration(),
				"No. of threads", ": " + nThreads);

		summaryReport.addResultSummaryTableHeadings();
	}

	public void setupErrorLog() {
		String errorLogFile = reportPath + Util.getFileSeparator()
				+ "ErrorLog.txt";
		try {
			System.setErr(new PrintStream(new FileOutputStream(errorLogFile)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"Error while setting up the Error log!");
		}
	}

	public void updateResultSummary(String scenarioName, String testcaseName,
			String testcaseDescription, String executionTime, String testStatus) {
		summaryReport.updateResultSummary(scenarioName, testcaseName,
				testcaseDescription, executionTime, testStatus);

	}

	public void wrapUp(Boolean testExecutedInUnitTestFramework) {
		overallEndTime = Util.getCurrentTime();
		String totalExecutionTime = Util.getTimeDifference(overallStartTime,
				overallEndTime);
		summaryReport.addResultSummaryFooter(totalExecutionTime);

		if (testExecutedInUnitTestFramework.booleanValue()) {
			File testNgResultSrc = new File(
					this.frameworkParameters.getRelativePath()
							+ Util.getFileSeparator()
							+ this.properties.getProperty("TestNgReportPath"));
			File testNgResultDest = new File(reportPath
					+ Util.getFileSeparator() + "TestNG Results");
			try {
				FileUtils.copyDirectory(testNgResultSrc, testNgResultDest);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void launchResultSummary() {
		if (reportSettings.generateHtmlReports)
			try {
				Runtime.getRuntime().exec(
						"RunDLL32.EXE shell32.dll,ShellExec_RunDLL "
								+ reportPath + "\\HTML Results\\Summary.Html");
			} catch (IOException e) {
				e.printStackTrace();
			}
		else if (reportSettings.generateExcelReports)
			try {
				Runtime.getRuntime().exec(
						"RunDLL32.EXE shell32.dll,ShellExec_RunDLL "
								+ reportPath + "\\Excel Results\\Summary.xls");
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	public void createTestStatusFile(String currentScenario,
			String currentTestcase, String currentTestDescription,
			String executionTime, String testStatus) {
		
		ALMIntegration aLMStatus=new ALMIntegration();
		File file = new File(filePath);
		String[] finalTestCase = currentTestcase.split("P");
		String preTCDteils = "";
		String[] preTestCaseName = null;
		String HPALMBatchUpdateVal=new ALMIntegration().loadProperties("HPALMBatchUpdate");
		try {
			if(file.exists()){
				preTCDteils = new FileUtil().readDataFromFile(filePath);
				if(!(preTCDteils.equals(""))){
					preTestCaseName =  preTCDteils.split(";");
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(!(currentTestcase.contains("P"))){
			if(HPALMBatchUpdateVal.equalsIgnoreCase("True")){
				if(preTCDteils.contains("Passed")){
					if(preTestCaseName[2].equalsIgnoreCase(currentScenario)){
						new FileUtil().appendTextToFile(preTestCaseName[2]	+ "," + preTestCaseName[0] + ","+ preTestCaseName[1], statusFilePath);
					}
				}
				new FileUtil().appendTextToFile(currentScenario+ "," + currentTestcase + ","+ testStatus, statusFilePath);
			}else{
				try {
					aLMStatus.pushStatusToALM(currentScenario, currentTestcase, testStatus);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			new FileUtil().writeTextToFile("",filePath);
		}else{
			try {
				if(file.exists()){
					if(preTCDteils.equals("")){
						if(testStatus.equalsIgnoreCase("Passed")){
							if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
								try {
									aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
							}
							new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
									+";"+currentScenario
									+";"+currentTestDescription
									+";"+executionTime, filePath);
						}else{
							if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
								try {
									aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
								} catch (FileNotFoundException e) {
									e.printStackTrace();
								}
							}else{
								new FileUtil().appendTextToFile(currentScenario+ "," + finalTestCase[0] + ","+ testStatus, statusFilePath);
							}
							
							new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
									+";"+currentScenario
									+";"+currentTestDescription
									+";"+executionTime, filePath);
						}
					}else{
						if(currentTestcase.contains(preTestCaseName[0])){
							if(preTCDteils.contains("Passed")){
								if(testStatus.equalsIgnoreCase("Passed")){
									if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
										try {
											aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
										} catch (FileNotFoundException e) {
											e.printStackTrace();
										}
									}
									new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
											+";"+currentScenario
											+";"+currentTestDescription
											+";"+executionTime, filePath);
								}else{
									if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
										try {
											aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
										} catch (FileNotFoundException e) {
											e.printStackTrace();
										}
									}else{
										new FileUtil().appendTextToFile(currentScenario+ "," + finalTestCase[0] + ","+ testStatus, statusFilePath);
									}
									
									new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
											+";"+currentScenario
											+";"+currentTestDescription
											+";"+executionTime, filePath);
								}
							}
						}else{
							if(HPALMBatchUpdateVal.equalsIgnoreCase("True")){
								if(preTCDteils.contains("Passed")){
									new FileUtil().appendTextToFile(preTestCaseName[2]	+ "," + preTestCaseName[0] + ","+ preTestCaseName[1], statusFilePath);
								}
							}
							if(testStatus.equalsIgnoreCase("Passed")){
								if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
									try {
										aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}
								new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
										+";"+currentScenario
										+";"+currentTestDescription
										+";"+executionTime, filePath);
							}else{
								if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
									try {
										aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
									} catch (FileNotFoundException e) {
										e.printStackTrace();
									}
								}else{
									new FileUtil().appendTextToFile(currentScenario+ "," + finalTestCase[0] + ","+ testStatus, statusFilePath);
								}
								
								new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
										+";"+currentScenario
										+";"+currentTestDescription
										+";"+executionTime, filePath);
							}
						}
					}
				}else{
					if(testStatus.equalsIgnoreCase("Passed")){
						if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
							try {
								aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}
						new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
								+";"+currentScenario
								+";"+currentTestDescription
								+";"+executionTime, filePath);
					}else{
						if(HPALMBatchUpdateVal.equalsIgnoreCase("False")){
							try {
								aLMStatus.pushStatusToALM(currentScenario, finalTestCase[0], testStatus);
							} catch (FileNotFoundException e) {
								e.printStackTrace();
							}
						}else{
							new FileUtil().appendTextToFile(currentScenario+ "," + finalTestCase[0] + ","+ testStatus, statusFilePath);
						}
						new FileUtil().writeTextToFile(finalTestCase[0]+";"+testStatus
								+";"+currentScenario
								+";"+currentTestDescription
								+";"+executionTime, filePath);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addFinalTestcaseStatusToFile()
	{
		try {
			String	testcase = new FileUtil().readDataFromFile(filePath);
			String pushedData = new FileUtil().readDataFromFile(statusFilePath);
			String[] preTestCaseName =  testcase.split(";");
			if(testcase.contains("Passed"))
			{
				if(!pushedData.contains(preTestCaseName[0])){
					new FileUtil().appendTextToFile(preTestCaseName[2]	+ "," + preTestCaseName[0] + ","+ preTestCaseName[1], statusFilePath);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}