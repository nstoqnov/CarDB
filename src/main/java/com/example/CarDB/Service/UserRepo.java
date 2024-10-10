package com.example.CarDB.Service;

import com.example.CarDB.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
}
