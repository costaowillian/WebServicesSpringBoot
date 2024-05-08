package com.willian.WebServicesSpringBoot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.willian.WebServicesSpringBoot.entities.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long>{

}
