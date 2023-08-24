package com.llit.exception;

import com.llit.service.implementation.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private EmailServiceImpl emailServiceImpl;
    @ExceptionHandler(FlightsServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleFlightsServiceException(FlightsServiceException ex, WebRequest webRequest) {
        StringBuilder emailMessage = new StringBuilder();
        emailMessage.append(ex.getMessage());
        //log.error(ex.getMessage());
        emailServiceImpl.sendMailWithLogFile("Exception occurred.", emailMessage.toString());
        addAttributes(ex, webRequest);
        return "searchFlights";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleGeneralException(Exception ex, WebRequest webRequest) {
        addAttributes(ex, webRequest);
        return "searchFlights";
    }

    private void addAttributes(Exception ex, WebRequest webRequest){
        Object attribute = webRequest
                .getAttribute(Model.class.getName(), RequestAttributes.SCOPE_REQUEST);
        if(attribute != null){
            ((Model) attribute).addAttribute("exception", ex);
        }
    }
}
