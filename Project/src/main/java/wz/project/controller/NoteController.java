package wz.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import wz.project.dto.NoteDTO;
import wz.project.service.NoteService;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NoteDTO saveNote(@RequestBody NoteDTO noteDTO){
        return noteService.saveNote(noteDTO);
    }

    @PutMapping("/{id}")
    public NoteDTO editNote(@PathVariable UUID id, @RequestBody NoteDTO noteDTO){
        return noteService.editNote(id, noteDTO);
    }

    @DeleteMapping("/{id}")
    public NoteDTO deleteNote(@PathVariable UUID id) {
        return noteService.deleteById(id);
    }

}
