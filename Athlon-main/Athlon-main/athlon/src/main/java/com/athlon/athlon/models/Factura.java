package com.athlon.athlon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facturaID;

    private String fechaf; // Fecha de la factura
    private String fechaf_Vencimiento; // Fecha de vencimiento

    private String precioF; // Precio de la factura

    // Relación con Cliente (muchas facturas pueden pertenecer a un cliente)
    @ManyToOne
    @JoinColumn(name = "clienteid_clienteid", referencedColumnName = "clienteID")
    private Cliente clienteID;

    // Relación con Planes (muchas facturas pueden estar asociadas a un plan)
    @ManyToOne
    @JoinColumn(name = "planid_planid", referencedColumnName = "planID")
    private Planes planID;
}
