package utp.edu.codekion.finanzas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class IngresoMesDto {

    private String mes;
    private BigDecimal total;

    public IngresoMesDto(String mes, BigDecimal total) {
        this.mes = mes;
        this.total = total;
    }
}
