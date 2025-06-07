package com.presupuestoNew.presupuesto.Interfaz;

import java.time.LocalDate;

public interface OperacionInterfaz {

    // Interfaz para los Modelos de Datos de las Operaciones

    // Metodos GET
    public Long getId();
    public LocalDate getFechaOperacion();
    public LocalDate getFechaUpdate();
    public String getConcepto();
    public Double getMonto();
    public String getDenominacion();
    public String getTipoOperacionPersonalizada();

    // Metodos SET
    public void setId(Long id);
    public void setConcepto(String concepto);
    public void setMonto(Double monto);
    public void setDenominacion(String denominacion);
    public void setTipoOperacionPersonalizada(String tipoOperacionPersonalizada);

}
