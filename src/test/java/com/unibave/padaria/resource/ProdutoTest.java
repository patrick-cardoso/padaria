package com.unibave.padaria.resource;



import com.unibave.padaria.model.Produto;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment
        = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest {

    @Inject
    private TestRestTemplate restTemplate;


    private static Produto produtoTemp;


    private static final String BASE_URI = "/api/produtos";

    @Test
    public void _01_adicionaProduto(){
        Produto produto = new Produto();

        produto.setDescricao("Descrição Teste");

        ResponseEntity<Produto> response = restTemplate
                .postForEntity(BASE_URI, produto, Produto.class);


        produtoTemp = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(produtoTemp.getDescricao()).isEqualTo(produto.getDescricao());
    }

    @Test
    public void _02_atualizaProduto(){

        produtoTemp.setDescricao("Descrição Teste atualizado");

        ResponseEntity<Produto> response = restTemplate
                .exchange(BASE_URI+"/"+produtoTemp.getId(),
                        HttpMethod.PUT,
                        new HttpEntity<>(produtoTemp),
                        Produto.class);

        //System.out.print(response.getBody());
       Produto produto = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(produtoTemp.getDescricao()).isEqualTo(produto.getDescricao());

    }

    @Test
    public void _03_buscaProduto(){

        ResponseEntity<Produto> response = restTemplate
                .exchange(BASE_URI+"/"+produtoTemp.getId(),
                        HttpMethod.GET,
                        null,
                        Produto.class);

        Produto produto = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(produtoTemp.getDescricao()).isEqualTo(produto.getDescricao());

    }

    @Test
    public void _04_buscaProdutos(){

        ResponseEntity<PageDTO<Produto>> response = restTemplate
                .exchange(BASE_URI,
                        HttpMethod.GET, null, getPageTypeReference());
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        PageDTO<Produto> produtos = response.getBody();
        assertThat(produtos.getTotalElements()).isEqualTo(1);
        assertThat(produtos.getContent().size()).isEqualTo(1);

    }


    @Test
    public void _05_deletaProduto(){

        ResponseEntity<Produto> response = restTemplate
                .exchange(BASE_URI+"/"+produtoTemp.getId(),
                        HttpMethod.DELETE,
                        null,
                        Produto.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }



    private ParameterizedTypeReference<PageDTO<Produto>>
    getPageTypeReference() {
        return new ParameterizedTypeReference<PageDTO<Produto>>() {
        };
    }
}
