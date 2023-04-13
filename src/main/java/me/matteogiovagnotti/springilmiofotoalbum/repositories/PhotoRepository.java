package me.matteogiovagnotti.springilmiofotoalbum.repositories;

import me.matteogiovagnotti.springilmiofotoalbum.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    public List<Photo> findByTitleContainingIgnoreCase(String title);
}
