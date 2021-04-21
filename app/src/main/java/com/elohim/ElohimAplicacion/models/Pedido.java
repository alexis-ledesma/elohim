package com.elohim.ElohimAplicacion.models;

public class Pedido {
    String id;
    String idCliente;
    String nombre;
    String direccion;
    String numero;
    int roles;
    int conchas;
    int panques;
    boolean enCamino;
    float total;
    double destinoLatitud;
    double destinoLongitud;

    public Pedido(String idCliente, String nombre, String direccion, String numero, int roles, int conchas, int panques, boolean enCamino, float total,double destinoLatitud,double destinoLongitud) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.roles = roles;
        this.conchas = conchas;
        this.panques = panques;
        this.enCamino = enCamino;
        this.total = total;
        this.destinoLatitud = destinoLatitud;
        this.destinoLongitud = destinoLongitud;
    }

    public Pedido(){

    }

    public Pedido(String id, String idCliente, String nombre, String direccion, String numero, int roles, int conchas, int panques, boolean enCamino) {
        this.id = id;
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero = numero;
        this.roles = roles;
        this.conchas = conchas;
        this.panques = panques;
        this.enCamino = enCamino;
    }

    public Pedido(String idCliente, String nombre, String direccion, String numeroTelefono, int roles, int conchas, int panques, boolean enCamino, float total) {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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

    public boolean isEnCamino() {
        return enCamino;
    }

    public void setEnCamino(boolean enCamino) {
        this.enCamino = enCamino;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public double getDestinoLatitud() {
        return destinoLatitud;
    }

    public void setDestinoLatitud(double destinoLatitud) {
        this.destinoLatitud = destinoLatitud;
    }

    public double getDestinoLongitud() {
        return destinoLongitud;
    }

    public void setDestinoLongitud(double destinoLongitud) {
        this.destinoLongitud = destinoLongitud;
    }
}
