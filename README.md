# Articulos
Api Restful para el manejo de articulos implementado en Apache Spark con persistencia en Elasticsearch. 
![](https://github.com/lussei/Articulos/blob/master/app.png)



## Como llamar a la API:<br>

### Operaciones:
#### POST (Agrega un nuevo articulo)
```bash
          curl -X POST 
            http://localhost:8080/articulos 
            -d '{
            "id": "MLA608007087",
            "site_id": "MLA",
            "title": "Item De Testeo, Por Favor No Ofertar --kc:off",
            "seller_id": 202593498,
            "category_id": "MLA3530",
            "price": 10,
            "base_price": 10,
            "original_price": 0,
            "currency_id": "ARS",
            "initial_quantity": 1,
            "available_quantity": 1,
            "sold_quantity": 0,
            "buying_mode": "buy_it_now",
            "listing_type_id": "gold_special",
            "start_time": "Feb 26, 2016 2:26:07 PM",
            "stop_time": "Feb 21, 2036 2:26:07 PM",
            "end_time": "Feb 21, 2036 2:26:07 PM",
            "condition": "new",
            "permalink": "http://articulo.mercadolibre.com.ar/MLA-608007087-item-de-testeo-por-favor-no-ofertar-kcoff-_JM",
            "thumbnail": "http://mla-s1-p.mlstatic.com/614711-MLA20604586883_022016-I.jpg",
            "secure_thumbnail": "https://a248.e.akamai.net/mla-s1-p.mlstatic.com/614711-MLA20604586883_022016-I.jpg",
            "accepts_mercadopago": true,
            "international_delivery_mode": "none",
            "listing_source": "",
            "automatic_relist": false,
            "date_created": "Feb 26, 2016 2:26:07 PM",
            "last_updated": "Feb 26, 2016 2:26:09 PM"
        }'
```
Retorna:
Exito
```json
	{
		"status": "SUCCESS",
		"message": ""
	}
```
Error
```json
	{
		"status": "ERROR",
		"data": "El articulo ya existe."
	}
```


#### GET sin paramertros (retorna todos los registros)
```bash
curl -X GET  http://localhost:8080/articulos 

```
Retorna: 
```json
{
    "status": "SUCCESS",
    "data": [
        {
            "id": "20180531141120987",
            "seller_id": 0,
            "price": 0,
            "base_price": 0,
            "original_price": 0,
            "initial_quantity": 0,
            "available_quantity": 0,
            "sold_quantity": 0,
            "accepts_mercadopago": false,
            "automatic_relist": false
        },
		...
	]
}
```

#### GET con paramertros (retrorna el articulo con el id pasado por parametro)

```bash
curl -X GET  http://localhost:8080/articulos/MLA608007087
```
Retrorna: 
```json
{
    "status": "SUCCESS",
    "data": [
        {
            "id": "20180531141120987",
            "seller_id": 0,
            "price": 0,
            "base_price": 0,
            "original_price": 0,
            "initial_quantity": 0,
            "available_quantity": 0,
            "sold_quantity": 0,
            "accepts_mercadopago": false,
            "automatic_relist": false
        }
    ]
}
```

#### PUT (Actualiza el contenido del articulo indicado en el parametro)
```bash
curl -X PUT
  http://localhost:8080/articulos/MLA608007087
  -d '        {
            "id": "MLA608007087",
            "site_id": "MLA",
            "title": "Item De Testeo, Por Favor No Ofertar --kc:off",
            "seller_id": 202593498,
            "category_id": "MLA3530",
            "price": 10,
            "base_price": 10,
            "original_price": 0,
            "currency_id": "ARS",
            "initial_quantity": 1,
            "available_quantity": 1,
            "sold_quantity": 0,
            "buying_mode": "buy_it_now",
            "listing_type_id": "gold_special",
            "start_time": "Feb 26, 2016 2:26:07 PM",
            "stop_time": "Feb 21, 2036 2:26:07 PM",
            "end_time": "Feb 21, 2036 2:26:07 PM",
            "condition": "new",
            "permalink": "http://articulo.mercadolibre.com.ar/MLA-608007087-item-de-testeo-por-favor-no-ofertar-kcoff-_JM",
            "thumbnail": "http://mla-s1-p.mlstatic.com/614711-MLA20604586883_022016-I.jpg",
            "secure_thumbnail": "https://a248.e.akamai.net/mla-s1-p.mlstatic.com/614711-MLA20604586883_022016-I.jpg",
            "accepts_mercadopago": true,
            "international_delivery_mode": "none",
            "listing_source": "",
            "automatic_relist": false,
            "date_created": "Feb 26, 2016 2:26:07 PM",
            "last_updated": "Feb 26, 2016 2:26:09 PM"
        }'        
```
Retorna:

Exito:
```json
	{
		"status": "SUCCESS",
		"message": ""
	}
```
Error
```json
	{
		"status": "ERROR",
		"data": "El articulo no existe."
	}
```
#### DELETE (Elimina el contenido del articulo indicado en el parametro)
```bash
curl -X DELETE 
  http://localhost:8080/articulos/MLA608007084
```
Retorna:
	Exito
```json
	{
		"status": "SUCCESS",
		"message": ""
	}
```
Error
```json
{
		"status": "ERROR",
		"data": "El articulo no existe."
	}
```

### Como ver articulos en el browser: 

#### http://localhost:8080/articulos (muestra todos los articulos)
![](https://github.com/lussei/Articulos/blob/master/consulta1.png)

#### http://localhost:8080/articulos/[articuloID] (muestra el articulo por ID)
![](https://github.com/lussei/Articulos/blob/master/consulta2.png)

