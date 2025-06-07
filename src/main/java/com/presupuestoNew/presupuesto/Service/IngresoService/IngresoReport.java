package com.presupuestoNew.presupuesto.Service.IngresoService;

import com.presupuestoNew.presupuesto.Enum.IngresoEnum;
import com.presupuestoNew.presupuesto.Interfaz.ReportInterfaz;
import com.presupuestoNew.presupuesto.Model.IngresoModel;
import com.presupuestoNew.presupuesto.Repository.IngresoRepository;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class IngresoReport implements ReportInterfaz<IngresoEnum> {

    // Inyeccion de dependencias

    private final IngresoRepository ingresoRepository;

    @Autowired
    public IngresoReport(IngresoRepository ingresoRepository){
        this.ingresoRepository = ingresoRepository;
    }

    // Total de Ingreso
    @Override
    public Double total(){
        return ingresoRepository.findAll().stream()
                .map(IngresoModel::getMonto)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    // Total de Ingreso por Rango
    @Override
    public Double totalRange(LocalDate inicio, LocalDate fin){
        List<IngresoModel> Ingresos =  ingresoRepository.findByFechaOperacionBetween(inicio, fin);
        return Ingresos.isEmpty() ? 0.0 : Ingresos.stream()
                .mapToDouble(IngresoModel::getMonto)
                .filter(Objects::nonNull)
                .sum();
    }

    // Ingreso promedio por Rango
    @Override
    public Double promedioReport(LocalDate inicio, LocalDate fin){
        List<IngresoModel> ingresos = ingresoRepository.findByFechaOperacionBetween(inicio, fin);
        return ingresos.isEmpty() ? 0.0 : ingresos.stream()
                .mapToDouble(IngresoModel::getMonto)
                .filter(Objects::nonNull)
                .average()
                .orElse(0.0);
    }
    @Override
    // Ingreso maximo por rango
    public Double maxReport(LocalDate inicio, LocalDate fin){
        List<IngresoModel> max = ingresoRepository.findByFechaOperacionBetween(inicio, fin);
        return max.isEmpty() ? 0.0 : max.stream()
                .mapToDouble(IngresoModel::getMonto)
                .filter(Objects::nonNull)
                .max()
                .orElse(0.0);
    }
    @Override
    // Ingreso minimo por rango
    public Double minReport(LocalDate inicio, LocalDate fin){
        List<IngresoModel> min = ingresoRepository.findByFechaOperacionBetween(inicio, fin);
        return min.isEmpty() ? 0.0 : min.stream()
                .mapToDouble(IngresoModel::getMonto)
                .filter(Objects::nonNull)
                .min()
                .orElse(0.0);
    }


    // Reporte por rango de Fecha en formato CSV
    @Override
    public void exportToCsvFecha(String filePath, LocalDate inicio, LocalDate fin){

        List<IngresoModel> ingresCSV = ingresoRepository.findByFechaOperacionBetween(inicio, fin);

        try(CSVWriter writer  = new CSVWriter((new FileWriter(filePath)))){
            writer.writeNext(new String[]{"ID","Fecha de Operacion", "Concepto", "Tipo de Operacion", "Monto", "Denomicacion"});
            for(IngresoModel ingreso : ingresCSV) {
                writer.writeNext(new String[]{
                        String.valueOf(ingreso.getId()),
                        String.valueOf(ingreso.getFechaOperacion()),
                        String.valueOf(ingreso.getConcepto()),
                        String.valueOf(ingreso.getTipoOperacion()),
                        String.valueOf(ingreso.getMonto()),
                        String.valueOf(ingreso.getDenominacion())
                });
            }

            // Estadisticas Generales
            writer.writeNext(new String[]{"Ingreso Maximo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(maxReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Ingreso Minimo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(minReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Ingreso promedio del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(promedioReport(inicio, fin))
            });

        }catch (IOException e){
            e.printStackTrace();
        }
    }



    // Reporte por rango de Fecha en formato CSV y por tipo de ingreso
    @Override
    public void exportToCsvTipo(String filePath, LocalDate inicio, LocalDate fin, IngresoEnum ingresoEnum){

        List<IngresoModel> ingresCSV = ingresoRepository.findByFechaOperacionBetween(inicio, fin).stream()
                .filter(ingreso -> ingreso.getTipoOperacion().equals(ingresoEnum)).collect(Collectors.toList());

        try(CSVWriter writer  = new CSVWriter((new FileWriter(filePath)))){
            writer.writeNext(new String[]{"ID","Fecha de Operacion", "Concepto", "Tipo de Operacion", "Monto", "Denomicacion"});
            for(IngresoModel ingreso : ingresCSV) {
                writer.writeNext(new String[]{
                        String.valueOf(ingreso.getId()),
                        String.valueOf(ingreso.getFechaOperacion()),
                        String.valueOf(ingreso.getConcepto()),
                        String.valueOf(ingreso.getTipoOperacion()),
                        String.valueOf(ingreso.getMonto()),
                        String.valueOf(ingreso.getDenominacion())
                });
            }

            // Estadisticas Generales
            writer.writeNext(new String[]{"Ingreso Maximo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(maxReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Ingreso Minimo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(minReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Ingreso promedio del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(promedioReport(inicio, fin))
            });

        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
