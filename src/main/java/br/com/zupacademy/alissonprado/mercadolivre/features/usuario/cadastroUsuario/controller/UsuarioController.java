package br.com.zupacademy.alissonprado.mercadolivre.features.usuario.cadastroUsuario.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.usuario.cadastroUsuario.request.CadastroUsuarioRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest){
        Usuario usuario = cadastroUsuarioRequest.toModel();

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuario cadastrado com sucesso");
    }
}
