package jtk.springboot.mysql.sakila.entities.repo;

import jtk.springboot.mysql.sakila.entities.Films;
import jtk.springboot.mysql.sakila.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by jubin on 31/12/16.
 */
@RepositoryRestResource(collectionResourceRel = "allFilms", path = "films")
public interface FilmsRepository extends JpaRepository<Films,Integer> {
}
