package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PresupuestoUpdateDto {

    private String nombre;
    private String descripcion;
    private BigDecimal monto;
    private String id_cuenta;

}