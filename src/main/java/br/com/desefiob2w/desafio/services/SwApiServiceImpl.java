package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.dto.SwApiPlanetsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class SwApiServiceImpl implements SwApiService {

    @Value("${api.sw}")
    private String swapiUrl;

    @Override
    public <T> SwApiPlanetsDTO findPlanetByName(String name) {

        RestTemplate restTemplate = new RestTemplate();
        URI uri = UriComponentsBuilder.fromHttpUrl(swapiUrl)
                .path("/api")
                .path("/planets/")
                .queryParam("search", name)
                .build()
                .toUri();

        ResponseEntity<SwApiPlanetsDTO> response = restTemplate
                .exchange(uri, HttpMethod.GET, creatRequest(uri), SwApiPlanetsDTO.class);

        return response.getBody();
    }

    private static RequestEntity<?> creatRequest(URI uri) {
        return RequestEntity.get(uri).header("user-agent", "")
                .accept(MediaType.APPLICATION_JSON).build();
    }

    @Override
    public String countFilms(String name) {

        SwApiPlanetsDTO planetsDTO = this.findPlanetByName(name);

        return Integer.toString(planetsDTO.getResults().stream()
                .filter(nt -> nt.getName().equalsIgnoreCase(name))
                .mapToInt(nt -> nt.getFilms().size())
                .sum());
    }
}
