package utp.edu.codekion.finanzas.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FechasAndUsuarioDto {

    private String id_usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
