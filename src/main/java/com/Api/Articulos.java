package com.Api;
import com.Data.Articulo;
import com.Error.ExcepcionValidacion;
import com.Service.StandardResponse;
import com.Service.StatusResponse;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import io.netty.handler.codec.http.HttpResponseStatus;
import spark.ModelAndView;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static  spark.Spark.*;
public class Articulos {

    public  static void main(String args[]){
       final com.Service.Articulos articiuloService = new com.Service.Articulos();
        port(8080);


        //Get by id
        get("/articulo/:id", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String data  = new Gson().toJson(GetArticulo(request.params("id")));
            model.put("data", data);
            return new ModelAndView(model, "vista.vm"); // located in the resources directory
        }, new VelocityTemplateEngine());


        //Get all
        get("/articulo", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String data  = new Gson().toJson(GetArticulo(""));
            model.put("data", data);
            return new ModelAndView(model, "vista.vm"); // located in the resources directory
        }, new VelocityTemplateEngine());


        get("/test",(request,response)->{
            response.type("application/json");
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS,"")
            );

        });
        get("/articulos",(request,response)->{
            response.type("application/json");
            ArrayList<Articulo> resp = articiuloService.get(null);
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(resp))
            );

        });
        get("/articulos/:id",(request,response)->{
            response.type("application/json");
            Articulo articulo =  request.params("id") != null ? new Articulo(request.params("id")):null;
            ArrayList<Articulo> resp = articiuloService.get(articulo);
            return new Gson().toJson(
                    new StandardResponse(StatusResponse.SUCCESS, new Gson().toJsonTree(resp))
            );


        });
        post("/articulos",(request,response)->{
            try {
                response.type("application/json");
                Articulo articulo = new Gson().fromJson(request.body(),Articulo.class);
                articiuloService.add(articulo);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, ""));
            }catch (ExcepcionValidacion ev){
                return procesarValidacion(ev,response);
            }catch (Exception e){
                return procesarError(e, response);
            }

        });
        put("/articulos/:id",(request,response)->{

            try {
                response.type("application/json");
                Articulo articulo = new Gson().fromJson(request.body(),Articulo.class);
                articulo.setId(request.params("id"));
                articiuloService.update(articulo);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS, ""));
            }catch (ExcepcionValidacion ev){
                return procesarValidacion(ev,response);
            }catch (Exception e){
                return procesarError(e,response);
            }
        });
        delete("/articulos/:id",(request,response)->{

            try {
                response.type("application/json");
                Articulo articulo = new Gson().fromJson(request.body(),Articulo.class);
                articulo.setId(request.params("id"));
                articiuloService.delete(articulo);
                return new Gson().toJson(new StandardResponse(StatusResponse.SUCCESS,""));
            }catch (ExcepcionValidacion ev){
                return procesarValidacion(ev,response);
            }
            catch (Exception e){
                return procesarError(e,response);
            }
        });
    }






    public static Articulo[] GetArticulo(String id) throws Exception{

        OkHttpClient client = new OkHttpClient();
        String url = "http://localhost:8080/articulos";
        if(id != "")
            url = url + "/" + id;




        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            com.squareup.okhttp.Response response = client.newCall(request).execute();
            String result = response.body().string();
            StandardResponse sresp = new Gson().fromJson(result, StandardResponse.class);
            Articulo[] a = new Gson().fromJson(sresp.getData(), Articulo[].class);

            return a;

        }catch(Exception e){throw e;}

    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new VelocityTemplateEngine().render(new ModelAndView(model, templatePath));
    }


    public  static String procesarValidacion(Exception e, Response response){
        e.printStackTrace();
        String Mensaje="";
        response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
        return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, new Gson().toJsonTree(e.getMessage() )));
    }
    public  static String procesarError(Exception e, Response response){
        e.printStackTrace();
        String Mensaje="";
        response.status(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
        return new Gson().toJson(new StandardResponse(StatusResponse.ERROR, "Se produjo un error" ));
    }
}
