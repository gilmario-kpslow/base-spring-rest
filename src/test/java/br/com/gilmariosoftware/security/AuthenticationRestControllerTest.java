package br.com.gilmariosoftware.security;

import br.com.gilmariosoftware.security.jwt.JwtTokenUtil;
import br.com.gilmariosoftware.security.jwt.JwtUserFactory;
import br.com.gilmariosoftware.usuario.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author gilmario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthenticationRestControllerTest() {
    }

    @Test
    public void testeCreateAuthenticationTokenMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"admin\",\"password\":\"123456\"}"))
                .andExpect(status().isOk()).andDo((mr) -> {
            System.out.println(mr.getResponse().getContentAsString());
        });
    }

    @Test
    public void testeRefreshTokenMethod() throws Exception {

        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        UserDetails details = JwtUserFactory.create(usuario);
        String token = jwtTokenUtil.generateToken(details);

        mvc.perform(MockMvcRequestBuilders.post("/api/refresh")
                .contentType(MediaType.APPLICATION_JSON).header("Authorization", token))
                .andExpect(status().isOk()).andDo((mr) -> {
            System.out.println(mr.getResponse().getContentAsString());
        });

    }

}
