package me.matteogiovagnotti.springilmiofotoalbum.repositories;

import java.util.Optional;

import me.matteogiovagnotti.springilmiofotoalbum.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    public Optional<User> findByEmail(String email);
}
