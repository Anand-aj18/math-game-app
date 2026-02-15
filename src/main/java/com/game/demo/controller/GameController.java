package com.game.demo.controller;

import com.game.demo.entity.Question;
import com.game.demo.entity.Score;
import com.game.demo.repository.QuestionRepo;
import com.game.demo.repository.ScoreRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final QuestionRepo repo;
    private final ScoreRepo scoreRepo;

    public GameController(
            QuestionRepo repo,
            ScoreRepo scoreRepo){

        this.repo = repo;
        this.scoreRepo = scoreRepo;
    }

    @GetMapping("/questions")
    public List<Question> getQuestions(@RequestParam int c){
        return repo.findByClassLevelOrderByLevelAsc(c);
    }


    @PostMapping("/score")
    public void saveScore(
            @RequestParam String user,
            @RequestParam int pts,
            @RequestParam int cls,
            @RequestParam int age,
            @RequestParam String gender){

        Score s = new Score();

        s.setUsername(user);
        s.setPoints(pts);
        s.setClassLevel(cls);
        s.setAge(age);
        s.setGender(gender);

        scoreRepo.save(s);
    }

    @GetMapping("/leaderboard")
    public List<Score> leaderboard(@RequestParam int cls){

        return scoreRepo
                .findTop10ByClassLevelOrderByPointsDesc(cls);
    }

}
