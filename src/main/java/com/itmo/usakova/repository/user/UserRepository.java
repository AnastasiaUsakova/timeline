package com.itmo.usakova.repository.user;

import com.itmo.usakova.entity.player.User;
import com.itmo.usakova.repository.IBaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends IBaseRepository<User> {
}
