package br.com.desefiob2w.desafio.repository;

import br.com.desefiob2w.desafio.document.Planet;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Mock
    private Planet planet;

    @Mock
    private Planet planet1;

    @Mock
    private List<Planet> lista;

    @BeforeEach
    public void setUp() {
        planet = new Planet("1", "Kashyyyk", "tropical", "jungle, forests, lakes, rivers", "1");
        planet1 = new Planet("2", "Polis Massa", "artificial temperate", "airless asteroid", "1");
        lista = Arrays.asList(planet, planet1);
    }

    @Test
    public void whenCreate_thePersistData() {
        this.planetRepository.save(planet);
        assertEquals(planet.getId(), "1");
        assertEquals(planet.getName(), "Kashyyyk");
        assertEquals(planet.getClimate(), "tropical");
        assertEquals(planet.getTerrain(), "jungle, forests, lakes, rivers");
        assertEquals(planet.getNumFilms(), "1");
    }

    @Test
    public void whenDelete_thenRemoveData() {
        this.planetRepository.save(planet);
        planetRepository.delete(planet);
        assertEquals(planetRepository.findById(planet.getId()), Optional.empty());
    }

    @Test
    public void whenUpdate_thenChangeAndPersistData(){
        this.planetRepository.save(planet);

        planet = new Planet(planet.getId(),"Kashyyyk_updated","tropical_br","rainforest","2");
        this.planetRepository.save(planet);

        planet = planetRepository.findById(planet.getId()).orElse(null);

        assertEquals(planet.getName(), "Kashyyyk_updated");
        assertEquals(planet.getClimate(), "tropical_br");
    }

    @Test
    public void whenFindByName_thenFindByName(){
        this.planetRepository.save(planet);

        planet = planetRepository.findByName(planet.getName()).orElse(null);

        assertEquals(planet.getName(), "Kashyyyk");

    }

    @Test
    public void whenFindById_thenFindById(){
        this.planetRepository.save(planet);

        planet = planetRepository.findById(planet.getId()).orElse(null);

        assertEquals(planet.getId(), "1");

    }

    @Test
    public void whenFindAll_thenFindAll(){
        this.planetRepository.save(planet);
        this.planetRepository.save(planet1);

        List<Planet> planets = planetRepository.findAll();

        assertTrue(planets.containsAll(lista));

    }

}
