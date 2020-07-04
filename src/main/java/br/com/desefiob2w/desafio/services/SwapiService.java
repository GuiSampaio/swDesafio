package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.dto.SwApiPlanetsDTO;

public interface SwapiService {

    <T> SwApiPlanetsDTO findPlanetByName(String name);
}
