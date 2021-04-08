package wz.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wz.project.model.Note;
import java.util.UUID;

public interface NoteRepository extends JpaRepository<Note, UUID> {
    Note findByTitle(String title);
}