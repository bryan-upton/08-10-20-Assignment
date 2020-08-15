package com.brjann.nutritionApp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.brjann.nutritionApp.entity.IntakeEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Intake {
	
	private Long id;
	private String intake;
	private String description;
	private double calories;
	
	@JsonIgnore
	private Set<IntakeEntry> intakeEntries;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getIntake() {
		return intake;
	}
	
	public void setIntake(String intake) {
		this.intake = intake;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getCalories() {
		return calories;
	}
	
	public void setCalories(double calories) {
		this.calories = calories;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "intake_intakeEntry",
			joinColumns = @JoinColumn(name = "intakeEntryId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "intakeId", referencedColumnName = "id"))
	public Set<IntakeEntry> getIntakeEntries() {
		return intakeEntries;
	}
	
	public void setIntakeEntries(Set<IntakeEntry> intakeEntries) {
		this.intakeEntries = intakeEntries;
	}
	
}
