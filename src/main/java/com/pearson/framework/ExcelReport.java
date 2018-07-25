
package com.pearson.framework;

public class ExcelReport implements ReportType
{
    private ExcelDataAccess testLogAccess;
    private ExcelDataAccess resultSummaryAccess;
    private ReportSettings reportSettings;
    private ReportTheme reportTheme;
    private ExcelCellFormatting cellFormatting;
    private int currentSectionRowNum;
    private int currentSubSectionRowNum;
    private static /* synthetic */ int[] $SWITCH_TABLE$com$cognizant$framework$Status;
    
    public ExcelReport(final ReportSettings reportSettings, final ReportTheme reportTheme) {
        this.cellFormatting = new ExcelCellFormatting();
        this.currentSectionRowNum = 0;
        this.currentSubSectionRowNum = 0;
        this.reportSettings = reportSettings;
        this.reportTheme = reportTheme;
        this.testLogAccess = new ExcelDataAccess(String.valueOf(reportSettings.getReportPath()) + Util.getFileSeparator() + "Excel Results", reportSettings.getReportName());
        this.resultSummaryAccess = new ExcelDataAccess(String.valueOf(reportSettings.getReportPath()) + Util.getFileSeparator() + "Excel Results", "Summary");
    }
    
    public void initializeTestLog() {
        this.testLogAccess.createWorkbook();
        this.testLogAccess.addSheet("Cover_Page");
        this.testLogAccess.addSheet("Test_Log");
        this.initializeTestLogColorPalette();
        this.testLogAccess.setRowSumsBelow(false);
    }
    
    private void initializeTestLogColorPalette() {
        this.testLogAccess.setCustomPaletteColor((short)8, this.reportTheme.getHeadingBackColor());
        this.testLogAccess.setCustomPaletteColor((short)9, this.reportTheme.getHeadingForeColor());
        this.testLogAccess.setCustomPaletteColor((short)10, this.reportTheme.getSectionBackColor());
        this.testLogAccess.setCustomPaletteColor((short)11, this.reportTheme.getSectionForeColor());
        this.testLogAccess.setCustomPaletteColor((short)12, this.reportTheme.getContentBackColor());
        this.testLogAccess.setCustomPaletteColor((short)13, this.reportTheme.getContentForeColor());
        this.testLogAccess.setCustomPaletteColor((short)14, "#008000");
        this.testLogAccess.setCustomPaletteColor((short)15, "#FF0000");
        this.testLogAccess.setCustomPaletteColor((short)16, "#FF8000");
        this.testLogAccess.setCustomPaletteColor((short)17, "#000000");
        this.testLogAccess.setCustomPaletteColor((short)18, "#00FF80");
    }
    
    public void addTestLogHeading(final String heading) {
        this.testLogAccess.setDatasheetName("Cover_Page");
        int rowNum = this.testLogAccess.getLastRowNum();
        if (rowNum != 0) {
            rowNum = this.testLogAccess.addRow();
        }
        this.cellFormatting.setFontName("Copperplate Gothic Bold");
        this.cellFormatting.setFontSize((short)12);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = true;
        this.cellFormatting.setBackColorIndex((short)8);
        this.cellFormatting.setForeColorIndex((short)9);
        this.testLogAccess.setValue(rowNum, 0, heading, this.cellFormatting);
        this.testLogAccess.mergeCells(rowNum, rowNum, 0, 4);
    }
    
    public void addTestLogSubHeading(final String subHeading1, final String subHeading2, final String subHeading3, final String subHeading4) {
        this.testLogAccess.setDatasheetName("Cover_Page");
        final int rowNum = this.testLogAccess.addRow();
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = false;
        this.cellFormatting.setBackColorIndex((short)9);
        this.cellFormatting.setForeColorIndex((short)8);
        this.testLogAccess.setValue(rowNum, 0, subHeading1, this.cellFormatting);
        this.testLogAccess.setValue(rowNum, 1, subHeading2, this.cellFormatting);
        this.testLogAccess.setValue(rowNum, 2, "", this.cellFormatting);
        this.testLogAccess.setValue(rowNum, 3, subHeading3, this.cellFormatting);
        this.testLogAccess.setValue(rowNum, 4, subHeading4, this.cellFormatting);
    }
    
