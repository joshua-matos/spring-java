package com.joshuamatos.spring.note;

import com.joshuamatos.spring.note.type.NoteDto;
import com.joshuamatos.spring.note.type.NoteMapper;
import com.joshuamatos.spring.note.type.NoteRequest;
import jakarta.transaction.Transactional;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

//can be converted to a unit test with @ExtendsWith(MockitoExtension.class) and @Mock @InjectMocks
//use doReturn() - good luck!

@SpringBootTest
@TestInstance(value = Lifecycle.PER_CLASS)
class NoteServiceTest {
    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private NoteService noteService;

    Note note = new Note();
    Note note2 = new Note();
    NoteRequest noteRequest = new NoteRequest();

    @BeforeAll
    void setupTest() {
        note.setId(1L);
        note.setTitle("Test Title");
        note.setNoteBody("Test Note Body");
        note.setPinned(true);

        note2.setId(2L);
        note2.setTitle("Test Title 2");
        note2.setNoteBody("Test Note Body 2");
        note2.setPinned(false);

        noteRepository.saveAll(List.of(note, note2));

        noteRequest.setTitle("Test Title 3");
        noteRequest.setNoteBody("Test Note Body 3");
        noteRequest.setPinned(true);
    }

    @Test
    @Transactional @Rollback
    @Description("Test to see if the getAllNotes method returns a list of notes")
    void getAllNotes() {
        var noteDtoList = Stream.of(note, note2)
                .map(NoteMapper::map)
                .sorted(Comparator.comparing(NoteDto::id))
                .collect(Collectors.toList());

        var result = noteService.getAllNotes();
        assertThat(result).isEqualTo(noteDtoList);
    }

    @Test
    @Transactional @Rollback
    @Description("Test to see if the getPinnedNotes method returns a list of pinned notes")
    void getPinnedNotes() {
        var pinnedNoteDtoList = Stream.of(note)
                .map(NoteMapper::map)
                .collect(Collectors.toList());
        var result = noteService.getPinnedNotes();
        assertThat(result).isEqualTo(pinnedNoteDtoList);
        assertThat(noteService.getPinnedNotes()).isEqualTo(pinnedNoteDtoList);
    }

    @Test
    @Transactional @Rollback
    @Description("Test to see if the getNoteById method returns a note by id")
    void getNoteById() {
        var noteDto = NoteMapper.map(note);
        var result = noteService.getNoteById(1L);

        assertThat(result).isEqualTo(noteDto);
        assertThat(noteService.getNoteById(1L)).isEqualTo(noteDto);
    }

    @Test
    @Transactional @Rollback
    @Description("Test to see if the createNote method creates a note")
    void createNote() {
        noteService.createNote(noteRequest);

        var result = noteService.getNoteById(3L);

        assertThat(result.title()).isEqualTo(noteRequest.getTitle());
        assertThat(result.noteBody()).isEqualTo(noteRequest.getNoteBody());
        assertThat(result.pinned()).isEqualTo(noteRequest.getPinned());
    }

    @Test
    @Transactional @Rollback
    @Description("Test to see if the updateNoteById method updates a note by id")
    void updateNote() {
        noteService.updateNoteById(1L, noteRequest);

        var result = noteService.getNoteById(1L);

        assertThat(result.id()).isEqualTo(note.getId());
        assertThat(result.title()).isEqualTo(noteRequest.getTitle());
        assertThat(result.noteBody()).isEqualTo(noteRequest.getNoteBody());
        assertThat(result.pinned()).isEqualTo(noteRequest.getPinned());
    }

    @Test
    @Transactional @Rollback
    @Description("Test to see if the deleteNoteById method deletes a note by id and throws an exception")
    void deleteNoteById() {
        var getNoteById = noteService.getNoteById(1L);

        assertThat(getNoteById).isNotNull();

        noteService.deleteNoteById(1L);

        assertThrows(RuntimeException.class, () -> {
            noteService.getNoteById(1L);
        });
    }
}