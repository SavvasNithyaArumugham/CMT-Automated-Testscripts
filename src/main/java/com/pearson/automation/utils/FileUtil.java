package com.pearson.automation.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;

/**********************************************************************************************
 * FileUtil.java - This program checks for empty diredtory, read data from file
 * and write data into file
 * 
 * @author Duvvuru Naveen
 * @version 1.0
 ***********************************************************************************************/

public class FileUtil {

	private String[] linesOfDataInTxtFile;
	private ArrayList<String> lines = new ArrayList<String>();
	private String dataInTxtFile;
	private ArrayList listDataInTxtFile = new ArrayList();
	ArrayList<String> listData = new ArrayList<String>();

	/*
	 * Comments by - Srinivasa Vegi Function - Required for Dir Verification
	 * Purpose - Checks if a directory is empty
	 */
	/**
	 * .
	 * 
	 * @param folderName
	 *            folderName
	 * @return FIFA folderName
	 */

	public static boolean checkforEmptyDirectory(final String folderName) {
		java.io.File file = new java.io.File(folderName);
		if (file.isDirectory()) {
			return file.list().length > 0;
		}
		return false;
	}

	public static void deleteFile(String filePath) {
		try {
			File file = new File(filePath);
			file.delete();
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Write text into file
	public void writeTextToFile(String mapDetails, String filePath) {
		try {
			File file = new File(filePath);
			file.delete();
			file.createNewFile();

			FileUtils.writeStringToFile(file, mapDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Append text into file
	public void appendTextToFile(String mapDetails, String filePath) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(filePath, true); // the true will append the new
													// data
			fw.write(mapDetails + "\n");// appends the string to the file
		} catch (IOException ioe) {
			System.err.println("IOException: " + ioe.getMessage());
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Clear text into file
	public void clearFile(String filePath) {
		try {
			File file = new File(filePath);
			file.delete();
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Write list of text to given file
	public void writeListTextToFile(ArrayList mapDetails, String filePath) {
		try {
			File file = new File(filePath);
			file.delete();
			file.createNewFile();

			FileUtils.writeLines(file, mapDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Read lines data from Text file by given line count and return data
	public ArrayList<String> readDataFromFileByLineCount(int noOfFilesToUpload, String filePath) {
		try {
			File file = new File(filePath);
			List<String> fileLinesData = FileUtils.readLines(file);

			int count = 0;

			for (String strLine : fileLinesData) {
				if (count < (noOfFilesToUpload)) {
					lines.add(strLine);
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return lines;

	}

	// Read data from given file and return data
	public String readDataFromFile(String filePath) throws Exception {
		try {
			File file = new File(filePath);
			dataInTxtFile = FileUtils.readFileToString(file);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dataInTxtFile;
	}

	// Read set of data from given file and return data
	public String[] readSetOFDataFromFile(String filePath) throws Exception {
		try {
			File file = new File(filePath);
			List<String> lines = FileUtils.readLines(file);

			linesOfDataInTxtFile = new String[lines.size()];

			for (int index = 0; index < lines.size(); index++) {
				linesOfDataInTxtFile[index] = lines.get(index);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return linesOfDataInTxtFile;
	}

	// Read list of data from given file and return data
	public ArrayList readListOFDataFromFile(String filePath) throws Exception {
		try {
			File file = new File(filePath);
			List<String> lines = FileUtils.readLines(file);

			listDataInTxtFile = new ArrayList(lines.size());

			for (String strLine : lines) {
				listDataInTxtFile.add(strLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listDataInTxtFile;
	}

	public void deleteIfFileExistsInDownloadPath(String filePath, String fileorFolderName) {
		try {
			File fileName = new File(filePath + "\\" + fileorFolderName);
			while (true) {
				if (fileName.exists()) {
					fileName.delete();
					break;
				} else {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteFileWithContainsTxtInDownloadPath(String filePath, String containsFileOrFolderName) {
		try {
			File folder = new File(filePath);
			File[] listOfFiles = folder.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].getName().contains(containsFileOrFolderName)) {
					File fileName = new File(filePath + "\\" + listOfFiles[i].getName());
					if (fileName.exists()) {
						fileName.delete();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Delete file
	public void deleteFilesForPathByPrefix(final String path, final String prefix) {
		try (DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(Paths.get(path), prefix + "*")) {
			for (final Path newDirectoryStreamItem : newDirectoryStream) {
				Files.delete(newDirectoryStreamItem);
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void waitUptoFileDownloadComplete(String filePath, String fileName) {
		try {

			File zipFile = new File(filePath + "\\" + fileName);
			long start_time = System.currentTimeMillis();
			long wait_time = 200000;
			long end_time = start_time + wait_time;
			while (System.currentTimeMillis() < end_time) {
				if (zipFile.exists()) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean readContentsOfZipFile(String filepath, String zipFileName, String fileNameToFind) {
		boolean flag = false;
		try {
			String zipfile = filepath + "\\" + zipFileName;
			FileInputStream fis = new FileInputStream(zipfile);
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (entry.toString().contains(fileNameToFind)) {
					flag = true;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public void deleteIfAllFilesExistsInDownloadPath(String filePath, String fileNames) {
		try {
			if (fileNames.contains(",")) {
				String splittedFileName[] = fileNames.split(",");

				if (splittedFileName != null) {
					for (String fileOrFolderName : splittedFileName) {
						String finalFileOrFolderName;
						if (fileOrFolderName.contains(".")) {
							String splitVal[] = fileOrFolderName.split(Pattern.quote("."));
							String part1 = splitVal[0];
							finalFileOrFolderName = part1 + "." + splitVal[1];
						} else {
							finalFileOrFolderName = fileOrFolderName;
						}

						File fileOrFolderNameVal = new File(filePath + "\\" + finalFileOrFolderName);
						while (true) {
							if (fileOrFolderNameVal.exists()) {
								fileOrFolderNameVal.delete();
								break;
							} else {
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitUptoAllFilesDownloadComplete(String filePath, String fileNames) {
		try {
			if (fileNames.contains(",")) {
				String splittedFileName[] = fileNames.split(",");

				if (splittedFileName != null) {
					for (String fileNameVal : splittedFileName) {
						String finalFileName;
						if (fileNameVal.contains(".")) {
							String splitVal[] = fileNameVal.split(Pattern.quote("."));
							String part1 = splitVal[0];
							finalFileName = part1 + "." + splitVal[1];
						} else {
							finalFileName = fileNameVal;
						}

						File zipFile = new File(filePath + "\\" + finalFileName);
						long start_time = System.currentTimeMillis();
						long wait_time = 200000;
						long end_time = start_time + wait_time;
						while (System.currentTimeMillis() < end_time) {
							if (zipFile.exists()) {
								break;
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void renamefile(String filePath, String fileName, String newfile) {
		try {

			File oldfile = new File(filePath + "\\" + fileName);
			File renamefile = new File(filePath + "\\" + newfile);	
			oldfile.renameTo(renamefile);			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> readZip(String zipFilePath) {
		
		listData.clear();
		try {

			ZipFile zFile= new ZipFile(zipFilePath);
			
            Enumeration<? extends ZipEntry> entries = zFile.entries();
 
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String name = entry.getName();			
                listData.add(name);
		}
            
            zFile.close();
		}catch (Exception e) {
			System.out.println("Error opening zip file" + e);
			e.printStackTrace();
		}
		
		return listData;
	}
}
