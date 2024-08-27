package utp.edu.codekion.finanzas.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utp.edu.codekion.finanzas.model.Usuario;
import utp.edu.codekion.finanzas.repository.UsuarioRepository;
import utp.edu.codekion.finanzas.service.IService.IUsuarioService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioImpl implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
