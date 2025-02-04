package com.registration.repository;

import com.registration.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    @Transactional
    @Modifying
    @Query("delete from Registration r")
    int deleteFirstBy();


    Optional<Registration> findByName(String name);
}