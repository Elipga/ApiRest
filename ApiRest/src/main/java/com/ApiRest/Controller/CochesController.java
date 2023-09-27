package com.ApiRest.Controller;
import com.ApiRest.Domain.Coche;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CochesController {
    private List<Coche> coches = new ArrayList<>();

    @GetMapping("/coches")
    public List<Coche> listarCoches() {
        return coches;
    }

    @GetMapping("/coches/{id}")
    public ResponseEntity<Coche> getCoche(@PathVariable String id) {
        for (Coche elemento: coches){
            if(elemento.getId().equals(id))
                return ResponseEntity.ok(elemento);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/coches/{id}/propietarios") //devuelve el propietario
    public ResponseEntity<String> getPropietario(@PathVariable String id) {
        for (Coche elemento: coches){
            if(elemento.getId().equals(id))
                return ResponseEntity.ok(elemento.getPropietario());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/coches")
    public ResponseEntity<String> anyadirCoche(@RequestBody Coche c){
        if (c.getId().equals("")) {
            return ResponseEntity.badRequest().body("Id no puede estar vacío");
        }
        if (c.getPropietario().equals("")) {
            return ResponseEntity.badRequest().body("Propietario no puede estar vacío");
        }
        coches.add(c);
        return ResponseEntity.ok("Coche creado con éxito");
    }
}
