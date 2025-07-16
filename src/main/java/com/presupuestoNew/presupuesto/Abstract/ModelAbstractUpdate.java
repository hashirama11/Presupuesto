package com.presupuestoNew.presupuesto.Abstract;

import jakarta.persistence.MappedSuperclass;

import java.time.LocalDate;

@MappedSuperclass
public abstract class ModelAbstractUpdate {

    private LocalDate fechaOperacion;

    private LocalDate fechaUpdate;

    private String concepto;

    private Double monto;

    private String denominacion;

    private String tipoOperacionPersonalizada;

    protected ModelAbstractUpdate(String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada) {
        this.concepto = concepto;
        this.monto = monto;
        this.denominacion = denominacion;
        this.tipoOperacionPersonalizada = tipoOperacionPersonalizada;
    }

    protected ModelAbstractUpdate(){}

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public LocalDate getFechaUpdate() {
        return fechaUpdate;
    }

    public String getConcepto() {
        return concepto;
    }

    public Double getMonto() {
        return monto;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public String getTipoOperacionPersonalizada() {
        return tipoOperacionPersonalizada;
    }

    @Override
    public String toString() {
        return "ModelAbstractUpdate{" +
                "fechaOperacion=" + fechaOperacion +
                ", fechaUpdate=" + fechaUpdate +
                ", concepto='" + concepto + '\'' +
                ", monto=" + monto +
                ", denominacion='" + denominacion + '\'' +
                ", tipoOperacionPersonalizada='" + tipoOperacionPersonalizada + '\'' +
                '}';
    }
}
