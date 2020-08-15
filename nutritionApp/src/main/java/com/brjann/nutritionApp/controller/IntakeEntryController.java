package com.brjann.nutritionApp.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brjann.nutritionApp.service.IntakeEntryService;

@RestController
@RequestMapping("users/{id}/intakeEntries")
public class IntakeEntryController {
        
        @Autowired
        private IntakeEntryService service;
        
        @RequestMapping(method=RequestMethod.POST)
        public ResponseEntity<Object> createIntakeEntry(@RequestBody Set<Long> intakeIds, @PathVariable Long id) {
                try {
                        return new ResponseEntity<Object>(service.submitNewIntakeEntry(intakeIds, id), HttpStatus.CREATED);
                } catch (Exception e) {
                        return new ResponseEntity<Object>(e, HttpStatus.BAD_REQUEST);
                }
        }
        
//        @RequestMapping(value="/{entryId}", method=RequestMethod.PUT)
//        public ResponseEntity<Object> updateExerciseEntry(@RequestBody ExerciseEntry exerciseEntry, @PathVariable Long exerciseEntryId) {
//                try {
//                        if (exerciseEntry.getExerciseStatus().equals(ExerciseEntryStatus.CANCELED)) {
//                                return new ResponseEntity<Object>(service.cancelExerciseEntry(exerciseEntryId), HttpStatus.OK);
//                        } else if (exerciseEntry.getExerciseStatus().equals(ExerciseEntryStatus.DELIVERED)) {
//                                return new ResponseEntity<Object>(service.completeExerciseEntry(exerciseEntryId), HttpStatus.OK);
//                        }
//                        return new ResponseEntity<Object>("Invalid update request.", HttpStatus.BAD_REQUEST);
//                } catch (Exception e) {
//                        return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
//                }
//        }

}
