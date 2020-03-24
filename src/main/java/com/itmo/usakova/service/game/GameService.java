package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.IEntity;
import com.itmo.usakova.entity.card.TemplateCard;
import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.OriginalCard;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.service.card.TemplateCartService;
import com.itmo.usakova.service.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@Service
public class GameService {

    private final UserService userService;
    private final PlayerService playerService;
    private final GameSessionService sessionService;
    private final TemplateCartService templateCartService;
    private final OriginalCardService originalCardService;
    private final PlayerCardService playerCardService;

    public GameService(UserService userService, PlayerService playerService, GameSessionService sessionService, TemplateCartService templateCartService, OriginalCardService originalCardService, PlayerCardService playerCardService) {
        this.userService = userService;
        this.playerService = playerService;
        this.sessionService = sessionService;
        this.templateCartService = templateCartService;
        this.originalCardService = originalCardService;
        this.playerCardService = playerCardService;
    }

    /**
     * Стартуем игровую сессию
     */
    public Player start(final String userName) {
        //создаем игрока
        GameSession session = sessionService.create(new GameSession(null, 1L, LocalDateTime.now(), null));
        return Optional.of(new User(null, userName))
                .map(userService::create)
                .map(u -> new Player(null, 1L, u, session))
                .map(playerService::create)
                .map(this::calculateDeck)
                .orElse(null);
    }

    /**
     * Берем карту в руку
     */
    public OriginalCard getFromDeck(Long playerId) {
        return Optional.ofNullable(playerId)
                .map(playerService::get)
                .map(p -> Optional.ofNullable(p.getSession())
                            .map(GameSession::getId)
                            .map(originalCardService::getFromDeck)
                            .map(c -> addCard(playerId, c))
                            .orElse(null)
                )
                .orElse(null);
    }

    public List<OriginalCard> getHand(Long playerId) {
        return playerCardService.findByPlayer(playerId).stream()
                .map(PlayerCard::getCard)
                .map(originalCardService::get)
                .collect(toList());
    }

    //=======================================================================================================================

    /**
     * Замешиваем колоду
     */
    private Player calculateDeck(Player player) {
        tempData(); //todo убрать как в бд загружу вс
        List<OriginalCard> all = templateCartService.getAll().stream()
                .map(c -> new OriginalCard(null, true, c, player.getSession()))
                .collect(toList());
        shuffle(all);
        all.forEach(originalCardService::create);
        return player;
    }

    private void shuffle(List<? extends IEntity> list) {
        int seed = 12;
        Collections.shuffle(list, new Random(seed));
    }

    private OriginalCard addCard(Long playerId, OriginalCard card) {
        playerCardService.create(new PlayerCard(null, playerId, card.getId()));
        return card;
    }

    private void tempData() {
        int startDate = 2000;
        for (int i = 0; i < 20; i++) {
            TemplateCard card = new TemplateCard(null, String.valueOf(++startDate), "Description " + startDate);
            templateCartService.create(card);
        }
    }
}
