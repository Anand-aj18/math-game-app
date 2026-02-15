package com.game.demo.repository;

import com.game.demo.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Long> {

    List<Question> findByClassLevelAndLevel(int classLevel, int level);
    List<Question> findByClassLevelOrderByLevelAsc(int classLevel);

}