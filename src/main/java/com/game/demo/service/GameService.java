package com.game.demo.service;

import com.game.demo.entity.*;
import com.game.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final QuestionRepo qRepo;
    private final ScoreRepo sRepo;

    public GameService(QuestionRepo q,ScoreRepo s){
        qRepo=q;
        sRepo=s;
    }

    public List<Question> load(int c,int l){
        return qRepo.findByClassLevelAndLevel(c,l);
    }

    public void saveScore(String user,int pts){

        Score s=new Score();
        s.setUsername(user);
        s.setPoints(pts);

        sRepo.save(s);
    }


}
