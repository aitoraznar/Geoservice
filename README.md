# Geoservice

#### Requisitos
* **MySQL >= 5.5**
* Base de datos llamada **geonames**, el script para crear está incluido en el archivo **geonames.sql**
* URL's de prueba:
    * http://localhost:8080/geoservice/locate
    * http://localhost:8080/geoservice/locate?name=Gaizka&zip=28027

#### Especificaciones
Implementar un servlet que reciba peticiones GET con los siguientes parámetros:  
  * Nombre de usuario
  * Código Postal  

A partir del CP, y utilizando el servicio de geonames, obtenemos la ciudad del usuario (http://www.geonames.org).  

Almacenamos la información en bbdd, en dos tablas. En la tabla master guardamos el nombre del usuario. En la tabla detalle guardamos su cp y su ciudad.  

El servidor responde en formato JSON, indicando si todo ha ido bien o si ha habido algún tipo de error.  
