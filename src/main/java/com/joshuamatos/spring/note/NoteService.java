package com.joshuamatos.spring.note;

import com.joshuamatos.spring.note.type.NoteDto;
import com.joshuamatos.spring.note.type.NoteMapper;
import com.joshuamatos.spring.note.type.NoteRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;

    public List<NoteDto> getAllNotes() {
        return noteRepository.findAllByOrderByCreatedDateAsc();
    }

    public List<NoteDto> getPinnedNotes() {
        return noteRepository.findAllByPinnedOrderByCreatedDateDesc(true);
    }

    public NoteDto getNoteById(Long id) {
        var note = noteRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Note not found")
        );
        return NoteMapper.map(note);
    }

    public void createNote(NoteRequest noteRequest) {
        if(noteRequest != null){
            var note = new Note();
            note.setTitle(noteRequest.getTitle());
            note.setNoteBody(noteRequest.getNoteBody());
            note.setPinned(noteRequest.getPinned());
            note.setCreatedDate(Instant.now());
            noteRepository.save(note);
        } else {
            throw new IllegalArgumentException("NoteRequest cannot be null");
        }
    }

    @Transactional
    public void updateNoteById(Long id, NoteRequest noteRequest) {
        if(noteRequest == null) {
            throw new IllegalArgumentException("NoteRequest cannot be null");
        }

        var note = noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));

        if(!ObjectUtils.isEmpty(noteRequest.getTitle())) {
            note.setTitle(noteRequest.getTitle());
        }
        if(!ObjectUtils.isEmpty(noteRequest.getNoteBody())) {
            note.setNoteBody(noteRequest.getNoteBody());
        }
        if(!ObjectUtils.isEmpty(noteRequest.getPinned())) {
            note.setPinned(noteRequest.getPinned());
        }

    }

    public void deleteNoteById(Long id){
        if(noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Note not found");
        }
    }
}
