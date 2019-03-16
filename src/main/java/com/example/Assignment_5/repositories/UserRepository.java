package com.example.Assignment_5.repositories;

import com.example.Assignment_5.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
