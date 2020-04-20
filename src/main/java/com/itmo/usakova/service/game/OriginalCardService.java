package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.OriginalCard;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.repository.game.OriginalCardRepository;
import com.itmo.usakova.repository.game.PlayerCardRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OriginalCardService extends AbstractService<OriginalCard, OriginalCardRepository> {

    @Autowired
    public OriginalCardService(OriginalCardRepository origCard) {
       OriginalCardRepository id_player = origCard;
    }

    public List<OriginalCard> getBySessionId(Long sessionId) {
        return origCard.findAll().getCard()
                .filter(c -> Optional.ofNullable(c.getSession())
                            .map(GameSession::getId)
                            .map(id -> sessionId.equals(id))
                            .orElse(false)
                )
                .collect(Collectors.toList());
    }

 /*   @Autowired
    public OriginalCardService(OriginalCardRepository repository) {
        super(repository);
    }

    public List<OriginalCard> getBySessionId(Long sessionId) {
        return repository.findAll().stream()
                .filter(c -> Optional.ofNullable(c.getSession())
                            .map(GameSession::getId)
                            .map(id -> sessionId.equals(id))
                            .orElse(false)
                )
                .collect(Collectors.toList());
    }

*/
    /**
     * Забираем карту из колоды
     */
    public OriginalCard getFromDeck(Long sessionId) {
        return Optional.ofNullable(getBySessionId(sessionId))
                .flatMap(list -> list.stream().filter(c -> c.isAtDeck()).findFirst())
                .map(c -> {
                    c.setAtDeck(false);
                    return update(c);
                })
                .orElse(null);
    }
}
