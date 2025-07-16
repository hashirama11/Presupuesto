package com.presupuestoNew.presupuesto.Controller;

import com.presupuestoNew.presupuesto.Model.GastoModel;
import com.presupuestoNew.presupuesto.Service.GastoService.GastoCRUD;
import com.presupuestoNew.presupuesto.Service.GastoService.GastoReport;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
@RequestMapping("/gasto")
public class GastoController {
    
    // Inyeccion de dependencias
    
    private final GastoCRUD gastoCRUD;
    private final GastoReport gastoReport;

    @Autowired
    public GastoController(GastoCRUD gastoCRUD, GastoReport gastoReport){
        this.gastoCRUD = gastoCRUD;
        this.gastoReport = gastoReport;
    }

    // Controladores CRUD

    // Crear registro gasto
    @PostMapping("/create")
    public ResponseEntity<GastoModel> creategasto(@RequestBody GastoModel gastoModel){
        GastoModel gasto = gastoCRUD.crudCreate(
                gastoModel.getConcepto(),
                gastoModel.getMonto(),
                gastoModel.getDenominacion(),
                gastoModel.getTipoOperacionPersonalizada(),
                gastoModel.getTipoOperacion()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(gasto);
    }

    // Obtener un registro de gasto
    @GetMapping("/get/{id}")
    public GastoModel getgastoId(@PathVariable Long id){
        return gastoCRUD.crudRead(id);
    }

    // Actualizar un registro
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updategasto(@PathVariable Long id,
                                                @RequestBody GastoModel gastoModel){
        gastoCRUD.crudUpdate(id, gastoModel.getConcepto(), gastoModel.getMonto(), gastoModel.getDenominacion(), gastoModel.getTipoOperacionPersonalizada(), gastoModel.getTipoOperacion());
        return ResponseEntity.ok("Datos modificados con exito");
    }

    // Eliminar un registro
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletegasto(@PathVariable Long id){
        try {
            gastoCRUD.crudDelete(id);
            return ResponseEntity.ok("gasto eliminado con exito");
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        }
    }

    // Controladores de Servicios

    // Controlador Total Gasto
    @GetMapping("/total")
    public ResponseEntity<String> totalgasto(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var gasto = gastoReport.totalRange(inicio, fin);
            return ResponseEntity.ok("El total de gasto es " + gasto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

    // Controlador promedio Gasto
    @GetMapping("/promedio")
    public ResponseEntity<String> promedio(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var gasto = gastoReport.promedioReport(inicio, fin);
            return ResponseEntity.ok("El gasto promedio es " + gasto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

    // Controlador max Gasto
    @GetMapping("/max")
    public ResponseEntity<String> max(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var gasto = gastoReport.maxReport(inicio, fin);
            return ResponseEntity.ok("El gasto maximo es " + gasto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

    // Controlador promedio Gasto
    @GetMapping("/min")
    public ResponseEntity<String> min(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
        try{
            var gasto = gastoReport.minReport(inicio, fin);
            return ResponseEntity.ok("El gasto minimo es " + gasto);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error " + e.getMessage());
        }
    }

}
