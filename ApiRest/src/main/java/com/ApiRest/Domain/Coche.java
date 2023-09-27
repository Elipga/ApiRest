package com.ApiRest.Domain;

public class Coche {
    private String id;
    private String propietario;

    public Coche(String id, String propietario) {
        this.id = id;
        this.propietario = propietario;
    }

    public String getId() {
        return id;
    }

    public String getPropietario() {
        return propietario;
    }
}
