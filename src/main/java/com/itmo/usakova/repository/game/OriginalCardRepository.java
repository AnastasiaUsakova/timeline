package com.itmo.usakova.repository.game;

import com.itmo.usakova.entity.game.OriginalCard;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OriginalCardRepository extends IBaseRepository<OriginalCard> {
}
