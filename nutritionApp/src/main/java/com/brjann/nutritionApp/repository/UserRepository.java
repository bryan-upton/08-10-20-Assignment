package com.brjann.nutritionApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.brjann.nutritionApp.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {}
