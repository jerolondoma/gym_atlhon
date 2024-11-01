package com.athlon.athlon.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "Cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    /*Atributos de la tabla cliente */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clienteID;

    private String nombre;
    private String apellido;
    private String email;
    private String fecharegistro;
    private String fechavencimiento;
    
}
