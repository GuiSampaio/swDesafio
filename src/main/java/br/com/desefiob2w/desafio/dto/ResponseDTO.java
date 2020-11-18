package br.com.desefiob2w.desafio.dto;

public class ResponseDTO<T> {

    private T dados;
    private String msg;

    public ResponseDTO(T dados, String msg) {
        this.dados = dados;
        this.msg = msg;
    }

    public ResponseDTO(T dados) {
        this.dados = dados;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
