package com.psic.junit;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import com.psic.manager.AppointmentPhysioManager;
import com.psic.pojo.Appointment;

public class TestPhysioClass {
	
	@Test
	public void addPatient() {
		AppointmentPhysioManager.getInstance().registerNewPatient(100, "Sandeep", "Hatfield", "74711");
		assertTrue(AppointmentPhysioManager.getInstance().patientsSize() == 16);
	}
	
	@Test
	public void bookAppoinment() {
		Appointment app = new Appointment();
		app.setAppointmentID(1);
		app.setPatientID(123);
		app.setPhysicianID(1005);
		AppointmentPhysioManager.getInstance().blockAppointment(app);
		assertTrue(AppointmentPhysioManager.getInstance().getAppointmentsByPatientID(123).size()==1);
	}
	

}
