package com.joshuamatos.spring.note;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joshuamatos.spring.note.type.NoteMapper;
import com.joshuamatos.spring.note.type.NoteRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc
class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NoteService noteService;

    @Test
    void getAllNotes() throws Exception {
        var note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setNoteBody("Test Note Body");
        note.setPinned(true);

        var noteDtoList = Stream.of(note).map(NoteMapper::map).collect(Collectors.toList());

        when(noteService.getAllNotes()).thenReturn(noteDtoList);
        when(noteService.getAllNotes()).thenReturn(noteDtoList);

        mockMvc.perform(get("/api/v2/note"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noteDtoList)));
    }

    @Test
    void getPinnedNotes() throws Exception {
        var pinnedNote = new Note();
        pinnedNote.setId(1L);
        pinnedNote.setTitle("Test Title");
        pinnedNote.setNoteBody("Test Note Body");
        pinnedNote.setPinned(true);

        var unpinnedNote = new Note();
        unpinnedNote.setId(2L);
        unpinnedNote.setTitle("Test Title");
        unpinnedNote.setNoteBody("Test Note Body");
        unpinnedNote.setPinned(false);

        var noteDtoList = Stream.of(pinnedNote, unpinnedNote).map(NoteMapper::map).toList();

        when(noteService.getPinnedNotes()).thenReturn(noteDtoList);

        mockMvc.perform(get("/api/v2/note/pinned"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noteDtoList)));
    }

    @Test
    void getNoteById() throws Exception {
        var note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setNoteBody("Test Note Body");
        note.setPinned(true);

        var noteDto = NoteMapper.map(note);

        when(noteService.getNoteById(1L)).thenReturn(noteDto);
        when(noteService.getNoteById(1L)).thenReturn(noteDto);

        mockMvc.perform(get("/api/v2/note/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(noteDto)));
    }

    @Test
    void createNote() throws Exception {
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setTitle("Test Title");
        noteRequest.setNoteBody("Test Note Body");
        noteRequest.setPinned(true);

        mockMvc.perform(post("/api/v2/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteRequest)))
                .andExpect(status().isOk());

        verify(noteService).createNote(any(NoteRequest.class));
    }

    @Test
    void updateNote() throws Exception {
        var noteId = 1L;
        NoteRequest noteRequest = new NoteRequest();
        noteRequest.setTitle("Test Title");
        noteRequest.setNoteBody("Test Note Body");
        noteRequest.setPinned(true);

        doNothing().when(noteService).updateNoteById(noteId, noteRequest);

        mockMvc.perform(put("/api/v2/note/{noteId}", noteId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteRequest)))
                .andExpect(status().isOk());

        verify(noteService).updateNoteById(any(Long.class), any(NoteRequest.class));
    }

    @Test
    void deleteNote() throws Exception {
        var noteId = 1L;
        mockMvc.perform(delete("/api/v2/note/{noteId}", noteId))
                .andExpect(status().isOk());
        verify(noteService).deleteNoteById(noteId);
    }

}