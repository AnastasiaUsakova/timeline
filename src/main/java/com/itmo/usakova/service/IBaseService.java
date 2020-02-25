package com.itmo.usakova.service;

import com.itmo.usakova.entity.card.IEntity;
import com.itmo.usakova.util.exception.NotFoundException;

import java.util.List;

public interface IBaseService<ENTITY extends IEntity> {

    List<ENTITY> getAll();
    ENTITY get(Long id);
    ENTITY create(ENTITY entity);
    ENTITY update(ENTITY entity);
    void delete(Long id) throws NotFoundException;
}
