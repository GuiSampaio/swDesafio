package br.com.desefiob2w.desafio.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwApiPlanetsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<SwapiDTO> results;
}
