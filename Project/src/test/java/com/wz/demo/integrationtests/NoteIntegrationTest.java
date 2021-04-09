package com.wz.demo.integrationtests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import wz.project.dto.NoteDTO;

import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.fail;

@AutoConfigureMockMvc
class NoteIntegrationTest extends AbstractNoteIntegrationTest {

  //  @Autowired
 //   ObjectMapper objectMapper;

    @LocalServerPort
    int port;

    @BeforeEach
    public void before() {
        RestAssured.port = port;
    }

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    void checkIfNoteWasCreatedWithTitleAndContent() {

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Hi");
        noteDTO.setContent("Peter");

        Response response = with().body(noteDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().post("/notes");

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
    void checkIfContentOfNoteWasUpdated(){

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Hi");
        noteDTO.setContent("Peter");

        Response response = with().body(noteDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/notes");

        Assertions.assertEquals(200, response.statusCode());

        try {
            NoteDTO result = response.body().as(NoteDTO.class);
            Assertions.assertEquals(noteDTO.getTitle(), result.getTitle());
            Assertions.assertNotEquals(noteDTO.getContent(),result.getContent());
        } catch (Exception e) {
            fail(e);
        }

    }

    @Test
    void checkIfNoteWasSuccessfullyDeleted(){

        NoteDTO noteDTO = new NoteDTO();
        noteDTO.setTitle("Hi");
        noteDTO.setContent("Peter");

        Response response = with().body(noteDTO)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().put("/notes");

        Assertions.assertEquals(200, response.statusCode());

        try {
            NoteDTO result = response.body().as(NoteDTO.class);
            Assertions.assertEquals(noteDTO.getDeleted(), result.getDeleted());
        } catch (Exception e) {
            fail(e);
        }

    }


}
