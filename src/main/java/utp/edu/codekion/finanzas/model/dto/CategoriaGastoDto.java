package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CategoriaGastoDto {

    private String descripcion;
    private BigDecimal monto;

    public CategoriaGastoDto(String descripcion, BigDecimal monto) {
        this.descripcion = descripcion;
        this.monto = monto;
    }
}
