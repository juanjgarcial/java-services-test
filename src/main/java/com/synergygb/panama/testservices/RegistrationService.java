package com.synergygb.panama.testservices;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
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
@Path("register")
public class RegistrationService {

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerOnline(String params) {

        RegisterInfo input;
        RegisterResponse registerResponse = new RegisterResponse();
        WebServiceStatus status;

        try {
            input = (RegisterInfo) JSONUtility.objectFromJsom(params, RegisterInfo.class);
        } catch (Exception e) {
            status = new WebServiceStatus(WebServiceStatus.getFAIL_CODE(), WebServiceStatus.getFAIL_STATUS(), "Ocurrió un error interpretando los "
                    + "parámetros de entrada", e.getMessage());
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }

        if (input.cookie == null || input.clientInfo == null || input.carInfo == null) {
            status = new WebServiceStatus(WebServiceStatus.getFAIL_CODE(), WebServiceStatus.getFAIL_STATUS(), "Ocurrió un error interpretando los "
                    + "parámetros de entrada. Falta un parámetro o el tipo no es el esperado", "INVALID_PARAMETERS");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }

        if (!input.cookie.equalsIgnoreCase("7ifWg7Gk0DhHPHYiKVE9RA6WWpr6m8Cb")
                && !input.cookie.equalsIgnoreCase("T264mFvo2v1BG0Yv07YtL5azf9f8T88K")
                && !input.cookie.equalsIgnoreCase("PcC0UV60608GJerSq973Gh9mAG1FMNyk")) {
            status = new WebServiceStatus(WebServiceStatus.getOK_CODE(), WebServiceStatus.getFAIL_STATUS(), "Su sesión ha vencido o es inválida. "
                    + "Por favor vuelva a iniciar sesión en la aplicación", "INVALID_SESSION");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }

        if (input.clientInfo.name == null
                || input.clientInfo.address == null
                || input.clientInfo.city == null
                || input.clientInfo.birthday == null
                || input.clientInfo.id == null
                || input.clientInfo.phoneNumber == null
                || input.clientInfo.sex == null
                || input.carInfo.affectedPiece == null
                || input.carInfo.brand == null
                || input.carInfo.model == null
                || input.carInfo.plate == null
                || input.carInfo.year == null) {

            status = new WebServiceStatus(WebServiceStatus.getFAIL_CODE(), WebServiceStatus.getFAIL_STATUS(), "Ocurrió un error interpretando los "
                    + "parámetros de entrada. Falta un parámetro o el tipo no es el esperado", "INVALID_PARAMETERS");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            dateFormat.parse(input.clientInfo.birthday);
        } catch (Exception e) {
            status = new WebServiceStatus(WebServiceStatus.getFAIL_CODE(), WebServiceStatus.getFAIL_STATUS(), "El formato de fecha no es "
                    + "válido", "INVALID_DATA");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }

        Random random = new Random();
        int selection = random.nextInt(10);

        if (selection % 4 != 0) {
            registerResponse.registrationNumber = String.valueOf(random.nextInt(100000000));
            SimpleDateFormat dateFormatComplete = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            registerResponse.dateCreated = dateFormatComplete.format(date);
            registerResponse.status = WebServiceStatus.okResponse();
        } else {
            status = new WebServiceStatus(WebServiceStatus.getOK_CODE(), WebServiceStatus.getFAIL_STATUS(), "Error al insertar el registro, por favor "
                    + "intente de nuevo", "REGISTRATION_ERROR");
            return Response.ok(JSONUtility.jsonFromObject(status), MediaType.APPLICATION_JSON).build();
        }

        String jsonBody = JSONUtility.jsonFromObject(registerResponse);

        ResponseBuilder responseBuilder = Response.ok(jsonBody, MediaType.APPLICATION_JSON);
        Response response = responseBuilder.build();

        return response;
    }

}

class RegisterInfo {

    public String cookie;
    public ClientInfo clientInfo;
    public CarInfo carInfo;
}

class ClientInfo {

    public String name;
    public boolean isForeigner;
    public String id;
    public String birthday;
    public String city;
    public String address;
    public String phoneNumber;
    public String sex;
}

class CarInfo {

    public String year;
    public String brand;
    public String model;
    public String plate;
    public String affectedPiece;
}

class RegisterResponse {

    public String registrationNumber;
    public String dateCreated;
    public WebServiceStatus status;
}
