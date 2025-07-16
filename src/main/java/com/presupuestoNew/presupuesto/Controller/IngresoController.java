package com.presupuestoNew.presupuesto.Controller;

import com.presupuestoNew.presupuesto.Model.IngresoModel;
import com.presupuestoNew.presupuesto.Service.IngresoService.IngresoCRUD;
import com.presupuestoNew.presupuesto.Service.IngresoService.IngresoReport;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/ingreso")
public class IngresoController {

    // Inyecciones de Servicios

    private final IngresoCRUD ingresoCRUD;
    private final IngresoReport ingresoReport;

    @Autowired
    public IngresoController(IngresoCRUD ingresoCRUD, IngresoReport ingresoReport){

        this.ingresoCRUD = ingresoCRUD;
        this.ingresoReport = ingresoReport;
    }

    // Controladores CRUD
        // Crear registro Ingreso
    @PostMapping("/create")
    public ResponseEntity<IngresoModel> createIngreso(@RequestBody IngresoModel ingresoModel){
        IngresoModel ingreso = ingresoCRUD.crudCreate(
                ingresoModel.getConcepto(),
                ingresoModel.getMonto(),
                ingresoModel.getDenominacion(),
                ingresoModel.getTipoOperacionPersonalizada(),
                ingresoModel.getTipoOperacion()

        );
        return ResponseEntity.status(HttpStatus.CREATED).body(ingreso);
    }

        // Obtener un registro de Ingreso
    @GetMapping("/get/{id}")
    public IngresoModel getIngresoId(@PathVariable Long id){
        return ingresoCRUD.crudRead(id);
    }

        // Actualizar un registro
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateIngreso(@PathVariable Long id,
                                                @RequestBody IngresoModel ingresoModel){
        ingresoCRUD.crudUpdate(id, ingresoModel.getConcepto(), ingresoModel.getMonto(), ingresoModel.getDenominacion(), ingresoModel.getTipoOperacionPersonalizada(),ingresoModel.getTipoOperacion() );
        return ResponseEntity.ok("Datos modificados con exito");
    }

        // Eliminar un registro
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIngreso(@PathVariable Long id){
        try {
            ingresoCRUD.crudDelete(id);
            return ResponseEntity.ok("Ingreso eliminado con exito");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }



    // Controladores Servicios

    // Controlador Total Ingreso
    @GetMapping("/total")
    public ResponseEntity<String> totalingreso(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var ingreso = ingresoReport.totalRange(inicio, fin);
            return ResponseEntity.ok("El total de ingreso es " + ingreso);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

    // Controlador promedio Ingreso
    @GetMapping("/promedio")
    public ResponseEntity<String> promedio(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var ingreso = ingresoReport.promedioReport(inicio, fin);
            return ResponseEntity.ok("El ingreso promedio es " + ingreso);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

    // Controlador max Ingreso
    @GetMapping("/max")
    public ResponseEntity<String> max(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var ingreso = ingresoReport.maxReport(inicio, fin);
            return ResponseEntity.ok("El ingreso maximo es " + ingreso);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

    // Controlador promedio Ingreso
    @GetMapping("/min")
    public ResponseEntity<String> min(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var min = ingresoReport.minReport(inicio, fin);
            return ResponseEntity.ok("El ingreso minimo es " + min);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }


}
