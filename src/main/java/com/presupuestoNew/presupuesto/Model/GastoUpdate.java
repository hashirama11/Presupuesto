package com.presupuestoNew.presupuesto.Model;

import com.presupuestoNew.presupuesto.Abstract.ModelAbstractUpdate;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ActualizacionTipoGasto")
public class GastoUpdate extends ModelAbstractUpdate {

    /*
    * Entidad para Registro de cambios en la entidad Gasto
    * Solo aplica si se uso el metodo Update de la clase GastoModel
    */

    @EmbeddedId
    private GastoUpdateId id;

    @MapsId("gastoId")  // Mapea al campo gastoId del EmbeddedId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gasto_id", insertable = false, updatable = false)
    private GastoModel gasto;

    private LocalDate fechacaptura;

    // Constructor para capturar estado
    public GastoUpdate(GastoModel gastoOriginal, Long version) {
        super(
                gastoOriginal.getConcepto(),
                gastoOriginal.getMonto(),
                gastoOriginal.getDenominacion(),
                gastoOriginal.getTipoOperacionPersonalizada()
        );
        this.id = new GastoUpdateId(gastoOriginal.getId(), version);
        this.gasto = gastoOriginal;
        this.fechacaptura = LocalDate.now();

    }

    protected GastoUpdate() {}

    public GastoUpdateId getId() {
        return id;
    }

    public GastoModel getGasto() {
        return gasto;
    }

    public LocalDate getFechacaptura() {
        return fechacaptura;
    }
}


