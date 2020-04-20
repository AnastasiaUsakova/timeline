package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerCardRepository extends IBaseRepository<PlayerCard> {
    List<PlayerCard> findByPlayer(Long id);
    @Transactional
    @Procedure(procedureName = "checkIfRightPlace")
    int joinGame(@Param("player_card") int player_card, @Param("position") int position);
}
