package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PresupuestoDto {

    private Integer id_usuario;
    private Integer id_categoria_usuario;
    private String nombre;
    private String descripcion;
    private BigDecimal monto;
    private Integer id_cuenta;

}