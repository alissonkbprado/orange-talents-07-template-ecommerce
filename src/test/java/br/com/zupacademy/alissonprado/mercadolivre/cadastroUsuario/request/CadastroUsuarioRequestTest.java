package br.com.zupacademy.alissonprado.mercadolivre.cadastroUsuario.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CadastroUsuarioRequestTest {

    @Test
    public void deveRetornarUsuarioAoChamarMetodoToModel(){
        CadastroUsuarioRequest cadastroUsuarioRequest = new CadastroUsuarioRequest("luke@jedi.com", "yoda1234");

        Usuario usuario = cadastroUsuarioRequest.toModel();

        assertEquals("luke@jedi.com", usuario.getEmail());
    }
}