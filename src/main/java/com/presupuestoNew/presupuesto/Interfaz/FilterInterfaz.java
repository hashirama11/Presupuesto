package com.presupuestoNew.presupuesto.Interfaz;

import java.time.LocalDate;
import java.util.List;

public interface FilterInterfaz<T, E extends Enum<E>>{

    List<T> filterDate(LocalDate inicio, LocalDate fin);
    List<T> filterEnum(E e);
    List<T> filterMonto(Double monto);
}
