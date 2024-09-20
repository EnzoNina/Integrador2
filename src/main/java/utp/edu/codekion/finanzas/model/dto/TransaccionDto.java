package utp.edu.codekion.finanzas.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class TransaccionDto {

    private String id_usuario;
    private String id_tipo_categoria;
    private String id_tipo_concepto;
    private String id_tipo_frecuencia;
    private String id_tipo_divisa;
    private BigDecimal monto;
    private String descripcion;

}
