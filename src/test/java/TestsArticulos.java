

import com.Articulos.Data.Articulo;
import com.Articulos.Error.ExcepcionValidacion;
import com.Articulos.Service.Articulos;
import com.google.gson.Gson;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.text.SimpleDateFormat;
import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestsArticulos {
    private final String baseURI = "http://localhost:8080";
    private final Articulo a = getTestArticle();

    @Test
    public void test05_Put200(){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Articulo a2 = getTestArticle();
        httpRequest.body(new Gson().toJson(a2)).post("/articulos");
        try {
            Thread.sleep(2000);
        }catch (Exception e){}
        Response response2 = httpRequest.body(new Gson().toJson(a2)).put("/articulos/" + a2.getId());
        int statusCode = response2.getStatusCode();
        httpRequest.body(new Gson().toJson(a2)).delete("/articulos/" + a.getId());
        Assert.assertEquals("200",String.valueOf(statusCode));
    }
    @Test
    public void test06_Put500(){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Articulo a2 = getTestArticle();
        Response response2 = httpRequest.body(new Gson().toJson(a2)).put("/articulos/" + a2.getId());
        int statusCode = response2.getStatusCode();
        Assert.assertEquals("500", String.valueOf(statusCode));
    }
    @Test
    public void test07_Delete200(){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Articulo a2 = getTestArticle();
        httpRequest.body(new Gson().toJson(a2)).post("/articulos");
        try {
            Thread.sleep(1000);
        }catch (Exception e){}

        Response response2 = httpRequest.body(new Gson().toJson(a2)).delete("/articulos/" + a2.getId());
        int statusCode = response2.getStatusCode();
        Assert.assertEquals("200",String.valueOf(statusCode));
    }
    @Test
    public void test08_Delete500(){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Articulo a2 = getTestArticle();
        Response response2 = httpRequest.body(new Gson().toJson(a2)).delete("/articulos/" + a2.getId());
        int statusCode = response2.getStatusCode();
        Assert.assertEquals("500", String.valueOf(statusCode));
    }

    @Test
    public void test01_Post200(){//Run First this
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.body(new Gson().toJson(a)).post("/articulos");
        int statusCode = response.getStatusCode();
        Assert.assertEquals("200", String.valueOf(statusCode));
    }

    @Test
    public void test02_Get200(){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/articulos");
        int statusCode = response.getStatusCode();
        Assert.assertEquals("200", String.valueOf(statusCode));
    }
    @Test
    public void test03_GetId200(){
        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/articulos/sarasa");
        int statusCode = response.getStatusCode();
        Assert.assertEquals("200", String.valueOf(statusCode));
    }

    @Test
    public void test04_Post500(){

        RestAssured.baseURI = baseURI;
        RequestSpecification httpRequest = RestAssured.given();
        Articulo a2 = getTestArticle();
        httpRequest.body(new Gson().toJson(a2)).post("/articulos");
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        Response response2 = httpRequest.body(new Gson().toJson(a2)).post("/articulos");
        httpRequest.body(new Gson().toJson(a2)).delete("/articulos/" + a2.getId());
        int statusCode = response2.getStatusCode();
        Assert.assertEquals("500", String.valueOf(statusCode));
    }









    private static Articulo getTestArticle(){
        String id = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        return new Articulo(id);
    }

}

