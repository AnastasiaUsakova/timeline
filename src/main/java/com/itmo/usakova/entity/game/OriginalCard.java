package com.itmo.usakova.entity.game;

import com.itmo.usakova.entity.IEntity;
import com.itmo.usakova.entity.card.TemplateCard;
import com.itmo.usakova.entity.player.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity(name = "original_card")
public class OriginalCard implements IEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "at_deck")
    private boolean atDeck;

    @ManyToOne
    @JoinColumn(name = "template_card_id")
    private TemplateCard card;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private GameSession session;
}
