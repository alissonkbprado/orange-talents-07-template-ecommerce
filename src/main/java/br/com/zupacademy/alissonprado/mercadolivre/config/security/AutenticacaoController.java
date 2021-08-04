package br.com.zupacademy.alissonprado.mercadolivre.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private GeraTokenService geraTokenService;

    //Método para implementar a lógica de autenticação
    @PostMapping
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();

        try {
            //O Spring sabe chamar a nossa classe de autenticação(AutenticacaoService) e validar login e senha.
            Authentication authentication = authManager.authenticate(dadosLogin);

            //Gerar o token para retornar ao cliente
            String token = geraTokenService.gerarToken(authentication);

            //Retornar o Token por meio de um DTO + o tipo de autenticação "Bearer"
            return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}
