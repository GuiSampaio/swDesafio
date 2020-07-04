package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.document.Planet;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PlanetService {

    Planet createPlanet(Planet planet);

    Optional<Planet> findById(String id);

    Optional<Planet> findByName(String name);

    List<Planet> findAllPlanets();

    void deletePlanet(String id);

}
