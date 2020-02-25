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
import java.time.format.DateTimeFormatter;

public class JsonLocalDateDeserializer extends JsonDeserializer<LocalDate> {

    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Const.LOCAL_DATE_FORMAT);

    @Override
    public LocalDate deserialize(JsonParser jsonParse,
                                 DeserializationContext ctxt) throws IOException {
        final ObjectCodec codec = jsonParse.getCodec();
        final TextNode node = codec.readTree(jsonParse);
        final String text = node.textValue();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return LocalDate.parse(text, formatter);
    }
}
