package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;

@Data
public class UsuariosCategoriasRequestDto {

    private String id_usuario;
    private String id_tipo_transaccion;
    private String id_categoria;
    private String descripcion;

}