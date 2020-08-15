package com.brjann.nutritionApp.controller;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.brjann.nutritionApp.entity.User;
import com.brjann.nutritionApp.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static String UPLOADED_FOLDER = "./pictures/";	
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getUser(@PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.getUserById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND); 
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Object> getUsers() {
		return new ResponseEntity<Object>(service.getUser(), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		return new ResponseEntity<Object>(service.createUser(user), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> updateUser(@RequestBody User user, @PathVariable Long id) {
		try {
			return new ResponseEntity<Object>(service.updateUser(user, id), HttpStatus.OK); 
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
		try {
			service.deleteUser(id);
			return new ResponseEntity<Object>("Successfully deleted user with id: " + id, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value="/{id}/profilePicture", method = RequestMethod.POST)
	public ResponseEntity<Object> singleFileUpload(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
		if (file.isEmpty()) {
			return new ResponseEntity<Object>("Please upload a file.", HttpStatus.BAD_REQUEST);
		}
		
		try {
			String url = UPLOADED_FOLDER + file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(url);
			Files.write(path, bytes);
			return new ResponseEntity<Object>(service.updateProfilePicture(id, url), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}