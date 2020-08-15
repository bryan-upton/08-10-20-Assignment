package com.brjann.nutritionApp.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brjann.nutritionApp.entity.User;
import com.brjann.nutritionApp.repository.UserRepository;
import com.brjann.nutritionApp.service.UserService;

@Service
public class UserService {
	
	private static final Logger Logger = LogManager.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repo;
	
	public User getUserById(Long id) throws Exception {
		try {
			return repo.findOne(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to retrieve user: " + id, e);
			throw e;
		}
	}
	
	public Iterable<User> getUser() {
		return repo.findAll();
	}
	
	public User createUser(User user) {
		return repo.save(user);
	}
	
	public User updateUser(User user, Long id) throws Exception {
		try {
			User oldUser = repo.findOne(id);
			oldUser.setUserName(user.getUserName());
			oldUser.setUserProfile(user.getUserProfile());
			oldUser.setProfilePictureUrl(user.getProfilePictureUrl());
			return repo.save(oldUser);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to update user: " + id, e);
			throw new Exception("Unable to update user.");
		}
	}
	
	public void deleteUser(Long id) throws Exception {
		try {
			repo.delete(id);
		} catch (Exception e) {
			Logger.error("Exception occurred while trying to delete user: " + id, e);
			throw new Exception("Unable to delete user.");
		}
	}
	
	public User updateProfilePicture(Long userId, String url) throws Exception {
		User user = repo.findOne(userId);
		if (user == null) {
			throw new Exception("User does not exist.");
		}
		user.setProfilePictureUrl(url);
		return repo.save(user);
	}

}
