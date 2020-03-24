package com.itmo.usakova.controller.game;

import com.itmo.usakova.controller.AbstractController;
import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
public class GameSessionController extends AbstractController<GameSession> {

    @Autowired
    public GameSessionController(IBaseService<GameSession> service) {
        super(service);
    }
}
