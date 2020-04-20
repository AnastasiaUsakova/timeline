package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GameSessionRepository extends IBaseRepository<GameSession> {

    @Transactional
    @Procedure(procedureName = "createSession")
    int createSession();
}
