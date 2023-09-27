package com.ApiRest.Controller;
import com.ApiRest.Domain.Coche;
import com.ApiRest.Domain.Exposicion;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExposicionesController {
    private List<Exposicion> exposiciones = new ArrayList<>();

    @GetMapping("/exposiciones")
    public List<Exposicion> listarExposiciones() {
        return exposiciones; //devuelve lista de exposiciones
    }

    @GetMapping("/exposiciones/{id}")
    public ResponseEntity<Exposicion> getExposicion(@PathVariable String id) {
        for (Exposicion elemento : exposiciones) { //recorre colección de exposiciones
            if (elemento.getId().equals(id)) //encuentra la exposición por el id
                return ResponseEntity.ok(elemento); //devuelve la exposición
        }
        return ResponseEntity.notFound().build(); //si ninguna coincide con el id: not found
    }

    @PostMapping("/exposiciones")
    public ResponseEntity<String> anyadirExposicion(@RequestBody Exposicion e) {
        if (e.getId().equals("")) {
            return ResponseEntity.badRequest().body("Id no puede estar vacío");
        }
        exposiciones.add(e);
        return ResponseEntity.ok("");
    }

    @GetMapping("exposiciones/{id}/coches")
    public ResponseEntity<List<Coche>> getCochesExposicion(@PathVariable String id) {
        for (Exposicion elemento : exposiciones) { //recorre las exposiciones
            if (elemento.getId().equals(id)) { //encuentra la exposicion
                if (elemento.getCochesExpo().isEmpty()) //si la lista de coches está vacía: no content
                    return ResponseEntity.noContent().build();
                return ResponseEntity.ok(elemento.getCochesExpo()); //devuelve la lista de coches de la expo

            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/exposiciones/{id}/coches")
    public ResponseEntity<String> anyadirCocheAExpo(@PathVariable String id, @RequestBody Coche c) {
        if (c.getId().equals("")) {
            return ResponseEntity.badRequest().body("Id no puede estar vacío");
        }
        if (c.getPropietario().equals("")) {
            return ResponseEntity.badRequest().body("Propietario no puede estar vacío");
        }
        for (Exposicion elemento : exposiciones) { //recorre la lista de exposiciones
            if (elemento.getId().equals(id)) { //encuentra la exposición por el id
                elemento.anyadirCoche(c); //anyade el coche a la lista de coches de la exposición
                return ResponseEntity.ok("");
            }
        }
        return ResponseEntity.notFound().build(); //si no encuentra la exposición: not found
    }

    @GetMapping("/exposiciones/{idExpo}/coches/{idCoche}") //devuelve el coche especifico de la expo especifica
    public ResponseEntity<Coche> getCocheDeExpo(@PathVariable String idExpo, @PathVariable String idCoche) {
        for (Exposicion elemento : exposiciones) { //recorre las exposiciones
            if (elemento.getId().equals(idExpo)) { //encuentra la exposicion
                for (Coche coche : elemento.getCochesExpo()){ //recorre los coches de la exposición
                    if(coche.getId().equals(idCoche))
                        return ResponseEntity.ok(coche);
                }
                return ResponseEntity.notFound().build(); //no encuentra el coche: not found
            }
        }
        return ResponseEntity.notFound().build(); //no encuentra la exposición: not found
    }
}
