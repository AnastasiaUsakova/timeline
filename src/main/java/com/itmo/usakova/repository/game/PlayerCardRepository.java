package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerCardRepository extends IBaseRepository<PlayerCard> {
}
