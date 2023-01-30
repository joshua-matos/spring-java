package com.joshuamatos.spring.note;

import com.joshuamatos.spring.note.type.NoteDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<NoteDto> findAllByOrderByCreatedDateAsc();

    List<NoteDto> findAllByPinnedOrderByCreatedDateDesc(boolean pinned);
}
