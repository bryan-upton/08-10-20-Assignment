package com.brjann.nutritionApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brjann.nutritionApp.entity.Intake;
import com.brjann.nutritionApp.service.IntakeService;

@RestController
@RequestMapping("/intakes")
public class IntakeController {
	
	@Autowired
	private IntakeService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getIntakes() {
		return new ResponseEntity<Object>(service.getIntakes(), HttpStatus.OK); 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createIntake(@RequestBody Intake intake) {
		return new ResponseEntity<Object>(service.createIntake(intake), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateIntake(@RequestBody Intake intake, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateIntake(intake, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update intake.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteIntake(@PathVariable Long id) {
		try {
			service.removeIntake(id);
			return new ResponseEntity<Object>("Successfully deleted intake with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete intake.", HttpStatus.BAD_REQUEST);
		}

	}
}
