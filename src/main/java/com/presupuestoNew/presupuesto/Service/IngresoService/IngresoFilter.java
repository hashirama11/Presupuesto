package com.presupuestoNew.presupuesto.Service.IngresoService;

import com.presupuestoNew.presupuesto.Enum.IngresoEnum;
import com.presupuestoNew.presupuesto.Interfaz.FilterInterfaz;
import com.presupuestoNew.presupuesto.Model.IngresoModel;
import com.presupuestoNew.presupuesto.Repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IngresoFilter implements FilterInterfaz<IngresoModel, IngresoEnum> {

    // Inyeccion de dependencias

    private final IngresoRepository ingresoRepository;

    @Autowired
    public IngresoFilter(IngresoRepository ingresoRepository){
        this.ingresoRepository = ingresoRepository;
    }

    // Filtros

    // Filtrar por fecha concreta: {Trae todos los registros} YYYY-MM-DD
    @Override
    public List<IngresoModel> filterDate(LocalDate inicio, LocalDate fin){
        return ingresoRepository.findByFechaOperacionBetween(inicio, fin);
    }

    // Filtrar por tipo de Ingreso
    @Override
    public List<IngresoModel> filterEnum(IngresoEnum tipoOperacion){
        return ingresoRepository.findByTipoOperacion(tipoOperacion);
    }

    // Filtrar por Rango de Monto
    @Override
    public List<IngresoModel> filterMonto(Double monto){
        return ingresoRepository.findByMonto(monto);
    }
}
