package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Charges")
public class Charges {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int classLevel;
	private float flatRate; // cost per singular class
	private float twoClassRate; // cost for enrolled in two classes
	private float multiClassRate; // cost for 3+ classes
	private double siblingDiscount; // discount for siblings

	public Charges(int classLevel, float flatRate, float twoClassRate,
			float multiClassRate, double siblingDiscount) {
		super();
		this.classLevel = classLevel;
		this.flatRate = flatRate;
		this.twoClassRate = twoClassRate;
		this.multiClassRate = multiClassRate;
		this.siblingDiscount = siblingDiscount;
	}

	// getters + setters
	public int getClassLevel() {
		return classLevel;
	}

	public void setClassLevel(int classLevel) {
		this.classLevel = classLevel;
	}

	public float getFlatRate() {
		return flatRate;
	}

	public void setFlatRate(float flatRate) {
		this.flatRate = flatRate;
	}

	public float getTwoClassRate() {
		return twoClassRate;
	}

	public void setTwoClassRate(float twoClassRate) {
		this.twoClassRate = twoClassRate;
	}

	public float getMultiClassRate() {
		return multiClassRate;
	}

	public void setMultiClassRate(float multiClassRate) {
		this.multiClassRate = multiClassRate;
	}

	public double getSiblingDiscount() {
		return siblingDiscount;
	}

	public void setSiblingDiscount(double siblingDiscount) {
		this.siblingDiscount = siblingDiscount;
	}

	public Charges() {
		// default constructor
	}

	@Override
	public String toString() {
		return "Charges [classLevel = " + classLevel + ", flatRate = " + flatRate + ", twoClassRate = " + twoClassRate
				+ ", multiClassRate = " + multiClassRate + ", siblingDiscount = " + siblingDiscount + "]";
	}
}