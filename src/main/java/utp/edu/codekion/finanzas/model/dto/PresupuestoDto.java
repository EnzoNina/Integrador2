package utp.edu.codekion.finanzas.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PresupuestoDto {

    private Integer id_usuario;
    private Integer id_categoria_usuario;
    private String nombre;
    private String descripcion;
    private BigDecimal monto;

}