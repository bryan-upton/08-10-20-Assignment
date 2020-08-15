package com.brjann.nutritionApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.brjann.nutritionApp.entity.ExerciseEntry;

public interface ExerciseEntryRepository extends CrudRepository<ExerciseEntry, Long> {}
