package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameSessionRepository extends IBaseRepository<GameSession> {
}
