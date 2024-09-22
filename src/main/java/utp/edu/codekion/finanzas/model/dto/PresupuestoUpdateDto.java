package utp.edu.codekion.finanzas.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PresupuestoUpdateDto {

    private String nombre;
    private String descripcion;
    private BigDecimal monto;

}