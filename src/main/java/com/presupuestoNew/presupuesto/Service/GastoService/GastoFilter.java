package com.presupuestoNew.presupuesto.Service.GastoService;

import com.presupuestoNew.presupuesto.Enum.GastoEnum;
import com.presupuestoNew.presupuesto.Interfaz.FilterInterfaz;
import com.presupuestoNew.presupuesto.Model.GastoModel;
import com.presupuestoNew.presupuesto.Repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GastoFilter implements FilterInterfaz<GastoModel, GastoEnum> {

    // Inyeccion de dependecias
    private final GastoRepository gastoRepository;

    @Autowired
    public GastoFilter(GastoRepository gastoRepository){
        this.gastoRepository = gastoRepository;
    }

    // Filtros

    // Filtrar por fecha concreta: {Trae todos los registros} YYYY-MM-DD
    @Override
    public List<GastoModel> filterDate(LocalDate inicio, LocalDate fin){
        return gastoRepository.findByFechaOperacionBetween(inicio, fin);
    }

    // Filtrar por tipo de Gasto
    @Override
    public List<GastoModel> filterEnum(GastoEnum tipoOperacion){
        return gastoRepository.findByTipoOperacion(tipoOperacion);
    }


    // Filtrar por Rango de Monto
    @Override
    public List<GastoModel> filterMonto(Double monto){
        return gastoRepository.findByMonto(monto);
    }
}
