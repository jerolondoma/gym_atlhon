package com.athlon.athlon.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import com.athlon.athlon.models.Cliente;
import com.athlon.athlon.repositories.ClienteRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteRepositorie clienteRepositorie;

    /*Obtener todos los clientes */
    @GetMapping
    public List <Cliente> obtener_todos_clientes() {
        return clienteRepositorie.findAll();
    }
    
    /*Obtener por medio de una id */
    @GetMapping("/{clienteID}")
    public Cliente obtener_cliente_id(@PathVariable ("clienteID") Long clienteID) {
        return clienteRepositorie.findById(clienteID).orElse(null);
    }

    /*Crear un cliente */
    @PostMapping()
    public Cliente crear_cliente(@RequestBody Cliente cliente) {
        return clienteRepositorie.save(cliente);
    }
    
    /*Actualizar cliente*/
    @PutMapping("/{clienteID}")
    public Cliente actualizar_cliente(@PathVariable ("clienteID") Long clienteID, @RequestBody Cliente cliente) {
        cliente.setClienteID(clienteID);
        return clienteRepositorie.save(cliente);
    }

    /* Eliminar cliente */
    @DeleteMapping("/{clienteID}")
    public void eliminar_cliente(@PathVariable ("clienteID") Long clienteID) {
        clienteRepositorie.deleteById(clienteID);
    }
}
