package com.br.stockcontrol.stockcontrol;

import com.br.stockcontrol.stockcontrol.Domain.Produto;
import com.br.stockcontrol.stockcontrol.Service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    public void testListarTodosProdutos() throws Exception {
        Produto produto = new Produto("1", "Produto Teste", 10L, LocalDate.now(), LocalDate.now().plusMonths(1), 50.0);
        when(produtoService.listarTodosProdutos()).thenReturn(Collections.singletonList(produto));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nome").value("Produto Teste"));
    }
}

