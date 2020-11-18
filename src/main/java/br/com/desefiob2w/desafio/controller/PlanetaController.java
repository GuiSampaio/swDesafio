package br.com.desefiob2w.desafio.controller;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.dto.ResponseDTO;
import br.com.desefiob2w.desafio.exception.PlanetException;
import br.com.desefiob2w.desafio.services.PlanetService;
import br.com.desefiob2w.desafio.util.Messages;
import br.com.desefiob2w.desafio.util.Routes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = Routes.API)
public class PlanetaController {

    private final PlanetService planetService;

    @Autowired
    public PlanetaController(PlanetService planetService) {
        this.planetService = planetService;
    }

    @PostMapping(path = Routes.INCLUDE)
    public ResponseEntity<?> save(@Valid @RequestBody Planet planet, UriComponentsBuilder uriBuilder) {
        try {
            Planet pl = planetService.createPlanet(planet);
            URI uri = uriBuilder.path("planet/findById/{id}").buildAndExpand(pl.getId()).toUri();
            return ResponseEntity.created(uri).body(new ResponseDTO<>(planet, Messages.CREATED));
        } catch (PlanetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = Routes.FIND_ALL)
    public ResponseEntity<?> listAll() {
        try {
            return new ResponseEntity<>(planetService.findAllPlanets(), HttpStatus.OK);
        } catch (PlanetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = Routes.FIND_BY_NAME)
    public ResponseEntity<?> findPlanetByName(@PathVariable(name = "name") String name) {
        try {
            return new ResponseEntity<>(planetService.findByName(name), HttpStatus.OK);
        } catch (PlanetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(path = Routes.FIND_BY_ID)
    public ResponseEntity<?> getPlanetById(@PathVariable(name = "id") String id) {
        try {
            return new ResponseEntity<>(planetService.findById(id), HttpStatus.OK);
        } catch (PlanetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = Routes.REMOVE)
    public ResponseEntity<?> deletePlanet(@PathVariable(name = "id") String id) {
        try {
            planetService.deletePlanet(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDTO<>(Messages.DELETED));
        } catch (PlanetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
