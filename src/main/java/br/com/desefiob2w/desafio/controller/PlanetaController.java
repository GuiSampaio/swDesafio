package br.com.desefiob2w.desafio.controller;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.services.PlanetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/planet")
public class PlanetaController {

    private final PlanetService planetService;

    @Autowired
    public PlanetaController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> save(@Valid @RequestBody Planet planet, UriComponentsBuilder uriBuilder) {
        Planet pl = planetService.createPlanet(planet);
        URI uri = uriBuilder.path("planet/findById/{id}").buildAndExpand(pl.getId()).toUri();
        return new ResponseEntity<>("Planeta criado: " + uri, HttpStatus.CREATED);
    }

    @GetMapping(path = "/findAll")
    public ResponseEntity<?> listAll() {
        return new ResponseEntity<>(planetService.findAllPlanets(), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName/{name}")
    public ResponseEntity<?> findPlanetByName(@PathVariable(name = "name") String name) {
        return new ResponseEntity<>(planetService.findByName(name), HttpStatus.OK);
    }

    @GetMapping(path = "/findById/{id}")
    public ResponseEntity<?> getPlanetById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(planetService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deletePlanet(@PathVariable(name = "id") String id) {
        planetService.deletePlanet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
