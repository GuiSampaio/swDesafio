package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.dto.SwApiPlanetsDTO;
import br.com.desefiob2w.desafio.exception.RestException;
import br.com.desefiob2w.desafio.repository.PlanetRepository;
import br.com.desefiob2w.desafio.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    private final PlanetRepository repository;
    private final SwapiService swapiService;

    @Autowired
    public PlanetServiceImpl(PlanetRepository repository, SwapiService swapiService) {
        this.repository = repository;
        this.swapiService = swapiService;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Planet createPlanet(Planet planet) {
        try {
            Optional<Planet> plnt = repository.findByName(planet.getName());
            if (plnt.isPresent()) {
                throw new RestException(Messages.EXIST_PLANET_NAME);
            }
            planet.setNumFilms(countFilms(planet.getName()));
            return repository.save(planet);
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    @Override
    public Planet findById(String id) {
        try {
            if (id.isEmpty()) {
                throw new RestException(Messages.EMPTY_ID);
            }
            return repository.findById(id).orElseThrow(
                    ()-> new RestException(Messages.NOT_FOUND));
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    @Override
    public Planet findByName(String name) {
        try {
            if (name.isEmpty()) {
                throw new RestException(Messages.EMPTY_NAME);
            }
            return repository.findByName(name)
                    .orElseThrow(()->new RestException(Messages.NOT_FOUND));
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    @Override
    public List<Planet> findAllPlanets() {
        try {
            List<Planet> planets = repository.findAll();
            if (planets.isEmpty()) {
                throw new RestException(Messages.EMPTY_LIST);
            }
            return planets;
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    @Override
    public void deletePlanet(String id) {
        try {
            if (id.isEmpty()) {
                throw new RestException(Messages.EMPTY_ID);
            }
            this.findById(id);
            repository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    public String countFilms(String name) {

        SwApiPlanetsDTO planetsDTO = swapiService.findPlanetByName(name);

        return Integer.toString(planetsDTO.getResults().stream()
                .filter(a -> a.getName().contentEquals(name))
                .mapToInt(a -> a.getFilms().size())
                .sum());
    }
}
