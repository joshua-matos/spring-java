package com.joshuamatos.spring.note.type;

import lombok.Data;

@Data
public class NoteRequest {
    private String title;
    private String noteBody;
    private Boolean pinned;
}
