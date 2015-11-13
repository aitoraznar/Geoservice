
package com.geonames.controllers;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.geonames.controllers.handlers.GeoControllerHandler;

@Path("/locate")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class GeoController {
    
    @GET
    public String get(@QueryParam("name") String name, @QueryParam("zip") String zip) {
    	return GeoControllerHandler.handleUserLocate(name, zip);
    }
}
