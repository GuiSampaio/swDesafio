package br.com.desefiob2w.desafio.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RestException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public RestException(String message) {super(message);}
}
