package com.itmo.usakova.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.itmo.usakova.configuration.jackson.JsonLocalDateDeserializer;
import com.itmo.usakova.configuration.jackson.JsonLocalDateSerializer;
import com.itmo.usakova.configuration.jackson.JsonLocalDateTimeDeserializer;
import com.itmo.usakova.configuration.jackson.JsonLocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class CommonConfiguration {

    private final static String LOCAL_DATE_TIME_WITH_TIMEZONE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSXXX";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(LOCAL_DATE_TIME_WITH_TIMEZONE_FORMAT));
        objectMapper.getSerializationConfig().withFeatures(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //LodalDate
        SimpleModule localDateModule = new SimpleModule();
        localDateModule.addDeserializer(
                LocalDate.class,
                new JsonLocalDateDeserializer()
        );
        localDateModule.addSerializer(
                LocalDate.class,
                new JsonLocalDateSerializer()
        );
        objectMapper.registerModule(localDateModule);

        //LodalDateTi,e
        SimpleModule localDateTimeModule = new SimpleModule();
        localDateTimeModule.addDeserializer(
                LocalDateTime.class,
                new JsonLocalDateTimeDeserializer()
        );
        localDateTimeModule.addSerializer(
                LocalDateTime.class,
                new JsonLocalDateTimeSerializer()
        );
        objectMapper.registerModule(localDateTimeModule);

        return objectMapper;
    }
}
