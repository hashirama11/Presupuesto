package com.presupuestoNew.presupuesto.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TipoIngreso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    protected TipoIngreso(){}

    public TipoIngreso(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
