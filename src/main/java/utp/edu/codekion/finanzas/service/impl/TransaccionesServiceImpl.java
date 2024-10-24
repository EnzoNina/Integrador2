package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.dto.CategoriaGastoDto;
import utp.edu.codekion.finanzas.model.dto.IngresoMesDto;
import utp.edu.codekion.finanzas.repository.TransaccionRepository;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionesServiceImpl implements ITransaccionService {

    //Repository
    private final TransaccionRepository transaccionRepository;


    @Override
    public List<Transacciones> findAll() {
        return transaccionRepository.findAll();
    }

    @Override
    public List<Transacciones> findByUsuario(Usuario usuario) {
        return transaccionRepository.findByUsuario(usuario);
    }

    @Override
    public Transacciones findById(Integer id) {
        return transaccionRepository.findById(id).orElse(null);
    }

    @Override
    public Transacciones save(Transacciones transacciones) {
        return transaccionRepository.save(transacciones);
    }

    @Override
    public void delete(Integer id) {
        Transacciones transaccion = transaccionRepository.findById(id).orElse(null);
        assert transaccion != null;
        transaccionRepository.delete(transaccion);
    }

    @Override
    public BigDecimal sumarTransaccionesPorCategoriaAndUsuario(Integer id_categoria, Usuario usuario) {
        return transaccionRepository.sumarTransaccionesPorCategoriaAndUsuario(id_categoria, usuario);
    }

    @Override
    public BigDecimal balanceTotal(Integer id_usuario) {
        return transaccionRepository.balanceTotal(id_usuario);
    }

    @Override
    public List<IngresoMesDto> ingresosPorMes(Integer id_usuario) {
        return transaccionRepository.ingresosPorMes(id_usuario);
    }

    @Override
    public List<IngresoMesDto> gastosPorMes(Integer id_usuario) {
        return transaccionRepository.gastosPorMes(id_usuario);
    }

    @Override
    public List<Transacciones> transaccionesRecientes(Integer id_usuario) {
        return transaccionRepository.transaccionesRecientes(id_usuario);
    }

    @Override
    public List<CategoriaGastoDto> gastosPorCategoria(Integer id_usuario) {
        return transaccionRepository.gastosPorCategoria(id_usuario);
    }


}
