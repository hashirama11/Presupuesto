package com.presupuestoNew.presupuesto.Interfaz;

public interface CrudInterfaz<T, E extends Enum<E>>{

    T crudCreate(String concepto,Double monto, String denominacion, String tipoOperaccionPersonalizada, E tipoOperacion );
    T crudRead(Long id);
    void crudUpdate(Long id, String concepto,Double monto, String denominacion, String tipoOperacionPersonalizada,  E tipoOperacion );
    void crudDelete(Long id);

}
