package br.com.zupacademy.alissonprado.mercadolivre.features.usuario.cadastroUsuario.controller;

import br.com.zupacademy.alissonprado.mercadolivre.features.usuario.cadastroUsuario.request.CadastroUsuarioRequest;
import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

     private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroUsuarioRequest cadastroUsuarioRequest){



        Usuario usuario = cadastroUsuarioRequest.toModel();

        logger.info("Inicio de cadastro do usuário: {}", usuario.getUsername().substring(0,5));
        logger.warn("Algo está com problemas.");

        usuarioRepository.save(usuario);

        logger.debug("Usuário cadastrado: {}", usuario.getUsername().substring(0,5));
        logger.trace("Cadastrado com sucesso: {}", usuario.getEmail().substring(0,5));
        logger.error("Algo deu muito errado.");

        return ResponseEntity.ok("Usuario cadastrado com sucesso");
    }
}
