package org.ignaciorios.apiservlet.webapp.headers.models;

public class Producto {

    private Long id;
    private String nombre;
    private String tipo;
    private int pecio;


    public Producto() {
    }

    public Producto(Long id, String nombre, String tipo, int pecio) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.pecio = pecio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPecio() {
        return pecio;
    }

    public void setPecio(int pecio) {
        this.pecio = pecio;
    }
}
