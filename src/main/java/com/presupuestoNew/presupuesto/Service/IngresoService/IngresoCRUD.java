package com.presupuestoNew.presupuesto.Service.IngresoService;

import com.presupuestoNew.presupuesto.Enum.IngresoEnum;
import com.presupuestoNew.presupuesto.Interfaz.CrudInterfaz;
import com.presupuestoNew.presupuesto.Model.IngresoModel;
import com.presupuestoNew.presupuesto.Repository.IngresoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IngresoCRUD implements CrudInterfaz<IngresoModel, IngresoEnum> {

    // Clase operaciones CRUD

    // Inyeccion de dependencias
    private final IngresoRepository ingresoRepository;

    @Autowired
    public IngresoCRUD(IngresoRepository ingresoRepository){
        this.ingresoRepository = ingresoRepository;
    }

    // Metodos

    // Metodo Create
    @Override
    public IngresoModel crudCreate(String concepto, Double monto, String denominacion,  String tipoOperaccionPersonalizada, IngresoEnum tipoOperacion){
        return ingresoRepository.save(new IngresoModel(concepto, monto, denominacion, tipoOperaccionPersonalizada, tipoOperacion));
    }

    // Metodo Read on {id}
    @Override
    public IngresoModel crudRead(Long id){
        return ingresoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ingreso con id " + id + " no encontrado"));
    }

    // Metodo Update
    @Transactional
    @Override
    public void crudUpdate(Long id, String concepto, Double monto, String denominacion, String tipoOperacionPersonalizada, IngresoEnum tipoOperacion ){
        IngresoModel ingresoUpdate = ingresoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ingreso con id " + id + " no encontrado"));

        ingresoUpdate.setConcepto(concepto);
        ingresoUpdate.setTipoOperacion(tipoOperacion);
        ingresoUpdate.setTipoOperacionPersonalizada(tipoOperacionPersonalizada);
        ingresoUpdate.setMonto(monto);
        ingresoUpdate.setDenominacion(denominacion);

        ingresoRepository.save(ingresoUpdate);

    }

    // Metodo Delete
    @Override
    public void crudDelete(Long id){
        if(!ingresoRepository.existsById(id)){
            throw new EntityNotFoundException("Ingreso con id " + id + " no existe");
        }
        ingresoRepository.deleteById(id);
    }
}
