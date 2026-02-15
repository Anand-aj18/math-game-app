package com.game.demo.loader;


import com.game.demo.entity.Question;
import com.game.demo.repository.QuestionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;


import java.io.InputStream;
import java.util.List;
@Component
public class SyllabusLoader implements CommandLineRunner {

    private final QuestionRepo repo;

    public SyllabusLoader(QuestionRepo r){
        repo = r;
    }

    @Override
    public void run(String... args) throws Exception {

        if(repo.count() > 0) return;

        ObjectMapper mapper = new ObjectMapper();

        InputStream is =
                getClass().getResourceAsStream("/syllabus.json");

        List<Question> qs =
                mapper.readValue(is,
                        new TypeReference<List<Question>>() {});

        repo.saveAll(qs);

        System.out.println("✅ Syllabus loaded!");
    }
}
