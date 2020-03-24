package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.repository.game.GameSessionRepository;
import com.itmo.usakova.repository.game.PlayerRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService extends AbstractService<Player, PlayerRepository> {

    @Autowired
    public PlayerService(PlayerRepository repository) {
        super(repository);
    }
}
