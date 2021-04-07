package service;

import dto.NoteDTO;
import model.Note;
import repositories.NoteRepository;

import javax.transaction.Transactional;
import java.util.UUID;

public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Transactional
    public NoteDTO saveNote(NoteDTO noteDTO) {
        Note note = new Note();
        Note savedNote = noteRepository.save(note);
        return new NoteDTO();
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

    public void deleteById(UUID id) {
        noteRepository.deleteById(id);
    }
}
