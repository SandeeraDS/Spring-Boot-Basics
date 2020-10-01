package com.ds.springbootdeep.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findByRole(String admin);
}