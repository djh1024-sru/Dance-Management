package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transactions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionID;
	private Float amount;
	private String paymentType;
	private String checkNumber;
	private LocalDateTime transactionTime;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Parent parent;

	public Transactions(Integer transactionID, String paymentType,
			Float amount, String checkNumber,
			LocalDateTime transactionTime, Parent parent) {
		super();
		this.transactionID = transactionID;
		this.amount = amount;
		this.paymentType = paymentType;
		this.checkNumber = checkNumber;
		this.transactionTime = transactionTime;
		this.parent = parent;
	}

	//getters + setters
	public Integer getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(Integer transactionID) {
		this.transactionID = transactionID;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(LocalDateTime transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}
	
	public Transactions() {
		// default constructor
	}
}