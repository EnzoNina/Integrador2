package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Banco;
import utp.edu.codekion.finanzas.repository.BancoRepository;
import utp.edu.codekion.finanzas.service.IService.IBancoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BancoServiceImpl implements IBancoService {

    private final BancoRepository bancoRepository;

    @Override
    public Banco findById(int id) {
        return bancoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Banco> findAll() {
        return bancoRepository.findAll();
    }
}
