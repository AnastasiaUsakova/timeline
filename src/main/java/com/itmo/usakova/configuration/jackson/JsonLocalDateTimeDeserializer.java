package com.itmo.usakova.configuration.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.itmo.usakova.util.exception.Const;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JsonLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Const.LOCAL_DATE_TIME_FORMAT);

    @Override
    public LocalDateTime deserialize(JsonParser jsonParse,
                                 DeserializationContext ctxt) throws IOException {
        final ObjectCodec codec = jsonParse.getCodec();
        final TextNode node = codec.readTree(jsonParse);
        final String text = node.textValue();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return LocalDateTime.parse(text, formatter);
    }
}
