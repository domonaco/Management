package it.monaco.medical.service.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import it.monaco.medical.service.controllers.dtos.ErrorDetails;
import it.monaco.medical.service.model.enums.EnumChannel;
import it.monaco.medical.service.model.enums.ErrorType;
import it.monaco.medical.service.model.exceptions.LegacyException;
import it.monaco.medical.service.utils.LegacyLogUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


public abstract  class LegacyBaseController {

    private static final Logger logger = LoggerFactory.getLogger(LegacyBaseController.class);

    @Context HttpServletRequest request;

    public abstract String apiURL();

    protected void setParamsForLog(String sessionId, EnumChannel channel, String host)  throws LegacyException{
        try {

            if(StringUtils.isBlank(sessionId)) {
                throw  new LegacyException("SessionId missing!", ErrorType.VALIDATION);
            }

            if(channel == null)
            {
                throw  new LegacyException("Channel missing!", ErrorType.VALIDATION);
            }

            if(StringUtils.isBlank(host))
            {
                throw new LegacyException("Host missing !", ErrorType.VALIDATION);
            }

            LegacyLogUtils.setParamsForLog(sessionId, channel, host);

        } catch (Exception e) {
            logger.error("Failed to set mandatory params for log", e);
        }
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
                ex.getBindingResult().toString());
        return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