    public void addTestLogTableHeadings() {
        this.testLogAccess.setDatasheetName("Test_Log");
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = true;
        this.cellFormatting.setBackColorIndex((short)8);
        this.cellFormatting.setForeColorIndex((short)9);
        this.testLogAccess.addColumn("Step_No", this.cellFormatting);
        this.testLogAccess.addColumn("Step_Name", this.cellFormatting);
        this.testLogAccess.addColumn("Description", this.cellFormatting);
        this.testLogAccess.addColumn("Status", this.cellFormatting);
        this.testLogAccess.addColumn("Step_Time", this.cellFormatting);
    }
    
    public void addTestLogSection(final String section) {
        this.testLogAccess.setDatasheetName("Test_Log");
        final int rowNum = this.testLogAccess.addRow();
        if (this.currentSubSectionRowNum != 0) {
            this.testLogAccess.groupRows(this.currentSubSectionRowNum, rowNum - 1);
        }
        if (this.currentSectionRowNum != 0) {
            this.testLogAccess.groupRows(this.currentSectionRowNum, rowNum - 1);
        }
        this.currentSectionRowNum = rowNum + 1;
        this.currentSubSectionRowNum = 0;
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = false;
        this.cellFormatting.setBackColorIndex((short)10);
        this.cellFormatting.setForeColorIndex((short)11);
        this.testLogAccess.setValue(rowNum, 0, section, this.cellFormatting);
        this.testLogAccess.mergeCells(rowNum, rowNum, 0, 4);
    }
    
    public void addTestLogSubSection(final String subSection) {
        this.testLogAccess.setDatasheetName("Test_Log");
        final int rowNum = this.testLogAccess.addRow();
        if (this.currentSubSectionRowNum != 0) {
            this.testLogAccess.groupRows(this.currentSubSectionRowNum, rowNum - 1);
        }
        this.currentSubSectionRowNum = rowNum + 1;
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = false;
        this.cellFormatting.setBackColorIndex((short)9);
        this.cellFormatting.setForeColorIndex((short)8);
        this.testLogAccess.setValue(rowNum, 0, " " + subSection, this.cellFormatting);
        this.testLogAccess.mergeCells(rowNum, rowNum, 0, 4);
    }
    
    public void updateTestLog(final String stepNumber, final String stepName, final String stepDescription, final Status stepStatus, final String screenShotName) {
        this.testLogAccess.setDatasheetName("Test_Log");
        final int rowNum = this.testLogAccess.addRow();
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.setBackColorIndex((short)12);
        final boolean stepContainsScreenshot = this.processStatusColumn(stepStatus);
        this.cellFormatting.centred = true;
        this.cellFormatting.bold = true;
        final int columnNum = this.testLogAccess.getColumnNum("Status", 0);
        this.testLogAccess.setValue(rowNum, columnNum, stepStatus.toString(), this.cellFormatting);
        this.cellFormatting.setForeColorIndex((short)13);
        this.cellFormatting.bold = false;
        this.testLogAccess.setValue(rowNum, "Step_No", stepNumber, this.cellFormatting);
        this.testLogAccess.setValue(rowNum, "Step_Time", Util.getCurrentFormattedTime(this.reportSettings.getDateFormatString()), this.cellFormatting);
        this.cellFormatting.centred = false;
        this.testLogAccess.setValue(rowNum, "Step_Name", stepName, this.cellFormatting);
        if (stepContainsScreenshot) {
            if (this.reportSettings.linkScreenshotsToTestLog) {
                this.testLogAccess.setHyperlink(rowNum, columnNum, "..\\Screenshots\\" + screenShotName);
                this.testLogAccess.setValue(rowNum, "Description", stepDescription, this.cellFormatting);
            }
            else {
                this.testLogAccess.setValue(rowNum, "Description", String.valueOf(stepDescription) + " (Refer screenshot @ " + screenShotName + ")", this.cellFormatting);
            }
        }
        else {
            this.testLogAccess.setValue(rowNum, "Description", stepDescription, this.cellFormatting);
        }
    }
    
