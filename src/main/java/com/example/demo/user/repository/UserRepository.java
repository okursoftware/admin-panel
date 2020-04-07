package com.example.demo.user.repository;


import com.example.demo.user.model.UserDatabaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface UserRepository extends JpaRepository<UserDatabaseModel, Long> {

    boolean existsByEmail( @Param("email") String email );

    Optional<UserDatabaseModel> findByEmail( @Param("email") String email );

    Optional<UserDatabaseModel> findByVerificationtoken( UUID verificationtoken );


}
