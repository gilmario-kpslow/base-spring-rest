package br.com.gilmariosoftware.usuario;

import br.com.gilmariosoftware.security.jwt.JwtTokenUtil;
import java.util.List;
import java.util.Objects;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import teste.util.JsonConverter;
import teste.util.LoginBuilder;

/**
 *
 * @author gilmario
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioResourceTest {

    @Autowired
    private MockMvc mvc;

    public UsuarioResourceTest() {
    }

    private static Usuario usuario;
    private static final String URL_RESOURCE = "/usuario";
    private final JsonConverter converter = new JsonConverter();
    private static String token;

    @Before
    public void setUp() throws Exception {
        if (Objects.isNull(usuario)) {
            usuario = new Usuario();
            usuario.setUsername("usuarioteste");
            usuario.setPassword("123456789");
            usuario.setNome("Usuario Teste");
        }
        if (Objects.isNull(token)) {
            token = new LoginBuilder().login(mvc, converter);
        }
    }

    @Test
    public void test01CreateMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post(URL_RESOURCE)
                .header(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(converter.toJson(usuario)))
                .andExpect(status().isOk()).andDo((mr) -> {
            usuario = converter.fromJson(mr.getResponse().getContentAsString(), Usuario.class);
            Objects.nonNull(usuario.getId());
        });
    }

    @Test
    public void test02ListMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_RESOURCE.concat("?page=0&size=10"))
                .header(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo((mr) -> {
            List<Usuario> resultado = converter.fromJsonList(mr.getResponse().getContentAsString(), Usuario.class);
            resultado.stream().findFirst().get();
        });
    }

    @Test
    public void test03UpdateMethod() throws Exception {
        usuario.setNome("Usaurio Teste01");
        mvc.perform(MockMvcRequestBuilders.put(URL_RESOURCE)
                .header(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(converter.toJson(usuario)))
                .andExpect(status().isOk()).andDo((mr) -> {
            Usuario resultado = converter.fromJson(mr.getResponse().getContentAsString(), Usuario.class);
            System.out.println(resultado.getNome().equals(usuario.getNome()));
        });
    }

    @Test
    public void test05CountMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_RESOURCE.concat("/count"))
                .header(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test06GetMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get(URL_RESOURCE.concat("/").concat(usuario.getId().toString()))
                .header(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void test07DeleteMethod() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(URL_RESOURCE.concat("/").concat(usuario.getId().toString()))
                .header(JwtTokenUtil.HTTP_HEADER_AUTHORIZATION, token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
