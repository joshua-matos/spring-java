package com.joshuamatos.spring.note.type;

import java.time.Instant;

public record NoteDto(Long id, String title, String noteBody, Boolean pinned,
                      Instant createdDate) {
}