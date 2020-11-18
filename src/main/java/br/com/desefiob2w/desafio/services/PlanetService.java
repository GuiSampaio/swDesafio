package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.exception.PlanetException;

import java.util.List;

public interface PlanetService {

    Planet createPlanet(Planet planet) throws PlanetException;

    Planet findById(String id) throws PlanetException;

    Planet findByName(String name) throws PlanetException;

    List<Planet> findAllPlanets() throws PlanetException;

    void deletePlanet(String id) throws PlanetException;

}
