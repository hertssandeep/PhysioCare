package com.psic.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.psic.pojo.Appointment;
import com.psic.pojo.Patient;
import com.psic.pojo.Physician;
import com.psic.pojo.VisitorAppointment;
import com.psic.utils.Constants;
import com.psic.utils.PrintTable;
import com.psic.utils.Utils;

public class AppointmentPhysioManager {
	
	
	static AppointmentPhysioManager manager;
	
	private Map<Integer,Physician> physicians;
	private Map<Integer,Patient> patients;
	private Map<String,List<Physician>> physiciansByExpertise;
	private Map<String,List<String>> treatmentToRooms;
	List<String> expertises;
	List<String> treatments;
	Map<String,Map<Integer,List<Appointment>>> appointments;
	Map<Integer,List<Appointment>> appointmentsByPatient;
	Map<String,Map<String,List<Appointment>>> roomToAppointment;
	
	public int patientsSize() {
		return patients.size();
	}
	
	public static AppointmentPhysioManager getInstance() {
		if(manager==null) {
			manager = new AppointmentPhysioManager();
		}
		
		return manager;
	}
	
	private AppointmentPhysioManager() {
		generateDefaultData();
	}
	
	private void generateDefaultData() {
		physicians = new HashMap<>();
		patients = new HashMap<>();
		
		addAllExpertises();
		physiciansByExpertise = new HashMap<>();
		for(String exp: expertises) {
			physiciansByExpertise.put(exp, new ArrayList<>());
		}
		
		generatePhysiciansData();
		
		
		
		patients.put(123, new Patient(421,"Vijith","London","214565482"));
		patients.put(456, new Patient(248,"Ritwik" ,"London","45423544"));
		patients.put(189, new Patient(213,"Sandeep","London","21354521354"));
		patients.put(176, new Patient(474,"Megha","Bristol","7548654321"));
		patients.put(165, new Patient(165,"Alex","Liverpool","214654862"));
		patients.put(198, new Patient(198,"Peter","Glasgow","1321547985"));
		patients.put(254, new Patient(254,"Rocky","London","5468854546"));
		patients.put(250, new Patient(250,"Madison","Glasgow","13213215454"));
		patients.put(249, new Patient(249,"James","Liverpool","1254564665"));
		patients.put(124, new Patient(124,"Lily","Sheffeld","132154468"));
		patients.put(125, new Patient(464,"Rose","Newcastle","1235465465"));
		patients.put(126, new Patient(454,"Lucy","London","13321325456"));
		patients.put(177, new Patient(422,"Bruce","Bristol","12134154564"));
		patients.put(102, new Patient(451,"Ricky","Newcastle","12313545212"));
		patients.put(188, new Patient(321,"Raja","London","1231354564"));
		
		
		
		List<String> rooms = new ArrayList<>();
		rooms.add("A");
		rooms.add("B");
		rooms.add("C");
		treatmentToRooms = new HashMap<>();
		treatmentToRooms.put(Constants.neuralMobil, rooms);
		treatmentToRooms.put(Constants.spineWorkout, rooms);
		treatmentToRooms.put(Constants.acupuncture, rooms);
		treatmentToRooms.put(Constants.massage, rooms);
		
		List<String> rehabRooms = new ArrayList<>();
		rehabRooms.add("Pool");
		treatmentToRooms.put(Constants.poolSwim, rehabRooms);
		
		appointments = new HashMap<>();
		appointmentsByPatient = new HashMap<>();
		
		roomToAppointment = new HashMap<>();
		
		visitorAppointments = new HashMap<>();
		
	}
	
