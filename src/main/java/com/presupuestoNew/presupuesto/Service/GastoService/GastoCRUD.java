package com.presupuestoNew.presupuesto.Service.GastoService;

import com.presupuestoNew.presupuesto.Enum.GastoEnum;
import com.presupuestoNew.presupuesto.Interfaz.CrudInterfaz;
import com.presupuestoNew.presupuesto.Model.GastoModel;
import com.presupuestoNew.presupuesto.Model.GastoUpdate;
import com.presupuestoNew.presupuesto.Repository.GastoRepository;
import com.presupuestoNew.presupuesto.Repository.GastoUpdateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GastoCRUD implements CrudInterfaz<GastoModel, GastoEnum> {

    // Clase operaciones CRUD

    // Inyeccion de dependencias
    private final GastoRepository gastoRepository;
    private final GastoUpdateRepository gastoUpdateRepository;

    @Autowired
    public GastoCRUD(GastoRepository gastoRepository, GastoUpdateRepository gastoUpdateRepository){
        this.gastoRepository = gastoRepository;
        this.gastoUpdateRepository = gastoUpdateRepository;
    }

    // Metodos

    // Metodo Create
    @Override
    public GastoModel crudCreate(String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada, GastoEnum tipoOperacion){
        return gastoRepository.save(new GastoModel(concepto, monto, denominacion, null, tipoOperacion));
    }

    // Metodo Read on {id}
    @Override
    public GastoModel crudRead(Long id){
        return gastoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Gasto con id " + id + " no encontrado"));
    }

    // Metodo Update
    @Transactional
    @Override
    public void crudUpdate(Long id, String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada, GastoEnum tipoOperacion){
        GastoModel gastoUpdate = gastoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Gasto con id " + id + " no encontrado"));

        // Obtener próxima versión
        Long proximaVersion = gastoUpdateRepository.findMaxVersionByGastoId(id)
                .orElse(0L) + 1;

        // Guardar estado PREVIO (versión N)
        GastoUpdate historico = new GastoUpdate(gastoUpdate, proximaVersion);
        gastoUpdateRepository.save(historico);

        gastoUpdate.setConcepto(concepto);
        gastoUpdate.setMonto(monto);
        gastoUpdate.setDenominacion(denominacion);
        gastoUpdate.setTipoOperacionPersonalizada(tipoOperacionPersonalizada);
        gastoUpdate.setTipoOperacion(tipoOperacion);

        // gastoRepository.save(gastoUpdate); No necesario

    }

    // Metodo Delete
    @Override
    public void crudDelete(Long id){
        if(!gastoRepository.existsById(id)){
            throw new EntityNotFoundException("Gasto con id " + id + " no existe");
        }
        gastoRepository.deleteById(id);
    }

}
