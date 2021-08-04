package br.com.zupacademy.alissonprado.mercadolivre.cadastroCategoria.request;

import br.com.zupacademy.alissonprado.mercadolivre.model.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaRequestTest {

    @Autowired
    CategoriaRequest categoriaRequest;

    @Test
    public void deveRetornarCategoriaMaeNulo(){
        CategoriaRequest categoriaRequest = new CategoriaRequest("Tecnologia", null);

        Categoria categoria = categoriaRequest.toModel();

        assertEquals(null, categoria.getCategoriaMae());
    }

    @Test
    public void naoDeveRetornarCategoriaMaeNulo(){
        CategoriaRequest categoriaRequest = new CategoriaRequest("Tecnologia", 1l);

        Categoria categoria = categoriaRequest.toModel();

        assertNotEquals(null, categoria.getCategoriaMae());
    }


}