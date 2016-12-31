package jtk.springboot.mysql.sakila.entities.repo;

import jtk.springboot.mysql.sakila.entities.Actor;
import jtk.springboot.mysql.sakila.entities.Films;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by jubin on 25/9/16.
 */

@RepositoryRestResource(collectionResourceRel = "allActors", path = "actors")
public interface ActorRepository extends JpaRepository<Actor,Integer> {

    @RestResource(path = "nameStartsWith", rel = "firstNameStartsWith")
    public List<Actor> findByFirstNameStartsWith(@Param("firstName") String firstName);

    public List<Films> findFilmsByFirstName(@Param("firstName") String firstName);

    public List<Actor> findActorsByFilmsTitle(@Param("title") String title);

    @Query("select f from Films f where f.length < 47")
    public List<Films> getShortMovies();

}
