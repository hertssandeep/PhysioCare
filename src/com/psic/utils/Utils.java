package com.psic.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Utils {
	
	//public static Scanner scanner;
	public static BufferedReader br;
	
	public static int getDayFromDate(String date) {

		Calendar cal = Calendar.getInstance();
		String[] days = date.split("-");
		cal.set(Integer.parseInt(days[2]), Integer.parseInt(days[1])-1, Integer.parseInt(days[0]));
		return cal.get(Calendar.DAY_OF_WEEK);
	
	}
	
	public static boolean validateDate(String date) {
		Calendar cal = Calendar.getInstance();
		String[] days = date.split("-");
		cal.set(Integer.parseInt(days[2]), Integer.parseInt(days[1])-1, Integer.parseInt(days[0]));
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.set(2021, 04, 01);
		end.set(2021, 04, 28);
		if(start.getTime().after(cal.getTime())) {
			System.out.println("Appointments for the selected date is already closed. Please enter dates between 01-05-2021 and 28-05-2021");
			return false;
		}
		if(end.getTime().before(cal.getTime())) {
			System.out.println("Appointments for the date entered are not opened yet. Please enter dates between 01-05-2021 and 28-05-2021");
			return false;
		}
		/*
		 * long mill = cal.getTime().getTime() - currDate.getTime(); long diff =
		 * TimeUnit.DAYS.convert(mill, TimeUnit.MILLISECONDS); if(diff>28) {
		 * System.out.println("Bookings for the selected date are not opened yet.");
		 * return false; }
		 */
		
		return true;
	}
	
	public static int readInteger() {
		try {
			return Integer.parseInt(br.readLine());
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String readString() {
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

}
