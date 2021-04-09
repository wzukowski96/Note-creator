package wz.project.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wz.project.dto.NoteDTO;
import wz.project.service.NoteService;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
    public NoteDTO saveNote(@RequestBody @Valid NoteDTO noteDTO){
        return noteService.saveNote(noteDTO);
    }

    @PutMapping("/update")
    public NoteDTO updateNote(@RequestBody @Valid NoteDTO noteDTO){
        return noteService.updateNote(noteDTO);
    }

    @PutMapping("/delete")
    public NoteDTO deleteNote(@RequestBody NoteDTO noteDTO){
        return noteService.deleteNote(noteDTO);
    }

    @GetMapping("/{title}")
    public String getNote(@PathVariable String title){
        return noteService.getNote(title);
    }
}
