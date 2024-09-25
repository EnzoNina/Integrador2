package utp.edu.codekion.finanzas.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PresupuestoDto {

    private String id_usuario;
    private String id_categoria;
    private String nombre;
    private String descripcion;
    private BigDecimal monto;

}
