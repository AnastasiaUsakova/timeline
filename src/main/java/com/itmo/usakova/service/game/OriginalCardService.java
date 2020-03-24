package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.OriginalCard;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.game.OriginalCardRepository;
import com.itmo.usakova.repository.game.PlayerCardRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OriginalCardService extends AbstractService<OriginalCard, OriginalCardRepository> {

    @Autowired
    public OriginalCardService(OriginalCardRepository repository) {
        super(repository);
    }
}
