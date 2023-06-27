package com.example.securityservice.repo;

import com.example.securityservice.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserCredentialsRepo extends JpaRepository<UserCredentials,Integer> {


    Optional<UserCredentials> findByName(String username);
}
