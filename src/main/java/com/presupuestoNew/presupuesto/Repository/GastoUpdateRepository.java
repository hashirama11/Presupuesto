package com.presupuestoNew.presupuesto.Repository;

import com.presupuestoNew.presupuesto.Model.GastoUpdate;
import com.presupuestoNew.presupuesto.Model.GastoUpdateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GastoUpdateRepository extends JpaRepository<GastoUpdate, GastoUpdateId> {

    @Query("SELECT MAX(gu.id.versionNumber) FROM GastoUpdate gu WHERE gu.id.gastoId = :gastoId")
    Optional<Long> findMaxVersionByGastoId(@Param("gastoId") Long gastoId);
}
