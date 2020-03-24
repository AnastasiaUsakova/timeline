package com.itmo.usakova.service.game;

import com.itmo.usakova.entity.game.GameSession;
import com.itmo.usakova.entity.game.Player;
import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.service.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class GameService {

    private final UserService userService;
    private final PlayerService playerService;
    private final GameSessionService sessionService;

    public GameService(UserService userService, PlayerService playerService, GameSessionService sessionService) {
        this.userService = userService;
        this.playerService = playerService;
        this.sessionService = sessionService;
    }

    public Player start(final String userName) {
        //создаем игрока
        GameSession session = sessionService.create(new GameSession(null, 1L, LocalDateTime.now(), null));

        return Optional.of(new User(null, userName))
                .map(userService::create)
                .map(u -> new Player(null, 1L, u, session))
                .map(playerService::create)
                .orElse(null);
    }
}
