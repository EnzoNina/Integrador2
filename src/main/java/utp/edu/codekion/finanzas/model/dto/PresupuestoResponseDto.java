package utp.edu.codekion.finanzas.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PresupuestoResponseDto {

    private String id;
    private String categoria;
    private String usuario;
    private String nombre;
    private String descripcion;
    private String monto;
    private String numeroCuenta;

}