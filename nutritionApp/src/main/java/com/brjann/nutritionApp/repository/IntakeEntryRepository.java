package com.brjann.nutritionApp.repository;

import org.springframework.data.repository.CrudRepository;

import com.brjann.nutritionApp.entity.IntakeEntry;

public interface IntakeEntryRepository extends CrudRepository<IntakeEntry, Long> {}
