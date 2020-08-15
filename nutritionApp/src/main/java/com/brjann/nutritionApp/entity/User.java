package com.brjann.nutritionApp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.brjann.nutritionApp.entity.UserProfile;
import com.brjann.nutritionApp.entity.ExerciseEntry;
import com.brjann.nutritionApp.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	
	private Long id;
	private String userName;
	private UserProfile userProfile;
	private Set<ExerciseEntry> exerciseEntries;
	private Set<IntakeEntry> intakeEntries;
	private String profilePictureUrl;
	
	@JsonIgnore
	private Set<User> following;
	
	private String password;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String username) {
		this.userName = username;
	}
	
	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	public UserProfile getUserProfile() {
		return userProfile;
	}
	
	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<ExerciseEntry> getExerciseEntries() {
		return exerciseEntries;
	}
	
	public void setExerciseEntries(Set<ExerciseEntry> exerciseEntries) {
		this.exerciseEntries = exerciseEntries;
	}
	
	@OneToMany(mappedBy = "user")
	public Set<IntakeEntry> getIntakeEntries() {
		return intakeEntries;
	}
		
	public void setIntakeEntries(Set<IntakeEntry> intakeEntries) {
		this.intakeEntries = intakeEntries;
	}
	
	public String getProfilePictureUrl() {
		return profilePictureUrl;
	}

	public void setProfilePictureUrl(String profilePictureUrl) {
		this.profilePictureUrl = profilePictureUrl;
	}



}
