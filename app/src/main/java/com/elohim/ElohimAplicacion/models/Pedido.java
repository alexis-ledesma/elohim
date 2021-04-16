package com.elohim.ElohimAplicacion.models;

public class Pedido {
    String id;
    String nombre;
    String direccion;
    String numero;
    int roles;
    int conchas;
    int panques;


    public Pedido(String id, String nombre, String direccion, String numero, int roles, int conchas, int panques) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.roles = roles;
        this.conchas = conchas;
        this.panques = panques;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getRoles() {
        return roles;
    }

    public void setRoles(int roles) {
        this.roles = roles;
    }

    public int getConchas() {
        return conchas;
    }

    public void setConchas(int conchas) {
        this.conchas = conchas;
    }

    public int getPanques() {
        return panques;
    }

    public void setPanques(int panques) {
        this.panques = panques;
    }
}