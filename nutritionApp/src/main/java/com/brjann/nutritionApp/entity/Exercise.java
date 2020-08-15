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

import com.brjann.nutritionApp.entity.ExerciseEntry;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exercise {
	
	private Long id;
	private String exercise;
	private String description;
	private double burn;
	
	@JsonIgnore
	private Set<ExerciseEntry> exerciseEntries;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getExercise() {
		return exercise;
	}
	
	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getBurn() {
		return burn;
	}
	
	public void setBurn(double burn) {
		this.burn = burn;
	}
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "exercise_exerciseEntry",
			joinColumns = @JoinColumn(name = "exerciseEntryId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "exerciseId", referencedColumnName = "id"))
	public Set<ExerciseEntry> getExerciseEntries() {
		return exerciseEntries;
	}
	
	public void setExerciseEntries(Set<ExerciseEntry> exerciseEntries) {
		this.exerciseEntries = exerciseEntries;
	}

}
