package wz.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wz.project.dto.NoteDTO;
import wz.project.errors.NoteNotFoundException;
import wz.project.model.Note;
import wz.project.repository.NoteRepository;
import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    public NoteDTO saveNote(NoteDTO noteDTO) {
        Note note = new Note(noteDTO.getTitle(), noteDTO.getContent());
        Note savedNote = noteRepository.save(note);
        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent(), savedNote.getVersion());
    }

    @Transactional
    public NoteDTO findNoteById(UUID id) throws NoteNotFoundException {
        return noteRepository.findById(id).map(note -> new NoteDTO(
                note.getId(),note.getTitle(),note.getContent()))
                .orElseThrow(NoteNotFoundException::new);
    }

    @Transactional
    public NoteDTO updateNote(NoteDTO noteDTO) {

        Note note = noteRepository.findByTitle(noteDTO.getTitle());

        if(note != null){
            note.setContent(noteDTO.getContent());
            note.setVersion(note.getVersion()+1);
            note = noteRepository.save(note);
            return new NoteDTO(note.getId(),note.getContent(),note.getTitle(), note.getVersion(),
                    note.isDeleted(), note.getCreated(), note.getModified());
        }
        return null;
    }

    public NoteDTO deleteNote(NoteDTO noteDTO){
        Note note = noteRepository.findByTitle(noteDTO.getTitle());
        if(note != null){
            note.setVersion(note.getVersion());
            note.setDeleted(true);
            note = noteRepository.save(note);
            return new NoteDTO(note.getId(),note.getContent(),note.getTitle(), note.getVersion(),
                    note.isDeleted(), note.getCreated(), note.getModified());
        }
        return null;
    }
}
