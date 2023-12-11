package com.example.sbpro.repository;

import com.example.sbpro.repository.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "select * from account where email = ?1", nativeQuery = true)
    Account findByEmail(String email);
}
