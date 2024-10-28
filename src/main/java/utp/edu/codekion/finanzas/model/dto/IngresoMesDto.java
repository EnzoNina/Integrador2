package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
public class IngresoMesDto {

    private String mes;
    private BigDecimal total;

    public IngresoMesDto(String mes, BigDecimal total) {
        this.mes = mes;
        this.total = total;
    }
}
