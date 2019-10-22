package ru.smartel.renderferma.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.smartel.renderferma.server.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