    private boolean processStatusColumn(final Status stepStatus) {
        boolean stepContainsScreenshot = false;
        switch ($SWITCH_TABLE$com$cognizant$framework$Status()[stepStatus.ordinal()]) {
            case 3: {
                this.cellFormatting.setForeColorIndex((short)14);
                stepContainsScreenshot = this.reportSettings.takeScreenshotPassedStep;
                break;
            }
            case 1: {
                this.cellFormatting.setForeColorIndex((short)15);
                stepContainsScreenshot = this.reportSettings.takeScreenshotFailedStep;
                break;
            }
            case 2: {
                this.cellFormatting.setForeColorIndex((short)16);
                stepContainsScreenshot = this.reportSettings.takeScreenshotFailedStep;
                break;
            }
            case 5: {
                this.cellFormatting.setForeColorIndex((short)17);
                stepContainsScreenshot = false;
                break;
            }
            case 4: {
                this.cellFormatting.setForeColorIndex((short)17);
                stepContainsScreenshot = true;
                break;
            }
            case 6: {
                this.cellFormatting.setForeColorIndex((short)18);
                stepContainsScreenshot = false;
                break;
            }
        }
        return stepContainsScreenshot;
    }
    
    public void addTestLogFooter(final String executionTime, final int nStepsPassed, final int nStepsFailed) {
        this.testLogAccess.setDatasheetName("Test_Log");
        int rowNum = this.testLogAccess.addRow();
        if (this.currentSubSectionRowNum != 0) {
            this.testLogAccess.groupRows(this.currentSubSectionRowNum, rowNum - 1);
        }
        if (this.currentSectionRowNum != 0) {
            this.testLogAccess.groupRows(this.currentSectionRowNum, rowNum - 1);
        }
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = true;
        this.cellFormatting.setBackColorIndex((short)8);
        this.cellFormatting.setForeColorIndex((short)9);
        this.testLogAccess.setValue(rowNum, 0, "Execution Duration: " + executionTime, this.cellFormatting);
        this.testLogAccess.mergeCells(rowNum, rowNum, 0, 4);
        rowNum = this.testLogAccess.addRow();
        this.cellFormatting.centred = false;
        this.cellFormatting.setBackColorIndex((short)9);
        this.cellFormatting.setForeColorIndex((short)14);
        this.testLogAccess.setValue(rowNum, "Step_No", "Steps passed", this.cellFormatting);
        this.testLogAccess.setValue(rowNum, "Step_Name", ": " + nStepsPassed, this.cellFormatting);
        this.cellFormatting.setForeColorIndex((short)8);
        this.testLogAccess.setValue(rowNum, "Description", "", this.cellFormatting);
        this.cellFormatting.setForeColorIndex((short)15);
        this.testLogAccess.setValue(rowNum, "Status", "Steps failed", this.cellFormatting);
        this.testLogAccess.setValue(rowNum, "Step_Time", ": " + nStepsFailed, this.cellFormatting);
        this.wrapUpTestLog();
    }
    
    private void wrapUpTestLog() {
        this.testLogAccess.autoFitContents(0, 4);
        this.testLogAccess.addOuterBorder(0, 4);
        this.testLogAccess.setDatasheetName("Cover_Page");
        this.testLogAccess.autoFitContents(0, 4);
        this.testLogAccess.addOuterBorder(0, 4);
    }
    
