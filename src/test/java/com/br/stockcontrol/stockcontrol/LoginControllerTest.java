package com.br.stockcontrol.stockcontrol;

import com.br.stockcontrol.stockcontrol.Domain.Usuario;
import com.br.stockcontrol.stockcontrol.Repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testFazerLoginSucesso() throws Exception {
        Usuario admin = new Usuario("admin@crsalimentos.com.br", "Admin");
        when(usuarioRepository.findByEmailAndSenha("admin@crsalimentos.com.br", "Admin")).thenReturn(admin);

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"admin@crsalimentos.com.br\", \"senha\": \"Admin\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Login bem-sucedido!"));
    }

}
