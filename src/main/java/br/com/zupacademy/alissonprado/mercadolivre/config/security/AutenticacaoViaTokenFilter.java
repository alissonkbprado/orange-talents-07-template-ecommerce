package br.com.zupacademy.alissonprado.mercadolivre.config.security;

import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import br.com.zupacademy.alissonprado.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private GeraTokenService geraTokenService;
    private UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(GeraTokenService geraTokenService, UsuarioRepository usuarioRepository) {
        this.geraTokenService = geraTokenService;
        this.usuarioRepository = usuarioRepository;
    }

    //Implementar a lógica de capturar o token e autenticar no usuário do Spring
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        boolean valido = geraTokenService.isTokenValido(token);

        //Se o token estiver válido, autenticar o usuário
        if (valido) {
            autenticarCliente(token);
        }

        //Encaminha o fluxo se estiver OK
        filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
        //Recupera id do Usuario do token
        Long idUsuario = geraTokenService.getIdUsuario(token);

        //Busca dados do banco
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        //Informar ao Spring que o usuário está autenticado (já autenticou anteriormente ao enviar o token correto)
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token ==null || token.isEmpty() || !token.startsWith("Bearer") ){
            return null;
        }

        //Retira "Bearer "
        return token.substring(7, token.length());
    }
}

