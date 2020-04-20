package com.itmo.usakova.service;

import com.itmo.usakova.entity.IEntity;
import com.itmo.usakova.repository.IBaseRepository;
import com.itmo.usakova.util.exception.NotFoundException;
import org.springframework.util.Assert;

import java.util.List;

public class AbstractService<ENTITY extends IEntity, REPOSITORY extends IBaseRepository<ENTITY>>
        implements IBaseService<ENTITY> {

    protected final REPOSITORY repository;

    public AbstractService(REPOSITORY repository) {
        this.repository = repository;
    }

    public AbstractService() {
    }

    @Override
    public List<ENTITY> getAll() {
        return repository.findAll();
    }

    @Override
    public ENTITY get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public ENTITY create(ENTITY entity) {
        return repository.save(checkNew(entity));
    }

    @Override
    public ENTITY update(ENTITY entity) {
        return repository.save(checkExist(entity));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        repository.deleteById(checkExist(id));
    }

    protected ENTITY checkNew(final ENTITY entity) throws IllegalArgumentException {
        Assert.notNull(entity, "Entity must not be null");
        if (!entity.isNew()) {
            throw new IllegalArgumentException("Entity must be new (id=null)");
        }
        return entity;
    }

    protected ENTITY checkExist(ENTITY entity) {
        Assert.notNull(entity, "Entity must not be null");
        checkExist(entity.getId());
        return entity;
    }

    protected Long checkExist(Long id) {
        Assert.notNull(id, "id must not be null");
        if (!repository.existsById(id)) {
            throw new NotFoundException(id);
        }
        return id;
    }
}
