package com.itmo.usakova.configuration.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.itmo.usakova.util.exception.Const;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Const.LOCAL_DATE_TIME_FORMAT);

    @Override
    public void serialize(LocalDateTime value,
                          JsonGenerator gen,
                          SerializerProvider serializers) throws IOException {
        if (value == null) {
            return;
        }
        String formattedDate = value.format(formatter);
        gen.writeString(formattedDate);
    }
}
