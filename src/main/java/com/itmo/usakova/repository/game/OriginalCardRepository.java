package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.OriginalCard;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OriginalCardRepository extends IBaseRepository<OriginalCard> {
    @Transactional
    @Procedure(procedureName = "getCard")
    void getCard(@Param("id_player") int id_player);

    @Procedure(procedureName = "cardToTable")
    int cardToTable(@Param("id_original_card") int id_original_card,
                    @Param("position") int position);
}
