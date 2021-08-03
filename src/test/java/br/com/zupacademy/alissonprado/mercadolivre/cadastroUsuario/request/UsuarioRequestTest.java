package br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioRequestTest {

    @Test
    public void deveRetornarUsuarioAoChamarMetodoToModel(){
        UsuarioRequest usuarioRequest = new UsuarioRequest("luke@jedi.com", "yoda");

        Usuario usuario = usuarioRequest.toModel();

        assertEquals("luke@jedi.com", usuario.getEmail());
    }
}