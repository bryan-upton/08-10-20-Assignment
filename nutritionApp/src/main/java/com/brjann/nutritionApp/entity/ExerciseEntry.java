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

import com.brjann.nutritionApp.util.ExerciseEntryStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ExerciseEntry {
	
	private Long id;
	private LocalDate entered;
	private double burnResult;
	private ExerciseEntryStatus exerciseStatus;
	private Set<Exercise> exercises;
	
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
	
	public double getBurnResult() {
		return burnResult;
	}
	
	public void setBurnResult(double burnResult) {
		this.burnResult = burnResult;
	}
	
	@ManyToMany(mappedBy = "exerciseEntries")
	public Set<Exercise> getExercises() {
		return exercises;
	}
	
	public void setExercises(Set<Exercise> exercises) {
		this.exercises = exercises;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public ExerciseEntryStatus getExerciseStatus() {
		return exerciseStatus;
	}

	public void setExerciseStatus(ExerciseEntryStatus exerciseStatus) {
		this.exerciseStatus = exerciseStatus;
	}



}