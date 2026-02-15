package com.game.demo.controller;

import com.game.demo.entity.Score;
import com.game.demo.repository.ScoreRepo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/top-rankers")
    public List<Map<String, Object>> getTopRankers() {

        List<Map<String, Object>> result = new ArrayList<>();

        for (int classNo = 1; classNo <= 10; classNo++) {

            Score top =
                    repo.findTopByClassLevelOrderByPointsDesc(classNo);

            if (top != null) {

                Map<String, Object> row = new HashMap<>();

                row.put("classNo", classNo);
                row.put("player", top.getUsername());
                row.put("score", top.getPoints());

                result.add(row);
            }
        }

        return result;
    }

}
