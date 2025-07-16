package com.presupuestoNew.presupuesto.Repository;

import com.presupuestoNew.presupuesto.Model.IngresoUpdate;
import com.presupuestoNew.presupuesto.Model.IngresoUpdateId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngresoUpdateRepository extends JpaRepository<IngresoUpdate, IngresoUpdateId> {

    @Query("SELECT MAX(gu.id.versionNumber) FROM IngresoUpdate gu WHERE gu.id.ingresoId = :ingresoId")
    Optional<Long> findMaxVersionByIngresoId(@Param("ingresoId") Long ingresoId);
}
