package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.dto.SwApiPlanetsDTO;

public interface SwApiService {

    <T> SwApiPlanetsDTO findPlanetByName(String name);
    String countFilms(String name);
}
