package com.itmo.usakova.entity.card;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public interface IEntity extends Serializable {
    Long getId();

    void setId(final Long id);

    @JsonIgnore
    default boolean isNew() {
        return getId() == null;
    }
}
