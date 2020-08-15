package com.brjann.nutritionApp.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brjann.nutritionApp.entity.ExerciseEntry;
import com.brjann.nutritionApp.entity.Exercise;
import com.brjann.nutritionApp.entity.User;
import com.brjann.nutritionApp.repository.ExerciseEntryRepository;
import com.brjann.nutritionApp.repository.ExerciseRepository;
import com.brjann.nutritionApp.repository.UserRepository;
import com.brjann.nutritionApp.util.ExerciseEntryStatus;

@Service
public class ExerciseEntryService {
	
	private static final Logger Logger = LogManager.getLogger(ExerciseEntryService.class);
//	private final int DELIVERY_DAYS = 7;
	
	@Autowired
	private ExerciseEntryRepository exerciseEntryRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ExerciseRepository exerciseRepo;
	
	public ExerciseEntry submitNewExerciseEntry(Set<Long> exerciseIds, Long userId) throws Exception {
		try {
			User user = userRepo.findOne(userId);
			ExerciseEntry exerciseEntry = initializeNewExerciseEntry(exerciseIds, user);
			return exerciseEntryRepo.save(exerciseEntry);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to create new exercise entry for user: " + userId, e);
			throw e;
		}
		
	}
	
//	public ExerciseEntry cancelExerciseEntry(Long exerciseEntryId) throws Exception {
//		try {
//			ExerciseEntry exerciseEntry = repo.findOne(exerciseEntryId);
//			exerciseEntry.setExerciseStatus(ExerciseEntryStatus.CANCELED);
//			return repo.save(exerciseEntry);
//		} catch (Exception e) {
//			Logger.error("Exception occurred while trying to cancel exercise entry: " + exerciseEntryId, e);
//			throw new Exception("Unable to update exercise entry.");
//		}
//	}
//	
//	public ExerciseEntry completeExerciseEntry(Long exerciseEntryId) throws Exception {
//		try {
//			ExerciseEntry exerciseEntry = repo.findOne(exerciseEntryId);
//			exerciseEntry.setExerciseStatus(ExerciseEntryStatus.DELIVERED);
//			return repo.save(exerciseEntry);
//		} catch (Exception e) {
//			Logger.error("Exception occurred while trying to complete exercise entry: " + exerciseEntryId, e);
//			throw new Exception("Unable to update exercise entry.");
//		}
//	}
	
	private ExerciseEntry initializeNewExerciseEntry(Set<Long> exerciseIds, User user) {
		ExerciseEntry exerciseEntry = new ExerciseEntry();
		exerciseEntry.setExercises(convertToExerciseSet(exerciseRepo.findAll(exerciseIds)));
		exerciseEntry.setEntered(LocalDate.now());
//		exerciseEntry.setEstimatedDelivery(LocalDate.now().plusDays(DELIVERY_DAYS));
		exerciseEntry.setUser(user);
		exerciseEntry.setBurnResult(calculateExerciseEntryTotal(exerciseEntry.getExercises()));
		exerciseEntry.setExerciseStatus(ExerciseEntryStatus.ENTERED);
		addExerciseEntryToExercises(exerciseEntry);
		return exerciseEntry;
	}
	
	private void addExerciseEntryToExercises(ExerciseEntry exerciseEntry) {
		Set<Exercise> exercises = exerciseEntry.getExercises();
		for (Exercise exercise : exercises) {
			exercise.getExerciseEntries().add(exerciseEntry);
		}
	}
	
	private Set<Exercise> convertToExerciseSet(Iterable<Exercise> iterable) {
		Set<Exercise> set = new HashSet<Exercise>();
		for (Exercise exercise : iterable) {
			set.add(exercise);
		}
		return set;
	}

	private double calculateExerciseEntryTotal(Set<Exercise> exercises) {
		double total = 0;
		for (Exercise exercise : exercises) {
			total += exercise.getBurn();
		}
		return total;
	}

}