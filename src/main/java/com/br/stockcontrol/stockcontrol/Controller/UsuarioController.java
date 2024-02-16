package com.br.stockcontrol.stockcontrol.Controller;

import com.br.stockcontrol.stockcontrol.Domain.Usuario;
import com.br.stockcontrol.stockcontrol.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar-admin")
    public ResponseEntity<?> criarAdmin() {
        Usuario usuarioExistente = usuarioService.encontrarUsuarioPorEmail("admin@crsalimentos.com.br");
        if (usuarioExistente != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O usuário administrativo já existe");
        }

        usuarioService.criarUsuarioUnico("admin@crsalimentos.com.br", "senha");

        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário administrativo criado com sucesso");
    }
}
