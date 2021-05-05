package com.psic.pojo;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Physician {
	
	private int id;
	private String physioFullName;
	private String physioAddress;
	private String physioNumber;
	private List<String> expertisedOn;
	private List<String> treatmentsOffered;
	private Map<String, List<String>> expToTreatments;
	private Map<Integer, List<String>> timings;
	private Map<Integer, List<String>> consultationHours;
	
	public Physician() {
		
	}
	
	
	
	public Physician(int id, String physioFullName, String physioAddress, String physioNumber) {
		this.id = id;
		this.physioFullName = physioFullName;
		this.physioAddress = physioAddress;
		this.physioNumber = physioNumber;
		expertisedOn = new ArrayList<>();
		treatmentsOffered = new ArrayList<>();
	}

	
	public Map<String, List<String>> getExpToTreatments() {
		return expToTreatments;
	}

	public void setExpToTreatments(Map<String, List<String>> expToTreatments) {
		this.expToTreatments = expToTreatments;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getphysioFullName() {
		return physioFullName;
	}
	public void setphysioFullName(String physioFullName) {
		this.physioFullName = physioFullName;
	}
	public String getphysioAddress() {
		return physioAddress;
	}
	public void setphysioAddress(String physioAddress) {
		this.physioAddress = physioAddress;
	}
	public String getphysioNumber() {
		return physioNumber;
	}
	public void setphysioNumber(String physioNumber) {
		this.physioNumber = physioNumber;
	}
	public List<String> getExpertisedOn() {
		return expertisedOn;
	}
	public void setExpertisedOn(List<String> expertisedOn) {
		this.expertisedOn = expertisedOn;
	}
	public List<String> getTreatmentsOffered() {
		return treatmentsOffered;
	}
	public void setTreatmentsOffered(List<String> treatmentsOffered) {
		this.treatmentsOffered = treatmentsOffered;
	}
	public Map<Integer, List<String>> getTimings() {
		return timings;
	}
	public void setTimings(Map<Integer, List<String>> timings) {
		this.timings = timings;
	}
	public Map<Integer, List<String>> getConsultationHours() {
		return consultationHours;
	}
	public void setConsultationHours(Map<Integer, List<String>> consultationHours) {
		this.consultationHours = consultationHours;
	}
	
	public void addExpertise(String exper) {
		this.expertisedOn.add(exper);
	}
	
	public void addTreatment(String treatment) {
		this.treatmentsOffered.add(treatment);
	}
	

}
