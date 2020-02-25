package com.itmo.usakova.repository;

import com.itmo.usakova.entity.card.IEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IBaseRepository<ENTITY extends IEntity> extends JpaRepository<ENTITY, Long> {
}
