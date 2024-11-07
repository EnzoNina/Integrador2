package utp.edu.codekion.finanzas.model.dto;

import lombok.Data;

@Data
public class CuentaBancariaResponseDto {

    public int id;
    public String nombre_usuario;
    public String nombre_banco;
    public String numero_cuenta;
    public String numero_cuenta_interbancario;
    public String tipo_cuenta;
    public String numero_tarjeta;
    public String tipo_tarjeta;

}