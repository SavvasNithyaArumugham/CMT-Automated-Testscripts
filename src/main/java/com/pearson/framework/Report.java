package com.pearson.framework;

import java.io.FilenameFilter;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.io.IOException;
import java.awt.image.RenderedImage;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Report {
	public ReportSettings reportSettings;
	public ReportTheme reportTheme;
	public int stepNumber;
	public int nStepsPassed;
	public int nStepsFailed;
	public int nTestsPassed;
	public int nTestsFailed;
	public List<ReportType> reportTypes;
	public String testStatus;
	public String failureDescription;
	
	

	public String getTestStatus() {
		return this.testStatus;
	}

	public String getFailureDescription() {
		return this.failureDescription;
	}

	public Report(final ReportSettings reportSettings,
			final ReportTheme reportTheme) {
		this.nStepsPassed = 0;
		this.nStepsFailed = 0;
		this.nTestsPassed = 0;
		this.nTestsFailed = 0;
		this.reportTypes = new ArrayList<ReportType>();
		this.testStatus = "Passed";
		this.reportSettings = reportSettings;
		this.reportTheme = reportTheme;
	}

	public void initialize() {
		if (this.reportSettings.generateExcelReports) {
			new File(String.valueOf(this.reportSettings.getReportPath())
					+ Util.getFileSeparator() + "Excel Results").mkdir();
			final ExcelReport excelReport = new ExcelReport(
					this.reportSettings, this.reportTheme);
			this.reportTypes.add((ReportType) excelReport);
		}
		if (this.reportSettings.generateHtmlReports) {
			new File(String.valueOf(this.reportSettings.getReportPath())
					+ Util.getFileSeparator() + "HTML Results").mkdir();
			final HtmlReport htmlReport = new HtmlReport(this.reportSettings,
					this.reportTheme);
			this.reportTypes.add((ReportType) htmlReport);
		}
		new File(String.valueOf(this.reportSettings.getReportPath())
				+ Util.getFileSeparator() + "Screenshots").mkdir();
	}

	public void initializeTestLog() {
		if (this.reportSettings.getReportName().equals("")) {
			throw new FrameworkException("The report name cannot be empty!");
		}
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).initializeTestLog();
		}
	}

	public void addTestLogHeading(final String heading) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addTestLogHeading(heading);
		}
	}

	public void addTestLogSubHeading(final String subHeading1,
			final String subHeading2, final String subHeading3,
			final String subHeading4) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addTestLogSubHeading(subHeading1,
					subHeading2, subHeading3, subHeading4);
		}
	}

	public void addTestLogTableHeadings() {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addTestLogTableHeadings();
		}
	}

	public void addTestLogSection(final String section) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addTestLogSection(section);
		}
		this.stepNumber = 1;
	}

	public void addTestLogSubSection(final String subSection) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addTestLogSubSection(subSection);
		}
	}

	public void updateTestLog(final String stepName,
			final String stepDescription, final Status stepStatus) {
		if (stepStatus.equals((Object) Status.FAIL)) {
			this.testStatus = "Failed";
			if (this.failureDescription == null) {
				this.failureDescription = stepDescription;
			} else {
				this.failureDescription = String
						.valueOf(this.failureDescription)
						+ "; "
						+ stepDescription;
			}
			++this.nStepsFailed;
		}
		if (stepStatus.equals((Object) Status.PASS)) {
			++this.nStepsPassed;
		}
		if (stepStatus.ordinal() <= this.reportSettings.getLogLevel()) {
			String screenshotName = null;
			if (stepStatus.equals((Object) Status.FAIL)
					&& this.reportSettings.takeScreenshotFailedStep) {
				screenshotName = String.valueOf(this.reportSettings
						.getReportName())
						+ "_"
						+ Util.getCurrentFormattedTime(
								this.reportSettings.getDateFormatString())
								.replace(" ", "_").replace(":", "-") + ".png";
				this.takeScreenshot(String.valueOf(this.reportSettings
						.getReportPath())
						+ Util.getFileSeparator()
						+ "Screenshots"
						+ Util.getFileSeparator()
						+ screenshotName);
			}
			if (stepStatus.equals((Object) Status.PASS)
					&& this.reportSettings.takeScreenshotPassedStep) {
				screenshotName = String.valueOf(this.reportSettings
						.getReportName())
						+ "_"
						+ Util.getCurrentFormattedTime(
								this.reportSettings.getDateFormatString())
								.replace(" ", "_").replace(":", "-") + ".png";
				this.takeScreenshot(String.valueOf(this.reportSettings
						.getReportPath())
						+ Util.getFileSeparator()
						+ "Screenshots"
						+ Util.getFileSeparator()
						+ screenshotName);
			}
			if (stepStatus.equals((Object) Status.SCREENSHOT)) {
				screenshotName = String.valueOf(this.reportSettings
						.getReportName())
						+ "_"
						+ Util.getCurrentFormattedTime(
								this.reportSettings.getDateFormatString())
								.replace(" ", "_").replace(":", "-") + ".png";
				this.takeScreenshot(String.valueOf(this.reportSettings
						.getReportPath())
						+ Util.getFileSeparator()
						+ "Screenshots"
						+ Util.getFileSeparator()
						+ screenshotName);
			}
			for (int i = 0; i < this.reportTypes.size(); ++i) {
				this.reportTypes.get(i).updateTestLog(
						Integer.toString(this.stepNumber), stepName,
						stepDescription, stepStatus, screenshotName);
			}
			++this.stepNumber;
		}
	}

	protected void takeScreenshot(final String screenshotPath) {
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final Rectangle rectangle = new Rectangle(0, 0, screenSize.width,
				screenSize.height);
		Robot robot;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			throw new FrameworkException(
					"Error while creating Robot object (for taking screenshot)");
		}
		final BufferedImage screenshotImage = robot
				.createScreenCapture(rectangle);
		final File screenshotFile = new File(screenshotPath);
		try {
			ImageIO.write(screenshotImage, "jpg", screenshotFile);
		} catch (IOException e2) {
			e2.printStackTrace();
			throw new FrameworkException(
					"Error while writing screenshot to .jpg file");
		}
	}

	public void addTestLogFooter(final String executionTime) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addTestLogFooter(executionTime,
					this.nStepsPassed, this.nStepsFailed);
		}
	}

	public void consolidateScreenshotsInWordDoc() {
		final String screenshotsConsolidatedFolderPath = String
				.valueOf(this.reportSettings.getReportPath())
				+ Util.getFileSeparator() + "Screenshots (Consolidated)";
		new File(screenshotsConsolidatedFolderPath).mkdir();
		final WordDocumentManager documentManager = new WordDocumentManager(
				screenshotsConsolidatedFolderPath,
				this.reportSettings.getReportName());
		final String screenshotsFolderPath = String.valueOf(this.reportSettings
				.getReportPath()) + Util.getFileSeparator() + "Screenshots";
		final File screenshotsFolder = new File(screenshotsFolderPath);

		FilenameFilter filenameFilter = new FilenameFilter() {
			public boolean accept(File dir, String fileName) {
				if (fileName.contains(Report.this.reportSettings
						.getReportName())) {
					return true;
				}
				return false;
			}
		};
		final File[] screenshots = screenshotsFolder.listFiles(filenameFilter);
		if (screenshots != null && screenshots.length > 0) {
			documentManager.createDocument();
			File[] array;
			for (int length = (array = screenshots).length, i = 0; i < length; ++i) {
				final File screenshot = array[i];
				documentManager.addPicture(screenshot);
			}
		}
	}

	public void initializeResultSummary() {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).initializeResultSummary();
		}
	}

	public void addResultSummaryHeading(final String heading) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addResultSummaryHeading(heading);
		}
	}

	public void addResultSummarySubHeading(final String subHeading1,
			final String subHeading2, final String subHeading3,
			final String subHeading4) {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addResultSummarySubHeading(subHeading1,
					subHeading2, subHeading3, subHeading4);
		}
	}

	public void addResultSummaryTableHeadings() {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addResultSummaryTableHeadings();
		}
	}

	public synchronized void updateResultSummary(final String scenarioName,
			final String testcaseName, final String testcaseDescription,
			final String executionTime, final String testStatus) {
		if (testStatus.equalsIgnoreCase("failed")) {
			++this.nTestsFailed;
		} else if (testStatus.equalsIgnoreCase("passed")) {
			++this.nTestsPassed;
		}
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).updateResultSummary(scenarioName,
					testcaseName, testcaseDescription, executionTime,
					testStatus);
		}
	}

	public void addResultSummaryFooter(final String totalExecutionTime) throws MessagingException {
		for (int i = 0; i < this.reportTypes.size(); ++i) {
			this.reportTypes.get(i).addResultSummaryFooter(totalExecutionTime,
					this.nTestsPassed, this.nTestsFailed);
		
		}
		
	}
}
