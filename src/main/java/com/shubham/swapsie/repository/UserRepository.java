package com.shubham.swapsie.repository;

import com.shubham.swapsie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmailAndPassword(String email, String password);
}
