package com.example.sbpro.repository;

import com.example.sbpro.repository.entity.User.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query(value = "select * from users where email = ?1 ", nativeQuery = true)
    Users findByEmail(String email);

    @Query("select case when count(c)> 0 then true else false end from users c where c.email = ?1")
    boolean existsByEmail(String email);
}
