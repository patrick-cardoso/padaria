package com.unibave.padaria.resource;


import com.unibave.padaria.model.Estabelecimento;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment
        = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EstabelecimentoHappyTest {

    @Inject
    private TestRestTemplate restTemplate;

    private static Estabelecimento estabelecimentoTemp;

    private static final String BASE_URI = "/api/estabelecimentos";

    @Test
    public void _01_adicionaEstabelecimento(){

        Estabelecimento estabelecimento = new Estabelecimento();

        estabelecimento.setNome("Teste Automatizado");
        estabelecimento.setEndereco("Rua Teste Automatizado");

        ResponseEntity<Estabelecimento> response = restTemplate
                .postForEntity(BASE_URI, estabelecimento, Estabelecimento.class);

        estabelecimentoTemp = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(estabelecimentoTemp.getNome()).isEqualTo(estabelecimento.getNome());
        assertThat(estabelecimentoTemp.getEndereco()).isEqualTo(estabelecimento.getEndereco());

    }

    @Test
    public void _02_atualizaEstabelecimento(){


        estabelecimentoTemp.setEndereco("Rua Teste Automatizado Atualizado");
        ResponseEntity<Estabelecimento> response = restTemplate
                .exchange(BASE_URI+"/"+estabelecimentoTemp.getId(),
                        HttpMethod.PUT,
                        new HttpEntity<>(estabelecimentoTemp),
                        Estabelecimento.class);

        Estabelecimento estabelecimento = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(estabelecimentoTemp.getNome()).isEqualTo(estabelecimento.getNome());
        assertThat(estabelecimentoTemp.getEndereco()).isEqualTo(estabelecimento.getEndereco());
    }

    @Test
    public void _03_buscaEstabelecimento(){

        ResponseEntity<Estabelecimento> response = restTemplate
                .exchange(BASE_URI+"/"+estabelecimentoTemp.getId(),
                        HttpMethod.GET,
                        null,
                        Estabelecimento.class);
        Estabelecimento estabelecimento = response.getBody();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(estabelecimentoTemp.getEndereco()).isEqualTo(estabelecimento.getEndereco());
        assertThat(estabelecimentoTemp.getNome()).isEqualTo(estabelecimento.getNome());

    }


    @Test
    public void _04_buscaEstabelecimentos(){

        ResponseEntity<PageDTO<Estabelecimento>> response = restTemplate
                .exchange(BASE_URI,
                        HttpMethod.GET, null, getPageTypeReference());
        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        PageDTO<Estabelecimento> estabelecimentos = response.getBody();
        assertThat(estabelecimentos.getTotalElements()).isEqualTo(1);
        assertThat(estabelecimentos.getContent().size()).isEqualTo(1);

    }

//    @Test
//    public void _05_adicionaProduto(){
//
//        Produto produto = new Produto();
//        produto.setDescricao("Descrição Teste");
//        ResponseEntity<Produto> prodAdd = restTemplate
//                .postForEntity(BASE_URI, produto, Produto.class);
//        Produto produtoTemp = prodAdd.getBody();
//
//
//
//        estabelecimentoTemp.addProduto(produtoTemp);
//        estabelecimentoTemp.setNome("Teste2");
//        System.out.print(estabelecimentoTemp);
//
//
//
//        ResponseEntity<Estabelecimento> estabTemp = restTemplate
//                .exchange(BASE_URI+"/"+estabelecimentoTemp.getId(),
//                        HttpMethod.PUT,
//                        new HttpEntity<>(estabelecimentoTemp),
//                        Estabelecimento.class);
//
//        assertThat(estabTemp.getStatusCode()).isEqualTo(HttpStatus.OK);
//
//        ResponseEntity<PageDTO<Produto>> response = restTemplate
//                .exchange(BASE_URI+"/"+estabelecimentoTemp.getId()+"/produtos",
//                        HttpMethod.GET,
//                        null,
//                        getPageTypeReferenceProd());
//
//
//        PageDTO<Produto> produtos = response.getBody();
//
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(estabelecimentoTemp.getProdutos()).isEqualTo(produtos);
//
//    }

    @Test
    public void _06_deletaEstabelecimento(){

        ResponseEntity<Estabelecimento> response = restTemplate
                .exchange(BASE_URI+"/"+estabelecimentoTemp.getId(),
                        HttpMethod.DELETE,
                        null,
                        Estabelecimento.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }


    private ParameterizedTypeReference<PageDTO<Estabelecimento>>
    getPageTypeReference() {
        return new ParameterizedTypeReference<PageDTO<Estabelecimento>>() {
        };
    }



    private ParameterizedTypeReference<PageDTO<Produto>>
    getPageTypeReferenceProd() {
        return new ParameterizedTypeReference<PageDTO<Produto>>() {
        };
    }
}
