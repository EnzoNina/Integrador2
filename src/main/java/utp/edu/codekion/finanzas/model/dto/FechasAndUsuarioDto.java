package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FechasAndUsuarioDto {

    private String id_usuario;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
}
