package com.example.sbpro.repository;

import com.example.sbpro.repository.entity.Game.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
@Component
public interface GameRepository extends JpaRepository<GameRecord, Integer> {


}
