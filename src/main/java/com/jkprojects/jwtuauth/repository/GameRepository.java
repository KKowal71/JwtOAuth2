package com.jkprojects.jwtuauth.repository;

import com.jkprojects.jwtuauth.model.Game;
import org.springframework.data.repository.CrudRepository;
public interface GameRepository extends CrudRepository<Game, Integer> {
}
