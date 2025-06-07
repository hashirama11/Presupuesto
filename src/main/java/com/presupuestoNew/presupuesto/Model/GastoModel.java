package com.presupuestoNew.presupuesto.Model;

import com.presupuestoNew.presupuesto.Abstract.ModelAbstract;
import com.presupuestoNew.presupuesto.Enum.GastoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;


@Entity
@Table(name = "Gasto")
public class GastoModel extends ModelAbstract {
    @Enumerated(EnumType.STRING)
    private GastoEnum tipoOperacion;

    public GastoEnum getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(GastoEnum tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public GastoModel(String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada, GastoEnum tipoOperacion){
        super(concepto, monto, denominacion, tipoOperacionPersonalizada);
        this.tipoOperacion = tipoOperacion;

    }

    public GastoModel(){
        super();
    }

    @Override
    public String toString(){
        return super.toString() + ", tipoOperacion='" + tipoOperacion + '\'' + '}';
    }

}
