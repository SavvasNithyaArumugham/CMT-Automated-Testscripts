package com.pearson.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**********************************************************************************************
 * ExcelParser.java - This class Parse Excel file into Java Objects.
 * 
 * @author Duvvuru Naveen
 * @version 1.0
 ***********************************************************************************************/

public class ExcelUtil {

	// Write data into Excel sheet
	public void writeDataIntoExcelSheet(String filePath, int sheetPosition,
			int rowVal, int columnVal, String cellValue) throws Exception {

		// Read the spreadsheet that needs to be updated
		FileInputStream fsIP = new FileInputStream(new File(filePath));

		// Access the workbook
		HSSFWorkbook wb = new HSSFWorkbook(fsIP);

		// Access the worksheet, so that we can update / modify it.
		HSSFSheet worksheet = wb.getSheetAt(sheetPosition);
		Cell cell = null; // declare a Cell object

		// Access the second cell in second row to update the value
		cell = worksheet.getRow(rowVal).getCell(columnVal);

		// Get current cell value value and overwrite the value
		cell.setCellValue(cellValue);

		// Close the InputStream
		fsIP.close();

		// Open FileOutputStream to write updates
		FileOutputStream output_file = new FileOutputStream(new File(filePath));

		// write changes
		wb.write(output_file);
		output_file.close();
	}

	// Get adjacent cell value based specific row cell value
	public String getDataFromExcelSheet(String filePath, String sheetName, String autoTestCaseNameVal) {
		String requiredCellVal="";
		try {
			FileInputStream fis = new FileInputStream(filePath);
			HSSFWorkbook wb = new HSSFWorkbook(fis);
			HSSFSheet ws = wb.getSheet(sheetName);

			int rowNum = ws.getLastRowNum() + 1;

			for (int index = 0; index < rowNum; index++) {
				HSSFRow row = ws.getRow(index);
				
				HSSFCell cell = row.getCell(0);
				HSSFCell adjacentCell = row.getCell(2);
				if(cell.getStringCellValue().equals(autoTestCaseNameVal))
				{
					requiredCellVal = adjacentCell.getStringCellValue();
				}
					
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return requiredCellVal;
	}
	
	public String cellToString(HSSFCell cell) {  
	    int type;
	    String result;
	    type = cell.getCellType();

	    switch (type) {

	        case Cell.CELL_TYPE_NUMERIC: // numeric value in Excel
	        	result = ""+cell.getNumericCellValue();
	            break;
	        case Cell.CELL_TYPE_FORMULA: // precomputed value based on formula
	            result = ""+cell.getNumericCellValue();
	            break;
	        case Cell.CELL_TYPE_STRING: // String Value in Excel 
	            result = ""+cell.getStringCellValue();
	            break;
	        case Cell.CELL_TYPE_BLANK:
	            result = "";
	            break;
	        case Cell.CELL_TYPE_BOOLEAN: //boolean value 
	            result=""+cell.getBooleanCellValue();
	            break;
	        case Cell.CELL_TYPE_ERROR:
	        	result="Error";
	        default:  
	            throw new RuntimeException("There is no support for this type of cell");                        
	    }

	    return result.toString();
	}
}
