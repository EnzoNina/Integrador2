package utp.edu.codekion.finanzas.service.IService;

import utp.edu.codekion.finanzas.model.Banco;

import java.util.List;

public interface IBancoService {

    Banco findById(int id);

    List<Banco> findAll();

}