package com.synergygb.panama.testservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author Juan Garcia <juan.garcia@synergy-gb.com>
 */
@Path("list")
public class GenericListService {
    
    @Path("/cities")
    @GET
    @Produces("application/json")
    public Response getCities() {
        
        String jsonBody = "[\"Panamá\",\"Chiriquí\",\"Colón\",\"Panamá Pacífico\",\"Chorrera\",\"Tocumen\",\"Arraijan\",\"San Miguelito\","
                + "\"Pacora\",\"Albrook\",\"Costa del Este\",\"Pueblo nuevo\", \"El Cangrejo\", \"Paitilla\", \"La Cresta\", \"San Francisco\", \"Otra\"]";
        
        ResponseBuilder responseBuilder = Response.ok(jsonBody, MediaType.APPLICATION_JSON);
        Response response = responseBuilder.build();
        
        return response;
    }
    
    @Path("/car-parts")
    @GET
    @Produces("application/json")
    public Response getCarParts() {
        
        String jsonBody = "[\"Defensa\",\"Tapa de Motor\",\"Parrilla\",\"Chasis\"]";
        
        ResponseBuilder responseBuilder = Response.ok(jsonBody, MediaType.APPLICATION_JSON);
        Response response = responseBuilder.build();
        
        return response;
    }
}
