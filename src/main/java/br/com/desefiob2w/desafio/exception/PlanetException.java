package br.com.desefiob2w.desafio.exception;

public class PlanetException extends Exception {

    private static final long serialVersionUID = -250249125703674844L;

    public PlanetException() {
    }

    public PlanetException(final String msg) {
        super(msg);
    }

    public PlanetException(final Throwable erro) {
        super(erro);
    }

    public PlanetException(String msg, Throwable erro) {
        super(msg, erro);
    }
}
