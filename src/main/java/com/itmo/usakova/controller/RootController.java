package com.itmo.usakova.controller;

import com.itmo.usakova.entity.card.TemplateCard;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
public class RootController {

    @GetMapping(value = "/deck")
    public TemplateCard getNextCardFromDeck() {
        return new TemplateCard(2L, "AAD", "asd");
    }
}
