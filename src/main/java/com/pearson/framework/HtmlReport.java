package com.pearson.framework;

import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import javax.mail.MessagingException;

import com.pearson.framework.selenium.FailXML;
import com.pearson.framework.selenium.SendMailTLS;

public class HtmlReport implements ReportType
{
    private String testLogPath;
    private String resultSummaryPath;
    private ReportSettings reportSettings;
    private ReportTheme reportTheme;
    private boolean isTestLogHeaderTableCreated;
    private boolean isTestLogMainTableCreated;
    private boolean isResultSummaryHeaderTableCreated;
    private boolean isResultSummaryMainTableCreated;
    private String currentSection;
    private String currentSubSection;
    private int currentContentNumber;
    private String updatescenarioName;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$cognizant$framework$Status;
    
    FailXML obj = new FailXML();
    
    ArrayList<String> failList = new ArrayList<String>();
    
    public HtmlReport(final ReportSettings reportSettings, final ReportTheme reportTheme) {
        this.isTestLogHeaderTableCreated = false;
        this.isTestLogMainTableCreated = false;
        this.isResultSummaryHeaderTableCreated = false;
        this.isResultSummaryMainTableCreated = false;
        this.currentSection = "";
        this.currentSubSection = "";
        this.currentContentNumber = 1;
        this.reportSettings = reportSettings;
        this.reportTheme = reportTheme;
        this.testLogPath = String.valueOf(reportSettings.getReportPath()) + Util.getFileSeparator() + "HTML Results" + Util.getFileSeparator() + reportSettings.getReportName() + ".html";
        this.resultSummaryPath = String.valueOf(reportSettings.getReportPath()) + Util.getFileSeparator() + "HTML Results" + Util.getFileSeparator() + "Summary" + ".html";
    }
    
    private String getThemeCss() {
        final String themeCss = "\t\t <style type='text/css'> \n\t\t\t body { \n\t\t\t\t background-color: " + this.reportTheme.getContentForeColor() + "; \n" + "\t\t\t\t font-family: Verdana, Geneva, sans-serif; \n" + "\t\t\t\t text-align: center; \n" + "\t\t\t } \n\n" + "\t\t\t small { \n" + "\t\t\t\t font-size: 0.7em; \n" + "\t\t\t } \n\n" + "\t\t\t table { \n" + "\t\t\t\t border: 1px solid #4D7C7B; \n" + "\t\t\t\t border-collapse: collapse; \n" + "\t\t\t\t border-spacing: 0px; \n" + "\t\t\t\t width: 95%; \n" + "\t\t\t\t margin-left: auto; \n" + "\t\t\t\t margin-right: auto; \n" + "\t\t\t } \n\n" + "\t\t\t tr.heading { \n" + "\t\t\t\t background-color: " + this.reportTheme.getHeadingBackColor() + "; \n" + "\t\t\t\t color: " + this.reportTheme.getHeadingForeColor() + "; \n" + "\t\t\t\t font-size: 0.9em; \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t } \n\n" + "\t\t\t tr.subheading { \n" + "\t\t\t\t background-color: " + this.reportTheme.getHeadingForeColor() + "; \n" + "\t\t\t\t color: " + this.reportTheme.getHeadingBackColor() + "; \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t font-size: 0.9em; \n" + "\t\t\t\t text-align: justify; \n" + "\t\t\t } \n\n" + "\t\t\t tr.section { \n" + "\t\t\t\t background-color: " + this.reportTheme.getSectionBackColor() + "; \n" + "\t\t\t\t color: " + this.reportTheme.getSectionForeColor() + "; \n" + "\t\t\t\t cursor: pointer; \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t font-size: 0.9em; \n" + "\t\t\t\t text-align: justify; \n" + "\t\t\t } \n\n" + "\t\t\t tr.subsection { \n" + "\t\t\t\t cursor: pointer; \n" + "\t\t\t } \n\n" + "\t\t\t tr.content { \n" + "\t\t\t\t background-color: " + this.reportTheme.getContentBackColor() + "; \n" + "\t\t\t\t color: " + this.reportTheme.getContentForeColor() + "; \n" + "\t\t\t\t font-size: 0.9em; \n" + "\t\t\t\t display: table-row; \n" + "\t\t\t } \n\n" + "\t\t\t td { \n" + "\t\t\t\t padding: 4px; \n" + "\t\t\t\t text-align: inherit\\0/; \n" + "\t\t\t\t word-wrap: break-word; \n" + "\t\t\t\t max-width: 450px; \n" + "\t\t\t } \n\n" + "\t\t\t th { \n" + "\t\t\t\t padding: 4px; \n" + "\t\t\t\t text-align: inherit\\0/; \n" + "\t\t\t\t word-break: break-all; \n" + "\t\t\t\t max-width: 450px; \n" + "\t\t\t } \n\n" + "\t\t\t td.justified { \n" + "\t\t\t\t text-align: justify; \n" + "\t\t\t } \n\n" + "\t\t\t td.pass { \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t color: green; \n" + "\t\t\t } \n\n" + "\t\t\t td.fail { \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t color: red; \n" + "\t\t\t } \n\n" + "\t\t\t td.done, td.screenshot { \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t color: black; \n" + "\t\t\t } \n\n" + "\t\t\t td.debug { \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t color: blue; \n" + "\t\t\t } \n\n" + "\t\t\t td.warning { \n" + "\t\t\t\t font-weight: bold; \n" + "\t\t\t\t color: orange; \n" + "\t\t\t } \n" + "\t\t </style> \n\n";
        return themeCss;
    }
    
