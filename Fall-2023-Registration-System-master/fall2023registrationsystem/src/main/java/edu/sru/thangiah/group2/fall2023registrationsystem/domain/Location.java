package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Location")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studioID;
	private String locationName;
	private String address;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;

	public Location(int studioID, String locationName, String address,
			String address2, String city, String state,
			String zip, String phoneNum) {
		super();
		this.studioID = studioID;
		this.locationName = locationName;
		this.address = address;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNum = phoneNum;
	}

	public int getStudioID() {
		return studioID;
	}

	public void setStudioID(int studioID) {
		this.studioID = studioID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Location() {
		// default constructor
	}

	@Override
	public String toString() {
		return "Location [studioID = " + studioID + ", location = " + locationName + ", address = " + address
				+ ", address2 = " + address2 + ", city = " + city + ", state = " + state + ", zip = " + zip
				+ ", phoneNum = " + phoneNum + "]";
	}
}