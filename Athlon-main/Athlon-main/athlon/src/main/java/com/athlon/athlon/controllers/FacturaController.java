package com.athlon.athlon.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.athlon.athlon.models.Factura;
import com.athlon.athlon.models.Cliente;
import com.athlon.athlon.models.Planes;
import com.athlon.athlon.repositories.FacturaRepositorie;
import com.athlon.athlon.repositories.ClienteRepositorie;
import com.athlon.athlon.repositories.PlanesRepositorie;

@RestController
@RequestMapping("/api/factura")
public class FacturaController {

    @Autowired
    private FacturaRepositorie facturaRepositorie;

    @Autowired
    private ClienteRepositorie clienteRepositorie;

    @Autowired
    private PlanesRepositorie planesRepositorie;

    // Obtener todas las facturas
    @GetMapping
    public List<Factura> obtener_todas_facturas() {
        return facturaRepositorie.findAll();
    }

    // Obtener factura por ID
    @GetMapping("/{facturaID}")
    public Factura obtener_factura_id(@PathVariable("facturaID") Long facturaID) {
        return facturaRepositorie.findById(facturaID).orElse(null);
    }

    // Crear una nueva factura
    @PostMapping()
    public Factura crear_factura(@RequestBody Factura factura) {
        // Imprimir la factura recibida para verificar los valores
        System.out.println("Factura recibida en el backend:");
        System.out.println(factura);

        // Obtener los datos completos del cliente
        Cliente cliente = clienteRepositorie.findById(factura.getClienteID().getClienteID()).orElse(null);
        if (cliente != null) {
            factura.setClienteID(cliente);  // Asignar cliente completo a la factura
        } else {
            System.out.println("Error: Cliente no encontrado");
        }

        // Obtener los datos completos del plan
        Planes plan = planesRepositorie.findById(factura.getPlanID().getPlanID()).orElse(null);
        if (plan != null) {
            factura.setPlanID(plan);  // Asignar plan completo a la factura

            // Asignar el precio del plan al campo precioF de la factura
            factura.setPrecioF(String.valueOf(plan.getPrecio()));
        } else {
            System.out.println("Error: Plan no encontrado");
        }

        // Verificar que las fechas estén correctamente capturadas
        System.out.println("Fecha de la factura recibida: " + factura.getFechaf());
        System.out.println("Fecha de vencimiento recibida: " + factura.getFechaf_Vencimiento());

        // Guardar la factura con el cliente, plan, fecha y precio completos
        Factura facturaGuardada = facturaRepositorie.save(factura);

        System.out.println("Factura guardada correctamente: " + facturaGuardada);

        return facturaGuardada;
    }

    // Obtener el historial de facturas con cliente y plan
    @GetMapping("/historial")
    public List<Map<String, Object>> obtenerHistorialFacturas() {
        List<Factura> facturas = facturaRepositorie.findAll();
        List<Map<String, Object>> historial = new ArrayList<>();

        for (Factura factura : facturas) {
            Map<String, Object> facturaInfo = new HashMap<>();
            facturaInfo.put("facturaID", factura.getFacturaID());
            facturaInfo.put("fechaF", factura.getFechaf());
            facturaInfo.put("precioF", factura.getPrecioF());
            facturaInfo.put("fechaVencimiento", factura.getFechaf_Vencimiento());

            // Obtener nombre del cliente
            Cliente cliente = factura.getClienteID();
            if (cliente != null) {
                facturaInfo.put("nombreCliente", cliente.getNombre());
            }

            // Obtener descripción y nombre del plan
            Planes plan = factura.getPlanID();
            if (plan != null) {
                facturaInfo.put("nombrePlan", plan.getNombreplan());
                facturaInfo.put("descripcionPlan", plan.getDescripcion());
            }

            historial.add(facturaInfo);
        }
        
        return historial;
    }

    // Actualizar una factura
    @PutMapping("/{facturaID}")
    public Factura actualizar_factura(@PathVariable("facturaID") Long facturaID, @RequestBody Factura factura) {
        factura.setFacturaID(facturaID);
        return facturaRepositorie.save(factura);
    }

    // Eliminar una factura
    @DeleteMapping("/{facturaID}")
    public void eliminar_factura(@PathVariable("facturaID") Long facturaID) {
        facturaRepositorie.deleteById(facturaID);
    }
}
