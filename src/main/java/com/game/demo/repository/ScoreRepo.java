package com.game.demo.repository;

import com.game.demo.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score,Long> {

    List<Score> findTop10ByOrderByPointsDesc();

    List<Score> findTop10ByClassLevelOrderByPointsDesc(int classLevel);

    Score findTopByClassLevelOrderByPointsDesc(int classLevel);
}
