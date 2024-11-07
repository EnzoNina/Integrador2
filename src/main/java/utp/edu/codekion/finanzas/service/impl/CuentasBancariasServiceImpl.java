package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Cuenta;
import utp.edu.codekion.finanzas.repository.CuentaBancariasRepository;
import utp.edu.codekion.finanzas.service.IService.ICuentasBancariasService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentasBancariasServiceImpl implements ICuentasBancariasService {

    private final CuentaBancariasRepository cuentaBancariasRepository;

    @Override
    public List<Cuenta> listarCuentasPorUsuario(int id_usuario) {
        return cuentaBancariasRepository.listarCuentasPorUsuario(id_usuario);
    }

    @Override
    public Cuenta obtenerCuentaPorId(int id) {
        return cuentaBancariasRepository.findById(id).orElse(null);
    }

    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
        return cuentaBancariasRepository.save(cuenta);
    }
}
