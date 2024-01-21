package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parent")
public class Parent {

	@Id
	private Integer parentID;
	private String parent1Name;
	private String parent1PhoneNum;
	private String parent2Name;
	private String parent2PhoneNum;
	private String email;
	private String primaryAddress;
	private String eContName;
	private String eContNum;
	private String password;
	private String role;
	private Float balance;

	// one parentID can have multiple transactions
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Transactions> transactions;

	public Parent(Integer parentID, String parent1Name, String parent1PhoneNum,
			String parent2Name, String parent2PhoneNum, String email,
			String primaryAddress, String eContName, String eContNum, Float balance) {
		super();
		this.parentID = parentID;
		this.parent1Name = parent1Name;
		this.parent1PhoneNum = parent1PhoneNum;
		this.parent2Name = parent2Name;
		this.parent2PhoneNum = parent2PhoneNum;
		this.email = email;
		this.primaryAddress = primaryAddress;
		this.eContName = eContName;
		this.eContNum = eContNum;
		this.balance = balance;
	}

	//getters + setters
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

	public String getParent1Name() {
		return parent1Name;
	}

	public void setParent1Name(String parent1Name) {
		this.parent1Name = parent1Name;
	}

	public String getParent1PhoneNum() {
		return parent1PhoneNum;
	}

	public void setParent1PhoneNum(String parent1PhoneNum) {
		this.parent1PhoneNum = parent1PhoneNum;
	}

	public String getParent2Name() {
		return parent2Name;
	}

	public void setParent2Name(String parent2Name) {
		this.parent2Name = parent2Name;
	}

	public String getParent2PhoneNum() {
		return parent2PhoneNum;
	}

	public void setParent2PhoneNum(String parent2PhoneNum) {
		this.parent2PhoneNum = parent2PhoneNum;
	}

	public String getPrimaryEmail() {
		return email;
	}

	public void setPrimaryEmail(String email) {
		this.email = email;
	}

	public String getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(String primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public String geteContName() {
		return eContName;
	}

	public void seteContName(String eContName) {
		this.eContName = eContName;
	}

	public String geteContNum() {
		return eContNum;
	}

	public void seteContNum(String eContNum) {
		this.eContNum = eContNum;
	}

	public Parent() {
		// default constructor
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Parent [parentID = " + parentID + ", parent1Name = " + parent1Name + ", parent1Num = " + parent1PhoneNum
				+ ", parent2Name = " + parent2Name + ", parent2Num = " + parent2PhoneNum + ", primaryEmail = " + email
				+ ", primaryAddress = " + primaryAddress + ", eContName = " + eContName + ", eContNum = " + eContNum + "]";
	}
}