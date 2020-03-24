package com.itmo.usakova.controller;

import com.itmo.usakova.entity.card.TemplateCard;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.service.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
public class RootController {

    private final GameService service;

    @Autowired
    public RootController(GameService service) {
        this.service = service;
    }

    @GetMapping(value = "/start/{username}")
    public Player start(@PathVariable(name = "username") final String username) {
        return service.start(username);
    }

    @GetMapping(value = "/deck")
    public TemplateCard getNextCardFromDeck() {
        return new TemplateCard(2L, "AAD", "asd");
    }
}
