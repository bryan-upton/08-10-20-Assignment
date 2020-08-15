package com.brjann.nutritionApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.brjann.nutritionApp.entity.Exercise;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {}