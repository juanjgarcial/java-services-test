package com.synergygb.panama.testservices;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 * @author Juan Garcia <juan.garcia@synergy-gb.com>
 */
@Path("login")
public class LoginService {
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(String params) {
        LoginInfo input;
        LoginResponse loginResponse = new LoginResponse();
        WebServiceStatus status;
       
        try {
            input = (LoginInfo) JSONUtility.objectFromJsom(params, LoginInfo.class);
        } catch (Exception e) {
            status = new WebServiceStatus(WebServiceStatus.getFAIL_CODE(), WebServiceStatus.getFAIL_STATUS(), "Ocurrió un error interpretando los "
                    + "parámetros de entrada"  , e.getMessage());
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }
        
        if (input.username == null || input.password == null || !isAlpha(input.username)) {
            status = new WebServiceStatus(WebServiceStatus.getFAIL_CODE(), WebServiceStatus.getFAIL_STATUS(), "Ocurrió un error interpretando los "
                    + "parámetros de entrada. Falta un parámetro o el tipo no es el esperado"  , "INVALID_PARAMETERS");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }
        
        if (input.username.equalsIgnoreCase("juanG")) {
            if (input.password.equalsIgnoreCase("sgb.juan")) {
                loginResponse.cookie = "7ifWg7Gk0DhHPHYiKVE9RA6WWpr6m8Cb";
                loginResponse.status = WebServiceStatus.okResponse();
            } else {
                status = new WebServiceStatus(WebServiceStatus.getOK_CODE(), WebServiceStatus.getFAIL_STATUS(), "Contraseña inválida"  , "INVALID_CREDENTIALS");
                return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
            }
        } else if (input.username.equalsIgnoreCase("jessicaM")) {
            if (input.password.equalsIgnoreCase("sgb*jessica")) {
                loginResponse.cookie = "T264mFvo2v1BG0Yv07YtL5azf9f8T88K";
                loginResponse.status = WebServiceStatus.okResponse();
            } else {
                status = new WebServiceStatus(WebServiceStatus.getOK_CODE(), WebServiceStatus.getFAIL_STATUS(), "Contraseña inválida"  , "INVALID_CREDENTIALS");
                return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
            }
        } else if (input.username.equalsIgnoreCase("admin")) {
            if (input.password.equalsIgnoreCase("sgb*panama*2015")) {
                loginResponse.cookie = "PcC0UV60608GJerSq973Gh9mAG1FMNyk";
                loginResponse.status = WebServiceStatus.okResponse();
            } else {
                status = new WebServiceStatus(WebServiceStatus.getOK_CODE(), WebServiceStatus.getFAIL_STATUS(), "Contraseña inválida"  , "INVALID_CREDENTIALS");
                return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
            }
        } else {
            status = new WebServiceStatus(WebServiceStatus.getOK_CODE(), WebServiceStatus.getFAIL_STATUS(), "Este usuario no existe"  , "INVALID_USER");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }
        
        String jsonBody = JSONUtility.jsonFromObject(loginResponse);
        
        ResponseBuilder responseBuilder = Response.ok(jsonBody, MediaType.APPLICATION_JSON);
        Response response = responseBuilder.build();
        
        return response;
    }
    
    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
}

class LoginInfo {
    public String username;
    public String password;
}

class LoginResponse {
    public String cookie;
    public WebServiceStatus status;
}