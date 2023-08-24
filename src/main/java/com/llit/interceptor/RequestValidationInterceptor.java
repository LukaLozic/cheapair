package com.llit.interceptor;
import com.llit.dto.FlightSearchRequestBody;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static com.llit.common.Constants.EUR;
import static com.llit.common.Constants.USD;

public class RequestValidationInterceptor implements HandlerInterceptor {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return paramsCheck(parseRequestBody(request));
    }

    private FlightSearchRequestBody parseRequestBody(HttpServletRequest request) {
        try {
            return objectMapper.readValue(request.getInputStream(), FlightSearchRequestBody.class);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing request body");
        }
    }

    private Boolean paramsCheck(FlightSearchRequestBody requestBody) throws Exception {
/*
        StringBuilder errorMessage = new StringBuilder();
        if(requestBody == null) {
            fillErrorMessage(errorMessage, "Request body is null.");
        }
        if(requestBody.getDepartureDate().isEmpty()) {
            fillErrorMessage(errorMessage, "Return date is null or empty.");
        }
        if(requestBody.getOriginLocationCode().isEmpty()) {
            fillErrorMessage(errorMessage, "Origin location code is null or empty.");
        }
        if(requestBody.getDestinationLocationCode().isEmpty()) {
            fillErrorMessage(errorMessage, "Destination location code is null or empty.");
        }
        if(requestBody.getNumberOfPassengers() == null || requestBody.getNumberOfPassengers().equals(0)) {
            fillErrorMessage(errorMessage, "Number of passenger is null or empty.");
        }
        if(requestBody.getNumberOfPassengers() > 10) {
            fillErrorMessage(errorMessage, "Max Number of passenger is 10.");
        }
        if(requestBody.getCurrency().isEmpty()) {
            fillErrorMessage(errorMessage, "Currency is null or empty.");
        }
        if(!requestBody.getCurrency().equals(EUR)) {
            if(!requestBody.getCurrency().equals(USD)) {
                fillErrorMessage(errorMessage, "Currency must be " + "" + EUR + " or " + USD);
            }
        }
        if(requestBody.getMax() == null || requestBody.getMax().equals(0)) {
            fillErrorMessage(errorMessage, "Max flights number is set to null or 0.");
        }
        if(requestBody.getMax() > 99) {
            fillErrorMessage(errorMessage, "Max flights number is 99.");
        }
        if(errorMessage.length() > 0){
            throw new ValidationException(errorMessage.toString());
        }*/
        return true;
    }

    private void fillErrorMessage(StringBuilder errorMessage, String message){
        if(errorMessage.length() > 0){
            errorMessage.append("\n" + ";");
        }
        errorMessage.append(message);
    }
}