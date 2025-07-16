package com.presupuestoNew.presupuesto.Repository;

import com.presupuestoNew.presupuesto.Enum.GastoEnum;
import com.presupuestoNew.presupuesto.Model.GastoModel;
import com.presupuestoNew.presupuesto.Model.GastoUpdate;
import com.presupuestoNew.presupuesto.Model.GastoUpdateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GastoRepository extends JpaRepository<GastoModel, Long> {

    List<GastoModel> findByFechaOperacionBetween(LocalDate inicio, LocalDate fin);


    List<GastoModel> findByTipoOperacion(GastoEnum ingresoEnum);

    List<GastoModel> findByMonto(Double monto);
}

