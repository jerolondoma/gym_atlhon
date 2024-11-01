package com.athlon.athlon.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import com.athlon.athlon.models.Planes;
import com.athlon.athlon.repositories.PlanesRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/planes")
public class PlanesController {

    @Autowired
    private PlanesRepositorie planesrepositorie;

    /*Obtener todos los planes */
    @GetMapping
    public List <Planes> obtener_todos_planes() {
        return planesrepositorie.findAll();
    }

    /*Obtener planes por medio de la ID */
    @GetMapping ("/{planID}")
    public Planes obtener_planes_id(@PathVariable("planID") Long planID) {
        return planesrepositorie.findById(planID).orElse(null);
    }

    /*Crear un plan  */
    @PostMapping()
    public Planes crear_plan(@RequestBody Planes planes) {
        return planesrepositorie.save(planes);
    }
    
    /*Actualizar planes */
    @PutMapping("/{planID}")
    public Planes actualizar_plan(@PathVariable ("planID") Long planID, @RequestBody Planes planes) {
        planes.setPlanID(planID);
        return planesrepositorie.save(planes);  
    }

    /*Eliminar plan*/
    @DeleteMapping("/{planID}")
    public void eliminar_plan(@PathVariable ("planID") Long planID) { 
        planesrepositorie.deleteById(planID);
    }
}
