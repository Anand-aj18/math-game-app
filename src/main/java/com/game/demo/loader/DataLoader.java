package com.game.demo.loader;

import com.game.demo.entity.Question;
import com.game.demo.repository.QuestionRepo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner load(QuestionRepo repo) {

        return args -> {

            // stop if DB already has data
            if(repo.count() > 0) return;

            ObjectMapper mapper = new ObjectMapper();

            InputStream is =
                    getClass().getResourceAsStream("/syllabus.json");

            if(is == null){
                System.out.println("❌ syllabus.json not found!");
                return;
            }

            List<Question> questions =
                    mapper.readValue(is,
                            new TypeReference<List<Question>>() {});

            repo.saveAll(questions);

            System.out.println("✅ Syllabus loaded into database!");
        };
    }
}
