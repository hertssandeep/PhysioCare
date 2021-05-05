package com.psic.pojo;
public class Patient {
	
	private int id;
	private String patientFullName;
	private String patientAddress;
	private String patientNumber;
		
	public Patient(int id, String patientFullName, String patientAddress, String patientNumber) {
		super();
		this.id = id;
		this.patientFullName = patientFullName;
		this.patientAddress = patientAddress;
		this.patientNumber = patientNumber;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getpatientFullName() {
		return patientFullName;
	}
	public void setpatientFullName(String patientFullName) {
		this.patientFullName = patientFullName;
	}
	public String getpatientAddress() {
		return patientAddress;
	}
	public void setpatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getpatientNumber() {
		return patientNumber;
	}
	public void setpatientNumber(String patientNumber) {
		this.patientNumber = patientNumber;
	}
}
