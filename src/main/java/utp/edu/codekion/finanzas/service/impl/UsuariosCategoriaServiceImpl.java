package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.model.UsuariosCategoria;
import utp.edu.codekion.finanzas.repository.UsuariosCategoriaRepository;
import utp.edu.codekion.finanzas.service.IService.IUsuarioCategoriaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuariosCategoriaServiceImpl implements IUsuarioCategoriaService {

    private final UsuariosCategoriaRepository usuariosCategoriaRepository;

    @Override
    public List<UsuariosCategoria> findByUsuario(Usuario usuario) {
        return usuariosCategoriaRepository.findByUsuario(usuario);
    }

    @Override
    public UsuariosCategoria findById(Integer id) {
        return usuariosCategoriaRepository.findById(id).orElse(null);
    }

    @Override
    public UsuariosCategoria save(UsuariosCategoria usuariosCategoria) {
        return usuariosCategoriaRepository.save(usuariosCategoria);
    }

    @Override
    public void delete(Integer id) {
        usuariosCategoriaRepository.deleteById(id);
    }

    @Override
    public UsuariosCategoria update(UsuariosCategoria usuariosCategoria) {
        return usuariosCategoriaRepository.save(usuariosCategoria);
    }
}
