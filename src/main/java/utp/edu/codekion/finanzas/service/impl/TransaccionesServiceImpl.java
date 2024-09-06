package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Transacciones;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.repository.TransaccionRepository;
import utp.edu.codekion.finanzas.service.IService.ITransaccionService;

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
        return List.of();
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
}
