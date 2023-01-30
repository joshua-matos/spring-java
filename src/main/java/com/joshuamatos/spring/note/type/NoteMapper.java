package com.joshuamatos.spring.note.type;

import com.joshuamatos.spring.note.Note;

public class NoteMapper {
        public static NoteDto map(Note note) {
            return new NoteDto(note.getId(), note.getTitle(), note.getNoteBody(), note.getPinned(), note.getCreatedDate());
        }
}
