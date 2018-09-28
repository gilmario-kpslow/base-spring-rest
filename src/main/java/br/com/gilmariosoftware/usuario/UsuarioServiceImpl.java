package br.com.gilmariosoftware.usuario;

import br.com.gilmariosoftware.generic.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author gilmario
 */
@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, UsuarioRepository> implements UsuarioService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario findByUsername(String username) {
        return this.repository.findByUsername(username);
    }

    @Override
    public Usuario createOrUpdate(Usuario entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.createOrUpdate(entity);
    }

}
