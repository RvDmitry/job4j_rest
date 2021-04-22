package ru.job4j.auth.controller;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.auth.domain.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class PersonControllerTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Gson json;

    @Test
    void whenFindAll() throws Exception {
        List<Person> persons = List.of(
                Person.of(1, "User", "user"),
                Person.of(2, "Admin", "admin")
        );
        String jsonStr = json.toJson(persons);
        when(personRepository.findAll()).thenReturn(persons);
        mockMvc.perform(get("/person/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonStr));
        verify(personRepository).findAll();
    }

    @Test
    void whenFindById() throws Exception {
        Person person = Person.of(1, "User", "user");
        when(personRepository.findById(1)).thenReturn(Optional.of(person));
        String jsonStr = json.toJson(person);
        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonStr));
        verify(personRepository).findById(anyInt());
    }
}