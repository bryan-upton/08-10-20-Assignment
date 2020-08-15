package com.brjann.nutritionApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.brjann.nutritionApp.entity.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {}
