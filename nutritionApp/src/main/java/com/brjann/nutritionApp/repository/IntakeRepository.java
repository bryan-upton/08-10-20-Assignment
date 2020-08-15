package com.brjann.nutritionApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.brjann.nutritionApp.entity.Intake;

public interface IntakeRepository extends CrudRepository<Intake, Long> {}