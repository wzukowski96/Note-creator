package wz.project.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        List<Note> list = listOfNotes.getNotes();
        list.add(note);
        listOfNotes.setNotes(list);

        Note savedNote = noteRepository.save(note);

        return new NoteDTO(savedNote.getId(), savedNote.getTitle(), savedNote.getContent(), savedNote.getVersion());
    }

    @Transactional
    public List<NoteDTO> showAllNotes(){
        return noteRepository.findAll().stream().map(note -> new NoteDTO(note.getId(),
                note.getTitle(),note.getContent(),note.getVersion(),note.isDeleted(),
                note.getModified(),note.getModified())).collect(Collectors.toList());
    }

    @Transactional
    public NoteDTO updateNote(NoteDTO noteDTO) {

        Note note = noteRepository.findByTitle(noteDTO.getTitle());
        ListOfNotes listOfNotes = new ListOfNotes();

        if(note != null){
            note.setContent(noteDTO.getContent());
            note.setVersion(note.getVersion()+1);

            List<Note> list = listOfNotes.getNotes();
            list.add(note);
            listOfNotes.setNotes(list);

            noteRepository.save(note);

            return new NoteDTO(note.getId(),note.getContent(),note.getTitle(), note.getVersion(),
                    note.isDeleted(), note.getCreated(), note.getModified());
        }
        return null;
    }

//
//    @Transactional
//    public boolean addLikedLocation(LocationDto locationDto, UUID userId) {
//        Location location = locationService.findByCountryAndCity(locationDto.getCountry(), locationDto.getCity());
//        AppUser appUser = userRepository.findById(userId).orElseGet(null);
//
//        List<Location> list = appUser.getLikedLocations();
//        log.info("User {} change status liked for location {} to", userId, locationDto.getCity());
//        changeLocationStatus(locationDto, location, list);
//
//        appUser.setLikedLocations(list);
//        userRepository.save(appUser);
//
//        return true;
//    }

//    private void changeLocationStatus(LocationDto locationDto, Location location, List<Location> list) {
//        if (location == null) {
//            location = locationDTOToLocation.apply(locationDto);
//        }
//        if (list.contains(location)) {
//            list.remove(location);
//            log.info("remove");
//        } else {
//            list.add(location);
//            log.info("add");
//        }
//    }



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
