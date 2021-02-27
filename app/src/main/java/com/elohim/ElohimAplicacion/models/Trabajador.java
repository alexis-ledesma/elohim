package com.elohim.ElohimAplicacion.models;

public class Trabajador {
    String id;
    String name;
    String email;
    String VehiculoMarca;
    String PlacaVehiculo;


    public Trabajador(String id, String name, String email, String vehiculoMarca, String placaVehiculo) {
        this.id = id;
        this.name = name;
        this.email = email;
        VehiculoMarca = vehiculoMarca;
        PlacaVehiculo = placaVehiculo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehiculoMarca() {
        return VehiculoMarca;
    }

    public void setVehiculoMarca(String vehiculoMarca) {
        VehiculoMarca = vehiculoMarca;
    }

    public String getPlacaVehiculo() {
        return PlacaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        PlacaVehiculo = placaVehiculo;
    }
}
