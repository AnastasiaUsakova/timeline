package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PlayerRepository extends IBaseRepository<Player> {
    @Transactional
    @Procedure(procedureName = "joinGame")
    void joinGame(@Param("username") char username, @Param("id_game_session") int id_session);
}
