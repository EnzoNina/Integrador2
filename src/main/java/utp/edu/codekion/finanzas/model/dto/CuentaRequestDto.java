package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;

@Data
public class CuentaRequestDto {

    public int id_usuario;
    public int id_banco;
    public String numero_cuenta;
    public String numero_cuenta_interbancario;
    public int id_tipo_cuenta;
    public String numero_tarjeta;
    public int id_tipo_tarjeta;

}