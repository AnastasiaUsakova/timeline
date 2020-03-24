package com.itmo.usakova.entity.game;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.itmo.usakova.configuration.jackson.JsonLocalDateTimeDeserializer;
import com.itmo.usakova.entity.IEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "game_session")
public class GameSession implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "qnt_player", nullable = false)
    private Long playerCount;

    @Column(name = "start_time")
    private LocalDateTime start;

    @Column(name = "ent_time")
    private LocalDateTime end;
}
