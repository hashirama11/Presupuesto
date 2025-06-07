package com.presupuestoNew.presupuesto.Service.GastoService;

import com.presupuestoNew.presupuesto.Enum.GastoEnum;
import com.presupuestoNew.presupuesto.Interfaz.ReportInterfaz;
import com.presupuestoNew.presupuesto.Model.GastoModel;
import com.presupuestoNew.presupuesto.Repository.GastoRepository;
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
public class GastoReport implements ReportInterfaz<GastoEnum> {

    // Inyeccion de dependencias

    private final GastoRepository gastoRepository;

    @Autowired
    public GastoReport(GastoRepository gastoRepository){
        this.gastoRepository = gastoRepository;
    }

    // Total de Gasto
    @Override
    public Double total(){
        return gastoRepository.findAll().stream()
                .map(GastoModel::getMonto)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    // Total de Gasto por Rango
    @Override
    public Double totalRange(LocalDate inicio, LocalDate fin){
       List<GastoModel> gastos =  gastoRepository.findByFechaOperacionBetween(inicio, fin);
       return gastos.isEmpty() ? 0.0 : gastos.stream()
                .mapToDouble(GastoModel::getMonto)
               .filter(Objects::nonNull)
                .sum();
    }

    // Gasto promedio por Rango
    @Override
    public Double promedioReport(LocalDate inicio, LocalDate fin){
        List<GastoModel> gastos = gastoRepository.findByFechaOperacionBetween(inicio, fin);
        return gastos.isEmpty() ? 0.0 : gastos.stream()
                .mapToDouble(GastoModel::getMonto)
                .filter(Objects::nonNull)
                .average()
                .orElse(0.0);
    }

    // Gasto maximo por rango
    @Override
    public Double maxReport(LocalDate inicio, LocalDate fin){
        List<GastoModel> max = gastoRepository.findByFechaOperacionBetween(inicio, fin);
        return max.isEmpty() ? 0.0 : max.stream()
                .mapToDouble(GastoModel::getMonto)
                .filter(Objects::nonNull)
                .max()
                .orElse(0.0);
    }
    @Override
    // Gasto minimo por rango
    public Double minReport(LocalDate inicio, LocalDate fin){
        List<GastoModel> min = gastoRepository.findByFechaOperacionBetween(inicio, fin);
        return min.isEmpty() ? 0.0 : min.stream()
                .mapToDouble(GastoModel::getMonto)
                .filter(Objects::nonNull)
                .min()
                .orElse(0.0);
    }

    // Reporte por rango de Fecha en formato CSV
    @Override
    public void exportToCsvFecha(String filePath, LocalDate inicio, LocalDate fin){

        List<GastoModel> ingresCSV = gastoRepository.findByFechaOperacionBetween(inicio, fin);

        try(CSVWriter writer  = new CSVWriter((new FileWriter(filePath)))){
            writer.writeNext(new String[]{"ID","Fecha de Operacion", "Concepto", "Tipo de Operacion", "Monto", "Denomicacion"});
            for(GastoModel gastoModel : ingresCSV){
                writer.writeNext(new String[]{
                        String.valueOf(gastoModel.getId()),
                        String.valueOf(gastoModel.getFechaOperacion()),
                        String.valueOf(gastoModel.getConcepto()),
                        String.valueOf(gastoModel.getTipoOperacion()),
                        String.valueOf(gastoModel.getMonto()),
                        String.valueOf(gastoModel.getDenominacion())
                });
            }

            // Estadisticas Generales
            writer.writeNext(new String[]{"Gasto Maximo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(maxReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Gasto Minimo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(minReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Gasto promedio del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(promedioReport(inicio, fin))
            });

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Reporte por rango de Fecha en formato CSV por tipo de gasto
    @Override
    public void exportToCsvTipo(String filePath, LocalDate inicio, LocalDate fin, GastoEnum gastoEnum){

        List<GastoModel> ingresCSV = gastoRepository.findByFechaOperacionBetween(inicio, fin).stream()
                .filter(gasto -> gasto.getTipoOperacion().equals(gastoEnum))
                .collect(Collectors.toList());

        try(CSVWriter writer  = new CSVWriter((new FileWriter(filePath)))){
            writer.writeNext(new String[]{"ID","Fecha de Operacion", "Concepto", "Tipo de Operacion", "Monto", "Denomicacion"});
            for(GastoModel gastoModel : ingresCSV){
                writer.writeNext(new String[]{
                        String.valueOf(gastoModel.getId()),
                        String.valueOf(gastoModel.getFechaOperacion()),
                        String.valueOf(gastoModel.getConcepto()),
                        String.valueOf(gastoModel.getTipoOperacion()),
                        String.valueOf(gastoModel.getMonto()),
                        String.valueOf(gastoModel.getDenominacion())
                });
            }

            // Estadisticas Generales
            writer.writeNext(new String[]{"Gasto Maximo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(maxReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Gasto Minimo del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(minReport(inicio, fin))
            });

            writer.writeNext(new String[]{"Gasto promedio del rengo de fecha Selecionado"});
            writer.writeNext(new String[]{
                    String.valueOf(promedioReport(inicio, fin))
            });

        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
