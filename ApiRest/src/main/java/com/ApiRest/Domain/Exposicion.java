package com.ApiRest.Domain;

import java.util.ArrayList;
import java.util.List;

public class Exposicion {

    private String id;
    private List<Coche> cochesExpo;


    public Exposicion(String id, List<Coche> cochesExpo) {
        this.id = id;
        this.cochesExpo = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public List<Coche> getCochesExpo() {
        return cochesExpo;
    }

    public void anyadirCoche(Coche c){
        cochesExpo.add(c);
    }
}
