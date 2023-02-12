package com.joshuamatos.spring.note;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//This can be used to create some default data in the database
//Simply uncomment the @Bean annotation and the @Configuration annotation
//You will have to update the test cases to reflect the new data.
//@Configuration
public class NoteConfig {

//    @Bean
    public CommandLineRunner noteCommandLineRunner(NoteRepository noteRepository) {

        return args -> {
            var lorem25 = "Lorem ipsum dolores tincidunt, nisl nisl aliquam nisl, eget aliquam nunc nisl eget nisl.";
            for (int i = 0; i < 10; i++) {
                Note note = new Note();
                note.setTitle("Title " + i);
                note.setNoteBody("Body " + lorem25 + i);
                note.setPinned(i % 2 == 0);
                noteRepository.save(note);
            }
        };
    }
}
