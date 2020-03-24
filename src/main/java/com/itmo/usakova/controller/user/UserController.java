package com.itmo.usakova.controller.user;

import com.itmo.usakova.controller.AbstractController;
import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends AbstractController<User> {

    @Autowired
    public UserController(IBaseService<User> service) {
        super(service);
    }
}
