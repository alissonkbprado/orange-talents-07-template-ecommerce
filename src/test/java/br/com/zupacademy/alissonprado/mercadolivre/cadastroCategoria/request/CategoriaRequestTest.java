package br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaRequestTest {

    @Autowired
    CategoriaRequest categoriaRequest;

    @Test
    public void deveRetornarCategoriaMaeNulo(){
        CategoriaRequest categoriaRequest = new CategoriaRequest("Tecnologia", null);

        Categoria categoria = categoriaRequest.toModel();

        assertEquals(null, categoria.getCategoriaMae().get());
    }

    @Test
    public void naoDeveRetornarCategoriaMaeNulo(){
        CategoriaRequest categoriaRequest = new CategoriaRequest("Tecnologia", 1l);

        Categoria categoria = categoriaRequest.toModel();

        assertNotEquals(null, categoria.getCategoriaMae().get());
    }

    @Test
    @DisplayName("deveria cadastrar categoria sem mae")
    void teste1() throws Exception {
        CategoriaRequest request = new CategoriaRequest("nome", null);

        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.verify(manager, Mockito.never())
                .find(Mockito.eq(Categoria.class), Mockito.anyLong());
    }

}