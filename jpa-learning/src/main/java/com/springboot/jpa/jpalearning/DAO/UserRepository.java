package com.springboot.jpa.jpalearning.DAO;

import com.springboot.jpa.jpalearning.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}