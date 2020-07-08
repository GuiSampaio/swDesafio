package br.com.desefiob2w.desafio.controller;

import br.com.desefiob2w.desafio.document.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test.properties")
public class PlanetEndpointTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private Planet planet;

    @Autowired
    private PlanetaController planetaController;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(planetaController).build();
        planet = new Planet("1", "Kashyyyk_test", "tropical", "jungle, forests, lakes, rivers", "1");
    }

    @Test
    public void whenSavePlanetAlreadyExists_thenReturnIsBadRequest() throws Exception {
        mockMvc.perform(post("/planet/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenSavePlanet_thenReturnIsCreated() throws Exception {
        mockMvc.perform(post("/planet/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenDeleteById_thenReturnIsNoContent() throws Exception {
        mockMvc.perform(delete("/planet/delete/{id}", planet.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenFindAllPlanets_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/planet/findAll")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindById_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/planet/findById/{id}", planet.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindByName_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/planet/findByName/{name}", planet.getName())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }


}
