/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste.util;

import br.com.gilmariosoftware.security.CurrentUser;
import br.com.gilmariosoftware.usuario.Usuario;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author gilmario
 */
public class LoginBuilder {

    public String login(MockMvc mvc, JsonConverter converter) throws Exception {
        CurrentUser currentUser = converter.fromJson(mvc.perform(MockMvcRequestBuilders.post("/api/auth")
                .contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"admin\",\"password\":\"123456\"}"))
                .andReturn().getResponse().getContentAsString(), CurrentUser.class);
        return currentUser.getToken();
    }

}
