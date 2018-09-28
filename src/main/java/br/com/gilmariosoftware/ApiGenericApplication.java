/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware;

import br.com.gilmariosoftware.usuario.Usuario;
import br.com.gilmariosoftware.usuario.UsuarioService;
import java.util.Objects;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author gilmario
 */
@SpringBootApplication
public class ApiGenericApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGenericApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UsuarioService service, PasswordEncoder encoder) {
        return args -> {
            Usuario admin = new Usuario();
            admin.setNome("Administrador");
            admin.setUsername("admin");
            admin.setPassword("123456");
            Usuario find = service.findByUsername(admin.getUsername());
            if (Objects.isNull(find)) {
                service.createOrUpdate(admin);
            }
        };
    }

}
