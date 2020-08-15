package com.brjann.nutritionApp.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brjann.nutritionApp.entity.Intake;
import com.brjann.nutritionApp.entity.IntakeEntry;
import com.brjann.nutritionApp.entity.User;
import com.brjann.nutritionApp.repository.IntakeEntryRepository;
import com.brjann.nutritionApp.repository.IntakeRepository;
import com.brjann.nutritionApp.repository.UserRepository;
import com.brjann.nutritionApp.util.IntakeEntryStatus;

@Service
public class IntakeEntryService {
	
	private static final Logger Logger = LogManager.getLogger(IntakeEntryService.class);
//	private final int DELIVERY_DAYS = 7;
	
	@Autowired
	private IntakeEntryRepository intakeEntryRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private IntakeRepository intakeRepo;
	
	public IntakeEntry submitNewIntakeEntry(Set<Long> intakeIds, Long userId) throws Exception {
		try {
			User user = userRepo.findOne(userId);
			IntakeEntry intakeEntry = initializeNewIntakeEntry(intakeIds, user);
			return intakeEntryRepo.save(intakeEntry);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to create new intake entry for user: " + userId, e);
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
	
	private IntakeEntry initializeNewIntakeEntry(Set<Long> intakeIds, User user) {
		IntakeEntry intakeEntry = new IntakeEntry();
		intakeEntry.setIntakes(convertToIntakeSet(intakeRepo.findAll(intakeIds)));
		intakeEntry.setEntered(LocalDate.now());
//		exerciseEntry.setEstimatedDelivery(LocalDate.now().plusDays(DELIVERY_DAYS));
		intakeEntry.setUser(user);
		intakeEntry.setCaloriesResult(calculateIntakeEntryTotal(intakeEntry.getIntakes()));
		intakeEntry.setIntakeStatus(IntakeEntryStatus.ENTERED);
		addIntakeEntryToIntakes(intakeEntry);
		return intakeEntry;
	}
	
	private void addIntakeEntryToIntakes(IntakeEntry intakeEntry) {
		Set<Intake> intakes = intakeEntry.getIntakes();
		for (Intake intake : intakes) {
			intake.getIntakeEntries().add(intakeEntry);
		}
	}
	
	private Set<Intake> convertToIntakeSet(Iterable<Intake> iterable) {
		Set<Intake> set = new HashSet<Intake>();
		for (Intake intake : iterable) {
			set.add(intake);
		}
		return set;
	}

	private double calculateIntakeEntryTotal(Set<Intake> intakes) {
		double total = 0;
		for (Intake intake : intakes) {
			total += intake.getCalories();
		}
		return total;
	}

}