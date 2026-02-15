package com.game.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Score {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private int points;

    private int classLevel;
    private int age;
    private String gender;

    // getters & setters
}

