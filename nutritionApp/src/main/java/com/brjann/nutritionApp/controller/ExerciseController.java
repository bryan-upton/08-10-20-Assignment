package com.brjann.nutritionApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brjann.nutritionApp.entity.Exercise;
import com.brjann.nutritionApp.service.ExerciseService;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
	
	@Autowired
	private ExerciseService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getExercises() {
		return new ResponseEntity<Object>(service.getExercises(), HttpStatus.OK); 
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createExercise(@RequestBody Exercise exercise) {
		return new ResponseEntity<Object>(service.createExercise(exercise), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateExercise(@RequestBody Exercise exercise, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateExercise(exercise, id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to update exercise.", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteExercise(@PathVariable Long id) {
		try {
			service.removeExercise(id);
			return new ResponseEntity<Object>("Successfully deleted exercise with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>("Unable to delete exercise.", HttpStatus.BAD_REQUEST);
		}

	}
}
