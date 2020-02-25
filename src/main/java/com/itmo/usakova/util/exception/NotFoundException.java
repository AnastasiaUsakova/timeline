package com.itmo.usakova.util.exception;

import static java.lang.String.format;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Long id) {
        super(format("Entity with id [%d] not found", id));
    }
}
