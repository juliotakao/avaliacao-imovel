package br.com.imoveis;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class ImoveisApiApplicationTests {
	private String url = "http://localhost:8080/api/v1/imoveis/";

    @Test
    public void retornarStatus200_PesquisaPaginada(){
        given().
                param("pageNumber", "1").
                param("pageSizer", "10").
        when().
                get(url).
        then().
                statusCode(200);
    }
    
    @Test
    public void retornarStatus404_PaginaNaoExiste(){
        given().
                param("pageNumber", "20").
                param("pageSizer", "10").
        when().
                get(url).
        then().
                statusCode(404);
    }
    
    @Test
    public void retornarStatus200_PesquisaIdExistente(){
        given().
        when().
                get(url + "1").
        then().
                statusCode(200);
    }
    
    @Test
    public void retornarStatus404_RegistroPesquisaNaoExiste(){
        given().
        when().
                get(url + "999").
        then().
                statusCode(404);
    }
    
    @Test
    public void retornarStatus201_CriandoImovel(){
    	
    	Map<String, String> map = new HashMap<String, String>();

        map.put("descricao", "CASA TESTE");
        map.put("logradouro", "RUA TESTE");
        map.put("numero", "1234");
        map.put("bairro", "JARDIM DOS TESTES");
        map.put("cidade", "CIDADE TESTE");
        map.put("uf", "AM");
        map.put("dormitorio", "20");
        map.put("suite", "3");
        map.put("area", "3000");
        map.put("valor", "139.58");

        given().
        		header("Content-Type","application/json" ).        		
                body(new JSONObject(map).toJSONString()).
        when().
                post(url).
        then().
                statusCode(201);
    }
    
    
    @Test
    public void retornarStatus200_AtualizaImovel(){
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("id", "10");
        map.put("descricao", "CASA ATUALIZADA");
        map.put("logradouro", "RUA TESTE");
        map.put("numero", "1234");
        map.put("bairro", "JARDIM DOS TESTES");
        map.put("cidade", "CIDADE TESTE");
        map.put("uf", "AM");
        map.put("dormitorio", "20");
        map.put("suite", "3");
        map.put("area", "3000");
        map.put("valor", "139.58");

        given().
        		header("Content-Type","application/json" ).        		
        		body(new JSONObject(map).toJSONString()).
        when().
                put(url + "10").
        then().
                statusCode(200);
    }
    
}