package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.repository.game.GameSessionRepository;
import com.itmo.usakova.repository.user.UserRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameSessionService extends AbstractService<GameSession, GameSessionRepository> {

    @Autowired
    public GameSessionService(GameSessionRepository repository) {
        super(repository);
    }
}
