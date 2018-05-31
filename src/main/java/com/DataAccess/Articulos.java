package com.DataAccess;

import com.Data.Articulo;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import com.google.gson.Gson;



import java.net.InetAddress;
import java.util.ArrayList;


public class Articulos {

    private TransportClient client;

    public Articulos() throws  Exception{
        try {
            PreBuiltTransportClient tc = new PreBuiltTransportClient(Settings.EMPTY);
            client = tc.addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));

        }
        catch(Exception e){
            e.printStackTrace();
            throw e;
        }

    }
    public void add(Articulo articulo){

        //BulkRequestBuilder br = client.prepareBulk();
        client.prepareIndex("articulos", "articulos", articulo.getId()).setSource(new Gson().toJson(articulo), XContentType.JSON).get();
    }
    public void update(Articulo articulo){}
    public void delete(Articulo articulo){
        client.prepareDelete("articulos", "articulos", articulo.getId()).get();
    }



    public ArrayList<Articulo> get(Articulo articulo){
        ArrayList<Articulo> response = new ArrayList<>();
        SearchRequestBuilder qb =  client.prepareSearch("articulos");
        qb.setSize(1000);
        if(articulo != null) qb = qb.setQuery(QueryBuilders.matchQuery("id", articulo.getId()));
        SearchResponse resp =  qb.get();
        try {
            if(resp != null) {
                SearchHits hits = resp.getHits();
                for (int i = 0; i <= hits.totalHits; i++)
                    response.add(new Gson().fromJson(hits.getAt(i).getSourceAsString(), Articulo.class));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;


    }
}
