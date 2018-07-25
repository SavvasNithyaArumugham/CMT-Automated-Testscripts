package com.pearson.automation.utils;

/**********************************************************************************************
 * EnvironmentUpdate.java - This program gets current Date & time and converts to EST
 * format.
 * 
 * @author Moshin Shariff 
 * @version 1.0
 ***********************************************************************************************/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class ENV_NAME {

	public static void main(String args[]) {
		
	String	environmentDomain = null;
	environmentDomain = System.getProperty("ENV");
		
		if(environmentDomain == null){

		System.out.print("Enter Environment: ");
		Scanner sp = new Scanner(System.in);
	//	String environmentDomain;

		switch (sp.nextLine()) {
		case "1":
			environmentDomain = "qawip.pearsoncms.com";
			break;
		case "2":
			environmentDomain = "qaarc.pearsoncms.com";
			break;
		case "3":
			environmentDomain = "usppewip.pearsoncms.com";
			break;
		case "4":
			environmentDomain = "ukppewip.pearsoncms.com";
			break;
		case "5":
			environmentDomain = "apppewip.pearsoncms.com";
			break;
		case "6":
			environmentDomain = "usppearc.pearsoncms.com";
			break;
		case "7":
			environmentDomain = "ukppearc.pearsoncms.com";
			break;
		case "8":
			environmentDomain = "apppearc.pearsoncms.com";
			break;
		default:
			environmentDomain = "Invalid entry";
			try {
				Runtime.getRuntime().exec("taskkill /f /im cmd.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
		System.out.println(environmentDomain);
		try {
			String tempFilePath = System.getProperty("user.dir").replace("\\src\\main\\java", "");
			String finalFilePath = tempFilePath + "/Global Settings.properties";

			File file = new File(finalFilePath);
			FileInputStream fileInput;

			fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			try {
				properties.load(fileInput);
			} catch (IOException e) {
				e.printStackTrace();
			}

			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "", oldtext = "";
			try {
				while ((line = reader.readLine()) != null) {
					oldtext += line + "\r\n";
				}
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String applicationURL = properties.getProperty("ApplicationUrl");
			String replacedtext = oldtext.replaceAll(applicationURL, "https://" + environmentDomain);
			String webDavURL = properties.getProperty("WebdavUrl");
			replacedtext = replacedtext.replaceAll(webDavURL, "https://" + environmentDomain + "/alfresco/webdav");
			FileWriter writer;
			try {
				writer = new FileWriter(finalFilePath);
				writer.write(replacedtext);
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String internalTo = properties.getProperty("IntenalMails");
			String externalTo = properties.getProperty("ExternalMails");
			String defaultTo = properties.getProperty("DefaultMails");

			System.out.println("\n");
			System.out.print("Select 1 for Mail to Internal\n\nSelect 2 for Mail to All\n\nSelect Mail option: ");
			Scanner mailOption = new Scanner(System.in);
			String mailId;
			switch (mailOption.nextLine()) {
			case "1":
				mailId = internalTo;
				break;
			case "2":
				mailId = externalTo;
				break;
			default:
				mailId = defaultTo;

			}
			System.out.println(mailId);
			BufferedReader reader1 = new BufferedReader(new FileReader(file));
			String line1 = "", oldtext1 = "";
			try {
				while ((line1 = reader1.readLine()) != null) {
					oldtext1 += line1 + "\r\n";
				}
				reader1.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			String mailTo = properties.getProperty("SendMailTo");
			String mailIDReplaceText = oldtext1.replaceAll(mailTo, "" + mailId);
			try {
				writer = new FileWriter(finalFilePath);
				writer.write(mailIDReplaceText);
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
