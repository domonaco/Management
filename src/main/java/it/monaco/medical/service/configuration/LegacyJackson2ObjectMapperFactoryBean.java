package it.monaco.medical.service.configuration;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;

public class LegacyJackson2ObjectMapperFactoryBean extends Jackson2ObjectMapperFactoryBean {

    public LegacyJackson2ObjectMapperFactoryBean() {
        super();
        setFeaturesToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        setSerializationInclusion(JsonInclude.Include.NON_ABSENT);

    }
}