package com.presupuestoNew.presupuesto.Model;

import com.presupuestoNew.presupuesto.Abstract.ModelAbstract;
import com.presupuestoNew.presupuesto.Enum.IngresoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ingreso")
public class IngresoModel extends ModelAbstract {

    // Atributos especiales de la clase

    @Enumerated(EnumType.STRING)
    private IngresoEnum tipoOperacion;

    public IngresoEnum getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(IngresoEnum tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    // COnstructor de la clase
    public IngresoModel(String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada, IngresoEnum tipoOperacion){
        super(concepto, monto, denominacion, tipoOperacionPersonalizada);
        this.tipoOperacion = tipoOperacion;

    }

    public IngresoModel(){
        super();
    }

    @Override
    public String toString() {
        return "IngresoModel{" +
                "tipoOperacion=" + tipoOperacion +
                '}';
    }
}
