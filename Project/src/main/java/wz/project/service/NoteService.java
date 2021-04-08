package wz.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import wz.project.dto.NoteDTO;
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
        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent());
    }

    @Transactional
    public NoteDTO editNote(UUID id, NoteDTO noteDTO) {

        Note note = noteRepository.findById(id).orElse(null);

        if(note != null){
            note.setTitle(noteDTO.getTitle());
            note.setContent(noteDTO.getContent());
            note = noteRepository.save(note);
            return new NoteDTO(note.getId(),note.getTitle(),note.getContent());
        }
        return null;
    }

    public NoteDTO deleteById(UUID id) {

        Note note = noteRepository.findById(id).orElse(null);

        if(note != null){
            note.setDeleted(true);
            note = noteRepository.save(note);
            return new NoteDTO(note.getId(),note.isDeleted());
        }
        return null;
    }
}
