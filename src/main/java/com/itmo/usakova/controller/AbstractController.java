package com.itmo.usakova.controller;

import com.itmo.usakova.entity.IEntity;
import com.itmo.usakova.service.IBaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public class AbstractController<ENTITY extends IEntity> {

    private final IBaseService<ENTITY> service;

    public AbstractController(IBaseService<ENTITY> service) {
        this.service = service;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ENTITY> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ENTITY get(@PathVariable("id") Long id) {
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ENTITY create(@Valid @RequestBody ENTITY entity) {
        return service.create(entity);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ENTITY update(@PathVariable("id") Long id, @Valid @RequestBody ENTITY entity) {
        return service.update(assureIdConsistent(entity, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    protected static <T extends IEntity> T assureIdConsistent(T bean, long id) {
        if (bean.isNew()) {
            bean.setId(id);
        } else if (bean.getId() != id) {
            throw new IllegalArgumentException("Entity must be with id=" + id);
        }
        return bean;
    }
}
