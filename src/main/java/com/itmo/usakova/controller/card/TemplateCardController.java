package com.itmo.usakova.controller.card;

import com.itmo.usakova.controller.AbstractController;
import com.itmo.usakova.entity.card.TemplateCard;
import com.itmo.usakova.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TemplateCardController extends AbstractController<TemplateCard> {

    @Autowired
    public TemplateCardController(IBaseService<TemplateCard> service) {
        super(service);
    }
}
