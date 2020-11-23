package br.com.desefiob2w.desafio.controller;

import br.com.desefiob2w.desafio.document.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class PlanetEndpointTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private Planet planet;

    @Autowired
    private PlanetaController planetaController;

    @BeforeEach
    public void initialiseRestAssuredMockMvcStandalone() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(planetaController).build();
        planet = new Planet("1", "Kashyyyk_test", "tropical", "jungle, forests, lakes, rivers", "1");
    }


    @Test
    public void whenSavePlanet_thenReturnIsCreated() throws Exception {
        mockMvc.perform(post("/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenSavePlanetAlreadyExists_thenReturnIsBadRequest() throws Exception {
        mockMvc.perform(post("/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenDeleteById_thenReturnIsNoContent() throws Exception {
        mockMvc.perform(delete("/delete/{id}", planet.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindAllPlanets_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/findAll")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindById_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/findById/{id}", planet.getId())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenFindByName_thenReturnIsOk() throws Exception {
        mockMvc.perform(get("/findByName/{name}", planet.getName())
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(planet)))
                .andExpect(status().isOk());
    }


}
