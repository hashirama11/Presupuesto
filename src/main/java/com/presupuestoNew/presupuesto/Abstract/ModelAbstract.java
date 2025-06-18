package com.presupuestoNew.presupuesto.Abstract;

import com.presupuestoNew.presupuesto.Interfaz.OperacionInterfaz;
import jakarta.persistence.*;

import java.time.LocalDate;

@MappedSuperclass
public abstract class ModelAbstract implements OperacionInterfaz {
    // Modelo Gasto

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fechaOperacion;

    @PrePersist
    protected void onCreate(){
        fechaOperacion = LocalDate.now();
    }

    private LocalDate fechaUpdate;

    @PreUpdate
    protected void onUpdate(){
        fechaUpdate = LocalDate.now();
    }

    private String concepto;

    private Double monto;

    private String denominacion;

    private String tipoOperacionPersonalizada;

    protected ModelAbstract( String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada) {
        this.concepto = concepto;
        this.monto = monto;
        this.denominacion = denominacion;
        this.tipoOperacionPersonalizada = tipoOperacionPersonalizada;
    }

    protected ModelAbstract(){}

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }
    @Override
    public LocalDate getFechaUpdate() {
        return fechaUpdate;
    }
    @Override
    public String getConcepto() {
        return concepto;
    }
    @Override
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    @Override
    public String getTipoOperacionPersonalizada(){return tipoOperacionPersonalizada;}
    @Override
    public void setTipoOperacionPersonalizada(String tipoOperacionPersonalizada){this.tipoOperacionPersonalizada = tipoOperacionPersonalizada;}
    @Override
    public Double getMonto() {
        return monto;
    }
    @Override
    public void setMonto(Double monto) {
        this.monto = monto;
    }
    @Override
    public String getDenominacion() {
        return denominacion;
    }
    @Override
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public String toString() {
        return "ModelAbstract{" +
                "id=" + id +
                ", fechaOperacion=" + fechaOperacion +
                ", fechaUpdate=" + fechaUpdate +
                ", concepto='" + concepto + '\'' +
                ", monto=" + monto +
                ", denominacion='" + denominacion + '\'' +
                ", tipoOperacionPersonalizada='" + tipoOperacionPersonalizada + '\'' +
                '}';
    }
}
