package com.itmo.usakova.service.card;

import com.itmo.usakova.entity.card.TemplateCard;
import com.itmo.usakova.repository.card.TemplateCartRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemplateCartService extends AbstractService<TemplateCard, TemplateCartRepository> {

    @Autowired
    public TemplateCartService(TemplateCartRepository repository) {
        super(repository);
    }
}
