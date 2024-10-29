package com.example.CarDB.Service;

import com.example.CarDB.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.OptionalInt;

public interface UserRepo extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    Optional<Object> findByEmail(String username);
}
