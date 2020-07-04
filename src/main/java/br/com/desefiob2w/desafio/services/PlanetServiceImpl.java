package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.dto.SwApiPlanetsDTO;
import br.com.desefiob2w.desafio.error.RestException;
import br.com.desefiob2w.desafio.repository.PlanetRepository;
import br.com.desefiob2w.desafio.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Pageable;
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
            if (verifyIfPlanetExists(planet.getName(), planet.getId())) {
                throw new RestException(Messages.EXIST_PLANET_NAME);
            }
            planet.setNumFilms(countFilms(planet.getName()));
            Planet p = repository.save(planet);
            return p;
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    @Override
    public Optional<Planet> findById(String id) {
        try {
            if (id.isEmpty()) {
                throw new RestException(Messages.EMPTY_ID);
            }
            return repository.findById(id);
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    @Override
    public Optional<Planet> findByName(String name) {
        try {
            if (name.isEmpty()) {
                throw new RestException(Messages.EMPTY_NAME);
            }
            return repository.findByName(name);
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
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
            } else if (!verifyIfPlanetExists(null, id)) {
                throw new RestException(Messages.NOT_FOUND + " id:"+id);
            }
            repository.deleteById(id);
        } catch (RestException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.ERROR);
        }
    }

    private boolean verifyIfPlanetExists(String name, String id) {
        if ((name != null && !name.isEmpty()) && this.findByName(name).isPresent() ||
                ((id != null && !id.isEmpty()) && this.findById(id).isPresent())) {
            return true;
        }
        return false;
    }

    public String countFilms(String name) {

        SwApiPlanetsDTO planetsDTO = swapiService.findPlanetByName(name);

        return Integer.toString(planetsDTO.getResults().stream()
                .filter(a -> a.getName().contentEquals(name))
                .mapToInt(a -> a.getFilms().size())
                .sum());
    }
}
