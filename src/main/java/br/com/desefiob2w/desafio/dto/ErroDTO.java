package br.com.desefiob2w.desafio.dto;

import java.io.Serializable;

public class ErroDTO implements Serializable {
    private static final long serialVersionUID = -3495140682479862662L;

    private int statusCode;
    private String error;
    private String campo;

    public ErroDTO(int statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    public ErroDTO(int statusCode, String error, String campo) {
        this.statusCode = statusCode;
        this.error = error;
        this.campo = campo;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
}
