package com.bootcamp.topic6.apptopic6.repository;

import com.bootcamp.topic6.apptopic6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
