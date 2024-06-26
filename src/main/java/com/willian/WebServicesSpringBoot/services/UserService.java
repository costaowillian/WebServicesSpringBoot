package com.willian.WebServicesSpringBoot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.willian.WebServicesSpringBoot.entities.User;
import com.willian.WebServicesSpringBoot.repositories.UserRepository;
import com.willian.WebServicesSpringBoot.services.exceptions.DatabaseException;
import com.willian.WebServicesSpringBoot.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj =  repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			User entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
			
			repository.delete(entity);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		try {
			User entity = repository.getReferenceById(id);
			updateData(entity, obj);
			return repository.save(entity); 
		}
		catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);		
		}
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setPhone(obj.getPhone());
		entity.setEmail(obj.getEmail());		
	}
}
