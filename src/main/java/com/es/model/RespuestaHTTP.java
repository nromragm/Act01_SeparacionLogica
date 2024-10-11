package com.es.model;

public class RespuestaHTTP {
    private int codigoRespuesta;

    private String mensajeRespuesta;

    private User user;

    public RespuestaHTTP(int codigoRespuesta, String mensajeRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public RespuestaHTTP(int codigoRespuesta, String mensajeRespuesta, User user) {
        this.codigoRespuesta = codigoRespuesta;
        this.mensajeRespuesta = mensajeRespuesta;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUserEmail(User user) {
        this.user = user;
    }

    public int getCodigoRespuesta() {
        return codigoRespuesta;
    }

    public void setCodigoRespuesta(int codigoRespuesta) {
        this.codigoRespuesta = codigoRespuesta;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    @Override
    public String toString() {
        return "RespuestaHTTP{" +
                "codigoRespuesta=" + codigoRespuesta +
                ", mensajeRespuesta='" + mensajeRespuesta + '\'' +
                ", user=" + user +
                '}';
    }
}
