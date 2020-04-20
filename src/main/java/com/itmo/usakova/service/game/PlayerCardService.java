package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.IBaseRepository;
import com.itmo.usakova.repository.game.PlayerCardRepository;
import com.itmo.usakova.repository.game.PlayerRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerCardService extends AbstractService<PlayerCard, PlayerCardRepository> {

    @Autowired
    public PlayerCardService(PlayerCardRepository repository) {
        super(repository);
    }

    public List<PlayerCard> findByPlayer(Long player) {
        return repository.findByPlayer(player);
    }
}
