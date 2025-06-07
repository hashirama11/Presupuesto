package com.presupuestoNew.presupuesto.Interfaz;

import java.time.LocalDate;

public interface ReportInterfaz<E extends Enum<E>> {

    Double total();
    Double totalRange(LocalDate inicio, LocalDate fin);
    Double promedioReport(LocalDate inicio, LocalDate fin);
    Double maxReport(LocalDate inicio, LocalDate fin);
    Double minReport(LocalDate inicio, LocalDate fin);
    void exportToCsvFecha(String filePath, LocalDate inicio, LocalDate fin);
    void exportToCsvTipo(String filePath, LocalDate inicio, LocalDate fin, E tipoOperacion);

}
