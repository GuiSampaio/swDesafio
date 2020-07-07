package br.com.desefiob2w.desafio;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.repository.PlanetRepository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PlanetRepositoryTest {

    @Autowired
    private PlanetRepository planetRepository;

    @Test
    public void whenCreate_thePersistData() {
        Planet planet = new Planet("1", "Kashyyyk", "tropical", "jungle, forests, lakes, rivers", "1");
        this.planetRepository.save(planet);
        assertEquals(planet.getId(), "1");
        assertEquals(planet.getName(), "Kashyyyk");
        assertEquals(planet.getClimate(), "tropical");
        assertEquals(planet.getTerrain(), "jungle, forests, lakes, rivers");
        assertEquals(planet.getNumFilms(), "1");
    }

    @Test
    public void whenDelete_thenRemoveData() {
        Planet planet = new Planet("1", "Kashyyyk", "tropical", "jungle, forests, lakes, rivers", "1");
        this.planetRepository.save(planet);
        planetRepository.delete(planet);
        assertEquals(planetRepository.findById(planet.getId()), Optional.empty());
    }

    @Test
    public void whenUpdate_thenChangeAndPersistData(){
        Planet planet = new Planet("1", "Kashyyyk", "tropical", "jungle, forests, lakes, rivers", "1");
        this.planetRepository.save(planet);

        planet = new Planet("Kashyyyk_updated","tropical_br","rainforest","2");
        this.planetRepository.save(planet);

        planet = planetRepository.findById(planet.getId()).orElse(null);

        assertEquals(planet.getName(), "Kashyyyk_updated");
        assertEquals(planet.getClimate(), "tropical_br");
    }

    @Test
    public void whenFindByName_thenFindByName(){
        Planet planet = new Planet("1", "Kashyyyk", "tropical", "jungle, forests, lakes, rivers", "1");
        this.planetRepository.save(planet);

        planet = planetRepository.findByName(planet.getName()).orElse(null);

        assertEquals(planet.getName(), "Kashyyyk");

    }

    @Test
    public void whenFindAll_thenFindAll(){
        Planet planet1 = new Planet("1", "Kashyyyk", "tropical", "jungle, forests, lakes, rivers", "1");
        Planet planet2 = new Planet("2", "Polis Massa", "artificial temperate", "airless asteroid", "1");

        this.planetRepository.save(planet1);
        this.planetRepository.save(planet2);

        List<Planet> lista = new ArrayList<>();
        lista.add(planet1);
        lista.add(planet2);

        List<Planet> planets = planetRepository.findAll();

        Assertions.assertTrue(planets.containsAll(lista));
    }

}