    public void initializeResultSummary() {
        this.resultSummaryAccess.createWorkbook();
        this.resultSummaryAccess.addSheet("Cover_Page");
        this.resultSummaryAccess.addSheet("Result_Summary");
        this.initializeResultSummaryColorPalette();
    }
    
    private void initializeResultSummaryColorPalette() {
        this.resultSummaryAccess.setCustomPaletteColor((short)8, this.reportTheme.getHeadingBackColor());
        this.resultSummaryAccess.setCustomPaletteColor((short)9, this.reportTheme.getHeadingForeColor());
        this.resultSummaryAccess.setCustomPaletteColor((short)10, this.reportTheme.getSectionBackColor());
        this.resultSummaryAccess.setCustomPaletteColor((short)11, this.reportTheme.getSectionForeColor());
        this.resultSummaryAccess.setCustomPaletteColor((short)12, this.reportTheme.getContentBackColor());
        this.resultSummaryAccess.setCustomPaletteColor((short)13, this.reportTheme.getContentForeColor());
        this.resultSummaryAccess.setCustomPaletteColor((short)14, "#008000");
        this.resultSummaryAccess.setCustomPaletteColor((short)15, "#FF0000");
    }
    
    public void addResultSummaryHeading(final String heading) {
        this.resultSummaryAccess.setDatasheetName("Cover_Page");
        int rowNum = this.resultSummaryAccess.getLastRowNum();
        if (rowNum != 0) {
            rowNum = this.resultSummaryAccess.addRow();
        }
        this.cellFormatting.setFontName("Copperplate Gothic Bold");
        this.cellFormatting.setFontSize((short)12);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = true;
        this.cellFormatting.setBackColorIndex((short)8);
        this.cellFormatting.setForeColorIndex((short)9);
        this.resultSummaryAccess.setValue(rowNum, 0, heading, this.cellFormatting);
        this.resultSummaryAccess.mergeCells(rowNum, rowNum, 0, 4);
    }
    
    public void addResultSummarySubHeading(final String subHeading1, final String subHeading2, final String subHeading3, final String subHeading4) {
        this.resultSummaryAccess.setDatasheetName("Cover_Page");
        final int rowNum = this.resultSummaryAccess.addRow();
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = false;
        this.cellFormatting.setBackColorIndex((short)9);
        this.cellFormatting.setForeColorIndex((short)8);
        this.resultSummaryAccess.setValue(rowNum, 0, subHeading1, this.cellFormatting);
        this.resultSummaryAccess.setValue(rowNum, 1, subHeading2, this.cellFormatting);
        this.resultSummaryAccess.setValue(rowNum, 2, "", this.cellFormatting);
        this.resultSummaryAccess.setValue(rowNum, 3, subHeading3, this.cellFormatting);
        this.resultSummaryAccess.setValue(rowNum, 4, subHeading4, this.cellFormatting);
    }
    
    public void addResultSummaryTableHeadings() {
        this.resultSummaryAccess.setDatasheetName("Result_Summary");
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = true;
        this.cellFormatting.setBackColorIndex((short)8);
        this.cellFormatting.setForeColorIndex((short)9);
        this.resultSummaryAccess.addColumn("Test_Scenario", this.cellFormatting);
        this.resultSummaryAccess.addColumn("Test_Case", this.cellFormatting);
        this.resultSummaryAccess.addColumn("Test_Description", this.cellFormatting);
        this.resultSummaryAccess.addColumn("Execution_Time", this.cellFormatting);
        this.resultSummaryAccess.addColumn("Test_Status", this.cellFormatting);
    }
    
