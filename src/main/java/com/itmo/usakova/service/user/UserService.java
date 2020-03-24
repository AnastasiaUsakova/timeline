package com.itmo.usakova.service.user;

import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.repository.user.UserRepository;
import com.itmo.usakova.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }
}
