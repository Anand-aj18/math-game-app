package com.game.demo.controller;

import com.game.demo.repository.ScoreRepo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

    private final ScoreRepo repo;

    public LeaderboardController(ScoreRepo r){
        repo=r;
    }

    @GetMapping
    public Object top(){
        return repo.findTop10ByOrderByPointsDesc();
    }
}
