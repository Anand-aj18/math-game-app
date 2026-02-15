package com.game.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Progress {

    @Id
    @GeneratedValue
    Long id;

    String username;
    int classLevel;
    int levelCompleted;
}
