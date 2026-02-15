package com.game.demo.controller;

import com.game.demo.entity.Progress;
import com.game.demo.entity.Question;
import com.game.demo.entity.Score;
import com.game.demo.repository.ProgressRepository;
import com.game.demo.repository.QuestionRepo;
import com.game.demo.repository.ScoreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private final QuestionRepo repo;
    private final ScoreRepo scoreRepo;

    private final ProgressRepository progressRepository;

    public GameController(
            QuestionRepo repo,
            ScoreRepo scoreRepo, ProgressRepository progressRepository){

        this.repo = repo;
        this.scoreRepo = scoreRepo;
        this.progressRepository = progressRepository;
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

    // Save each answer
    @PostMapping("/save-progress")
    public Progress saveProgress(@RequestBody Progress progress) {
        return progressRepository.save(progress);
    }

    // Review answers
    @GetMapping("/review/{userId}")
    public List<Progress> review(@PathVariable Long userId) {
        return progressRepository.findByUserId(userId);
    }

}
