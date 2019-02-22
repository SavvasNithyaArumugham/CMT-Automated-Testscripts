package com.pearson.automation.utils;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Iterator;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

public class CSVUtil {
	
	BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
	
	/**
	 * Update CSV by row and column
	 * 
	 * @param fileToUpdate
	 *            CSV file path to update
	 * @param replace
	 *            Replacement for your cell value
	 * @param row
	 *            Row for which need to update
	 * @param col
	 *            Column for which you need to update
	 * @throws IOException
	 */
	public static void updateCSV(String fileToUpdate, String replace, int row,
			int col) throws IOException {

		File inputFile = new File(fileToUpdate);

		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		// get CSV row column and replace with by using row and column
		csvBody.get(row)[col] = replace;
		reader.close();

		// Write to CSV file which is open
		CSVWriter writer = new CSVWriter(new FileWriter(inputFile), ',');
		writer.writeAll(csvBody);
		writer.flush();
		writer.close();
		
	
		
	}
	
	//Read data from CSV file
	public ArrayList<String> readLinesOfDataFromCSVFile(String csvFile)
	{
		ArrayList<String> csvFileRowDataList = new ArrayList<String>();
		try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

            	csvFileRowDataList.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return csvFileRowDataList;
	}
	
	//Read data from CSV file without header
		public ArrayList<String> readLinesOfDataFromCSVFileWithoutHeader(String csvFile)
		{
			ArrayList<String> csvFileRowDataList = new ArrayList<String>();
			try {

	            br = new BufferedReader(new FileReader(csvFile));
	            br.readLine();
	            while ((line = br.readLine()) != null) {

	            	csvFileRowDataList.add(line);
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
			
			return csvFileRowDataList;
		}
	
	//*********************************Verify data in CSV file*****************************//
	//*********************************Added by Sangeetha.L *******************************//
	public static boolean verifyDataInCell(String fileToVerify, String stringToComp, int row,
			int col) throws IOException {

		File inputFile = new File(fileToVerify);
		boolean flag =false;
		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		// get CSV row column and replace with by using row and column
		if (csvBody.get(row)[col].equalsIgnoreCase(stringToComp))
				{
					flag=true;
					reader.close();
					return flag;
				};
		reader.close();
		return flag;
	}
	
	
	
	//*********************************Read data in CSV file - Added for NALS*****************************//
			
		public static String readDataInCell(String fileToVerify,int row,int col) throws IOException 
		{

			File inputFile = new File(fileToVerify);
			// Read existing file
			CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
			List<String[]> csvBody = reader.readAll();
			String cellValue = csvBody.get(row)[col];
			reader.close();
			return cellValue;
		}
		
		
		
	
	//*********************************Verify number of rows in CSV file*****************************//
	//*********************************Added by Sangeetha.L *******************************//
	
	public int getRowCount(String csvFile ) throws IOException
	   {
		 int rowCount =0;
		 
		 try {
	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {

	            	rowCount++;
	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	      return rowCount;
	   }
	 
	
	public static boolean verifyDataInCellContains(String fileToVerify, String stringToComp, int row,
			int col) throws IOException {

		File inputFile = new File(fileToVerify);
		boolean flag =false;
		// Read existing file
		CSVReader reader = new CSVReader(new FileReader(inputFile), ',');
		List<String[]> csvBody = reader.readAll();
		// get CSV row column and replace with by using row and column
		if (csvBody.get(row)[col].contains(stringToComp))
				{
					flag=true;
					reader.close();
					return flag;
				};
		reader.close();
		return flag;
	}
}

