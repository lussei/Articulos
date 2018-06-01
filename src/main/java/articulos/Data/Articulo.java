package articulos.Data;

import java.util.Date;

public class Articulo {

    public  Articulo(){

    }
    public  Articulo(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId() {

    }

    public void setId(String id) {
        this.id = id;
    }

    private String id;
    private String site_id;
    private String title;
    private String subtitle;
    private int seller_id;
    private String category_id;
    private String official_store_id;
    private int price;
    private int base_price;
    private int original_price;
    private String currency_id;
    private int initial_quantity;
    private int available_quantity;
    private int sold_quantity;
    private String buying_mode;
    private String listing_type_id;
    private Date start_time;
    private Date stop_time;
    private Date end_time;
    private String condition;
    private String permalink;
    private String thumbnail;
    private String secure_thumbnail;
    private String video_id;
    private boolean accepts_mercadopago;
    private String international_delivery_mode;
    private String listing_source;
    private boolean automatic_relist;
    private Date date_created;
    private Date last_updated;
}
