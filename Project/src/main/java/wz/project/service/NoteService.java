package wz.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import wz.project.dto.NoteDTO;
import wz.project.model.ListOfNotes;
import wz.project.model.Note;
import wz.project.repository.NoteRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        ListOfNotes listOfNotes = new ListOfNotes();
        List<Note> list = noteRepository.findAll();
        list.add(note);
        listOfNotes.setNotes(list);
        Note savedNote = noteRepository.save(note);

        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent(), savedNote.getVersion());
    }

    @Transactional
    public String getNote(String title){
        return noteRepository.findByTitle(title).getContent();
    }

    @Transactional
    public List<NoteDTO> showAllNotes(){

        return noteRepository.findAll().stream().map(note -> new NoteDTO(note.getId(),
                note.getTitle(),note.getContent(),note.getVersion(),note.getDeleted(),
                note.getModified(),note.getModified())).filter(s -> s.getDeleted() == 0).collect(Collectors.toList());
    }

    @Transactional
    public NoteDTO updateNote(NoteDTO noteDTO) {

        Note note = noteRepository.findByTitle(noteDTO.getTitle());
        ListOfNotes listOfNotes = new ListOfNotes();

        if(note != null){
            note.setContent(noteDTO.getContent());
            note.setVersion(note.getVersion()+1);
            List<Note> list = noteRepository.findAll();
            list.add(note);
            listOfNotes.setNotes(list);
            noteRepository.save(note);

            return new NoteDTO(note.getId(),note.getContent(),note.getTitle(), note.getVersion(),
                    note.getDeleted(), note.getCreated(), note.getModified());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public NoteDTO deleteNote(NoteDTO noteDTO){
        Note note = noteRepository.findByTitle(noteDTO.getTitle());
        if(note != null){
            note.setVersion(note.getVersion());
            note.setDeleted(1);
            note = noteRepository.save(note);
            return new NoteDTO(note.getId(),note.getContent(),note.getTitle(), note.getVersion(),
                    note.getDeleted(), note.getCreated(), note.getModified());
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
