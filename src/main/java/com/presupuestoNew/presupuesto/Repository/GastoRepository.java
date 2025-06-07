package com.presupuestoNew.presupuesto.Repository;

import com.presupuestoNew.presupuesto.Enum.GastoEnum;
import com.presupuestoNew.presupuesto.Model.GastoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<GastoModel, Long> {

    List<GastoModel> findByFechaOperacionBetween(LocalDate inicio, LocalDate fin);


    List<GastoModel> findByTipoOperacion(GastoEnum ingresoEnum);

    List<GastoModel> findByMonto(Double monto);
}
