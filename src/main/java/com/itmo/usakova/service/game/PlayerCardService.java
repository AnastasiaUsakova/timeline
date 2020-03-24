package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.game.PlayerCardRepository;
import com.itmo.usakova.repository.game.PlayerRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerCardService extends AbstractService<PlayerCard, PlayerCardRepository> {

    @Autowired
    public PlayerCardService(PlayerCardRepository repository) {
        super(repository);
    }
}
