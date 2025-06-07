package com.presupuestoNew.presupuesto.Repository;

import com.presupuestoNew.presupuesto.Enum.IngresoEnum;
import com.presupuestoNew.presupuesto.Model.IngresoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<IngresoModel, Long> {

    List<IngresoModel> findByFechaOperacionBetween(LocalDate inicio, LocalDate fin);

    List<IngresoModel> findByTipoOperacion(IngresoEnum ingresoEnum);

    List<IngresoModel> findByMonto(Double monto);

}
