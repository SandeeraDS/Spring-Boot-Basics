package com.ds.springbootdeep.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

//    http://localhost:8080/users/search/findByRole?role=Admin -  this is the way of calling this method
    List<User> findByRole(@Param("role") String role);
}