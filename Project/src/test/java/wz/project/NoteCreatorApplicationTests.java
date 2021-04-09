package wz.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import wz.project.dto.NoteDTO;
import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.fail;

@AutoConfigureMockMvc
class NoteCreatorApplicationTests extends AbstractTestClass {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldCreateNewNoteWithTitleAndContent() {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Hello");
        noteDTO.setContent("world");

        Response response = with().body(noteDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/notes/create");

        Assertions.assertEquals(200, response.statusCode());

        try {
            NoteDTO result = response.body().as(NoteDTO.class);
            Assertions.assertEquals(noteDTO.getTitle(), result.getTitle());
            Assertions.assertEquals(noteDTO.getContent(), result.getContent());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void shouldUpdateTheNoteWithDifferentContent() {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Hello");
        noteDTO.setContent("world");

        Response response = with().body(noteDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/notes/update");

        Assertions.assertEquals(200, response.statusCode());

        try {
            NoteDTO result = response.body().as(NoteDTO.class);
            Assertions.assertNotEquals(noteDTO.getContent(), result.getContent());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void shouldDeleteTheNoteAndChangeDeletedVariable() {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Hello");
        noteDTO.setContent("world");
        noteDTO.setDeleted(0);

        Response response = with().body(noteDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/notes/delete");

        Assertions.assertEquals(200, response.statusCode());

        try {
            NoteDTO result = response.body().as(NoteDTO.class);
            Assertions.assertNotEquals(noteDTO.getDeleted(), result.getDeleted());
        } catch (Exception e) {
            fail(e);
        }
    }
}

