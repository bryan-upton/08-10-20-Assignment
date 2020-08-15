package com.brjann.nutritionApp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brjann.nutritionApp.entity.Exercise;
import com.brjann.nutritionApp.repository.ExerciseRepository;
import com.brjann.nutritionApp.service.ExerciseService;

@Service
public class ExerciseService {
	
	private static final Logger Logger = LogManager.getLogger(ExerciseService.class);
	
	@Autowired
	private ExerciseRepository repo;
	
	public Iterable<Exercise> getExercises() {
		return repo.findAll();
	}
	
	public Exercise createExercise(Exercise exercise) {
		return repo.save(exercise);
	}
	
	public Exercise updateExercise(Exercise exercise, Long id) throws Exception {
		try {
			Exercise oldExercise = repo.findOne(id);
			oldExercise.setExercise(exercise.getExercise());
			oldExercise.setDescription(exercise.getDescription());
			oldExercise.setBurn(exercise.getBurn());
			return repo.save(oldExercise);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to update exercise: " + id, e);
			throw new Exception("Unable to update exercise.");
		}
	}
	
	public void removeExercise(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to delete exercise: " + id, e);
			throw new Exception("Unable to delete exercise.");
		}
	}

}