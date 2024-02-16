package com.br.stockcontrol.stockcontrol.Service;

import com.br.stockcontrol.stockcontrol.Domain.Usuario;
import com.br.stockcontrol.stockcontrol.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario encontrarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email); // Implementação do método para encontrar usuário por email
    }

    public Usuario encontrarUsuarioPorEmailESenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    public Usuario criarUsuarioUnico(String email, String senha) {
        Usuario usuarioExistente = encontrarUsuarioPorEmail(email);
        if (usuarioExistente != null) {
            return null;
        }

        // Crie um novo usuário e salve-o
        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        return salvarUsuario(novoUsuario);
    }
}
