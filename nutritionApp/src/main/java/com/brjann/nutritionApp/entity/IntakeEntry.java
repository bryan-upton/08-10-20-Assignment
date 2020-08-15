package com.brjann.nutritionApp.entity;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.brjann.nutritionApp.util.IntakeEntryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class IntakeEntry {
	
	private Long id;
	private LocalDate entered;
	private double caloriesResult;
	private IntakeEntryStatus intakeStatus;
	private Set<Intake> intakes;
	
	@JsonIgnore
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public LocalDate getEntered() {
		return entered;
	}
	
	public void setEntered(LocalDate entered) {
		this.entered = entered;
	}
	
	public double getCaloriesResult() {
		return caloriesResult;
	}
	
	public void setCaloriesResult(double caloriesResult) {
		this.caloriesResult = caloriesResult;
	}
	
	@ManyToMany(mappedBy = "intakeEntries")
	public Set<Intake> getIntakes() {
		return intakes;
	}
	
	public void setIntakes(Set<Intake> intakes) {
		this.intakes = intakes;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public IntakeEntryStatus getIntakeStatus() {
		return intakeStatus;
	}

	public void setIntakeStatus(IntakeEntryStatus intakeStatus) {
		this.intakeStatus = intakeStatus;
	}



}