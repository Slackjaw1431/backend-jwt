package com.youtube.jwt.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.youtube.jwt.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	User findByUserName(String userName);

}
