package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Cuenta;

import java.util.List;

public interface ICuentasBancariasService {

    List<Cuenta> listarCuentasPorUsuario(int id_usuario);

    Cuenta obtenerCuentaPorId(int id);

    Cuenta guardarCuenta(Cuenta cuenta);

}