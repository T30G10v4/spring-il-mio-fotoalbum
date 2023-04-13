package me.matteogiovagnotti.springilmiofotoalbum.repositories;

import me.matteogiovagnotti.springilmiofotoalbum.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
