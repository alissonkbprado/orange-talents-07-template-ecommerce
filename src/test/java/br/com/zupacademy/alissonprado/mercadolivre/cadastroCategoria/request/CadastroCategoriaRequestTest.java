package br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CadastroCategoriaRequestTest {

    @Autowired
    CadastroCategoriaRequest cadastroCategoriaRequest;

    @Test
    public void deveRetornarCategoriaMaeNulo(){
        CadastroCategoriaRequest cadastroCategoriaRequest = new CadastroCategoriaRequest("Tecnologia", null);

        Categoria categoria = cadastroCategoriaRequest.toModel();

        assertTrue(categoria.getCategoriaMaeOptional().isEmpty());
    }

    @Test
    public void naoDeveRetornarCategoriaMaeNulo(){
        CadastroCategoriaRequest cadastroCategoriaRequest = new CadastroCategoriaRequest("Tecnologia", 1l);

        Categoria categoria = cadastroCategoriaRequest.toModel();

        assertNotEquals(null, categoria.getCategoriaMaeOptional().get());
    }

    @Test
    @DisplayName("deveria cadastrar categoria sem mae")
    void teste1() throws Exception {
        CadastroCategoriaRequest request = new CadastroCategoriaRequest("nome", null);

        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.verify(manager, Mockito.never())
                .find(Mockito.eq(Categoria.class), Mockito.anyLong());
    }

}