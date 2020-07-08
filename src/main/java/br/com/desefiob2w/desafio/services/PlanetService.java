package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.document.Planet;

import java.util.List;

public interface PlanetService {

    Planet createPlanet(Planet planet);

    Planet findById(String id);

    Planet findByName(String name);

    List<Planet> findAllPlanets();

    void deletePlanet(String id);

}
