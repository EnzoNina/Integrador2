package utp.edu.codekion.finanzas.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuariosCategoriasResponseDto {

    private String id;
    private String tipo_transaccion;
    private String categoria;
    private String descripcion;

}