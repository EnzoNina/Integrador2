package utp.edu.codekion.finanzas.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TransaccionResponseDto {

    private String id;
    private String usuario;
    private String categoria;
    private String tipo_transaccion;
    private String tipo_concepto;
    private String frecuencia;
    private String divisa;
    private String monto;
    private String descripcion;
    private String fecha;
    private String numeroCuenta;

}
