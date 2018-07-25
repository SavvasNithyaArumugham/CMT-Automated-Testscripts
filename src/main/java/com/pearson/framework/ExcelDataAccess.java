package com.pearson.framework;

import org.apache.poi.hssf.util.HSSFRegionUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import java.awt.Color;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelDataAccess
{
    private final String filePath;
    private final String fileName;
    private String datasheetName;
    
    public String getDatasheetName() {
        return this.datasheetName;
    }
    
    public void setDatasheetName(final String datasheetName) {
        this.datasheetName = datasheetName;
    }
    
    public ExcelDataAccess(final String filePath, final String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }
    
    private void checkPreRequisites() {
        if (this.datasheetName == null) {
            throw new FrameworkException("ExcelDataAccess.datasheetName is not set!");
        }
    }
    
    private HSSFWorkbook openFileForReading() {
        final String absoluteFilePath = String.valueOf(this.filePath) + Util.getFileSeparator() + this.fileName + ".xls";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(absoluteFilePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
        }
        HSSFWorkbook workbook;
        try {
            workbook = new HSSFWorkbook((InputStream)fileInputStream);
        }
        catch (IOException e2) {
            e2.printStackTrace();
            throw new FrameworkException("Error while opening the specified Excel workbook \"" + absoluteFilePath + "\"");
        }
        return workbook;
    }
    
    private void writeIntoFile(final HSSFWorkbook workbook) {
        final String absoluteFilePath = String.valueOf(this.filePath) + Util.getFileSeparator() + this.fileName + ".xls";
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(absoluteFilePath);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new FrameworkException("The specified file \"" + absoluteFilePath + "\" does not exist!");
        }
        try {
            workbook.write((OutputStream)fileOutputStream);
            fileOutputStream.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
            throw new FrameworkException("Error while writing into the specified Excel workbook \"" + absoluteFilePath + "\"");
        }
    }
    
    private HSSFSheet getWorkSheet(final HSSFWorkbook workbook) {
        final HSSFSheet worksheet = workbook.getSheet(this.datasheetName);
        if (worksheet == null) {
            throw new FrameworkException("The specified sheet \"" + this.datasheetName + "\"" + "does not exist within the workbook \"" + this.fileName + ".xls\"");
        }
        return worksheet;
    }
    
    public int getRowNum(final String key, final int columnNum, final int startRowNum) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        for (int currentRowNum = startRowNum; currentRowNum <= worksheet.getLastRowNum(); ++currentRowNum) {
            final HSSFRow row = worksheet.getRow(currentRowNum);
            final HSSFCell cell = row.getCell(columnNum);
            final String currentValue = this.getCellValueAsString(cell, formulaEvaluator);
            if (currentValue.equals(key)) {
                return currentRowNum;
            }
        }
        return -1;
    }
    
    private String getCellValueAsString(final HSSFCell cell, final FormulaEvaluator formulaEvaluator) {
        if (cell == null || cell.getCellType() == 3) {
            return "";
        }
        if (formulaEvaluator.evaluate((Cell)cell).getCellType() == 5) {
            throw new FrameworkException("Error in formula within this cell! Error code: " + cell.getErrorCellValue());
        }
        final DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(formulaEvaluator.evaluateInCell((Cell)cell));
    }
    
    public int getRowNum(final String key, final int columnNum) {
        return this.getRowNum(key, columnNum, 0);
    }
    
    public int getLastRowNum() {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        return worksheet.getLastRowNum();
    }
    
    public int getRowCount(final String key, final int columnNum, final int startRowNum) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        int rowCount = 0;
        boolean keyFound = false;
        for (int currentRowNum = startRowNum; currentRowNum <= worksheet.getLastRowNum(); ++currentRowNum) {
            final HSSFRow row = worksheet.getRow(currentRowNum);
            final HSSFCell cell = row.getCell(columnNum);
            final String currentValue = this.getCellValueAsString(cell, formulaEvaluator);
            if (currentValue.equals(key)) {
                ++rowCount;
                keyFound = true;
            }
            else if (keyFound) {
                break;
            }
        }
        return rowCount;
    }
    
    public int getRowCount(final String key, final int columnNum) {
        return this.getRowCount(key, columnNum, 0);
    }
    
    public int getColumnNum(final String key, final int rowNum) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        final HSSFRow row = worksheet.getRow(rowNum);
        for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
            final HSSFCell cell = row.getCell(currentColumnNum);
            final String currentValue = this.getCellValueAsString(cell, formulaEvaluator);
            if (currentValue.equals(key)) {
                return currentColumnNum;
            }
        }
        return -1;
    }
    
    public String getValue(final int rowNum, final int columnNum) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        final HSSFRow row = worksheet.getRow(rowNum);
        final HSSFCell cell = row.getCell(columnNum);
        return this.getCellValueAsString(cell, formulaEvaluator);
    }
    
    public String getValue(final int rowNum, final String columnHeader) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        HSSFRow row = worksheet.getRow(0);
        int columnNum = -1;
        for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
            final HSSFCell cell = row.getCell(currentColumnNum);
            final String currentValue = this.getCellValueAsString(cell, formulaEvaluator);
            if (currentValue.equals(columnHeader)) {
                columnNum = currentColumnNum;
                break;
            }
        }
        if (columnNum == -1) {
            throw new FrameworkException("The specified column header \"" + columnHeader + "\"" + "is not found in the sheet \"" + this.datasheetName + "\"!");
        }
        row = worksheet.getRow(rowNum);
        final HSSFCell cell2 = row.getCell(columnNum);
        return this.getCellValueAsString(cell2, formulaEvaluator);
    }
    
    private HSSFCellStyle applyCellStyle(final HSSFWorkbook workbook, final ExcelCellFormatting cellFormatting) {
        final HSSFCellStyle cellStyle = workbook.createCellStyle();
        if (cellFormatting.centred) {
            cellStyle.setAlignment((short)2);
        }
        cellStyle.setFillForegroundColor(cellFormatting.getBackColorIndex());
        cellStyle.setFillPattern((short)1);
        final HSSFFont font = workbook.createFont();
        font.setFontName(cellFormatting.getFontName());
        font.setFontHeightInPoints(cellFormatting.getFontSize());
        if (cellFormatting.bold) {
            font.setBoldweight((short)700);
        }
        font.setColor(cellFormatting.getForeColorIndex());
        cellStyle.setFont(font);
        return cellStyle;
    }
    
    public void setValue(final int rowNum, final int columnNum, final String value) {
        this.setValue(rowNum, columnNum, value, null);
    }
    
    public void setValue(final int rowNum, final int columnNum, final String value, final ExcelCellFormatting cellFormatting) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final HSSFRow row = worksheet.getRow(rowNum);
        final HSSFCell cell = row.createCell(columnNum);
        cell.setCellType(1);
        cell.setCellValue(value);
        if (cellFormatting != null) {
            final HSSFCellStyle cellStyle = this.applyCellStyle(workbook, cellFormatting);
            cell.setCellStyle(cellStyle);
        }
        this.writeIntoFile(workbook);
    }
    
    public void setValue(final int rowNum, final String columnHeader, final String value) {
        this.setValue(rowNum, columnHeader, value, null);
    }
    
    public void setValue(final int rowNum, final String columnHeader, final String value, final ExcelCellFormatting cellFormatting) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        HSSFRow row = worksheet.getRow(0);
        int columnNum = -1;
        for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
            final HSSFCell cell = row.getCell(currentColumnNum);
            final String currentValue = this.getCellValueAsString(cell, formulaEvaluator);
            if (currentValue.equals(columnHeader)) {
                columnNum = currentColumnNum;
                break;
            }
        }
        if (columnNum == -1) {
            throw new FrameworkException("The specified column header \"" + columnHeader + "\"" + "is not found in the sheet \"" + this.datasheetName + "\"!");
        }
        row = worksheet.getRow(rowNum);
        final HSSFCell cell2 = row.createCell(columnNum);
        cell2.setCellType(1);
        cell2.setCellValue(value);
        if (cellFormatting != null) {
            final HSSFCellStyle cellStyle = this.applyCellStyle(workbook, cellFormatting);
            cell2.setCellStyle(cellStyle);
        }
        this.writeIntoFile(workbook);
    }
    
    public void setHyperlink(final int rowNum, final int columnNum, final String linkAddress) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final HSSFRow row = worksheet.getRow(rowNum);
        final HSSFCell cell = row.getCell(columnNum);
        if (cell == null) {
            throw new FrameworkException("Specified cell is empty! Please set a value before including a hyperlink...");
        }
        this.setCellHyperlink(workbook, cell, linkAddress);
        this.writeIntoFile(workbook);
    }
    
    private void setCellHyperlink(final HSSFWorkbook workbook, final HSSFCell cell, final String linkAddress) {
        final HSSFCellStyle cellStyle = cell.getCellStyle();
        final HSSFFont font = cellStyle.getFont((Workbook)workbook);
        font.setUnderline((byte)1);
        cellStyle.setFont(font);
        final CreationHelper creationHelper = (CreationHelper)workbook.getCreationHelper();
        final Hyperlink hyperlink = creationHelper.createHyperlink(1);
        hyperlink.setAddress(linkAddress);
        cell.setCellStyle(cellStyle);
        cell.setHyperlink(hyperlink);
    }
    
    public void setHyperlink(final int rowNum, final String columnHeader, final String linkAddress) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final FormulaEvaluator formulaEvaluator = (FormulaEvaluator)workbook.getCreationHelper().createFormulaEvaluator();
        HSSFRow row = worksheet.getRow(0);
        int columnNum = -1;
        for (int currentColumnNum = 0; currentColumnNum < row.getLastCellNum(); ++currentColumnNum) {
            final HSSFCell cell = row.getCell(currentColumnNum);
            final String currentValue = this.getCellValueAsString(cell, formulaEvaluator);
            if (currentValue.equals(columnHeader)) {
                columnNum = currentColumnNum;
                break;
            }
        }
        if (columnNum == -1) {
            throw new FrameworkException("The specified column header \"" + columnHeader + "\"" + "is not found in the sheet \"" + this.datasheetName + "\"!");
        }
        row = worksheet.getRow(rowNum);
        final HSSFCell cell2 = row.getCell(columnNum);
        if (cell2 == null) {
            throw new FrameworkException("Specified cell is empty! Please set a value before including a hyperlink...");
        }
        this.setCellHyperlink(workbook, cell2, linkAddress);
        this.writeIntoFile(workbook);
    }
    
    public void createWorkbook() {
        final HSSFWorkbook workbook = new HSSFWorkbook();
        this.writeIntoFile(workbook);
    }
    
    public void addSheet(final String sheetName) {
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = workbook.createSheet(sheetName);
        worksheet.createRow(0);
        this.writeIntoFile(workbook);
        this.datasheetName = sheetName;
    }
    
    public int addRow() {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final int newRowNum = worksheet.getLastRowNum() + 1;
        worksheet.createRow(newRowNum);
        this.writeIntoFile(workbook);
        return newRowNum;
    }
    
    public void addColumn(final String columnHeader) {
        this.addColumn(columnHeader, null);
    }
    
    public void addColumn(final String columnHeader, final ExcelCellFormatting cellFormatting) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final HSSFRow row = worksheet.getRow(0);
        int lastCellNum = row.getLastCellNum();
        if (lastCellNum == -1) {
            lastCellNum = 0;
        }
        final HSSFCell cell = row.createCell(lastCellNum);
        cell.setCellType(1);
        cell.setCellValue(columnHeader);
        if (cellFormatting != null) {
            final HSSFCellStyle cellStyle = this.applyCellStyle(workbook, cellFormatting);
            cell.setCellStyle(cellStyle);
        }
        this.writeIntoFile(workbook);
    }
    
    public void setCustomPaletteColor(final short index, final String hexColor) {
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFPalette palette = workbook.getCustomPalette();
        if (index < 8 || index > 64) {
            throw new FrameworkException("Valid indexes for the Excel custom palette are from 0x8 to 0x40 (inclusive)!");
        }
        final Color color = Color.decode(hexColor);
        palette.setColorAtIndex(index, (byte)color.getRed(), (byte)color.getGreen(), (byte)color.getBlue());
        this.writeIntoFile(workbook);
    }
    
    public void mergeCells(final int firstRow, final int lastRow, final int firstCol, final int lastCol) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        worksheet.addMergedRegion(cellRangeAddress);
        this.writeIntoFile(workbook);
    }
    
    public void setRowSumsBelow(final boolean rowSumsBelow) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        worksheet.setRowSumsBelow(rowSumsBelow);
        this.writeIntoFile(workbook);
    }
    
    public void groupRows(final int firstRow, final int lastRow) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        worksheet.groupRow(firstRow, lastRow);
        this.writeIntoFile(workbook);
    }
    
    public void autoFitContents(int firstCol, final int lastCol) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        if (firstCol < 0) {
            firstCol = 0;
        }
        if (firstCol > lastCol) {
            throw new FrameworkException("First column cannot be greater than last column!");
        }
        for (int currentColumn = firstCol; currentColumn <= lastCol; ++currentColumn) {
            worksheet.autoSizeColumn(currentColumn);
        }
        this.writeIntoFile(workbook);
    }
    
    public void addOuterBorder(final int firstCol, final int lastCol) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final CellRangeAddress cellRangeAddress = new CellRangeAddress(0, worksheet.getLastRowNum(), firstCol, lastCol);
        HSSFRegionUtil.setBorderBottom(1, cellRangeAddress, worksheet, workbook);
        HSSFRegionUtil.setBorderRight(1, cellRangeAddress, worksheet, workbook);
        this.writeIntoFile(workbook);
    }
    
    public void addOuterBorder(final int firstRow, final int lastRow, final int firstCol, final int lastCol) {
        this.checkPreRequisites();
        final HSSFWorkbook workbook = this.openFileForReading();
        final HSSFSheet worksheet = this.getWorkSheet(workbook);
        final CellRangeAddress cellRangeAddress = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
        HSSFRegionUtil.setBorderBottom(1, cellRangeAddress, worksheet, workbook);
        HSSFRegionUtil.setBorderTop(1, cellRangeAddress, worksheet, workbook);
        HSSFRegionUtil.setBorderRight(1, cellRangeAddress, worksheet, workbook);
        HSSFRegionUtil.setBorderLeft(1, cellRangeAddress, worksheet, workbook);
        this.writeIntoFile(workbook);
    }
}