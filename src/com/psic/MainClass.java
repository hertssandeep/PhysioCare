package com.psic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.psic.junit.TestPhysioClass;
import com.psic.manager.AppointmentPhysioManager;
import com.psic.pojo.Appointment;
import com.psic.utils.Utils;

public class MainClass {
	
	public static void main(String[] args) throws  IOException {
		Utils.br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
				System.out.println("1. Patient Registration.");
				System.out.println("2. Book patient appointment.");
				System.out.println("3. Book visitor appointment.");
				System.out.println("4. Attend an appointment");
				System.out.println("5. Cancel an appointment.");
				System.out.println("6. Report of all appointments.");
				System.out.println("7. Report of appointments each patient has booked, attended, cancelled, and missed.");
				System.out.println("8. Exit");
				System.out.print("Please select one of the above options from 1 to 8: ");
				
				int input = Utils.readInteger();
				if(input==8) {
					Utils.br.close();
					System.exit(0);
				}
				else if(input == 1) {
					registrationForNewPatient();
				}
				else if(input==2) {
					bookPatientAppint();
				}
				else if(input==3) {
					bookVisitorAppint();
				}
				else if(input==4) {
					markAppointment();
				}
				else if(input ==5) {
					cancelAppointment();
				}
				else if(input==6) {
					AppointmentPhysioManager.getInstance().generateReport();
				}
				else if(input== 7) {
					AppointmentPhysioManager.getInstance().generatePatientsReport();
				}
				else {
					System.out.println("Incorrect input. Please enter inputs from 1-8");
				}
			
		}
	}
	
	public static void bookPatientAppint() {
		System.out.print("Please enter patient id: ");
		int id = Utils.readInteger();
		if(!AppointmentPhysioManager.getInstance().verifyPatient(id)) {
			System.out.println("No patients found with id. Please enter correct id "+id);
		}
		else {
			AppointmentPhysioManager.getInstance().bookAppointment(id);
		}
				
	}
	
	public static void bookVisitorAppint() {
		System.out.print("Please enter visitor name: ");
		
		String name = Utils.readString();
		System.out.print("Please enter date on which you want to book appointment with physician: ");
		String date = Utils.readString();
		AppointmentPhysioManager.getInstance().bookVisitorAppointment(name, date);
		
	}
	
	public static void registrationForNewPatient() {
		System.out.print("Please enter unique id: ");
		int id = Utils.readInteger();
		System.out.print("Please enter full name: ");
		//Utils.scanner.nextLine();
		String name = Utils.readString();
		System.out.print("Please enter address: ");
		String address = Utils.readString();
		System.out.print("Please enter number: ");
		String number = Utils.readString();
		if(AppointmentPhysioManager.getInstance().registerNewPatient(id, name, address, number)) {
			System.out.println("Patient registered successfully");
		}else {
			System.out.println("Patient with id "+id+" already exists. Please enter different id");
			
		}
		
	}
	
	public static void cancelAppointment() {
		System.out.print("Please enter your Patient id: ");
		int patient_id = Utils.readInteger();
		System.out.print("Please enter Appointment id you want to cancel: ");
		int appointment_id = Utils.readInteger();
		AppointmentPhysioManager.getInstance().cancelAppointment(patient_id, appointment_id);
	}
	
	public static void markAppointment() {
		System.out.print("Please enter Patient id: ");
		int patient_id = Utils.readInteger();
		System.out.print("Please enter Appointment id:");
		int appointment_id = Utils.readInteger();
		AppointmentPhysioManager.getInstance().markAppointment(patient_id, appointment_id);
	}	

}
