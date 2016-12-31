package jtk.springboot.mysql.sakila.entities.repo;

import jtk.springboot.mysql.sakila.entities.Actor;
import jtk.springboot.mysql.sakila.entities.Films;
import jtk.springboot.mysql.sakila.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by jubin on 25/9/16.
 */

@RepositoryRestResource(collectionResourceRel = "allLanguages", path = "languages")
public interface LanguageRepository extends JpaRepository<Language,Integer> {


}