    private String getJavascriptFunctions() {
        final String javascriptFunctions = "\t\t <script> \n\t\t\t function toggleMenu(objID) { \n\t\t\t\t if (!document.getElementById) return; \n\t\t\t\t var ob = document.getElementById(objID).style; \n\t\t\t\t if(ob.display === 'none') { \n\t\t\t\t\t try { \n\t\t\t\t\t\t ob.display='table-row-group'; \n\t\t\t\t\t } catch(ex) { \n\t\t\t\t\t\t ob.display='block'; \n\t\t\t\t\t } \n\t\t\t\t } \n\t\t\t\t else { \n\t\t\t\t\t ob.display='none'; \n\t\t\t\t } \n\t\t\t } \n\t\t\t function toggleSubMenu(objId) { \n\t\t\t\t for(i=1; i<10000; i++) { \n\t\t\t\t\t var ob = document.getElementById(objId.concat(i)); \n\t\t\t\t\t if(ob === null) { \n\t\t\t\t\t\t break; \n\t\t\t\t\t } \n\t\t\t\t\t if(ob.style.display === 'none') { \n\t\t\t\t\t\t try { \n\t\t\t\t\t\t\t ob.style.display='table-row'; \n\t\t\t\t\t\t } catch(ex) { \n\t\t\t\t\t\t\t ob.style.display='block'; \n\t\t\t\t\t\t } \n\t\t\t\t\t } \n\t\t\t\t\t else { \n\t\t\t\t\t\t ob.style.display='none'; \n\t\t\t\t\t } \n\t\t\t\t } \n\t\t\t } \n\t\t </script> \n";
        return javascriptFunctions;
    }
    