	public void generatePhysiciansData() {
		Physician pys1 = new Physician(1001, "Sethu Babu", "XYZ", "1321434322");
		pys1.addExpertise(Constants.physioTherapy);
		pys1.addExpertise(Constants.osteopathy);
		
		pys1.addTreatment(Constants.acupuncture);
		pys1.addTreatment(Constants.neuralMobil);
		pys1.addTreatment(Constants.massage);
		
		Map<String,List<String>> expToTreat1 = new HashMap<>();
		expToTreat1.put(Constants.physioTherapy, new ArrayList<>());
		expToTreat1.put(Constants.osteopathy, new ArrayList<>());
		
		expToTreat1.get(Constants.osteopathy).add(Constants.acupuncture);
		expToTreat1.get(Constants.physioTherapy).add(Constants.massage);
		expToTreat1.get(Constants.osteopathy).add(Constants.neuralMobil);
		pys1.setExpToTreatments(expToTreat1);
		
		Map<Integer,List<String>> timings = new HashMap<>();
		
		timings.put(2, new ArrayList<String>(){{
            add("10-12");
            add("14-16");
            add("17-19");
            }} );
		timings.put(3, new ArrayList<String>(){{
            add("9-11");
            add("15-17");
            }} );
		timings.put(5, new ArrayList<String>(){{
            add("10-12");
            add("16-18");
            }} );
		timings.put(6, new ArrayList<String>(){{
            add("9-11");
            add("13-15");
            add("16-18");
            }} );
		
		pys1.setTimings(timings);
		
		List<String> consultHours = new ArrayList<>(); 
		consultHours.add("10:00");
		consultHours.add("10:30");
		consultHours.add("11:00");
		consultHours.add("11:30");
		Map<Integer, List<String>> consult1 = new HashMap<>();
		consult1.put(4, consultHours);
		pys1.setConsultationHours(consult1);
		
		physicians.put(pys1.getId(), pys1);
		
		physiciansByExpertise.get(Constants.physioTherapy).add(pys1);
		physiciansByExpertise.get(Constants.osteopathy).add(pys1);
		
		
		
		Physician pys2 = new Physician(1002, "James Joseph", "ABC", "1321434982");
		pys2.addExpertise(Constants.physioTherapy);
		pys2.addExpertise(Constants.rehabilation);
		
		pys2.addTreatment(Constants.poolSwim);
		pys2.addTreatment(Constants.massage);
		
		Map<String,List<String>> expToTreat2 = new HashMap<>();
		expToTreat2.put(Constants.physioTherapy, new ArrayList<>());
		expToTreat2.put(Constants.rehabilation, new ArrayList<>());
		
		expToTreat2.get(Constants.physioTherapy).add(Constants.massage);
		expToTreat2.get(Constants.rehabilation).add(Constants.poolSwim);
		pys2.setExpToTreatments(expToTreat2);
		
		Map<Integer,List<String>> timings2 = new HashMap<>();
		
		timings2.put(2, new ArrayList<String>(){{
            add("9-11");
            add("13-15");
            add("16-18");
            }} );
		timings2.put(4, new ArrayList<String>(){{
            add("10-12");
            add("15-17");
            }} );
		timings2.put(5, new ArrayList<String>(){{
            add("10-12");
            add("16-18");
            }} );
		timings2.put(6, new ArrayList<String>(){{
            add("7-9");
            add("11-13");
            add("14-16");
            }} );
		
		pys2.setTimings(timings2);
		Map<Integer, List<String>> consult2 = new HashMap<>();
		consult2.put(3, consultHours);
		pys2.setConsultationHours(consult2);
		
		physicians.put(pys2.getId(), pys2);
		physiciansByExpertise.get(Constants.rehabilation).add(pys2);
		physiciansByExpertise.get(Constants.physioTherapy).add(pys2);
		
		Physician pys3 = new Physician(1003, "Jason Ali", "London", "6824592913");
		pys3.addExpertise(Constants.osteopathy);
		
		pys3.addTreatment(Constants.acupuncture);
		pys3.addTreatment(Constants.neuralMobil);
		
		Map<String,List<String>> expToTreat3 = new HashMap<>();
		expToTreat3.put(Constants.osteopathy, new ArrayList<>());
		
		expToTreat3.get(Constants.osteopathy).add(Constants.acupuncture);
		expToTreat3.get(Constants.osteopathy).add(Constants.neuralMobil);
		pys3.setExpToTreatments(expToTreat3);
		
		Map<Integer,List<String>> timings3 = new HashMap<>();
		
		timings3.put(3, new ArrayList<String>(){{
            add("9-11");
            add("13-15");
            add("16-18");
            }} );
		timings3.put(4, new ArrayList<String>(){{
            add("10-12");
            add("15-17");
            }} );
		timings3.put(5, new ArrayList<String>(){{
            add("10-12");
            add("16-18");
            }} );
		timings3.put(7, new ArrayList<String>(){{
            add("7-9");
            add("11-13");
            add("14-16");
            }} );
		
		pys3.setTimings(timings3);
		
		Map<Integer, List<String>> consult3 = new HashMap<>();
		consult3.put(2, consultHours);
		pys3.setConsultationHours(consult3);
		
		physicians.put(pys3.getId(), pys3);
		physiciansByExpertise.get(Constants.osteopathy).add(pys3);
		
		Physician pys4 = new Physician(1004, "John Abraham", "London", "6961592913");
		pys4.addExpertise(Constants.physioTherapy);
		
		pys4.addTreatment(Constants.massage);
		pys4.addTreatment(Constants.spineWorkout);
		
		Map<String,List<String>> expToTreat4 = new HashMap<>();
		expToTreat4.put(Constants.physioTherapy, new ArrayList<>());
		
		expToTreat4.get(Constants.physioTherapy).add(Constants.massage);
		expToTreat4.get(Constants.physioTherapy).add(Constants.spineWorkout);
		pys4.setExpToTreatments(expToTreat4);
		
		Map<Integer,List<String>> timings4 = new HashMap<>();
		
		timings4.put(3, new ArrayList<String>(){{
            add("10-12");
            add("12-16");
            add("17-19");
            }} );
		timings4.put(4, new ArrayList<String>(){{
            add("9-11");
            add("15-17");
            }} );
		timings4.put(5, new ArrayList<String>(){{
            add("10-12");
            add("15-17");
            }} );
		timings4.put(7, new ArrayList<String>(){{
            add("7-9");
            add("11-13");
            add("14-16");
            }} );
		
		pys4.setTimings(timings4);
		
		Map<Integer, List<String>> consult4 = new HashMap<>();
		consult4.put(6, consultHours);
		pys4.setConsultationHours(consult4);
		
		physicians.put(pys4.getId(), pys4);
		physiciansByExpertise.get(Constants.physioTherapy).add(pys4);
		
		Physician pys5 = new Physician(1005, "Rhea", "London", "6981292914");
		pys5.addExpertise(Constants.rehabilation);
		pys5.addExpertise(Constants.osteopathy);
		
		pys5.addTreatment(Constants.poolSwim);
		pys5.addTreatment(Constants.neuralMobil);
		pys5.addTreatment(Constants.acupuncture);
		
		Map<String,List<String>> expToTreat5 = new HashMap<>();
		expToTreat5.put(Constants.rehabilation, new ArrayList<>());
		expToTreat5.put(Constants.osteopathy, new ArrayList<>());
		
		expToTreat5.get(Constants.rehabilation).add(Constants.poolSwim);
		expToTreat5.get(Constants.osteopathy).add(Constants.neuralMobil);
		expToTreat5.get(Constants.osteopathy).add(Constants.acupuncture);
		pys5.setExpToTreatments(expToTreat5);
		
		Map<Integer,List<String>> timings5 = new HashMap<>();
		
		timings5.put(3, new ArrayList<String>(){{
            add("9-11");
            add("13-15");
            add("16-18");
            }} );
		timings5.put(4, new ArrayList<String>(){{
            add("10-12");
            add("15-17");
            }} );
		timings5.put(6, new ArrayList<String>(){{
            add("10-12");
            add("16-18");
            }} );
		timings5.put(1, new ArrayList<String>(){{
            add("7-9");
            add("11-13");
            add("14-16");
            }} );
		
		pys5.setTimings(timings5);
		
		Map<Integer, List<String>> consult5 = new HashMap<>();
		consult5.put(5, consultHours);
		pys5.setConsultationHours(consult5);
		
		physicians.put(pys5.getId(), pys5);
		physiciansByExpertise.get(Constants.rehabilation).add(pys5);
		physiciansByExpertise.get(Constants.osteopathy).add(pys5);
		
	}
	
