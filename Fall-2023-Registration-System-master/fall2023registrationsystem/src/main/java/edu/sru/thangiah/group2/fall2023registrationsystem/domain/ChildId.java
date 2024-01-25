package edu.sru.thangiah.group2.fall2023registrationsystem.domain;

import java.io.Serializable;

//creating composite keys of specific child information 

public class ChildId implements Serializable
{
	private int childID;
	private String activityID;
	private Integer activityLevel;
	
	
	public ChildId(int childID, String activityID, Integer activityLevel)
	{
		this.childID = childID; 
		this.activityID = activityID;
		this.activityLevel = activityLevel;
	}
	
	public ChildId()
	{
		
	}

	//getters
	public int getChildID() {
		return childID;
	}

	public String getActivityID() {
		return activityID;
	}

	public Integer getActivityLevel() {
		return activityLevel;
	}
	
}