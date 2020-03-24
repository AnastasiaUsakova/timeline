package com.itmo.usakova.controller.game;

import com.itmo.usakova.controller.AbstractController;
import com.itmo.usakova.entity.game.OriginalCard;
import com.itmo.usakova.entity.game.PlayerCard;
import com.itmo.usakova.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/originalcard")
public class OriginalCardController extends AbstractController<OriginalCard> {

    @Autowired
    public OriginalCardController(IBaseService<OriginalCard> service) {
        super(service);
    }
}
