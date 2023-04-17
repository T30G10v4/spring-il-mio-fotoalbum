package me.matteogiovagnotti.springilmiofotoalbum.repositories;

import me.matteogiovagnotti.springilmiofotoalbum.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
