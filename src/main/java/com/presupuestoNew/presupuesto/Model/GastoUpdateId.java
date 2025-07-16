package com.presupuestoNew.presupuesto.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GastoUpdateId implements Serializable {
    @Column(name = "gasto_id", nullable = false)
    private Long gastoId;       // ID del gasto original

    @Column(name = "version", nullable = false)
    private Long versionNumber; // Número de versión (incremental)

    // Constructores
    public GastoUpdateId() {}

    public GastoUpdateId(Long gastoId, Long versionNumber) {
        this.gastoId = gastoId;
        this.versionNumber = versionNumber;
    }

    // Getters (NO setters para inmutabilidad)
    public Long getGastoId() { return gastoId; }
    public Long getVersionNumber() { return versionNumber; }

    // ¡Sobreescribir equals() y hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GastoUpdateId that = (GastoUpdateId) o;
        return Objects.equals(gastoId, that.gastoId) &&
                Objects.equals(versionNumber, that.versionNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gastoId, versionNumber);
    }
}
