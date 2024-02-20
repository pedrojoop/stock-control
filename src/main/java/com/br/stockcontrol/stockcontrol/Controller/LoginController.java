package com.br.stockcontrol.stockcontrol.Controller;

import com.br.stockcontrol.stockcontrol.Domain.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class LoginController {

    private static final String USUARIO_PADRAO_EMAIL = "admin@crsalimentos.com.br";
    private static final String USUARIO_PADRAO_SENHA = "Admin";

    @PostMapping("/login")
    public ResponseEntity<String> fazerLogin(@RequestBody Usuario usuario, HttpServletRequest request) {
        if (usuario != null) {
            if (USUARIO_PADRAO_EMAIL.equals(usuario.getEmail()) && USUARIO_PADRAO_SENHA.equals(usuario.getSenha())) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", usuario.getEmail());
                return new ResponseEntity<>("Login bem-sucedido!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Email ou senha inválidos", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("Dados de login ausentes", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> fazerLogout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>("Logout realizado com sucesso!", HttpStatus.OK);
    }

    @GetMapping("/check-login")
    public ResponseEntity<String> verificarLogin(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            return new ResponseEntity<>("Usuário autenticado", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Usuário não autenticado", HttpStatus.UNAUTHORIZED);
        }
    }
}


