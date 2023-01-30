package com.joshuamatos.spring.note;

import com.joshuamatos.spring.note.type.NoteDto;
import com.joshuamatos.spring.note.type.NoteRequest;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/note")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public List<NoteDto> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/pinned")
    public List<NoteDto> getPinnedNotes() {
        return noteService.getPinnedNotes();
    }

    @GetMapping("/{id}")
    public NoteDto getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }

    @PostMapping
    public void createNote(@RequestBody NoteRequest noteRequest) {
         noteService.createNote(noteRequest);
    }

    @PutMapping("/{id}")
    public void updateNote(@PathVariable Long id, @RequestBody NoteRequest noteRequest) {
        noteService.updateNoteById(id, noteRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable Long id) {
        noteService.deleteNoteById(id);
    }
}
