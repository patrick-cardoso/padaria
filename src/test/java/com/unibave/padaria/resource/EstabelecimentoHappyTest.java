package com.unibave.padaria.resource;


import com.unibave.padaria.model.Estabelecimento;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
    public void _04_deletaEstabelecimento(){

        ResponseEntity<Estabelecimento> response = restTemplate
                .exchange(BASE_URI+"/"+estabelecimentoTemp.getId(),
                        HttpMethod.DELETE,
                        null,
                        Estabelecimento.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

    }
}
