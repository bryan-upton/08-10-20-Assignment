package com.brjann.nutritionApp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brjann.nutritionApp.entity.Intake;
import com.brjann.nutritionApp.repository.IntakeRepository;

@Service
public class IntakeService {
	
	private static final Logger Logger = LogManager.getLogger(IntakeService.class);
	
	@Autowired
	private IntakeRepository repo;
	
	public Iterable<Intake> getIntakes() {
		return repo.findAll();
	}
	
	public Intake createIntake(Intake intake) {
		return repo.save(intake);
	}
	
	public Intake updateIntake(Intake intake, Long id) throws Exception {
		try {
			Intake oldIntake = repo.findOne(id);
			oldIntake.setIntake(intake.getIntake());
			oldIntake.setDescription(intake.getDescription());
			oldIntake.setCalories(intake.getCalories());
			return repo.save(oldIntake);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to update intake: " + id, e);
			throw new Exception("Unable to update intake.");
		}
	}
	
	public void removeIntake(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to delete intake: " + id, e);
			throw new Exception("Unable to delete intake.");
		}
	}

}