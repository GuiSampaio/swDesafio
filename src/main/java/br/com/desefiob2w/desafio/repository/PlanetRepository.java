package br.com.desefiob2w.desafio.repository;

import br.com.desefiob2w.desafio.document.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
    Optional<Planet> findByName(String name);
}