	public void addAllExpertises() {
		expertises = new ArrayList<>();
		expertises.add(Constants.physioTherapy);
		expertises.add(Constants.osteopathy);
		expertises.add(Constants.rehabilation);
	}
	
	public boolean registerNewPatient(int id, String name, String address, String num) {
		if(patients.containsKey(id)) {
			return false;
		}
		else {
			synchronized(this) {
				patients.put(id, new Patient(id, name, address, num));
			}
			return true;
		}
	}
	
	public void bookAppointment(int patientID) {
		System.out.println("1. Look for treatments by expertise");
		System.out.println("2. Look for the physicians");
		System.out.print("Select 1 or 2: ");
		int option = Utils.readInteger();
		if(option == 1) {
			bookAppointmentByExpertise(patientID);
		}else if(option == 2) {
			bookAppointmentByPhysician(patientID);
		}
	}
	
	public void bookAppointmentByExpertise(int patientID) {
		for(int i=0; i<expertises.size(); i++) {
			System.out.println(i+1+". "+expertises.get(i));
		}
		System.out.print("Please select the expertise: ");
		int option = Utils.readInteger();
		String exp = expertises.get(option-1);
		System.out.print("Please enter the date on which you are looking to book appointment in dd-mm-yyyy format: ");
		String date = Utils.readString();
		boolean isValid =false;
		try {
			isValid = Utils.validateDate(date);
		}catch(Exception e) {
			System.out.println("Date entered is not valid. Please try again and enter valid date");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		if(!isValid) {
			return;
		}
		int day = Utils.getDayFromDate(date);
		List<Physician> phys = physiciansByExpertise.get(exp);
		List<Appointment> availableApps = new ArrayList<>();
		for(int i =0; i<phys.size(); i++) {
			Physician phy = phys.get(i);
			List<String> timings = phy.getTimings().get(day);
			if(timings==null)
				continue;
			availableApps.addAll(getAvailableAppointments(timings, phy, exp, patientID, date, 1));
		}
		bookAppointment(availableApps);
		
	}
	
	public void bookAppointmentByPhysician(int patientID) {
		List<String> headers = new ArrayList<>();
		headers.add("Physician ID");
		headers.add("Physician Name");
		List<List<String>> rows = new ArrayList<>();
		for(Physician phy: physicians.values()) {
			List<String> row = new ArrayList<>();
			row.add(""+phy.getId());
			row.add(phy.getphysioFullName());
			rows.add(row);
		}
		PrintTable table = new PrintTable();
		table.print(rows, headers);
		System.out.print("Please enter the Physician ID of whom you want to book appointment: ");
		int id = Utils.readInteger();
		Physician physician = physicians.get(id);
		System.out.print("Please enter the date on which you are looking to book appointment in dd-mm-yyyy format: ");
		String date = Utils.readString();
		boolean isValid =false;
		try {
			isValid = Utils.validateDate(date);
		}catch(Exception e) {
			System.out.println("Date entered is not valid. Please try again and enter valid date");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		if(!isValid) {
			return;
		}
		int day = Utils.getDayFromDate(date);
		if(physician.getTimings().get(day)==null) {
			System.out.println(physician.getphysioFullName()+" doesn't work on the selected date.");
			return;
		}else {
			List<String> timings = physician.getTimings().get(day);
			List<Appointment> availableAppointments =getAvailableAppointments(timings, physician, "", patientID, date, 2);
			bookAppointment(availableAppointments);
		}
		
	}
	
	public List<Appointment> getAvailableAppointments(List<String> timings, Physician physician, String exp, int patientID, String date, int option) {
		List<Appointment> availableAppointments = new ArrayList<>();
		List<Appointment> apps = null;
		if(appointments.get(date)!=null)
			apps= appointments.get(date).get(physician.getId());
		for(String time: timings) {
			if(apps==null || verifyAvailability(time, apps)) {
				List<String> treatments;
				if(option == 2) {
					treatments = physician.getTreatmentsOffered();
				}else {
					treatments = physician.getExpToTreatments().get(exp);
				}
				for(String treatment: treatments) {
					List<String> availableRooms = getAvailableRooms(date, time, treatment);
					if(availableRooms.size()==0)
						continue;
					Appointment appointment = new Appointment();
					appointment.setPhysicianID(physician.getId());
					appointment.setAppointmentDate(date);
					appointment.setPhysicianName(physician.getphysioFullName());
					appointment.setPatientID(patientID);
					appointment.setPatientName(patients.get(patientID).getpatientFullName());
					appointment.setTiming(time);
					appointment.setTreatment(treatment);
					appointment.setRoom(availableRooms.get(0));
					availableAppointments.add(appointment);
				}
			}
		}
		
		return availableAppointments;
	}
	
	public List<String> getAvailableRooms(String date, String time, String treatment) {
		List<String> rooms = treatmentToRooms.get(treatment);
		List<String> availableRooms = new ArrayList<>();
		for(String room: rooms) {
			if(roomToAppointment.get(date)!=null) {
				if(roomToAppointment.get(date).get(room)!=null) {
					List<Appointment> apps = roomToAppointment.get(date).get(room);
					boolean isBooked = false;
					for(Appointment app: apps) {
						if(verifyOverlapping(app.getTiming(), time) && app.isCancelled()==false) {
							isBooked =true;
							break;
						}
						
					}
					if(!isBooked)
						availableRooms.add(room);
				}else {
					availableRooms.add(room);
				}
			}else {
				availableRooms.add(room);
			}
		}
		
		return availableRooms;
	}
	
	public boolean verifyOverlapping(String time1, String time2) {
		List<Integer> list1 = getHours(time1);
		List<Integer> list2 = getHours(time2);
		
		if(list1.contains(list2.get(1)))
			return true;
		
		
		
		return false;
	}
	
	public List<Integer> getHours(String time){
		List<Integer> hours = new ArrayList<>();
		int startHour = Integer.parseInt(time.split("-")[0]);
		hours.add(startHour);
		hours.add(startHour+1);
		hours.add(startHour+2);
		
		return hours;
	}
	
	public void bookAppointment(List<Appointment> availableAppointments) {
		if(availableAppointments.size()==0){
			System.out.println("No appointments available for the selected day. please try with some other date");
			return;
		}
		List<String> headers = new ArrayList<>();
		headers.add("s.no");
		headers.add("Physician Name");
		headers.add("Treatment Name");
		headers.add("Appointment Timing");
		List<List<String>> rows = new ArrayList<>();
		for(int i=0;i<availableAppointments.size(); i++) {
			Appointment appointment = availableAppointments.get(i);
			List<String> row = new ArrayList<>();
			row.add(""+(i+1));
			row.add(appointment.getPhysicianName());
			row.add(appointment.getTreatment());
			row.add(appointment.getTiming());
			rows.add(row);
		}
		PrintTable table = new PrintTable();
		table.print(rows, headers);
		System.out.println();
		System.out.print("Please select s.no of the above appointments: ");
		int num = Utils.readInteger();
		Appointment app = availableAppointments.get(num-1);
		blockAppointment(app);
	}
	
	
	
	
	public synchronized void blockAppointment(Appointment app) {
		app.setAppointmentID(Appointment.max++);
		if(appointments.get(app.getAppointmentDate())==null) {
			Map<Integer,List<Appointment>> apps = new HashMap<>();
			apps.put(app.getPhysicianID(), new ArrayList<>());
			apps.get(app.getPhysicianID()).add(app);
			appointments.put(app.getAppointmentDate(), apps);
		}else {
			Map<Integer,List<Appointment>> apps = appointments.get(app.getAppointmentDate());
			if(apps.get(app.getPhysicianID())==null) {
				apps.put(app.getPhysicianID(), new ArrayList<>());
				apps.get(app.getPhysicianID()).add(app);
			}else {
				apps.get(app.getPhysicianID()).add(app);
			}
		}
		if(appointmentsByPatient.get(app.getPatientID())==null) {
			appointmentsByPatient.put(app.getPatientID(), new ArrayList<>());
			appointmentsByPatient.get(app.getPatientID()).add(app);
		}else {
			appointmentsByPatient.get(app.getPatientID()).add(app);
		}
		
		if(roomToAppointment.get(app.getAppointmentDate())==null) {
			Map<String,List<Appointment>> roomToApp = new HashMap<>();
			roomToApp.put(app.getRoom(), new ArrayList<>());
			roomToApp.get(app.getRoom()).add(app);
			roomToAppointment.put(app.getAppointmentDate(), roomToApp);
		}else {
			Map<String,List<Appointment>> roomToApp = roomToAppointment.get(app.getAppointmentDate());
			if(roomToApp.get(app.getRoom())==null) {
				roomToApp.put(app.getRoom(), new ArrayList<>());
				roomToApp.get(app.getRoom()).add(app);
			}else {
				roomToAppointment.get(app.getAppointmentDate()).get(app.getRoom()).add(app);
			}
		}
		System.out.println("Appointment booked successfully. Appointment ID: "+app.getAppointmentID());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Please press enter to return to main menu: ");
		Utils.readString();
		
	}
	
	public boolean verifyAvailability(String time, List<Appointment> apps) {
		for(Appointment app: apps) {
			if(app.getTiming().equals(time) && app.isCancelled()==false) {
				return false;
			}
		}
		return true;
	}

	public boolean verifyPatient(int id) {
		return patients.containsKey(id);
	}
	
	public List<Appointment> getAppointmentsByPatientID(int id){
		return appointmentsByPatient.get(id).stream().filter(app -> app.isCancelled()==false).collect(Collectors.toList());
	}
	
	public void cancelAppointment(int patientID, int appointmentID) {
		if(!appointmentsByPatient.containsKey(patientID)) {
			System.out.println("No appointments available for patient");
			return;
		}
			
		List<Appointment> appointments = getAppointmentsByPatientID(patientID);
		for(Appointment app: appointments) {
			if(app.getAppointmentID() == appointmentID) {
				if(app.isAttended()) {
					System.out.println("Cannot cancel the Attended Appointment");
					System.out.println("Please press enter to return to main menu");
					Utils.readString();
					return;
				}
					
				cancelAppointment(app);
				System.out.println("Your Appointment has been cancelled successfully.");
				System.out.println("Please press enter to return to main menu.");
				Utils.readString();
				return;
			}
		}
		
		System.out.println("Appointment with id: "+appointmentID+" is not found for the selected patient");
	}
	
	public synchronized void cancelAppointment(Appointment app) {
		app.setCancelled(true);
	}
	
	private Map<String, List<VisitorAppointment>> visitorAppointments;
	
	public void bookVisitorAppointment(String visitorName, String date) {
		boolean isValid =false;
		try {
			isValid = Utils.validateDate(date);
		}catch(Exception e) {
			System.out.println("Date entered is not valid. Please try again and enter valid date");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		if(!isValid)
			return;
		
		int day = Utils.getDayFromDate(date);
		List<Physician> phys = new ArrayList<>();
		List<VisitorAppointment> availableAppointments = new ArrayList<>();
		for(Physician phy: physicians.values()) {
			if(phy.getConsultationHours().containsKey(day)) {
				availableAppointments.addAll(getVisitorAppointments(phy, visitorName, date, day));
			}
		}
		
		if(availableAppointments.size()==0) {
			System.out.println("Appointments for the selected date are not avialable. Please try with some other date");
			return;
		}
		System.out.println("s.no.\tPhysician Name\ttime");
		for(int i=0; i<availableAppointments.size(); i++) {
			VisitorAppointment app = availableAppointments.get(i);
			System.out.println(i+1+"\t"+app.getPhysicianName()+"\t"+app.getTime());
		}
		System.out.print("Please select your preferred appointment: ");
		int option = Utils.readInteger();
		VisitorAppointment appointment = availableAppointments.get(option-1);
		if(visitorAppointments.containsKey(date)) {
			visitorAppointments.get(date).add(appointment);
		}
		else {
			visitorAppointments.put(date, new ArrayList<>());
			visitorAppointments.get(date).add(appointment);
		}
		System.out.println("Appointment booked successfully");
		System.out.println("Please press enter return to main menu: ");
		Utils.readString();
	}
	
	public List<VisitorAppointment> getVisitorAppointments(Physician physician, String visitorName, String date, int day) {
		List<VisitorAppointment> appointments = new ArrayList<>();
		for(String time: physician.getConsultationHours().get(day)) {
			if(visitorAppointments.get(date)==null) {
				VisitorAppointment appointment = new VisitorAppointment();
				appointment.setPhysicianID(physician.getId());
				appointment.setDate(date);
				appointment.setTime(time);
				appointment.setVisitorName(visitorName);
				appointment.setPhysicianName(physician.getphysioFullName());
				appointments.add(appointment);
			}else {
				List<VisitorAppointment> apps = visitorAppointments.get(date);
				boolean isBooked = false;
				for(VisitorAppointment app: apps) {
					if(app.time.equals(time)) {
						isBooked = true;
						break;
					}
				}
				if(!isBooked) {
					VisitorAppointment appointment = new VisitorAppointment();
					appointment.setPhysicianID(physician.getId());
					appointment.setDate(date);
					appointment.setTime(time);
					appointment.setVisitorName(visitorName);
					appointment.setPhysicianName(physician.getphysioFullName());
					appointments.add(appointment);
				}
					
			}
		}
		
		return appointments;
	}
	
	public void markAppointment(int patientID, int appointmentID) {
		if(!appointmentsByPatient.containsKey(patientID)) {
			System.out.println("No Appointments are booked by this patient.");
			return;
		}
		List<Appointment> apps = getAppointmentsByPatientID(patientID);
		for(Appointment app: apps) {
			if(app.getAppointmentID()==appointmentID) {
				if(app.isCancelled()) {
					System.out.println("This appointment has been cancelled. Cannot attend a cancelled appoointment");
					return ;
				}
				app.setAttended(true);
				System.out.println("Appointment has been marked active");
				return ;
			}
		}
		System.out.println("Appointment with ID "+appointmentID+" is not found for patient");
		
	}
	
	public void generateReport() {
		generateAppointmentReport();
		generateVisitorAppointmentReport();
	}
	
	public void generateAppointmentReport() {
		List<String> headers = new ArrayList<>();
		headers.add("Physician Name");
		headers.add("Treatment Name");
		headers.add("Patient Name");
		headers.add("Date");
		headers.add("Time");
		headers.add("Room");
		List<List<String>> rows = new ArrayList<>();
		
		for(Map<Integer, List<Appointment>> apps: appointments.values()) {
			for(int i: apps.keySet()) {
				for(Appointment app: apps.get(i)) {
					List<String> row = new ArrayList<>();
					row.add(app.getPhysicianName());
					row.add(app.getTreatment());
					row.add(app.getPatientName());
					row.add(app.getAppointmentDate());
					row.add(app.getTiming());
					row.add(app.getRoom());
					rows.add(row);
				}
			}
		}
		System.out.println("Patient Appointments: ");
		if(rows.size()==0) {
			System.out.println("No Patient Appointments are Booked");
			return;
		}
		PrintTable print = new PrintTable();
		print.print(rows, headers);
		
	}
	
	public void generateVisitorAppointmentReport() {
		List<String> headers = new ArrayList<>();
		headers.add("Physician Name");
		headers.add("Visitor Name");
		headers.add("Date");
		headers.add("Time");
		List<List<String>> rows = new ArrayList<>();
		
		for(List<VisitorAppointment> apps: visitorAppointments.values()) {
			for(VisitorAppointment app: apps) {
				List<String> row = new ArrayList<>();
				row.add(app.getPhysicianName());
				row.add(app.getVisitorName());
				row.add(app.getDate());
				row.add(app.getTime());
				rows.add(row);
			}
			
		}
		System.out.println("Visitor Appointments: ");
		if(rows.size()==0) {
			System.out.println("No Visitor Appointments are booked");
			return;
		}
		PrintTable print = new PrintTable();
		print.print(rows, headers);
	
	}
	
	public void generatePatientsReport() {
		List<String> headers = new ArrayList<>();
		headers.add("Patient ID");
		headers.add("Patient Name");
		headers.add("Appointment ID");
		headers.add("Is Attended");
		headers.add("Is Cancelled");
		headers.add("Is Missed");
		List<List<String>> rows = new ArrayList<>();
		for(List<Appointment> apps: appointmentsByPatient.values()) {
			for(Appointment app: apps) {
				List<String> row = new ArrayList<>();
				row.add(""+app.getPatientID());
				row.add(app.getPatientName());
				row.add(""+app.getAppointmentID());
				if(app.isAttended())
					row.add("Yes");
				else
					row.add("No");
				if(app.isCancelled())
					row.add("Yes");
				else
					row.add("No");
				if(app.isCancelled())
					row.add("No");
				else if(!app.isAttended())
					row.add("Yes");
				else
					row.add("No");
				
				rows.add(row);
				
			}
			
		}
		
		System.out.println("Appointments report per patient:");
		if(rows.size()==0) {
			System.out.println("No Appointments Booked");
			return;
		}
		PrintTable table = new PrintTable();
		table.print(rows, headers);
		
	}
	
	
}
