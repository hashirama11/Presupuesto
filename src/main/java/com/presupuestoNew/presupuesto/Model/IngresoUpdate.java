package com.presupuestoNew.presupuesto.Model;

import com.presupuestoNew.presupuesto.Abstract.ModelAbstractUpdate;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "IngresoUpdate")
public class IngresoUpdate extends ModelAbstractUpdate {

    /*
     * Entidad para Registro de cambios en la entidad Ingreso
     * Solo aplica si se uso el metodo Update de la clase IngresoModel
     */

    @EmbeddedId
    private IngresoUpdateId id;

    @MapsId("ingresoId")  // Mapea al campo gastoId del EmbeddedId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingreso_id", insertable = false, updatable = false)
    private IngresoModel ingreso;

    private LocalDate fechacaptura;

    // Constructor para capturar estado
    public IngresoUpdate(IngresoModel ingresoOriginal, Long version){
        super(
                ingresoOriginal.getConcepto(),
                ingresoOriginal.getMonto(),
                ingresoOriginal.getDenominacion(),
                ingresoOriginal.getTipoOperacionPersonalizada()
        );
        this.id = new IngresoUpdateId(ingresoOriginal.getId(), version);
        this.ingreso = ingresoOriginal;
        this.fechacaptura = LocalDate.now();
    }

    protected IngresoUpdate(){}

    public IngresoUpdateId getId() {
        return id;
    }

    public IngresoModel getIngreso() {
        return ingreso;
    }

    public LocalDate getFechacaptura() {
        return fechacaptura;
    }
}
