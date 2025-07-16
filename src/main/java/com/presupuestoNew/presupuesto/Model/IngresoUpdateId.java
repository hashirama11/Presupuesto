package com.presupuestoNew.presupuesto.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngresoUpdateId implements Serializable {

    @Column(name = "ingreso_id", nullable = false)
    private Long ingresoId;       // ID del gasto original

    @Column(name = "version", nullable = false)
    private Long versionNumber; // Número de versión (incremental)

    // Constructores
    public IngresoUpdateId(){}

    public IngresoUpdateId(Long ingresoId, Long versionNumber){
        this.ingresoId = ingresoId;
        this.versionNumber = versionNumber;

    }

    // Getters (NO setters para inmutabilidad)
    public Long getIngresooId() { return ingresoId; }
    public Long getVersionNumber() { return versionNumber; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngresoUpdateId that = (IngresoUpdateId) o;
        return Objects.equals(ingresoId, that.ingresoId) &&
                Objects.equals(versionNumber, that.versionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingresoId, versionNumber);
    }

}