    public void initializeTestLog() {
        final File testLogFile = new File(this.testLogPath);
        try {
            testLogFile.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while creating HTML test log file");
        }
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(testLogFile);
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
            throw new FrameworkException("Cannot find HTML test log file");
        }
        final PrintStream printStream = new PrintStream(outputStream);
        final String testLogHeadSection = "<!DOCTYPE html> \n<html> \n\t <head> \n\t\t <meta charset='UTF-8'> \n\t\t <title>" + this.reportSettings.getProjectName() + " - " + this.reportSettings.getReportName() + " Automation Execution Results" + "</title> \n\n" + this.getThemeCss() + this.getJavascriptFunctions() + "\t </head> \n";
        printStream.println(testLogHeadSection);
        printStream.close();
    }
    
    public void addTestLogHeading(final String heading) {
        if (!this.isTestLogHeaderTableCreated) {
            this.createTestLogHeaderTable();
            this.isTestLogHeaderTableCreated = true;
        }
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogHeading = "\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> \n\t\t\t\t\t\t " + heading + " \n" + "\t\t\t\t\t </th> \n" + "\t\t\t\t </tr> \n";
            bufferedWriter.write(testLogHeading);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding heading to HTML test log");
        }
    }
    
    private void createTestLogHeaderTable() {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogHeaderTable = "\t <body> \n\t\t <table id='header'> \n\t\t\t <thead> \n";
            bufferedWriter.write(testLogHeaderTable);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding header table to HTML test log");
        }
    }
    
    public void addTestLogSubHeading(final String subHeading1, final String subHeading2, final String subHeading3, final String subHeading4) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogSubHeading = "\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <th>&nbsp;" + subHeading1.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t\t <th>&nbsp;" + subHeading2.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t\t <th>&nbsp;" + subHeading3.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t\t <th>&nbsp;" + subHeading4.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t </tr> \n";
            bufferedWriter.write(testLogSubHeading);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding sub-heading to HTML test log");
        }
    }
    
    private void createTestLogMainTable() {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogMainTable = "\t\t\t </thead> \n\t\t </table> \n\n\t\t <table id='main'> \n";
            bufferedWriter.write(testLogMainTable);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding main table to HTML test log");
        }
    }
    
    public void addTestLogTableHeadings() {
        if (!this.isTestLogMainTableCreated) {
            this.createTestLogMainTable();
            this.isTestLogMainTableCreated = true;
        }
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogTableHeading = "\t\t\t <thead> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th>Step No</th> \n\t\t\t\t\t <th>Step Name</th> \n\t\t\t\t\t <th>Description</th> \n\t\t\t\t\t <th>Status</th> \n\t\t\t\t\t <th>Step Time</th> \n\t\t\t\t </tr> \n\t\t\t </thead> \n\n";
            bufferedWriter.write(testLogTableHeading);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding main table headings to HTML test log");
        }
    }
    
    public void addTestLogSection(final String section) {
        String testLogSection = "";
        if (!this.currentSection.equals("")) {
            testLogSection = "\t\t\t </tbody>";
        }
        this.currentSection = section.replaceAll("[^a-zA-Z0-9]", "");
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            testLogSection = String.valueOf(testLogSection) + "\t\t\t <tbody> \n\t\t\t\t <tr class='section'> \n\t\t\t\t\t <td colspan='5' onclick=\"toggleMenu('" + this.currentSection + "')\">+ " + section + "</td> \n" + "\t\t\t\t </tr> \n" + "\t\t\t </tbody> \n" + "\t\t\t <tbody id='" + this.currentSection + "' style='display:table-row-group'> \n";
            bufferedWriter.write(testLogSection);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding section to HTML test log");
        }
    }
    
    public void addTestLogSubSection(final String subSection) {
        this.currentSubSection = subSection.replaceAll("[^a-zA-Z0-9]", "");
        this.currentContentNumber = 1;
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogSubSection = "\t\t\t\t <tr class='subheading subsection'> \n\t\t\t\t\t <td colspan='5' onclick=\"toggleSubMenu('" + this.currentSection + this.currentSubSection + "')\">&nbsp;+ " + subSection + "</td> \n" + "\t\t\t\t </tr> \n";
            bufferedWriter.write(testLogSubSection);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding sub-section to HTML test log");
        }
    }
    
    public void updateTestLog(final String stepNumber, final String stepName, final String stepDescription, final Status stepStatus, final String screenShotName) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            String testStepRow = "\t\t\t\t <tr class='content' id='" + this.currentSection + this.currentSubSection + this.currentContentNumber + "'> \n" + "\t\t\t\t\t <td>" + stepNumber + "</td> \n" + "\t\t\t\t\t <td class='justified'>" + stepName + "</td> \n";
            ++this.currentContentNumber;
            switch ($SWITCH_TABLE$com$cognizant$framework$Status()[stepStatus.ordinal()]) {
                case 1: {
                    if (this.reportSettings.takeScreenshotFailedStep) {
                        testStepRow = String.valueOf(testStepRow) + this.getTestStepWithScreenshot(stepDescription, stepStatus, screenShotName);
                        break;
                    }
                    testStepRow = String.valueOf(testStepRow) + this.getTestStepWithoutScreenshot(stepDescription, stepStatus);
                    break;
                }
                case 3: {
                    if (this.reportSettings.takeScreenshotPassedStep) {
                        testStepRow = String.valueOf(testStepRow) + this.getTestStepWithScreenshot(stepDescription, stepStatus, screenShotName);
                        break;
                    }
                    testStepRow = String.valueOf(testStepRow) + this.getTestStepWithoutScreenshot(stepDescription, stepStatus);
                    break;
                }
                case 4: {
                    testStepRow = String.valueOf(testStepRow) + this.getTestStepWithScreenshot(stepDescription, stepStatus, screenShotName);
                    break;
                }
                default: {
                    testStepRow = String.valueOf(testStepRow) + this.getTestStepWithoutScreenshot(stepDescription, stepStatus);
                    break;
                }
            }
            testStepRow = String.valueOf(testStepRow) + "\t\t\t\t\t <td><small>" + Util.getCurrentFormattedTime(this.reportSettings.getDateFormatString()) + "</small>" + "</td> \n" + "\t\t\t\t </tr> \n";
            bufferedWriter.write(testStepRow);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while updating HTML test log");
        }
    }
    
    private String getTestStepWithScreenshot(final String stepDescription, final Status stepStatus, final String screenShotName) {
        String testStepRow;
        if (this.reportSettings.linkScreenshotsToTestLog) {
            testStepRow = "\t\t\t\t\t <td class='justified'>" + stepDescription + "</td> \n" + "\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" + "<a href='..\\Screenshots\\" + screenShotName + "'>" + stepStatus + "</a>" + "</td> \n";
        }
        else {
            testStepRow = "\t\t\t\t\t <td class='justified'>" + stepDescription + " (Refer Screenshot @ " + screenShotName + ")" + "</td> \n" + "\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" + stepStatus + "</td> \n";
        }
        return testStepRow;
    }
    
    private String getTestStepWithoutScreenshot(final String stepDescription, final Status stepStatus) {
        final String testStepRow = "\t\t\t\t\t <td class='justified'>" + stepDescription + "</td> \n" + "\t\t\t\t\t <td class='" + stepStatus.toString().toLowerCase() + "'>" + stepStatus + "</td> \n";
        return testStepRow;
    }
    
    public void addTestLogFooter(final String executionTime, final int nStepsPassed, final int nStepsFailed) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.testLogPath, true));
            final String testLogFooter = "\t\t\t </tbody> \n\t\t </table> \n\n\t\t <table id='footer'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <tfoot> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4'>Execution Duration: " + executionTime + "</th> \n" + "\t\t\t\t </tr> \n" + "\t\t\t\t <tr class='subheading'> \n" + "\t\t\t\t\t <td class='pass'>&nbsp;Steps passed</td> \n" + "\t\t\t\t\t <td class='pass'>&nbsp;: " + nStepsPassed + "</td> \n" + "\t\t\t\t\t <td class='fail'>&nbsp;Steps failed</td> \n" + "\t\t\t\t\t <td class='fail'>&nbsp;: " + nStepsFailed + "</td> \n" + "\t\t\t\t </tr> \n" + "\t\t\t </tfoot> \n" + "\t\t </table> \n" + "\t </body> \n" + "</html>";
            bufferedWriter.write(testLogFooter);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding footer to HTML test log");
        }
    }
    
    public void initializeResultSummary() {
        final File resultSummaryFile = new File(this.resultSummaryPath);
        try {
            resultSummaryFile.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while creating HTML result summary file");
        }
        FileOutputStream outputStream;
        try {
            outputStream = new FileOutputStream(resultSummaryFile);
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
            throw new FrameworkException("Cannot find HTML result summary file");
        }
        final PrintStream printStream = new PrintStream(outputStream);
        final String resultSummaryHeader = "<!DOCTYPE html> \n<html> \n\t <head> \n\t\t <meta charset='UTF-8'> \n\t\t <title>" + this.reportSettings.getProjectName() + " - Automation Execution Results Summary" + "</title> \n\n" + this.getThemeCss() + this.getJavascriptFunctions() + "\t </head> \n";
        printStream.println(resultSummaryHeader);
        printStream.close();
    }
    
    public void addResultSummaryHeading(final String heading) {
        if (!this.isResultSummaryHeaderTableCreated) {
            this.createResultSummaryHeaderTable();
            this.isResultSummaryHeaderTableCreated = true;
        }
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            final String resultSummaryHeading = "\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4' style='font-family:Copperplate Gothic Bold; font-size:1.4em;'> \n\t\t\t\t\t\t " + heading + " \n" + "\t\t\t\t\t </th> \n" + "\t\t\t\t </tr> \n";
            bufferedWriter.write(resultSummaryHeading);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding heading to HTML result summary");
        }
    }
    
    private void createResultSummaryHeaderTable() {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            final String resultSummaryHeaderTable = "\t <body> \n\t\t <table id='header'> \n\t\t\t <thead> \n";
            bufferedWriter.write(resultSummaryHeaderTable);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding header table to HTML result summary");
        }
    }
    
    public void addResultSummarySubHeading(final String subHeading1, final String subHeading2, final String subHeading3, final String subHeading4) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            final String resultSummarySubHeading = "\t\t\t\t <tr class='subheading'> \n\t\t\t\t\t <th>&nbsp;" + subHeading1.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t\t <th>&nbsp;" + subHeading2.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t\t <th>&nbsp;" + subHeading3.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t\t <th>&nbsp;" + subHeading4.replace(" ", "&nbsp;") + "</th> \n" + "\t\t\t\t </tr> \n";
            bufferedWriter.write(resultSummarySubHeading);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding sub-heading to HTML result summary");
        }
    }
    
    private void createResultSummaryMainTable() {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            final String resultSummaryMainTable = "\t\t\t </thead> \n\t\t </table> \n\n\t\t <table id='main'> \n\t\t\t <colgroup> \n";
            bufferedWriter.write(resultSummaryMainTable);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding main table to HTML result summary");
        }
    }
    
    public void addResultSummaryTableHeadings() {
        if (!this.isResultSummaryMainTableCreated) {
            this.createResultSummaryMainTable();
            this.isResultSummaryMainTableCreated = true;
        }
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            final String resultSummaryTableHeading = "\t\t\t <thead> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th>Test Scenario</th> \n\t\t\t\t\t <th>Test Case</th> \n\t\t\t\t\t <th>Test Description</th> \n\t\t\t\t\t <th>Execution Time</th> \n\t\t\t\t\t <th>Test Status</th> \n\t\t\t\t </tr> \n\t\t\t </thead> \n\n";
            bufferedWriter.write(resultSummaryTableHeading);
            bufferedWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding main table headings to HTML result summary");
        }
    }
    
    public void updateResultSummary(final String scenarioName, final String testcaseName, final String testcaseDescription, final String executionTime, final String testStatus) {
        try {
            final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            String testcaseRow;
            updatescenarioName=scenarioName;
            if (this.reportSettings.linkTestLogsToSummary) {
                testcaseRow = "\t\t\t\t <tr class='content' > \n\t\t\t\t\t <td class='justified'>" + scenarioName + "</td> \n" + "\t\t\t\t\t <td class='justified'><a href='" + scenarioName + "_" + testcaseName + ".html' " + "target='about_blank'>" + testcaseName + "</a>" + "</td> \n" + "\t\t\t\t\t <td class='justified'>" + testcaseDescription + "</td> \n" + "\t\t\t\t\t <td>" + executionTime + "</td> \n";
            }
            else {
                testcaseRow = "\t\t\t\t <tr class='content' > \n\t\t\t\t\t <td class='justified'>" + scenarioName + "</td> \n" + "\t\t\t\t\t <td class='justified'>" + testcaseName + "</td> \n" + "\t\t\t\t\t <td class='justified'>" + testcaseDescription + "</td> \n" + "\t\t\t\t\t <td>" + executionTime + "</td> \n";
            }
            if (testStatus.equalsIgnoreCase("passed")) {
                testcaseRow = String.valueOf(testcaseRow) + "\t\t\t\t\t <td class='pass'>" + testStatus + "</td> \n" + "\t\t\t\t </tr> \n";
            }
            else {
                testcaseRow = String.valueOf(testcaseRow) + "\t\t\t\t\t <td class='fail'>" + testStatus + "</td> \n" + "\t\t\t\t </tr> \n";
            }
            bufferedWriter.write(testcaseRow);
            bufferedWriter.close();
            
            if (testStatus.equalsIgnoreCase("failed")) {
    			failList.add(testcaseName);
    		}
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while updating HTML result summary");
        }
    }
    
    public void addResultSummaryFooter(final String totalExecutionTime, final int nTestsPassed, final int nTestsFailed) throws MessagingException {
        try {
        	
            final BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(this.resultSummaryPath, true));
            final String resultSummaryFooter = "\t\t\t </tbody> \n\t\t </table> \n\n\t\t <table id='footer'> \n\t\t\t <colgroup> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t\t <col style='width: 25%' /> \n\t\t\t </colgroup> \n\n\t\t\t <tfoot> \n\t\t\t\t <tr class='heading'> \n\t\t\t\t\t <th colspan='4'>Total Duration: " + totalExecutionTime + "</th> \n" + "\t\t\t\t </tr> \n" + "\t\t\t\t <tr class='subheading'> \n" + "\t\t\t\t\t <td class='pass'>&nbsp;Tests passed</td> \n" + "\t\t\t\t\t <td class='pass'>&nbsp;: " + nTestsPassed + "</td> \n" + "\t\t\t\t\t <td class='fail'>&nbsp;Tests failed</td> \n" + "\t\t\t\t\t <td class='fail'>&nbsp;: " + nTestsFailed + "</td> \n" + "\t\t\t\t </tr> \n" + "\t\t\t </tfoot> \n" + "\t\t </table> \n" + "\t </body> \n" + "</html>";
            bufferWriter.write(resultSummaryFooter);
            bufferWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FrameworkException("Error while adding footer to HTML result summary");
        }
        obj.modifyFailXML(failList, updatescenarioName);
        obj.createXML(failList, updatescenarioName);
        SendMailTLS mail = new SendMailTLS();
		int TotalExecuted = nTestsPassed+nTestsFailed;
		mail.sendMsg(updatescenarioName,TotalExecuted,totalExecutionTime,nTestsPassed,nTestsFailed);
    }
    
    static /* synthetic */ int[] $SWITCH_TABLE$com$cognizant$framework$Status() {
        final int[] $switch_TABLE$com$cognizant$framework$Status = HtmlReport.$SWITCH_TABLE$com$cognizant$framework$Status;
        if ($switch_TABLE$com$cognizant$framework$Status != null) {
            return $switch_TABLE$com$cognizant$framework$Status;
        }
        final int[] $switch_TABLE$com$cognizant$framework$Status2 = new int[Status.values().length];
        try {
            $switch_TABLE$com$cognizant$framework$Status2[Status.DEBUG.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            $switch_TABLE$com$cognizant$framework$Status2[Status.DONE.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            $switch_TABLE$com$cognizant$framework$Status2[Status.FAIL.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            $switch_TABLE$com$cognizant$framework$Status2[Status.PASS.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            $switch_TABLE$com$cognizant$framework$Status2[Status.SCREENSHOT.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        try {
            $switch_TABLE$com$cognizant$framework$Status2[Status.WARNING.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError6) {}
        return HtmlReport.$SWITCH_TABLE$com$cognizant$framework$Status = $switch_TABLE$com$cognizant$framework$Status2;
    }
}
