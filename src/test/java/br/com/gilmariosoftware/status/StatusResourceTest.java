package br.com.gilmariosoftware.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 *
 * @author gilmario
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StatusResourceTest {

    @Autowired
    private MockMvc mvc;

    public StatusResourceTest() {
    }

    @Test
    public void testIndex() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/status").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("{\"versao\":\"0.1.0-SNAPSHOT\",\"nome\":\"Generic-API\",\"dataRelease\":[2018,9,16]}")));

    }

}
