package br.com.gilmariosoftware.security.jwt;

import br.com.gilmariosoftware.usuario.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static JwtUser create(Usuario user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities());
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