    public void updateResultSummary(final String scenarioName, final String testcaseName, final String testcaseDescription, final String executionTime, final String testStatus) {
        this.resultSummaryAccess.setDatasheetName("Result_Summary");
        final int rowNum = this.resultSummaryAccess.addRow();
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.setBackColorIndex((short)12);
        this.cellFormatting.setForeColorIndex((short)13);
        this.cellFormatting.centred = false;
        this.cellFormatting.bold = false;
        this.resultSummaryAccess.setValue(rowNum, "Test_Scenario", scenarioName, this.cellFormatting);
        final int columnNum = this.resultSummaryAccess.getColumnNum("Test_Case", 0);
        this.resultSummaryAccess.setValue(rowNum, columnNum, testcaseName, this.cellFormatting);
        if (this.reportSettings.linkTestLogsToSummary) {
            this.resultSummaryAccess.setHyperlink(rowNum, columnNum, String.valueOf(scenarioName) + "_" + testcaseName + ".xls");
        }
        this.resultSummaryAccess.setValue(rowNum, "Test_Description", testcaseDescription, this.cellFormatting);
        this.cellFormatting.centred = true;
        this.resultSummaryAccess.setValue(rowNum, "Execution_Time", executionTime, this.cellFormatting);
        this.cellFormatting.bold = true;
        if (testStatus.equalsIgnoreCase("Passed")) {
            this.cellFormatting.setForeColorIndex((short)14);
        }
        if (testStatus.equalsIgnoreCase("Failed")) {
            this.cellFormatting.setForeColorIndex((short)15);
        }
        this.resultSummaryAccess.setValue(rowNum, "Test_Status", testStatus, this.cellFormatting);
    }
    
    public void addResultSummaryFooter(final String totalExecutionTime, final int nTestsPassed, final int nTestsFailed) {
        this.resultSummaryAccess.setDatasheetName("Result_Summary");
        int rowNum = this.resultSummaryAccess.addRow();
        this.cellFormatting.setFontName("Verdana");
        this.cellFormatting.setFontSize((short)10);
        this.cellFormatting.bold = true;
        this.cellFormatting.centred = true;
        this.cellFormatting.setBackColorIndex((short)8);
        this.cellFormatting.setForeColorIndex((short)9);
        this.resultSummaryAccess.setValue(rowNum, 0, "Total Duration: " + totalExecutionTime, this.cellFormatting);
        this.resultSummaryAccess.mergeCells(rowNum, rowNum, 0, 4);
        rowNum = this.resultSummaryAccess.addRow();
        this.cellFormatting.centred = false;
        this.cellFormatting.setBackColorIndex((short)9);
        this.cellFormatting.setForeColorIndex((short)14);
        this.resultSummaryAccess.setValue(rowNum, "Test_Scenario", "Tests passed", this.cellFormatting);
        this.resultSummaryAccess.setValue(rowNum, "Test_Case", ": " + nTestsPassed, this.cellFormatting);
        this.cellFormatting.setForeColorIndex((short)8);
        this.resultSummaryAccess.setValue(rowNum, "Test_Description", "", this.cellFormatting);
        this.cellFormatting.setForeColorIndex((short)15);
        this.resultSummaryAccess.setValue(rowNum, "Execution_Time", "Tests failed", this.cellFormatting);
        this.resultSummaryAccess.setValue(rowNum, "Test_Status", ": " + nTestsFailed, this.cellFormatting);
        this.wrapUpResultSummary();
    }
    
    private void wrapUpResultSummary() {
        this.resultSummaryAccess.autoFitContents(0, 4);
        this.resultSummaryAccess.addOuterBorder(0, 4);
        this.resultSummaryAccess.setDatasheetName("Cover_Page");
        this.resultSummaryAccess.autoFitContents(0, 4);
        this.resultSummaryAccess.addOuterBorder(0, 4);
    }
    
    static /* synthetic */ int[] $SWITCH_TABLE$com$cognizant$framework$Status() {
        final int[] $switch_TABLE$com$cognizant$framework$Status = ExcelReport.$SWITCH_TABLE$com$cognizant$framework$Status;
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
        return ExcelReport.$SWITCH_TABLE$com$cognizant$framework$Status = $switch_TABLE$com$cognizant$framework$Status2;
    }
}