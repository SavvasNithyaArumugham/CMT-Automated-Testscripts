package com.pearson.automation.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**********************************************************************************************
 * DateUtil.java - This program gets current Date & time and converts to EST
 * format.
 * 
 * @author Duvvuru Naveen
 * @version 1.0
 ***********************************************************************************************/
public class DateUtil {

	private String customizedDate;

	// Get EST Time Zone
	private static Date shiftTimeZone(Date date, TimeZone sourceTimeZone,
			TimeZone targetTimeZone) {
		Calendar sourceCalendar = Calendar.getInstance();
		sourceCalendar.setTime(date);
		sourceCalendar.setTimeZone(sourceTimeZone);

		Calendar targetCalendar = Calendar.getInstance();
		for (int field : new int[] { Calendar.YEAR, Calendar.MONTH,
				Calendar.DAY_OF_MONTH, Calendar.HOUR, Calendar.MINUTE,
				Calendar.SECOND, Calendar.MILLISECOND }) {
			targetCalendar.set(field, sourceCalendar.get(field));
		}
		targetCalendar.setTimeZone(targetTimeZone);
		return targetCalendar.getTime();
	}

	// Get EST Details
	public static String getESTDetails(String zone) {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy;HH:mm:ss");
		sf.format(date);
		TimeZone tz = sf.getTimeZone();
		TimeZone tz1 = TimeZone.getTimeZone(zone);
		Date c = shiftTimeZone(date, tz, tz1);

		return sf.format(c);
	}

	// Or Get EST Date and Time
	public static String getESTDateAndTime(String timeZoneFor) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneFor));

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy;h:mm:ss");
		df.setTimeZone(cal.getTimeZone());
		return df.format(cal.getTime());
	}

	public static String getCurrentDateAndTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// get current date time with Date()
		Date date = new Date();

		return dateFormat.format(date);
	}

	public static String getDayOfWeekAndDate() {
		Date dt = new Date();

		SimpleDateFormat formatter = new SimpleDateFormat(
				"EEE dd-MMM-yyyy HH:mm:ss");

		formatter.format(dt);
		return "" + dt;
	}

	public String convertDateToCustomizedFormat(String customFormat,
			String inputDate) throws ParseException {
		try {

			DateFormat dateFormat = new SimpleDateFormat(customFormat);
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

			Date date = formatter.parse(inputDate);
			customizedDate = dateFormat.format(date);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return customizedDate;
	}

	public String getCurrentDate() {

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String dateVal = dateFormat.format(date);

		return dateVal;
	}

	public String getCurrentDateInYYYYMMDD() {

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		Date date = new Date();
		String dateVal = dateFormat.format(date);

		return dateVal;
	}
	
	public String convertDateToCustomizedFormat(String customFormat, String inputDate, String requiredFormate) throws ParseException
    {
        try {

            DateFormat dateFormat = new SimpleDateFormat(requiredFormate);
            SimpleDateFormat formatter = new SimpleDateFormat(customFormat);

            Date date = formatter.parse(inputDate);
            customizedDate = dateFormat.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return customizedDate;
    }
	
	public String getUTCformatDateandTime()
	{
		
		try{
			DateFormat formatterIST = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
			formatterIST.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
			Date dt = new Date();
			DateFormat formatterUTC = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
			formatterUTC.setTimeZone(TimeZone.getTimeZone("UTC")); // UTC timezone
			System.out.println(formatterUTC.format(dt)); // output: Tue Feb 13 07:04:26 UTC 2018
			
			customizedDate= formatterUTC.format(dt);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return customizedDate;
			
	}
	
	
}
