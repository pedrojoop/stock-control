package com.br.stockcontrol.stockcontrol.Controller;

import com.br.stockcontrol.stockcontrol.Domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody Usuario usuario) {
        // Verifica se o objeto Usuario é nulo
        if (usuario != null) {
            // Verifica se o email e senha correspondem aos do usuário administrativo predefinido
            if ("admin@crsalimentos.com.br".equals(usuario.getEmail()) && "Admin".equals(usuario.getSenha())) {
                // Retorna uma resposta de sucesso (200 OK) se for o usuário administrativo
                return new ResponseEntity<>("Login bem-sucedido!", HttpStatus.OK);
            } else {
                // Caso contrário, retorna uma resposta de erro (401 Unauthorized)
                return new ResponseEntity<>("Email ou senha inválidos", HttpStatus.UNAUTHORIZED);
            }
        } else {
            // Se o objeto Usuario for nulo, retorna uma resposta de erro (400 Bad Request)
            return new ResponseEntity<>("Dados de login ausentes", HttpStatus.BAD_REQUEST);
        }
    }
}
