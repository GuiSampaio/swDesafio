package br.com.desefiob2w.desafio.services;

import br.com.desefiob2w.desafio.document.Planet;
import br.com.desefiob2w.desafio.exception.PlanetException;
import br.com.desefiob2w.desafio.repository.PlanetRepository;
import br.com.desefiob2w.desafio.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository repository;
    @Autowired
    private SwApiService swapiService;


    public PlanetServiceImpl() {
    }


    @Override
    @Transactional(rollbackFor = Exception.class)

    public Planet createPlanet(Planet planet) throws PlanetException {
        Optional<Planet> plnt = repository.findByName(planet.getName());
        if (plnt.isPresent()) {
            throw new PlanetException(Messages.EXIST_PLANET_NAME);
        }
        planet.setNumFilms(swapiService.countFilms(planet.getName()));
        return repository.save(planet);
    }

    @Override
    public Planet findById(String id) throws PlanetException {
        if (id.isEmpty()) {
            throw new PlanetException(Messages.EMPTY_ID);
        }
        return repository.findById(id).orElseThrow(() -> new PlanetException(Messages.NOT_FOUND));
    }

    @Override
    public Planet findByName(String name) throws PlanetException {
        if (name.isEmpty()) {
            throw new PlanetException(Messages.EMPTY_NAME);
        }
        return repository.findByName(name).orElseThrow(() -> new PlanetException(Messages.NOT_FOUND));
    }

    @Override
    public List<Planet> findAllPlanets() throws PlanetException {
        List<Planet> planets = repository.findAll();
        if (planets.isEmpty()) {
            throw new PlanetException(Messages.EMPTY_LIST);
        }
        return planets;
    }

    @Override
    public void deletePlanet(String id) throws PlanetException {
        if (id.isEmpty()) {
            throw new PlanetException(Messages.EMPTY_ID);
        }
        this.findById(id);
        repository.deleteById(id);
    }

}
