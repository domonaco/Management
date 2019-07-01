package it.monaco.medical.service.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/monitor")
public class QuattroRuoteMonitorController extends LegacyBaseController {

    public static Logger logger = LoggerFactory.getLogger(QuattroRuoteMonitorController.class);

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping()
    {
        logger.trace(apiURL()+"/ping");
        return "Quattroruote microservice up and running!";
    }

    @Override
    public String apiURL() {
        return "/monitor";
    }
}
